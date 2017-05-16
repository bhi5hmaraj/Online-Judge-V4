import java.util.*;
import java.io.*;
public class uva_10496
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int distance(int x1 , int y1 , int x2 , int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	static final int INF = (int)(1e7);
	static int cost[][];
	static int memo[][];
	static int V;
	static int TSP(int mask , int prev) {
		
		if(memo[mask][prev] != -1)
			return memo[mask][prev];
		else {
			int dist = Integer.MAX_VALUE;
			for(int i = 0;i < V;i++)
				if(i != prev && (mask & (1 << i)) != 0)
					dist = Math.min(dist,cost[prev][i] + TSP(mask ^ (1 << prev), i));
			
			return memo[mask][prev] = dist;
		}
		
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			s1.nextLine();
			int sx = s1.nextInt();
			int sy = s1.nextInt();
			V = s1.nextInt() + 1;
			int X[] = new int[V];
			int Y[] = new int[V];
			cost = new int[V][V];
			memo = new int[1 << V][V];
			X[0] = sx;
			Y[0] = sy;
			for(int i=1;i<V;i++) {
				X[i] = s1.nextInt();
				Y[i] = s1.nextInt();
			}
			for(int i=0;i<V;i++)
				for(int j=0;j<V;j++)
					cost[i][j] = distance(X[i] , Y[i] , X[j] , Y[j]);
			for(int i=0;i<memo.length;i++)
				Arrays.fill(memo[i], -1);
			for(int i=1;i<V;i++)
				memo[1 << i][i] = INF; // All the starting configuration other than 0 is invalid
			memo[1][0] = 0;
			int mask = (1 << V) - 1;
			int minDist = INF;
			for(int i=1;i<V;i++)
				minDist = Math.min(minDist,cost[i][0] + TSP(mask, i));
			
			out.println("The shortest path has length " + minDist);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}