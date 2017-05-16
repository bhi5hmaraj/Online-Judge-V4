import java.util.*;
import java.io.*;
public class DijkstraStanfordCourse {


	/************************ SOLUTION STARTS HERE ***********************/
	private static boolean marked[]; // Part of dijkstra
	private static long distTo[];
	private static ArrayList<Edge>[] adj;

	static class Edge implements Comparable<Edge> {
		int v;
		long cost;

		Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
	}

	private static void dijkstra(int start , int V) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		Arrays.fill(distTo, -1);
		while (V > 0) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				V--;
				for (Edge e : adj[u])
					if (!marked[e.v])
						pq.add(new Edge(e.v, e.cost + distTo[u]));
			}
		}
	}


	// Answer : 2599,2610,2947,2052,2367,2399,2029,2442,2505,3068
	private static void solve(FastScanner s1, PrintWriter out){

		int V = 200;
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();

		marked = new boolean[V + 1];
		distTo = new long[V + 1];

		for(int i=1;i<=V;i++) {
			StringTokenizer st = new StringTokenizer(s1.nextLine());
			st.nextToken();
			while(st.hasMoreTokens()) {
				String split[] = st.nextToken().split(",");
				adj[i].add(new Edge(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
			}
		}

		dijkstra(1, V);	

		final int INF = 1000000;
		int dest[] = {7,37,59,82,99,115,133,165,188,197};
		for(int i=0;i<dest.length;i++)
			out.print((i == 0 ? "" : ",") + (distTo[dest[i]] > 0 ? distTo[dest[i]] : INF));
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("dijkstraData.txt"));
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