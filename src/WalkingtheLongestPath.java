import java.util.*;
import java.io.*;
public class WalkingtheLongestPath
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static boolean DP[][];
	static int pos[];
	static int level[];
	static int V;
	static int parent[];
	static boolean marked[];
	static void dfs(int u , int len , int par) {
		marked[u] = true;
		level[u] = len;
		parent[u] = par;
		for(int v : adj[u])
			if(!marked[v])
				dfs(v,len + 1,u);
	}
	static String hamiltonianTour(int mask , int u , int len) {
		if(!DP[mask][u]) {
			DP[mask][u] = true;
			pos[len] = u;
			if(len == V) {
				StringBuilder sb = new StringBuilder();
				for(int i=1;i<=V;i++)
					sb.append(pos[i] + " ");

				return sb.toString();
			}
			String res = null;
			for(int v : adj[u])
				if((mask & (1 << v)) == 0 && (res = hamiltonianTour(mask | (1 << v), v , len + 1)) != null)  
					return res;

			return res;
		}
		return null;
	}


	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		int E = s1.nextInt();
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();

		while(E-->0){
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		if(V <= 22) {
			DP      = new boolean[1 << (V + 1)][V + 1];
			pos 	= new int[V + 1];
			String res;
			for(int i=1;i<=V;i++)
				if((res = hamiltonianTour(1 << i, i , 1)) != null) {
					out.println(V);
					out.println(res);
					return;
				}
		}
		else {
			StringBuilder sb = null;
			int maxLen = 0;
			double time = 0;
			for(int i=1;i<=V;i++) {
				int maxPos = 1;
				long start = System.nanoTime();
				marked = new boolean[V + 1];
				parent = new int[V + 1];
				level  = new int[V + 1];
				dfs(i, 1, -1);
				for(int j=1;j<=V;j++)
					maxPos = level[j] > level[maxPos] ? j : maxPos;
				
				if(level[maxPos] > maxLen) {
					maxLen = level[maxPos];
					sb = new StringBuilder();
					while(maxPos != -1) {
						sb.append(maxPos + " ");
						maxPos = parent[maxPos];
					}
				}
				long stop = System.nanoTime();
				time += ((stop - start) / 1e9);
				if(time >= 3.95) {  // Cutoff time , to escape TLE
					out.println(maxLen);
					out.println(sb.toString());
					return;
				}
				
			}
			out.println(maxLen);
			out.println(sb.toString());
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