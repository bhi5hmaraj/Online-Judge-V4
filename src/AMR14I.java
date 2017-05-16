import java.util.*;
import java.io.*;
class AMR14I
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			
			int N = s1.nextInt();
			int M = s1.nextInt();
			int S = s1.nextInt();
			int P = s1.nextInt();
			int Q = s1.nextInt();
			
			int parent[] = new int[N];

			for(int i=0;i<N - 1;i++)
				parent[i] = i + 1;
			
			parent[N - 1] = M - 1;
			
			int skipP[] = new int[N];
			int skipQ[] = new int[N];
			for(int i=0;i<P;i++)
				skipP[0] = parent[skipP[0]];
			for(int i=0;i<Q;i++)
				skipQ[0] = parent[skipQ[0]];
			for(int i=1;i<N;i++) {
				skipP[i] = parent[skipP[i - 1]];
				skipQ[i] = parent[skipQ[i - 1]];
			}
			
			int currP = 0;
			int currQ = 0;
			int cnt = 0;
			
			// System.out.println("parent " + Arrays.toString(parent));
			// System.out.println("skipP " + Arrays.toString(skipP));
			// System.out.println("skipQ " + Arrays.toString(skipQ));
			
			for(int i=0;i<S;i++) {
				currP = skipP[currP];
				currQ = skipQ[currQ];
				cnt += currP == currQ ? 1 : 0;
			}
			
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