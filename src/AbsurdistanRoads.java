import java.util.*;
import java.io.*;
public class AbsurdistanRoads
{


	/************************ SOLUTION STARTS HERE ***********************/
	static ArrayList<Edge>[] adj;
	static int distTo[];
	static class Edge implements Comparable<Edge> {
		int u , v;
		int cost;

		Edge(int u , int v, int cost) {
			this.u = u;
			this.v = v;
			this.cost = cost;

		}
		Edge(int v , int cost) {
			this(-1 , v , cost);
		}
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	static void dfs(int u, int par, int dist) {
		distTo[u] = dist;
		for(Edge e : adj[u])
			if(e.v != par)
				dfs(e.v, u, dist + e.cost);
	}

	static class DisjointSetUnion {
		private int parent[];
		private int size[];
		private int cnt;

		DisjointSetUnion(int length) {
			this.cnt = length;
			parent = new int[length + 10];
			size = new int[length + 10];
			Arrays.fill(size, 1);
			for (int i = 0; i < parent.length; i++)
				parent[i] = i;
		}

		int root(int p) {
			return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
		}

		int sizeOf(int p) {
			return size[root(p)];
		}

		boolean connected(int u, int v) {
			return root(u) == root(v);
		}

		int components() {
			return cnt;
		}

		void union(int u, int v) {
			if (!connected(u, v)) {
				cnt--;
				int rootU = root(u);
				int rootV = root(v);
				if (size[rootU] < size[rootV]) {
					parent[rootU] = rootV;
					size[rootV] += size[rootU];
				} 
				else {
					parent[rootV] = rootU;
					size[rootU] += size[rootV];
				}
			}
		}
	}


	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		String line = null;
		while((line = s1.nextLine()) != null){

			int V = Integer.parseInt(line);
			int edges[][] = new int[V][3];
			Edge arr[] = new Edge[V*V];
			BitSet adjacent[] = new BitSet[V + 1];
			adj = new ArrayList[V + 1];
			for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();

			for(int i=1;i<=V;i++) {
				adjacent[i] = new BitSet(V + 1);
				adjacent[i].set(i);
			}
			for(int i=1;i<=V;i++)
				for(int j=1;j<=V;j++) 
					arr[(i - 1)*V + (j - 1)] = new Edge(i, j, s1.nextInt());

			Arrays.sort(arr);
			DisjointSetUnion dsu = new DisjointSetUnion(V);
			int ptr = 0;
			for (int i = 0; i < arr.length && dsu.components() != 1; i++) {
				if (!dsu.connected(arr[i].u, arr[i].v)) {
					dsu.union(arr[i].u, arr[i].v);
					edges[ptr][0] = arr[i].u;
					edges[ptr][1] = arr[i].v;
					edges[ptr][2] = arr[i].cost;
					adjacent[arr[i].u].set(arr[i].v);
					adjacent[arr[i].v].set(arr[i].u);
					ptr++;
					adj[arr[i].u].add(new Edge(arr[i].v, arr[i].cost));
					adj[arr[i].v].add(new Edge(arr[i].u, arr[i].cost));
				}
			}


			if(V == 2) {
				edges[ptr][0] = edges[ptr-1][1];
				edges[ptr][1] = edges[ptr-1][0];
				edges[ptr][2] = edges[ptr-1][2];
			}

			int dist[][] = new int[V + 1][];

			for(int i=1;i<=V;i++) {
				distTo = new int[V + 1];
				dfs(i, -1, 0);
				dist[i] = distTo;
			}

			boolean flag = false;
			
			for(int i=0;i<arr.length;i++)
				if(!adjacent[arr[i].u].get(arr[i].v) && dist[arr[i].u][arr[i].v] != arr[i].cost) {
					edges[ptr][0] = arr[i].u;
					edges[ptr][1] = arr[i].v;
					edges[ptr][2] = arr[i].cost;
					flag = true;
					break;
				}
			
			if(!flag) {
				for(int i=0;i<arr.length;i++)
					if(!adjacent[arr[i].u].get(arr[i].v)) {
						edges[ptr][0] = arr[i].u;
						edges[ptr][1] = arr[i].v;
						edges[ptr][2] = arr[i].cost;
						break;
					}
			}
			
			for(int a[] : edges)
				out.println(a[0] + " " + a[1] + " " + a[2]);

			out.println();
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