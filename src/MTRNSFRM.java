import java.util.*;
import java.io.*;
class MTRNSFRM
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int N , M ;
	static long  A[][] , B[][] , C[][];
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			M = s1.nextInt();
			A = new long[N][];
			B = new long[N][];
			C = new long[N][M];
			for(int i=0;i<N;i++)
				A[i] = s1.nextLongArray(M);
			for(int i=0;i<N;i++)
				B[i] = s1.nextLongArray(M);
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					C[i][j] = A[i][j] - B[i][j];
			
			boolean flag = true;
			for(int i=0;i<N;i++)
				for(int j=0;j<M;j++)
					flag &= C[i][j] + C[0][0] - C[i][0] - C[0][j] == 0;
			
			if(!flag)
				out.println(-1);
			else {
				
				long pts[] = new long[N + M];
				for(int i=0;i<N;i++)
					pts[i] = C[i][0] - C[0][0];
				for(int i=0;i<M;i++)
					pts[N + i] = -C[0][i];
				
				Arrays.sort(pts);
				long x = pts[pts.length / 2];
				long minCost = 0;
				for(long pt : pts)
					minCost += Math.abs(x - pt);
				
				out.println(minCost);
			}
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