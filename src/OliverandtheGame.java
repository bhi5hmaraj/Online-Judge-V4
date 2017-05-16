import java.util.*;
import java.io.*;
public class OliverandtheGame
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer>[] adj;
	static int level[];
	static int DP[][];
	static int V;
	static int log(int N) {
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	
	
	
	static void dfs(int u , int par , int lev) {
		level[u] = lev;
		for(int v : adj[u])
			if(v != par) {
				DP[0][v] = u;
				dfs(v, u, lev + 1);
			}
	}
	
	static void build() {
		for(int i=1;i<DP.length;i++)
			for(int j=1;j<=V;j++)
				DP[i][j] = DP[i - 1][DP[i - 1][j]];
	}
	
	static boolean isAncestor(int from , int to) {
		int diff = level[from] - level[to];
		while(diff > 0) {
			int moveDist = log(diff);
			from = DP[moveDist][from];
			diff -= (1 << moveDist);
		}
		return from == to;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		V = s1.nextInt();
		int E = V - 1;
		adj = new ArrayList[V + 1];
		level = new int[V + 1];
		DP = new int[log(V) + 1][V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		dfs(1, -1, 1);
		build();
		
		int Q = s1.nextInt();
		while(Q-->0) {
			int type = s1.nextInt();
			int to   = s1.nextInt();
			int from = s1.nextInt();
			if(type == 0)
				out.println((level[from] >= level[to] && isAncestor(from, to)) ? "YES" : "NO");
			else
				out.println((level[from] <= level[to] && isAncestor(to, from)) ? "YES" : "NO");
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	

	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new OliverandtheGame().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}

	void run(){	
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