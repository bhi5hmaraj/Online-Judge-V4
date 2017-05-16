/************************	      ௳  		   ************************/
import java.util.*;
import java.io.*;
public class SCCStanford
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



	private static void solve(FastScanner s1, PrintWriter out){

		int V = 875714;
		String line = null;
		ArrayList<Integer>[] adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();
		while((line = s1.nextLine()) != null) {
			String split[] = line.split(" ");
			adj[Integer.parseInt(split[0])].add(Integer.parseInt(split[1]));
		}

		SCC scc = new SCC(adj, true);
		int cnt[] = new int[scc.numberOfComponents()];
		for(int i=1;i<=V;i++)
			cnt[scc.group[i]]++;

		Arrays.sort(cnt);
		for(int i=0;i<Math.min(5,cnt.length);i++)	// get 5 largest SCCs
			out.print(i == 0 ? cnt[cnt.length - 1 - i] : "," + cnt[cnt.length - 1 - i]);
	}

	// Answer = 434821,968,459,313,211

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/
	/*	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("SCC_input.txt"));
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    */
	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new SCCStanford().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}
	// Time : 9.179989097 (original)
	void run(){	
		try (FastScanner in = new FastScanner(/*new FileInputStream("SCC_input.txt")*/System.in) ;
				PrintWriter out = 
						new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false)) {
			long start = System.nanoTime();
			solve(in, out);
			long stop = System.nanoTime();
			System.out.println("Time : " + ((stop - start) / 1e9));
			in.close();
			out.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	static class FastScanner implements AutoCloseable{
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
		public void close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}