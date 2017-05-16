import java.util.*;
import java.io.*;
class CHSQARR_sparse
{


	/************************ SOLUTION STARTS HERE ***********************/


	static class SparseTable2D  // <O (MNlog(M)log(N)) , O(1) > 
	{

		/*
		 * Dont use Math.log its (very * 2 ^ 64) slow for a lot of queries (10^6), 
		 * instead precompute the log values . 
		 * Costed me several days
		 * 
		 * In a sparse table you break the query rectangle into four smaller rects each with 
		 * dimensions log(height) X log(width)
		 * 
		 * So the query time is O(1) , but the build time is O(M * N * log(M) * log(N))
		 * 
		 * 	********|****|******
		 * 	********|****|******
		 * 	********|****|******
		 * 	--------|----|------
		 * 	******************
		 * 	******************
		 */

		int sparse[][][][];
		int log[];
		int R,C;

		int getMax(int x1,int y1,int x2,int y2, boolean build){
			int x_sz = x2 - x1 + 1;
			int y_sz = y2 - y1 + 1;
			int k1 = (x_sz == 1) ? 0 :log[build ? (x_sz - 1) : x_sz];
			int k2 = (y_sz == 1) ? 0 :log[build ? (y_sz - 1) : y_sz];
			int NW = sparse[k1][k2][x1][y1]; 					   //North-West
			int NE = sparse[k1][k2][x1][y_sz - (1 << k2) + y1];    		   //North-East
			int SW = sparse[k1][k2][x_sz - (1 << k1) + x1][y1]; 		   //South-West
			int SE = sparse[k1][k2][x_sz - (1 << k1) + x1][y_sz - (1 << k2) + y1]; //South-East
			return (int) Math.max(Math.max(NW,NE),Math.max(SW,SE));
		}

		SparseTable2D(int arr[][],int R,int C)
		{
			this.R = R;
			this.C = C;

			log = new int[Math.max(R,C) + 1000];
			for(int i=2;i<log.length;i++)		
				log[i] = ((i & (i-1)) == 0) ? log[i-1] + 1 : log[i-1];

			int k1 = log[R] + 1;
			int k2 = log[C] + 1;
			sparse = new int[k1][k2][R][C];
			for(int i=0;i<R;i++)
				for(int j=0;j<C;j++)
					sparse[0][0][i][j] = arr[i][j];

			for(int h=0;h<k1;h++)
			{
				for(int v=0;v<k2;v++)
				{
					if(!(h == 0 && v == 0))
					{
						for(int i=0;i+(1<<h) <= R;i++)
						{
							for(int j=0;j+(1<<v) <= C;j++)
							{
								sparse[h][v][i][j] = getMax(i, j, i + (1<<h) - 1, j + (1<<v) - 1, true);
							}
						}
					}
				}
			}

		}
	}


	static class SparseTable1D
	{
		int sparse[][];
		int len;
		
		static int log(int n){
			return 31 - Integer.numberOfLeadingZeros(n);
		}
		SparseTable1D(int arr[])
		{
			len = arr.length;

			int k = log(len) + 1;	    
			sparse = new int[len][k];
			for(int i=0;i<len;i++)
				sparse[i][0] = arr[i];

			for(int j=1;j<k;j++)
			{
				for(int i=0;i+(1<<j) <= len;i++)
				{
					sparse[i][j] = Math.max(sparse[i][j-1],sparse[i+(1<<(j-1))][j-1]);
				}
			}
		}

		int getMaxInRange(int L,int R)
		{
			int sz = R - L + 1;
			int k  = log(sz);
			int v1 = sparse[L][k];
			int v2 = sparse[L + (sz - (1 << k))][k];
			return Math.max(v1,v2);
		}

	}

	private static int sumOfRegion(int prefixSum[][],int x1,int y1,int x2,int y2)
	{
		int entire = prefixSum[x2][y2];
		int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
		int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
		int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;		
		return entire - N - W + NW;
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int R = s1.nextInt();
		int C = s1.nextInt();
		int arr[][] = new int[R][];
		for(int i=0;i<R;i++)
			arr[i] = s1.nextIntArray(C);

		int prefixSum[][] = new int[R][C];
		for(int i=0;i<R;i++)
			prefixSum[i][0] = arr[i][0];

		for(int i=0;i<R;i++)
			for(int j=1;j<C;j++)
				prefixSum[i][j] = prefixSum[i][j-1] + arr[i][j];

		for(int i=1;i<R;i++)
			for(int j=0;j<C;j++)
				prefixSum[i][j] += prefixSum[i-1][j];	

		SparseTable2D spTable = new SparseTable2D(arr, R, C);

		int Q = s1.nextInt();	
		while(Q-->0)
		{
			int a = s1.nextInt();
			int b = s1.nextInt();
			int min = Integer.MAX_VALUE;

			for(int i=0;i<=R-a;i++)
			{
				for(int j=0;j<=C-b;j++)
				{
					int sum = sumOfRegion(prefixSum, i, j, i + a - 1, j + b - 1);		    		    
					int max = spTable.getMax(i, j, i + a - 1, j + b - 1, false);
					int time = (max * a * b) - sum;
					min = Math.min(min,time);

				}
			}	    
			out.println(min);
		}
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    


	static String outputFile = "output_CHSQARR.txt";
	/*
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
	solve(in, out);
	in.close();
	out.close();
    }
	 */
	static class FastScanner{
		BufferedReader reader;
		StringTokenizer st;
		FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
		String next()
		{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
		st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
		String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
		int    nextInt()   {return Integer.parseInt(next());}
		long   nextLong()  {return Long.parseLong(next());}		
		double nextDouble(){return Double.parseDouble(next());}
		char   nextChar()  {return next().charAt(0);}
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}