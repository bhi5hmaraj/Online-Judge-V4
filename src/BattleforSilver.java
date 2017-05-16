import java.util.*;
import java.io.*;
public class BattleforSilver
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static BitSet[] adj;
	static int V;
	static int cost[];
	static ArrayList<Integer>[] graph;
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line = null;
		while((line = s1.nextLine()) != null) {
			
			String sp[] = line.split(" ");
			V = Integer.parseInt(sp[0]);
			int E = Integer.parseInt(sp[1]);
			adj = new BitSet[V + 1];
			graph = new ArrayList[V + 1];
			for(int i=1;i<=V;i++) {
				adj[i] = new BitSet(V + 1);
				graph[i] = new ArrayList<>();
			}
			
			cost = s1.nextIntArrayOneBased(V);
			while(E-->0) {
				int u = s1.nextInt();
				int v = s1.nextInt();
				adj[u].set(v);
				adj[v].set(u);
				graph[u].add(v);
				graph[v].add(u);
			}
			
			int max = 0;
			for(int i=1;i<=V;i++) {
				
				for(int v : graph[i])
					max = Math.max(max,cost[i] + cost[v]);
				
				int sz = graph[i].size();
				for(int j=0;j<sz;j++) {
					int u = graph[i].get(j);
					for(int k=j+1;k<sz;k++) {
						int v = graph[i].get(k);
						if(adj[u].get(v))
							max = Math.max(max,cost[i] + cost[u] + cost[v]);
					}
				}
				
				for(int j=0;j<sz;j++) {
					int u = graph[i].get(j);
					for(int k=j+1;k<sz;k++) {
						int v = graph[i].get(k);
						for(int l=k+1;l<sz;l++) {
							int w = graph[i].get(l);
							if(adj[u].get(w) && adj[u].get(v) && adj[v].get(w))
								max = Math.max(max,cost[i] + cost[u] + cost[v] + cost[w]);
						}
					}
				}
				
				
			}
			
			System.out.println(max);
			
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