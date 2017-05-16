import java.util.*;
import java.io.*;
public class AladdinandtheReturnJourney
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class FenwickTree {  // In this variant of BIT each query is **NOT** a cumulative sum , it is the actual freq
		// Point Query and Range update
		int tree[];
		int len;
		FenwickTree(int len) {
			this.len = len;
			tree = new int[len + 10];
		}
		void update(int idx , int val) {
			for(;idx <= len;idx += (idx & -idx))
				tree[idx] += val;
		}
		int query(int idx) {
			int sum = 0;
			for(;idx > 0;idx -= (idx & -idx))
				sum += tree[idx];

			return sum;
		}
		int query(int L , int R) {
			return query(R) - query(L - 1);
		}
		void update(int L , int R , int val) {
			if(L <= R && L > 0) {
				update(L, val);
				update(R + 1, -val);
			}
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
		int sum = 0;
		// int ff = from , tt = to;
		while(head[from] != head[to]) {
			sum += BIT.query(stamp[head[from]], stamp[from]);
			from = parent[head[from]];
		}
		sum += BIT.query(stamp[to], stamp[from]);
		// System.out.printf("from = %d to = %d sum = %d \n" , ff , tt , sum);
		return sum;
	}

	static int countGenie(int u , int v) {
		int lca = LCAHLD(u, v);
		// System.out.printf("lca of (%d , %d) = %d \n" , u , v , lca);
		return queryUp(u, lca) + queryUp(v, lca) - nodeCost[stamp[lca]];
	}

	static void updateGenie(int u , int cost) {
		BIT.update(stamp[u], -nodeCost[stamp[u]]);
		nodeCost[stamp[u]] = cost;
		BIT.update(stamp[u], cost);
	}
	static ArrayList<Integer>[] adj;
	static int size[] , head[] , parent[] , level[] , stamp[] , time  , nodeCost[] , V ;
	static FenwickTree BIT ;
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){ // This problem uses 0 based indexing except the segTree and stamp

		// throw new RuntimeException();
		for(int tc = 1 , T = s1.nextInt();tc <= T;tc++) {
			//s1.nextLine(); // consume empty line
			time = 1; // silly mistake !! 
			V = s1.nextInt();
			int[] genie = s1.nextIntArray(V);
			int E = V - 1;
			adj = new ArrayList[V];
			for(int i=0;i<V;i++) adj[i] = new ArrayList<>();
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].add(v);
				adj[v].add(u);
			}

			size = new int[V];
			head = new int[V];
			parent = new int[V];
			level = new int[V];
			stamp = new int[V];
			nodeCost = new int[V + 1];

			precompute(0, 0, 1);
			buildHLD(0, -1, 0);


			BIT = new FenwickTree(V);
			for(int i=0;i<V;i++) {
				BIT.update(stamp[i], genie[i]);
				nodeCost[stamp[i]] = genie[i];
			}
			genie = null;
			
			int Q = s1.nextInt();
			out.println("Case " + tc + ":");
			while(Q-->0) {
				if(s1.nextInt() == 0)
					out.println(countGenie(s1.nextInt(), s1.nextInt()));
				else
					updateGenie(s1.nextInt(), s1.nextInt());
			}
			// out.println();
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
				new AladdinandtheReturnJourney().run();
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