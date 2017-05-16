import java.util.*;
import java.io.*;
public class uva_247
{


	/************************ SOLUTION STARTS HERE ***********************/


	static class SCC {

		/*
		 * Identify sinks (reverse post order in inverse graph)
		 * Start normal dfs from the above order , the resulting components form SCC)
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

		@SuppressWarnings("unchecked")
		SCC(Iterable<Integer>[] adj , boolean oneBased) {
			int st = oneBased ? 1 : 0;
			int V = adj.length - st; 
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
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int V;
		int T = 0;
		ArrayList<StringBuilder> dump = new ArrayList<>();
		while((V = s1.nextInt()) != 0) {
			T++;
			int E = s1.nextInt();
			StringBuilder sb = new StringBuilder();
/*			if(E == 0) {
				out.println("Calling circles for data set " + T +":");
				continue;
			}*/
			String people[] = new String[V];
			HashMap<String , Integer> map = new HashMap<>();
			HashSet<Integer>[] adj = new HashSet[V];
			for(int i=0;i<V;i++)
				adj[i] = new HashSet<>();

			while(E-->0) {
				String u = s1.next();
				String v = s1.next();
				if(!map.containsKey(u)){ map.put(u, map.size()); people[map.get(u)] = u;}
				if(!map.containsKey(v)){ map.put(v, map.size()); people[map.get(v)] = v;}
				adj[map.get(u)].add(map.get(v));
			}

			SCC scc = new SCC(adj, false);
			int group[] = scc.getSCC();
			// System.out.println(Arrays.toString(group));
			ArrayList<String>[] comp = new ArrayList[scc.numberOfComponents()];
			for(int i=0;i<comp.length;i++)
				comp[i] = new ArrayList<>();

			for(int i=0;i<V;i++)
				comp[group[i]].add(people[i]);

			sb.append("Calling circles for data set " + T +":\n");

			for(int i=comp.length - 1;i>=0;i--) {
				boolean first = true;
				for(String s : comp[i]) {
					if(s != null) {
						sb.append((first ? "" : ", ") + s);
						first = false;
					}
				}
				sb.append("\n");
			}
			dump.add(sb);
		}
		for(int i=0,end=dump.size()-1;i < end;i++)
			out.println(dump.get(i));
		out.print(dump.get(dump.size() - 1));
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