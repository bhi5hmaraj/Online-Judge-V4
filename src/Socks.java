import java.util.*;
import java.io.*;
public class Socks
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static HashSet<Integer>[] adj;
	static HashMap<Integer , Integer> colorFreq;
	static int color[];
	static boolean marked[];
	
	static int dfs(int u) {
		marked[u] = true;
		int cnt = 1;
		colorFreq.put(color[u], colorFreq.getOrDefault(color[u], 0) + 1);
		for(int v : adj[u])
			if(!marked[v])
				cnt += dfs(v);
		return cnt;
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int V = s1.nextInt();
		int E = s1.nextInt();
		int K = s1.nextInt();
		color = s1.nextIntArrayOneBased(V);
		adj = new HashSet[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new HashSet<>();
		marked = new boolean[V + 1];
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		int toColor = 0;
		for(int i=1;i<=V;i++) {
			if(!marked[i]) {
				colorFreq = new HashMap<>();
				int size = dfs(i);
				int max = 0;
				for(Map.Entry<Integer, Integer> e : colorFreq.entrySet())
					max = Math.max(max,e.getValue());
				toColor += size - max;
			}
		}
		
		out.println(toColor);
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