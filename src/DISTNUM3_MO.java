/************************	      ௳  		   ************************/
import java.util.*;
import java.io.*;
class DISTNUM3_MO
{
	// Potential speedups
	// 	Change block size
	// 	change root
	// 	arraylist to array
	// 	shuffle before sort
	// 	coordinate compression and removing Hashmaps
	// 	!!!  Costed me a LOT OF TLE --> Final insight order based on number of pending updates !!!!!

	/*
	 * Idea adapted from :
	 * 			http://uoj.ac/submission/61485
	 * 			https://blog.anudeep2011.com/mos-algorithm/  
	 * 			http://codeforces.com/blog/entry/43230
	 * 			http://codeforces.com/blog/entry/10508
	 * 			http://codeforces.com/blog/entry/22060 			
	 * 			http://codeforces.com/blog/entry/44711 (Very Crazy)	(This is why it works) O(N^(5/3))
	 * 			
	 */

	/************************ SOLUTION STARTS HERE ***********************/

	//static ArrayList<Integer>[] adj;
	static int adj[][];
	static int clock = 0 , eulerTour[] , start[] , end[] , V;
	static int BLOCK_SIZE;

	/* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
	static int DP[][]; // One based vertices
	static int level[];
	static int parent[];
	static int log(int N){
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	static void binaryLift() {

		for(int i=1;i<=V;i++)
			DP[0][i] = parent[i];

		for (int i = 1; i < DP.length; i++) 
			for (int j = 1; j <= V; j++) 
				DP[i][j] = DP[i - 1][DP[i - 1][j]];

	}

	static int LCA(int u , int v){

		if(level[v] < level[u]){
			int temp = u;
			u = v;
			v = temp;
		}
		int diff = level[v] - level[u];
		while(diff > 0){        // Bring v to the same level as u
			int log = log(diff);
			v = DP[log][v];
			diff -= (1 << log);
		}
		while(u != v){
			int i = log(level[u]);
			for(;i > 0 && DP[i][u] == DP[i][v];)
				i--;

			u = DP[i][u];
			v = DP[i][v];
		}

		return u;
	}

	static void dfs(int u , int par , int lev) {
		eulerTour[clock] = u;
		start[u] = clock++;
		parent[u] = par;
		level[u] = lev;
		for(int v : adj[u])
			if(v != par)
				dfs(v, u, lev + 1);

		eulerTour[clock] = u;
		end[u] = clock++;
	}



	static class Query {
		int L , R , numUpdatesLess , LCA , id;

		public Query(int l, int r, int numUpdatesLess, int lCA , int id) {
			L = l;
			R = r;
			this.numUpdatesLess = numUpdatesLess;
			LCA = lCA;
			this.id = id;
		}
		@Override
		public String toString() {
			return String.format("[L = %d R = %d updatesLess = %d LCA = %d id = %d]", L , R , numUpdatesLess , LCA , id);
		}
	}

	static class Update {
		int idx , prevVal, newVal;

		public Update(int idx, int newVal , int prevVal) {
			this.idx = idx;
			this.newVal = newVal;
			this.prevVal = prevVal;
		}
		@Override
		public String toString() {
			return String.format("[idx = %d prevVal = %d newVal = %d", idx, prevVal , newVal);
		}
	}

	static class MoComparator implements Comparator<Query> {
		@Override
		public int compare(Query o1, Query o2) {
			if(blockCache[o1.L] != blockCache[o2.L])
				return blockCache[o1.L] - blockCache[o2.L];
			else if(blockCache[o1.R] != blockCache[o2.R])
				return blockCache[o1.R] - blockCache[o2.R];
			else
				return o1.numUpdatesLess - o2.numUpdatesLess;
		}
	}

	static int freq[];
	static int distinctCount;
	static boolean marked[];
	static int blockCache[];
	static int val[];
	static HashMap<Integer , Integer> map;
	static void visit(int idx) {
		if(marked[idx]) {
			freq[val[idx]]--;
			if(freq[val[idx]] == 0)
				distinctCount--;
		}
		else {
			freq[val[idx]]++;
			if(freq[val[idx]] == 1)
				distinctCount++;
		}

		marked[idx] = !marked[idx];
	}
	static void update(int idx , int newVal) {
		if(marked[idx]) {
			visit(idx);
			val[idx] = newVal;
			visit(idx);
		}
		else
			val[idx] = newVal;
	}

	static int countDistinct(int u , int v) {
		int lca = LCA(u, v);
		BitSet bitSet = new BitSet(map.size());
		bitSet.set(val[lca]);
		while(u != lca) {
			bitSet.set(val[u]);
			u = parent[u];
		}
		while(v != lca) {
			bitSet.set(val[v]);
			v = parent[v];
		}
		
		return bitSet.cardinality();
	}
	
	static int[][] packU(int n, int[] from, int[] to , int isOneBased) {	// Courtesy : UWI ( adjacency list using Jagged Arrays )
		int[][] g = new int[n + isOneBased][];
		int[] p = new int[n + isOneBased];
		for (int f : from)
			p[f]++;
		for (int t : to)
			p[t]++;
		for (int i = 0 + isOneBased; i < n + isOneBased; i++)
			g[i] = new int[p[i]];
		for (int i = 0; i < from.length; i++) {
			g[from[i]][--p[from[i]]] = to[i];
			g[to[i]][--p[to[i]]] = from[i];
		}
		return g;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		clock = 1;
		int MAX = (int) 1e5;
		int qSZ = 0, uSZ = 0;
		V = s1.nextInt();
		int Q = s1.nextInt();
		int E = V - 1;
		Query queries[] = new Query[MAX];
		Update updates[] = new Update[MAX];
		map = new HashMap<>();	// Used to compress the keys
		
		//adj = new ArrayList[V + 1];
		
		//for(int i=1;i<=V;i++) 
			//adj[i] = new ArrayList<>();

		val = s1.nextIntArrayOneBased(V);
		for(int i=1;i<=V;i++) {
			if(!map.containsKey(val[i]))
				map.put(val[i], map.size());
			
			val[i] = map.get(val[i]);
		}
		int currVal[] = new int[V + 1];
		System.arraycopy(val, 0, currVal, 0, V + 1);
		int from[] = new int[E];
		int to[] = new int[E];
		while(E-->0) {
			from[E] = s1.nextInt();
			to[E] = s1.nextInt();
		}
		adj = packU(V, from, to , 1);
		start = new int[V + 1];
		end = new int[V + 1];
		eulerTour = new int[2 * (V + 1)];
		level = new int[V + 1];
		marked = new boolean[V + 1];
		DP = new int[log(V) + 1][V + 1];
		parent = new int[V + 1];
		blockCache = new int[2 * (V + 1)];
		dfs(1 , 0, 0);
		binaryLift();
/*
		out.println("start " + Arrays.toString(start));
		out.println("end " + Arrays.toString(end));
		out.println("eulerTour " + Arrays.toString(eulerTour));

*/

