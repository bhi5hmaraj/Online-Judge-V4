import java.util.*;
import java.io.*;
public class EternalVictory
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge {
		int v;
		long cost;
		Edge(int uu, long cc) {
			v = uu;
			cost = cc;
		}
	}
	
	static long dfs(int u , int par) {
		
		for(Edge e : adj[u])
			if(e.v != par) {
				end[e.v] = dfs(e.v, u);
				sumOfEdge[u] += e.cost + sumOfEdge[e.v];
			}
		
		end[u] = Long.MAX_VALUE;
		for(Edge e : adj[u])
			if(e.v != par) 
				end[u] = Math.min(end[u],e.cost + end[e.v] + (2L * (sumOfEdge[u] - (e.cost + sumOfEdge[e.v]))));
		
		end[u] = end[u] == Long.MAX_VALUE ? 0 : end[u];
		
		return end[u];
	}
	
	static long sumOfEdge[] , end[];
	static ArrayList<Edge>[] adj;
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int V = s1.nextInt();
		int E = V  - 1;
		adj = new ArrayList[V + 1];
		sumOfEdge = new long[V + 1];
		end = new long[V + 1];
		for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			long cost = s1.nextLong();
			adj[u].add(new Edge(v, cost));
			adj[v].add(new Edge(u, cost));
		}
		
		out.println(dfs(1, -1));
		
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