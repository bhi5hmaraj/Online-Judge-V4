import java.util.*;
import java.io.*;
public class CavesandTunnels
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class SegmentTree  { // Implemented to store min in a range , point update and range query
		int tree[];
		int len;
		int size;
		SegmentTree(int len) { // arr should be a 1 based array
			this.len = len;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new int[size];
		}
		void update(int node,int idx,int val,int nl,int nr) {
			if(nl == nr && nl == idx)
				tree[node] += val;
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				tree[node] = Math.max(tree[2*node],tree[(2 * node) + 1]);
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		int query(int L , int R){
			return query(1, L, R, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr) {
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}

	}


	static int LCAHLD(int u , int v) {
		if(head[u] == head[v])
			return level[u] < level[v] ? u : v;
		else
			return level[head[u]] < level[head[v]] ? LCAHLD(u, parent[head[v]]) : LCAHLD(v, parent[head[u]]);
	}

	static void buildHLD(int u , int par , int hd) {

		head[u] = hd;
		stamp[u] = time++;
		
		int maxSize = 0;
		int next = -1;
		for(int v : adj[u]) 
			if(v != par && size[v] > maxSize) {
				maxSize = size[v];
				next = v;
			}

		if(next != -1)
			buildHLD(next, u, hd); // Continue the current chain for the maximum branch

		for(int v : adj[u]) 
			if(v != par && v != next) 
				buildHLD(v, u, v); // start a new chain for others

	}

	static int precompute(int u , int par ,int lev) {
		size[u] = 1;
		parent[u] = par;
		level[u] = lev;
		for(int v : adj[u])
			if(v != par)
				size[u] += precompute(v, u , lev + 1);

		return size[u];
	}


	static int queryUp(int from , int to) {
		int max = 0;
		// int ff = from , tt = to;
		while(head[from] != head[to]) {
			max = Math.max(max,segTree.query(stamp[head[from]], stamp[from]));
			from = parent[head[from]];
		}
		max = Math.max(max , segTree.query(stamp[to], stamp[from]));
		// System.out.printf("from = %d to = %d sum = %d \n" , ff , tt , sum);
		return max;
	}

	static int getMax(int u , int v) {
		int lca = LCAHLD(u, v);
		// System.out.printf("lca of (%d , %d) = %d \n" , u , v , lca);
		return Math.max(queryUp(u, lca),queryUp(v, lca));
	}

	static void update(int u , int cost) {
		segTree.update(stamp[u], cost);
	}
	static ArrayList<Integer>[] adj;
	static int size[] , head[] , parent[] , level[] , stamp[] , time  , nodeCost[] , V ;
	static SegmentTree segTree;
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){ 

			time = 1; // silly mistake !! 
			V = s1.nextInt();
			int E = V - 1;
			adj = new ArrayList[V + 1];
			for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}

			size = new int[V + 1];
			head = new int[V + 1];
			parent = new int[V + 1];
			level = new int[V + 1];
			stamp = new int[V + 1];

			precompute(1, 0, 1);
			buildHLD  (1, 0, 1);

			segTree = new SegmentTree(V);
			
			int Q = s1.nextInt();
			while(Q-->0) {
				if(s1.nextChar() == 'I')
					update(s1.nextInt(), s1.nextInt());
				else
					out.println(getMax(s1.nextInt(), s1.nextInt()));
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
	/*
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new CavesandTunnels().run();
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
	}*/
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