		while(Q-->0) {
			if(s1.nextInt() == 1) { // Query
				int u = s1.nextInt();
				int v = s1.nextInt();
				Query q;
				if(end[u] < start[v]) 	// Cousin Nodes
					q = new Query(end[u], start[v], uSZ, LCA(u, v), qSZ);
				else if(start[u] > end[v])
					q = new Query(end[v], start[u], uSZ, LCA(u, v), qSZ);
				else 			// Ancestors
					q = new Query(Math.min(start[u],start[v]), Math.max(start[u],start[v]), uSZ, -1, qSZ);

				queries[qSZ++] = q;
			}
			else {
				int idx = s1.nextInt();
				int newVal = s1.nextInt();
				if(!map.containsKey(newVal))
					map.put(newVal, map.size());
				newVal = map.get(newVal);
				updates[uSZ++] = new Update(idx, newVal, currVal[idx]);
				currVal[idx] = newVal;
			}
		}
		
		freq = new int[map.size()];
		
		// BLOCK_SIZE = (int) (Math.sqrt(2*V) + 1);
		// BLOCK_SIZE = (int) (Math.sqrt(eulerTour.length) + 1);
		
		BLOCK_SIZE = (int) (Math.pow(2*V, 2.0 / 3.0) + 1);
		
		for(int i=0;i<blockCache.length;i++)
			blockCache[i] = i / BLOCK_SIZE;
		
		Arrays.sort(queries, 0, qSZ, new MoComparator());
		
		int ans[] = new int[qSZ];
		int moLeft = -1 , moRight = -1;
		int currUpd = 0;
		for(int i=0;i<qSZ;i++) {
			Query q = queries[i];
			while(currUpd < q.numUpdatesLess) {
				Update u = updates[currUpd];
				update(u.idx, u.newVal);
				currUpd++;
			}
			while(currUpd > q.numUpdatesLess) {
				Update u = updates[currUpd - 1];
				update(u.idx, u.prevVal);
				currUpd--;
			}
			while(moLeft < q.L - 1) {
				moLeft++;
				visit(eulerTour[moLeft]);
			}
			while(moLeft >= q.L) {
				visit(eulerTour[moLeft]);
				moLeft--;
			}
			while(moRight < q.R) {
				moRight++;
				visit(eulerTour[moRight]);
			}
			while(moRight > q.R) {
				visit(eulerTour[moRight]);
				moRight--;
			}

			if(q.LCA != -1) visit(q.LCA);
			ans[q.id] = distinctCount;
			if(q.LCA != -1) visit(q.LCA);

		}

		for(int a : ans)
			out.println(a);
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

/*	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
		solve(in, out);
		in.close();
		out.close();
	}
	*/
	static String outputFile = "DISTNUM3_MO.txt";
	public static void main(String[] args) throws InterruptedException  {
		
		Thread t = new Thread(null, new Runnable() {
			public void run() {
				try {
					new DISTNUM3_MO().run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "Increase Stack", 1 << 25);
		t.setName("DISTNUM_MO");
		t.start();
		t.join();
		
	}
	void run() throws Exception{	
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
		solve(in, out);
		in.close();
		out.close();
		
	/*	FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();*/
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