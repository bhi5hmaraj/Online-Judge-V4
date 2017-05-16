import java.util.*;
import java.io.*;
public class KefaandDishes
{


	/************************ SOLUTION STARTS HERE ***********************/

	static long cost[][];
	static long memo[][];
	static long satisfaction[];
	static int V;
	static final long INF = (long)(1e15);
	static long TSP(int mask , int prev) {
		
		// System.out.println("mask " + mask + " prev " + prev); 		
		
		if(memo[mask][prev] != -1)
			return memo[mask][prev];
		else {
			long dist = -INF;
			for(int remain = mask , i = Integer.numberOfTrailingZeros(remain); remain > 0;remain ^= 1 << i , i = Integer.numberOfTrailingZeros(remain))
				if(i != prev)  
					dist = Math.max(dist,cost[i][prev] + satisfaction[prev] + TSP(mask ^ (1 << prev), i));

			return memo[mask][prev] = dist;
		}
	}


	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		int M = s1.nextInt();
		int E = s1.nextInt();
		cost = new long[V][V];
		memo = new long[1 << V][V];
		satisfaction = s1.nextLongArray(V);
		for(long a[] : memo)
			Arrays.fill(a, -1);
		for(int i=0;i<V;i++)
			memo[1 << i][i] = satisfaction[i];
		while(E-->0)
			cost[s1.nextInt() - 1][s1.nextInt() - 1] = s1.nextLong();

		long max = -INF;
		for(int mask = 1;mask < 1 << V;mask++) 
			if(Integer.bitCount(mask) == M) 
				for(int remain = mask , prev = Integer.numberOfTrailingZeros(remain); remain > 0;remain ^= 1 << prev , prev = Integer.numberOfTrailingZeros(remain)) 
					max = Math.max(max,TSP(mask, prev));
		
		out.println(max);
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