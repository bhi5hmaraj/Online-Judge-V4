import java.util.*;
import java.io.*;
class JTREE
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Ticket {
		int valid;
		long cost;
		Ticket(int valid , long cost){
			this.valid = valid;
			this.cost = cost;
		}
	}
	static ArrayList<Ticket>[] adj;
	static ArrayList<Integer>[] tree;
	static int[] parent;
	static long memo[][];
	static int cnt = 0;
	static void dfs(int u , int par){
		parent[u] = par;
		for(int v:tree[u])
			if(v != par)
				dfs(v, u);
	}
	static final long INF = (long)(1e16);
	static long findOpt(int idx , int rem ){
		if(idx == 1)
			return 0;
		else {
			long min = INF;

			if(memo[idx][rem] != -1)
				return memo[idx][rem];

			if(rem == 0){
				for(Ticket t : adj[idx])
					min = Math.min(min,t.cost + findOpt(parent[idx], t.valid - 1));

			}
			else {
				min = Math.min(min,findOpt(parent[idx], rem - 1));
				for(Ticket t : adj[idx])
					min = Math.min(min,t.cost + findOpt(parent[idx], t.valid - 1));
			}

			return memo[idx][rem] = min;
		}
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int M = s1.nextInt();
		int E = N - 1;
		tree = new ArrayList[N + 1];
		adj  = new ArrayList[N + 1];
		parent = new int[N + 1];
		memo   = new long[N + 1][N + 1];
		for(int i=1;i<=N;i++)
			Arrays.fill(memo[i], -1);
		for(int i=1;i<=N;i++){
			tree[i] = new ArrayList<>();
			adj[i]  = new ArrayList<>();
		}
		while(E-->0){
			int u = s1.nextInt();
			int v = s1.nextInt();
			tree[u].add(v);
			tree[v].add(u);
		}
		dfs(1, -1);
		while(M-->0)
			adj[s1.nextInt()].add(new Ticket(s1.nextInt(), s1.nextLong()));

		int Q = s1.nextInt();
		while(Q-->0)
			out.println(findOpt(s1.nextInt(), 0));

	}
	static final int MAX = (int)(1e5);
	static long DP[];
	static SegmentTree segmentTree;
	static void rec(int u , int par , int lev){
		if(u == 1){
			DP[1] = 0;
			segmentTree.update(1, 0);
		}
		else {
			for(Ticket t : adj[u]) {
				int start = Math.max(1,lev - t.valid);
				int end = lev - 1;
				DP[u] = Math.min(DP[u],t.cost + segmentTree.query(start, end));
			}
			segmentTree.update(lev, DP[u]);
		}
		for(int v : tree[u])
			if(v != par)
				rec(v, u, lev + 1);
		
		segmentTree.update(lev, INF);
	}
	
	
	static class SegmentTree
	{
		long tree[];
		int len;
		int size;
		SegmentTree() 
		{
			len = MAX;
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new long[size];
			Arrays.fill(tree, INF);
		}
		void update(int node,int idx,long val,int nl,int nr)
		{
			if(nl == nr && nl == idx)
				tree[node] = val;
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);
				tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
			}
		}
		void update(int idx , long val){
			update(1, idx, val, 1, len);
		}
		long query(int L , int R){
			return query(1, L, R, 1, len);
		}
		long query(int node , int L , int R, int nl, int nr)
		{
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.min(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, int root , int nl , int nr) {

			int mid = (nl+nr)/2;
			if(nl <= nr){
				if (root == 0) {
					sb.append("Tree Empty\n");
					return sb;
				}
				if ((2*root+1) < size ) {
					toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, (2*root)+1 , mid+1 , nr);
				}
				if(root < size)
					sb.append(prefix).append(isTail ? "└── " : "┌── ").append( (tree[root] == INF ? "INF" : tree[root]) + " [" + nl+", "+nr+"]").append("\n");
				if ((2*root) < size) {
					toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, (2*root),nl,mid);
				}
			}
			return sb;
		}
		@Override
		public String toString() {
			return toString(new StringBuilder(), true, new StringBuilder(), 1 , 1, len).toString();
		}
	}
	@SuppressWarnings("unchecked")
	private static void solve2(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int M = s1.nextInt();
		int E = N - 1;
		tree = new ArrayList[N + 1];
		adj  = new ArrayList[N + 1];
		DP   = new long[N + 1];
		Arrays.fill(DP, INF);
		segmentTree = new SegmentTree();
		for(int i=1;i<=N;i++){
			tree[i] = new ArrayList<>();
			adj[i]  = new ArrayList<>();
		}
		while(E-->0){
			int u = s1.nextInt();
			int v = s1.nextInt();
			tree[u].add(v);
			tree[v].add(u);
		}
		while(M-->0)
			adj[s1.nextInt()].add(new Ticket(s1.nextInt(), s1.nextLong()));
		
		rec(1, -1, 1);
		int Q = s1.nextInt();
		while(Q-->0)
			out.println(DP[s1.nextInt()]);
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String[] args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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