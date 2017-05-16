import java.util.*;
import java.io.*;
class DISTNUM3
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer> adj[];
	static int DP[][]; // One based vertices
	static int level[];
	static int parent[];
	static int val[];
	static int V;
	static HashMap<Integer , Integer> map ;
	static void dfs(int u , int par , int lev) {
		parent[u] = par;
		level[u] = lev;
		for(int v : adj[u])
			if(v != par)
				dfs(v, u , lev + 1);
	}
	static int log(int N){
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	static void buildSparse() {
		
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
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		V = s1.nextInt();
		int E = V - 1;
		int Q = s1.nextInt();
		val = new int[V + 1];
		map = new HashMap<>();
		for(int i=1;i<=V;i++) {
			int num = s1.nextInt();
			if(!map.containsKey(num))
				map.put(num, map.size());
			val[i] = map.get(num);
		}

		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		level = new int[V + 1];
		DP = new int[log(V) + 1][V + 1];
		parent = new int[V + 1];
		
		dfs(1 , 0, 1);
		buildSparse();
		
		while(Q-->0) {
			if(1 == s1.nextInt()){	//out.println(countDistinct(s1.nextInt(), s1.nextInt()));
				int ans = countDistinct(s1.nextInt(), s1.nextInt());
			}
			else {
				int v = s1.nextInt();
				int y = s1.nextInt();
				if(!map.containsKey(y))
					map.put(y, map.size());
				val[v] = map.get(y);
			}
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
/*	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}   */ 
	static String outputFile = "DISTNUM3_CORRECT.txt";
  
	public static void main(String[] args) throws InterruptedException  {
		Thread t = new Thread(null, new Runnable() {
			public void run() {
				try {
					new DISTNUM3().run();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}, "Increase Stack", 1 << 25);
		t.setName("DISTNUM3");
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
		/*
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
		*/
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