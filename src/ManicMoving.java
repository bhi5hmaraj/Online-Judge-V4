import java.util.*;
import java.io.*;
public class ManicMoving
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Edge> adj[];
	static long cost[][];
	static int family[][] , K;
	static class Edge implements Comparable<Edge> {
		int u , v;
		long cost;
		Edge(int u , int v, long cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;

		}
		Edge(int v , long cost) {
			this(-1 , v , cost);
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}
	
	static boolean marked[];
	static int component[];
	static void dfs(int u , int id) {
		marked[u] = true;
		component[u] = id;
		for(Edge e : adj[u])
			if(!marked[e.v])
				dfs(e.v, id);
				
	}
	
	static long[] dijkstra(int start , int V) {  // Dependency adj (adjacency list of type Edge) , vertex count V 
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		boolean marked[] = new boolean[V + 1]; 
		long distTo[] = new long[V + 1];
		while (!pq.isEmpty()) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				for (Edge e : adj[u])
					if (!marked[e.v])
						pq.add(new Edge(e.v, e.cost + distTo[u]));
			}
		}

		return distTo;
	}
	
	static long memo[][];
	
	static long findOpt(int idx , int curr) {
		if(idx == K - 1)
			return cost[curr][family[idx][1]];
		else if(memo[idx][curr] != -1)
			return memo[idx][curr];
		else
			return memo[idx][curr] = Math.min(cost[curr][family[idx + 1][0]] + cost[family[idx + 1][0]][family[idx][1]] + findOpt(idx + 1, family[idx][1]),
					cost[curr][family[idx][1]] + cost[family[idx][1]][family[idx + 1][0]] + findOpt(idx + 1, family[idx + 1][0]));
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		for(int tc = 1 , T = s1.nextInt();tc <= T;tc++) {
			int V = s1.nextInt();
			int E = s1.nextInt();
			K = s1.nextInt();
			
			adj = new ArrayList[V + 1];
			for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();
			
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				long fuel = s1.nextLong();
				adj[u].add(new Edge(v, fuel));
				adj[v].add(new Edge(u, fuel));
			}
			
			cost = new long[V + 1][];
			family = new int[K][];
			for(int i=0;i<K;i++)
				family[i] = s1.nextIntArray(2);
			
			marked = new boolean[V + 1];
			component = new int[V + 1];
			int id = 1;
			for(int i=1;i<=V;i++)
				if(!marked[i])
					dfs(i, id++);
			
			int check = component[family[0][0]];
			boolean flag = true;
			for(int i=0;i<K;i++)
				flag &= component[family[i][0]] == check && component[family[i][1]] == check;
			
			if(!flag) 
				out.println("Case #" + tc + ": -1");
			else {
				
				for(int i=1;i<=V;i++)
					cost[i] = dijkstra(i, V);
				
				memo = new long[K][V + 1];
				for(long l[] : memo)
					Arrays.fill(l, -1);
				
				long fuelUsed = cost[1][family[0][0]] + findOpt(0, family[0][0]);
				out.println("Case #" + tc + ": " + fuelUsed);
				
			}
			
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("manic_moving.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/ "out.txt"); 
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