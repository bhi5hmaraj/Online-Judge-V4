import java.util.*;
import java.io.*;
public class Journey
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final int INF = (int)(1.1e9);
	static int memo[][];
	static ArrayList<Edge> adj[];
	
	static class Edge {
		int v , cost;
		Edge(int v , int cost) {
			this.v = v;
			this.cost = cost;
		}
	}
	
	static int findOpt(int u , int cityCnt) {		
		if(memo[u][cityCnt] != -1)
			return memo[u][cityCnt];
		else if(cityCnt == 0)
			return INF;
		else {
			int min = INF;
			for(Edge e : adj[u])
				min = Math.min(min,findOpt(e.v, cityCnt - 1) + e.cost);
			return memo[u][cityCnt] = min;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int M = s1.nextInt();
		int T = s1.nextInt();
		adj = new ArrayList[N + 1];
		memo = new int[N + 1][N];
		for(int i=1;i<=N;i++)
			Arrays.fill(memo[i], -1);
		Arrays.fill(memo[1], INF);
		memo[1][0] = 0;
		for(int i=1;i<=N;i++)
			adj[i] = new ArrayList<>();
		while(M-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[v].add(new Edge(u, s1.nextInt()));
		}
		int maxCityCnt = 0;
		for(int i=0;i<N;i++)
			if(findOpt(N, i) <= T)
				maxCityCnt = i;
		// System.out.println("max city visit " + maxCityCnt);
		ArrayDeque<Integer> stack = new ArrayDeque<>();
		int curr = N;
		while(curr != 1) {
			stack.push(curr);
			for(Edge e : adj[curr])
				if(memo[e.v][maxCityCnt - 1] + e.cost == memo[curr][maxCityCnt]) {
					curr = e.v;
					maxCityCnt--;
					break;
				}
		}
		stack.push(1);
		out.println(stack.size());
		for(int a : stack)
			out.print(a + " ");
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