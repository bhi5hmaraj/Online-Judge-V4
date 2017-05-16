import java.util.*;
import java.io.*;
public class uva_10801
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Edge implements Comparable<Edge> {
		int u , v , lift;
		int cost;
		Edge(int u , int v, int cost , int lift) {
			this.u = u;
			this.v = v;
			this.cost = cost;
			this.lift = lift;

		}
		Edge(int v , int cost , int lift) {
			this(-1 , v , cost , lift);
		}
		@Override
		public int compareTo(Edge o) {
			return Long.compare(this.cost, o.cost);
		}
		@Override
		public String toString() {
			return String.format("floor = %d lift = %d cost = %d", v , lift , cost);
		}
	}

	static final int INF = (int)(1e6);
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line;
		while((line = s1.nextLine()) != null) {
			int in[] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			int N = in[0];
			int K = in[1];
			int cost[] = s1.nextIntArrayOneBased(N);
			ArrayList<Edge> adj[][] = new ArrayList[100][N + 1]; // 100 floors;
			
			for(int i=0;i<100;i++)
				for(int j=1;j<=N;j++)
					adj[i][j] = new ArrayList<>();
			
			for(int i=1;i<100;i++) 
				for(int j=1;j<=N;j++)
					for(int k=j+1;k<=N;k++) {
						adj[i][j].add(new Edge(i, 60, k));
						adj[i][k].add(new Edge(i, 60, j));
					}
			
			for(int i=1;i<=N;i++) {
				int floors[] = Arrays.stream(s1.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
				for(int j=0;j<floors.length - 1;j++) {
					int totalCost = cost[i] * (floors[j + 1] - floors[j]);
					adj[floors[j]][i].add(new Edge(floors[j + 1], totalCost, i));
					adj[floors[j + 1]][i].add(new Edge(floors[j], totalCost, i));
				}
			}
			
			

			boolean marked[][] = new boolean[100][N + 1];
			int distTo[][] = new int[100][N + 1];
			
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for(int i=1;i<=N;i++)
				pq.add(new Edge(0, 0, i));
			
			for(int a[] : distTo)
				Arrays.fill(a, INF);
			
			while(!pq.isEmpty()) {
				Edge min = pq.remove();
				// out.println(min);
				if(!marked[min.v][min.lift]) {
					marked[min.v][min.lift] = true;
					distTo[min.v][min.lift] = min.cost;
					for(Edge e : adj[min.v][min.lift]) {
						if(!marked[e.v][e.lift])
							pq.add(new Edge(e.v, min.cost + e.cost, e.lift));
					}
				}
			}
			
			int min = INF;
			for(int i=1;i<=N;i++)
				min = Math.min(min,distTo[K][i]);
			
			out.println(min == INF ? "IMPOSSIBLE" : min);
			
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