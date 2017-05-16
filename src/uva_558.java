import java.util.*;
import java.io.*;
public class uva_558
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge {
		int v;
		long cost;
		Edge(int vv, long wt) {
			v = vv;
			cost = wt;
		}
	}
	
	static class BellmanFordSSSP {
		
		static final long INF = (long)3e18;
		long distTo[];
		ArrayList<Edge>[] adj;
		int V, st;
		
		public BellmanFordSSSP(ArrayList<Edge>[] adj , int V , int start, boolean oneBased) {
			
			this.V = V;
			this.adj = adj;
			st = oneBased ? 1 : 0;
			distTo = new long[V + st];
			
			Arrays.fill(distTo, INF);
			distTo[start] = 0;
			
			for(int i=1;i<=V-1;i++) 
				for(int u=st;u<V+st;u++)
					for(Edge e : adj[u])
						distTo[e.v] = Math.abs(distTo[u]) == INF ? distTo[e.v] :Math.max(-INF,Math.min(distTo[e.v],distTo[u] + e.cost));
			
		}
		
		// Checks wether the graph contains a negative cycle or not
		
		boolean containsNegativeCycle() {
			for(int u=st;u<V+st;u++)
				for(Edge e : adj[u])
					if(distTo[u] != INF && distTo[u] + e.cost < distTo[e.v])
						return true;
			
			return false;
		}
		
		// Sets true for all vertices which are rechable from any negative cycle or null otherwise
		
		boolean[] reachableFromNegativeCycle() {
			if(!containsNegativeCycle())
				return null;
			else {
				ArrayDeque<Integer> queue = new ArrayDeque<>();
				boolean cycleMarked[] = new boolean[V + st];
				for(int u=st;u<V+st;u++)
					for(Edge e : adj[u])
						if(distTo[u] != INF && distTo[u] + e.cost < distTo[e.v] && !cycleMarked[u]) {
							cycleMarked[u] = true;
							queue.add(u);
						}
				
				while(!queue.isEmpty()) {
					int u = queue.remove();
					for(Edge e : adj[u])
						if(!cycleMarked[e.v]) {
							cycleMarked[e.v] = true;
							queue.add(e.v);
						}
				}
				return cycleMarked;
			}
		}

	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			
			int V = s1.nextInt();
			int E = s1.nextInt();
			ArrayList<Edge>[] adj = new ArrayList[V];
			for(int i=0;i<V;i++) adj[i] = new ArrayList<>();
			
			while(E-->0)
				adj[s1.nextInt()].add(new Edge(s1.nextInt(), s1.nextLong()));
			
			out.println(new BellmanFordSSSP(adj, V, 0, false).containsNegativeCycle() ? "possible" : "not possible");
			
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