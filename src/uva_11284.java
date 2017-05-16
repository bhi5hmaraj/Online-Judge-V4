import java.util.*;
import java.io.*;
public class uva_11284
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Edge>[] adj;

	static class Edge implements Comparable<Edge> {
		int v;
		int cost;

		Edge(int v, int cost) {
			this.v = v;
			this.cost = cost;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.cost, o.cost);
		}
	}

	private static int[] dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		pq.add(new Edge(start, 0));
		int distTo[] = new int[V];
		boolean marked[] = new boolean[V];
		while (!pq.isEmpty()) {
			Edge min = pq.remove();
			int u = min.v;
			if(!marked[u]){
				marked[u] = true;
				distTo[u] = min.cost;
				for (Edge e : adj[u])
					if (!marked[e.v])
						pq.add(new Edge(e.v, e.cost + distTo[u]));
			}
		}
		
		return distTo;
	}

	static int memo[][];
	static int cost[][];
	static int V , P;
	static final int INF = (int)1e7;
	static int city[];
	static int TSP(int mask , int prev) {
		
		
		if(memo[mask][prev] != -1)
			return memo[mask][prev];
		else {
			int dist = INF;
			int newMask = mask;
			for(int i=1;i < P;i++)
				if((newMask & (1 << i)) != 0)
					newMask = (city[i] == city[prev]) ? (newMask ^ (1 << i)) : newMask;
			
			for(int i = 0;i < P;i++)
				if((i != prev && (newMask & (1 << i)) != 0))
					dist = Math.min(dist,cost[city[prev]][city[i]] + TSP(newMask, i));
			
			return memo[mask][prev] = dist;
		}
		
	}
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			s1.nextLine();
			V = s1.nextInt() + 1;
			int E = s1.nextInt();
			adj = new ArrayList[V];
			for(int i=0;i<V;i++)
				adj[i] = new ArrayList<>();
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				int wt = (int) Math.round(s1.nextDouble() * 100.0);
				adj[u].add(new Edge(v, wt));
				adj[v].add(new Edge(u, wt));
			}
			cost = new int[V][];
			for(int i=0;i<V;i++)
				cost[i] = dijkstra(i);
			
			/*for(int i=0;i<V;i++)
				out.println(Arrays.toString(cost[i]));*/
			
			P = s1.nextInt() + 1; // We start from the house 
			city = new int[P];
			int saving[] = new int[P];
			for(int i=1;i<P;i++) {
				city[i] = s1.nextInt();
				saving[i] = (int)(Math.round(s1.nextDouble() * 100.0));
			}
			
			memo = new int[1 << P][P];
			for(int i=0;i<memo.length;i++)
				Arrays.fill(memo[i], -1);
			for(int i=1;i<P;i++)
				memo[1 << i][i] = INF;
			
			memo[1][0] = 0;
			
			int maxSaving = 0;
			for(int i=2;i<memo.length;i++) {
				int currSaving = 0;
				for(int j=1;j < P;j++) {
					if((i & (1 << j)) != 0)
						currSaving += saving[j];
				}
				for(int j=1;j < P;j++) {
					if((i & (1 << j)) != 0) {
						
						int total = (TSP(i, j) + cost[city[j]][0]);
						// out.println("mask " + Integer.toBinaryString(i) + " prev " + j + "RTT " + total);
						maxSaving = Math.max(maxSaving,Math.max(currSaving - total,0));
					}
				}
			}
			/*
			for(int i=0;i<memo.length;i++)
				out.println(i + "-->" + Arrays.toString(memo[i]));
			*/
			if(maxSaving == 0)
				out.println("Don't leave the house");
			else
				out.printf("Daniel can save $%.2f\n" , (maxSaving / 100.0));
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}