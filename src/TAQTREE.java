import java.util.*;
import java.io.*;
class TAQTREE
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge {
		int v , cost;
		Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	
	static class SegmentTree  { 
		int tree[];
		int len;
		int size;
		SegmentTree(int arr[]) { // arr should be a 1 based array
			len = arr.length - 1;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new int[size];
			build(arr, 1, 1, len);
		}
		void update(int node,int idx,int val,int nl,int nr) {
			if(nl == nr && nl == idx)
				tree[node] = val;
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				tree[node] = tree[2*node] + tree[(2 * node) + 1];
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		int query(int L , int R){
			return query(1, L, R, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr) {
			
			if(L > R) return 0;
			
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return query(2*node, L, mid , nl , mid) + query((2*node)+1, mid+1, R , mid+1,nr);
		}
		void build(int arr[],int node,int L,int R) {
			if(L == R)
				tree[node] = arr[L];
			else {
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = tree[2*node] + tree[(2 * node) + 1];
			}
		}
	}
	
	static int LCAHLD(int u , int v) {
		if(head[u] == head[v])
			return level[u] < level[v] ? u : v;
		else
			return level[head[u]] < level[head[v]] ? LCAHLD(u, parent[head[v]]) : LCAHLD(v, parent[head[u]]);
	}

	static void buildHLD(int u , int par , int hd , int cost) {
		
		head[u] = hd;
		stamp[u] = time++;
		edgeCost[stamp[u]] = cost;

		int maxSize = 0;
		Edge next = null;
		for(Edge e : adj[u]) 
			if(e.v != par && size[e.v] > maxSize) {
				maxSize = size[e.v];
				next = e;
			}
		
		if(next != null)
			buildHLD(next.v, u, hd, next.cost); // Continue the current chain for the maximum branch
		
		for(Edge e : adj[u]) 
			if(e.v != par && e.v != next.v) 
				buildHLD(e.v, u, e.v, e.cost); // start a new chain for others
		
	}
	
	static int precompute(int u , int par ,int lev) {
		size[u] = 1;
		parent[u] = par;
		level[u] = lev;
		for(Edge v : adj[u])
			if(v.v != par)
				size[u] += precompute(v.v, u , lev + 1);
		
		return size[u];
	}
	
	static ArrayList<Edge>[] adj;
	static int size[] , head[] , parent[] , level[] , stamp[] , time = 1 , edgeCost[] , V;
	static SegmentTree segTree;
	
	static int queryUp(int from , int to) {
		int sum = 0;
		while(head[from] != head[to]) {
			sum += segTree.query(stamp[head[from]], stamp[from]);
			from = parent[head[from]];
		}
		sum += segTree.query(stamp[to] + 1, stamp[from]);
		return sum;
	}
	
	static int dist(int u , int v) {
		int lca = LCAHLD(u, v);
		return queryUp(u, lca) + queryUp(v, lca);
	}
	
	static void updateEdgeCost(int u , int v , int cost) {
		segTree.update((level[u] < level[v]) ? stamp[v] : stamp[u], cost);
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		V 		 = s1.nextInt();
		int E 	 = V - 1;
		size 	 = new int[V + 1];
		head 	 = new int[V + 1];
		parent 	 = new int[V + 1];
		level 	 = new int[V + 1];
		stamp 	 = new int[V + 1];
		edgeCost = new int[V + 1];
		adj 	 = new ArrayList[V + 1];
		
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		
		int edges[][] = new int[E + 1][2];
		
		for(int i=1;i<=E;i++) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			int cost = s1.nextInt();
			adj[u].add(new Edge(v, cost));
			adj[v].add(new Edge(u, cost));
			edges[i][0] = u;
			edges[i][1] = v;
		}
		
		precompute(1, 0, 1);
		buildHLD(1, -1, 1, 0);

		segTree = new SegmentTree(edgeCost);
		int Q = s1.nextInt();
		while(Q-->0) {
			if(2 == s1.nextInt())
				out.println(dist(s1.nextInt(), s1.nextInt()));
			else {
				int e = s1.nextInt();
				updateEdgeCost(edges[e][0], edges[e][1], s1.nextInt());
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}