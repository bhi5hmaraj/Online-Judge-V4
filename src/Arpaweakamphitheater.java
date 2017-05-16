import java.util.*;
import java.io.*;
public class Arpaweakamphitheater
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] component;
	static boolean marked[];
	static int w[] , b[];
	static void dfs(int u , int id) {
		marked[u] = true;
		component[id].add(u);
		for(int v : adj[u])
			if(!marked[v])
				dfs(v, id);
	}
	
	static int MAX_W;
	static int MAX_N;
	static int memo[][];
	
	static final int INF = (int)(1e9) + 7;
	
	static int findOpt(int idx , int curr_w) {
		
		if(idx == MAX_N)
			return curr_w > MAX_W ? -INF : 0;
		else if(curr_w > MAX_W)
			return -INF;
		else if(component[idx].isEmpty())
			return memo[idx][curr_w] = findOpt(idx + 1, curr_w);
		else if(memo[idx][curr_w] != -1)
			return memo[idx][curr_w];
		else {
			int max = -INF;
			max = Math.max(max,findOpt(idx + 1, curr_w));
			int total_beauty = 0;
			int total_weight = 0;
			for(int c : component[idx]) {
				total_beauty += b[c];
				total_weight += w[c];
				max = Math.max(max,b[c] + findOpt(idx + 1, curr_w + w[c]));
			}
			max = Math.max(max,total_beauty + findOpt(idx + 1, curr_w + total_weight));
			
			return memo[idx][curr_w] = max;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		MAX_N  = s1.nextInt();
		int E = s1.nextInt();
		MAX_W = s1.nextInt();
		
		w = s1.nextIntArrayOneBased(MAX_N);
		b = s1.nextIntArrayOneBased(MAX_N);
		
		adj = new ArrayList[MAX_N + 1];
		component = new ArrayList[MAX_N + 1];
		
		for(int i = 0;i <= MAX_N;i++) {
			adj[i] = new ArrayList<>();
			component[i] = new ArrayList<>();
		}
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		memo = new int[MAX_N][MAX_W + 1];
		for(int a[] : memo)
			Arrays.fill(a, -1);
		
		marked = new boolean[MAX_N + 1];
		int id = 0;
		for(int i=1;i<=MAX_N;i++)
			if(!marked[i])
				dfs(i, id++);
		
		out.println(Math.max(0,findOpt(0, 0)));
			
		
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