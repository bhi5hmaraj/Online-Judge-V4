import java.util.*;
import java.io.*;
public class CompleteTheGraph
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static boolean marked[]; 
	private static long distTo[];
	private static ArrayList<Integer>[] adj;
	private static long cost[][];
	private static int V;
	static class Edge implements Comparable<Edge> {
		int u;
		int v;
		long cost;

		Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}
		Edge(int u , int v, long cost) {
			this(v, cost);
			this.u = u;
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	static void dfs(int u) {
		marked[u] = true;
		for(int v : adj[u])
			if(!marked[v])
				dfs(v);
	}
	static final long INF = (long)1e14;
	static boolean include[][];
	static boolean variable[][];

	static void makeINF(int start , int end) {

		include = new boolean[V][V];
		int par[] = new int[V];
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		marked = new boolean[V];
		distTo = new long[V];
		while (!pq.isEmpty()) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				par[u] = min.u;
				if(u == end)
					break;
				
				for (int v : adj[u]) 
					if (!marked[v])
						pq.add(new Edge(u , v, distTo[u] + cost[u][v]));
				
			}
		}

		int curr = end;
		while(curr != start) {
			int p = par[curr];
			if(variable[curr][p])
				include[curr][p] = include[p][curr] = true;
			curr = p;
		}

		for(int i=0;i<V;i++)
			for(int j=0;j<V;j++)
				if(variable[i][j] && !include[j][i]) {
					variable[i][j] = variable[j][i] = false;
					cost[i][j] = cost[j][i] = INF;
				}
		include = null; // Free memory
	}


	static boolean isReachable(int start , int end) {
		marked = new boolean[V];
		dfs(start);
		return marked[end];
	}

	private static long dijkstraWithoutZero(int start , int end) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		marked = new boolean[V];
		distTo = new long[V];
		while (!pq.isEmpty()) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				if(u == end)
					return min.cost;
				for (int v : adj[u]) {
					long wt = cost[u][v];
					if (!marked[v] && wt != 0)
						pq.add(new Edge(v, distTo[u] + wt));
				}
			}
		}

		return -1;
	}
	
	static void print(PrintWriter out) {
		boolean mark[][] = new boolean[V][V];
		out.println("YES");
		for(int i=0;i<V;i++)
			for(int v : adj[i])
				if(!mark[i][v]) {
					mark[i][v] = mark[v][i] = true;
					long d = cost[i][v];
					d = (d > 0) ? d : INF;
					out.println(i + " " + v + " " + d);
				}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		int E = s1.nextInt();
		long L = s1.nextLong();
		int start = s1.nextInt();
		int end = s1.nextInt();
		cost = new long[V][V];
		adj = new ArrayList[V];
		for(int i=0;i<V;i++)
			adj[i] = new ArrayList<>();
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			long wt = s1.nextLong();
			cost[u][v] = cost[v][u] = wt;
			adj[u].add(v);
			adj[v].add(u);
		}

		if(!isReachable(start, end))
			out.println("NO");
		else {
			long without = dijkstraWithoutZero(start, end);
			if(without > 0 && without < L)
				out.println("NO");
			else if(without == L) {
				print(out);
			}
			else {
				variable = new boolean[V][V];
				for(int i=0;i<V;i++)
					for(int v:adj[i]) {
						if(cost[i][v] == 0) {
							variable[i][v] = variable[v][i] = true;
							cost[i][v] = cost[v][i] = 1;
						}
					}

				long dist = dijkstraWithoutZero(start, end);
				makeINF(start, end);
				while(dist < L) {
					PriorityQueue<Edge> pq = new PriorityQueue<>();
					pq.add(new Edge(start, 0));
					marked = new boolean[V];
					distTo = new long[V];
					int from = -1 , to = -1;
					while ( !pq.isEmpty() ) { 	// Relaxation
						Edge min = pq.remove();
						int u = min.v;
						if(!marked[u]){
							marked[u] = true;
							distTo[u] = min.cost;
							if(u == end) {
								dist = min.cost;
								break;
							}
							for(int v : adj[u]){ 
								if (!marked[v]) { 
									long d = cost[u][v] + distTo[u];
									if(d <= L) {
										pq.add(new Edge(v, d));
										if(variable[u][v]) {
											from = u;
											to = v;
										}
									}
								}
							}
						}
					}


					if(from != -1 && to != -1 && dist <= L) {
						variable[from][to] = variable[to][from] = false;
						cost[from][to] += (L - dist);
						cost[to][from] += (L - dist);
					}

				}

				if(dist == L) 
					print(out);
				else 
					out.println("NO");
				
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}