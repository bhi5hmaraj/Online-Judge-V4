import java.util.*;
import java.io.*;
public class MinimumEdgesinaLayeredGraph
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int N , K;
	static long memo[][][];

	static long INF = (long) 1e14;

	static long count(int idx , int prev , int remain) {
		if(idx == K - 1)
			return remain == 1 ? prev : INF;
		else if(memo[idx][prev][remain] != -1)
			return memo[idx][prev][remain];
		else {
			long min = INF;
			for(int i=1;i<=remain;i++)
				min = Math.min(min,(prev * i) + count(idx + 1, i, remain - i));

			return memo[idx][prev][remain] = min;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		K = s1.nextInt();

		if(K > N)
			out.println(-1);
		else {
			memo = new long[K][N + 1][N + 1];
			for(long f[][] : memo)
				for(long s[] : f)
					Arrays.fill(s, -1);
			
			long minEdges = count(1, 1, N - 1);
			out.println(minEdges == INF ? -1 : minEdges);
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