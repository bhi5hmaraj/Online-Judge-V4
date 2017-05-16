import java.util.*;
import java.io.*;
public class uva_11504
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class SCC {

		/*
		 * Kosaraju-Sharir Algorithm
		 * 
		 * Identify sinks (reverse post order in inverse graph)
		 * Start normal dfs from the above order , the resulting components form SCC
		 * 
		 * If you want to use 1 based indexing set onBased flag to true
		 * 
		 */

		private ArrayList<Integer>[] invGraph;
		private ArrayDeque<Integer> stack;
		private Iterable<Integer>[] adj;
		private int group[];
		private boolean marked[];
		private int numOfComponents;
		private int st , V;


		@SuppressWarnings("unchecked")
		SCC(Iterable<Integer>[] adj , boolean oneBased) {
			st = oneBased ? 1 : 0;
			V = adj.length - st; 
			group = new int[V + st];
			this.adj = adj;
			invGraph = new ArrayList[V + st];
			for(int i=st;i<V + st;i++)
				invGraph[i] = new ArrayList<>();
			for(int i=st;i<V + st;i++)
				for(int j : adj[i])
					invGraph[j].add(i);

			marked = new boolean[V + st];
			stack = new ArrayDeque<>();
			for(int i=st;i<V + st;i++)
				if(!marked[i])
					reversePostOrder(i);

			marked = new boolean[V + st];
			int grp = 0;
			for(int i : stack)
				if(!marked[i])
					dfs(i, grp++);

			numOfComponents = grp;
			stack = null;
		}

		private void reversePostOrder(int u) {
			marked[u] = true;
			for(int v : invGraph[u])
				if(!marked[v])
					reversePostOrder(v);
			stack.push(u);
		}

		private void dfs(int u , int grp) {
			marked[u] = true;
			group[u] = grp;
			for(int v : adj[u])
				if(!marked[v])
					dfs(v, grp);
		}
		public int[] getSCC() {
			return group;
		}
		public int numberOfComponents() {
			return numOfComponents;
		}

		@SuppressWarnings("unchecked")
		public HashSet<Integer>[] getDAG() {
			HashSet<Integer>[] dag = new HashSet[numOfComponents];
			for(int i=0;i<numOfComponents;i++)
				dag[i] = new HashSet<>();
			for(int i=st;i<V + st;i++)
				for(int v : adj[i])
					if(group[i] != group[v])
						dag[group[i]].add(group[v]);

			return dag;
		}
	}


	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			int V = s1.nextInt();
			int E = s1.nextInt();
			HashSet<Integer>[] adj = new HashSet[V + 1];
			for(int i=1;i<=V;i++) adj[i] = new HashSet<>();
			while(E-->0)
				adj[s1.nextInt()].add(s1.nextInt());
			SCC scc = new SCC(adj, true);
			HashSet<Integer>[] dag = scc.getDAG();
			int inDegree[] = new int[scc.numberOfComponents()];
			for(int i=0,comp = scc.numberOfComponents();i < comp;i++)
				for(int v : dag[i])
					inDegree[v]++;

			int cnt = 0;
			for(int deg : inDegree)
				cnt += deg == 0 ? 1 : 0;

			out.println(cnt);
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
	}    
	 */
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new uva_11504().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}

	void run(){	
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		try {
			solve(in, out);
		}
		catch(StackOverflowError overflowError) {
			out.println("Stack Overflow !!");
		}
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