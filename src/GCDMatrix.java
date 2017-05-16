import java.util.*;
import java.io.*;
public class GCDMatrix
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int N , M;
	
	private static int sumOfRegion(int prefixSum[][] , int x1,int y1,int x2,int y2)
	{
		int entire = prefixSum[x2][y2];
		int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
		int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
		int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;		
		return entire - N - W + NW;
	}
	static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		M = s1.nextInt();
		int Q = s1.nextInt();
		
		int A[] = s1.nextIntArray(N);
		int B[] = s1.nextIntArray(M);
		
		int gcd[][] = new int[N][M];
		for(int i = 0;i<N;i++)
			for(int j=0;j<M;j++)
				gcd[i][j] = gcd(A[i], B[j]);
		
		int MAX = (int) 1e5;
		
		ArrayList<int[][]> num = new ArrayList<>();
		int pos[] = new int[MAX + 1];
		Arrays.fill(pos, -1);
		for(int i=0;i<N;i++)
			for(int j=0;j<M;j++) {
				int arr[][];
				if(pos[gcd[i][j]] == -1) {
					pos[gcd[i][j]] = num.size();
					arr = new int[N][M];
					num.add(arr);
				}
				else
					arr = num.get(pos[gcd[i][j]]);
				arr[i][j]++;
			}
		
		for(int prefixSum[][] : num) {

			for(int i=0;i<N;i++)
				for(int j=1;j<M;j++)
					prefixSum[i][j] += prefixSum[i][j-1] ;

			for(int i=1;i<N;i++)
				for(int j=0;j<M;j++)
					prefixSum[i][j] += prefixSum[i-1][j];	
		}
		

		
		while(Q-->0) {
			int r1 = s1.nextInt();
			int c1 = s1.nextInt();
			int r2 = s1.nextInt();
			int c2 = s1.nextInt();
			int cnt = 0;
			for(int prefixSum[][] : num) 
				cnt += sumOfRegion(prefixSum, r1, c1, r2, c2) >= 1 ? 1 : 0;
			
			out.println(cnt);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}