import java.util.*;
import java.io.*;
class AMR14C
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int prefixSum[];
	static int query(int L , int R) {
		return prefixSum[R] - prefixSum[L - 1];
	}
			
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			int M = s1.nextInt();
			int X = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			prefixSum = new int[M + 1];
			for(int a : arr)
				prefixSum[(a % M) + 1]++;
			for(int i=1;i<=M;i++)
				prefixSum[i] += prefixSum[i - 1];
			long cnt = 0;
			for(int i=0;i<M;i++)
				if(i <= X)
					cnt += (1L * query(i + 1, i + 1) * (query(1, ((X - i) % M) + 1) + query(M - i + 1, M))); 
				else 
					cnt += (1L * query(i + 1, i + 1) * (query(M - i + 1, M + X - i + 1)));
			
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