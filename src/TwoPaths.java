import java.util.*;
import java.io.*;
public class TwoPaths
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int depth[] , parent[];
	static ArrayList<Integer> [] adj;

	static void dfs(int u , int par) {
		parent[u] = par;
		for(int v : adj[u])
			if(v != par)
				dfs(v, u);
	}



	static int findMaxLengthPath(int u , int par) {
		depth[u] = 0;
		int maxLen = 0;
		for(int v : adj[u])
			if(v != par) {
				maxLen = Math.max(maxLen,findMaxLengthPath(v, u));
				depth[u] = Math.max(depth[u],depth[v] + 1);
			}

		int maxDepthChild = -1;
		int maxDepth = 0;
		for(int v : adj[u])
			if(v != par && depth[v] >= maxDepth) {
				maxDepth = depth[v];
				maxDepthChild = v;
			}

		int secMaxDepthChild = -1;
		int secMaxDepth = 0;
		for(int v : adj[u])
			if(v != par && v != maxDepthChild && depth[v] >= secMaxDepth) {
				secMaxDepth = depth[v];
				secMaxDepthChild = v;
			}


		maxLen = Math.max(maxLen,maxDepth + (maxDepthChild > 0 ? 1 : 0) + secMaxDepth + (secMaxDepthChild > 0 ? 1 : 0));

		return maxLen;
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = V - 1;
		adj = new ArrayList[V + 1];
		depth = new int[V + 1];
		parent = new int[V + 1];
		for(int i=1;i<=V;i++) adj[i] = new ArrayList<>();

		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		int maxProfit = 0;
		for(int i=1;i<=V;i++) {
			dfs(i, 0);
			for(int j= i + 1;j<=V;j++) {

				ArrayList<Integer> path = new ArrayList<>();
				int curr = j;
				while(curr != i) {
					path.add(curr);
					curr = parent[curr];
				}
				path.add(i);
				// out.println("Path from " + j + " to " + i + path);
				int len1 = path.size() - 1;
				for(int k = 0, sz = path.size();k < sz;k++) {
					int left = k == 0 ? -1 : path.get(k - 1);
					int right = k == sz - 1 ? -1 : path.get(k + 1);
					for(int v : adj[path.get(k)])
						if(v != left && v != right) 
							maxProfit = Math.max(maxProfit,len1 * findMaxLengthPath(v, path.get(k)));
				}
			}
		}

		out.println(maxProfit);

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