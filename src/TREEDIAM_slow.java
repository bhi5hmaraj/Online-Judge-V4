import java.util.*;
import java.io.*;
class TREEDIAM_slow
{


	/************************ SOLUTION STARTS HERE ***********************/

	static HashSet<Integer>[] adj;

	static boolean marked[] , temp[];
	static long W[];
	static long diameter;
	static int V;
	static void brute(int u){

		marked[u] = true;
		temp = new boolean[V + 1];
		diameter = Math.max(diameter,dfs(u, W[u]));
		for(int v:adj[u]){
			if(!marked[v])
				brute(v);
		}
	}

	static long dfs(int u , long curr){

		long max = curr;
		temp[u] = true;
		for(int v:adj[u])
			if(!temp[v])
				max = Math.max(max,dfs(v, curr + W[v]));

		return max;

	}
	static long mod = (long)(1e9) + 7;
	static long findProduct() {

		long prod = 1;
		marked = new boolean[V + 1];
		for(int i=1;i<=V;i++){
			if(!marked[i]){
				diameter = 0;
				brute(i);
				prod = ( (prod % mod) * (diameter % mod)) % mod;
			}
		}
		return prod;
	}


	private static void solve(FastScanner s1, PrintWriter out){

		V = s1.nextInt();
		W = s1.nextLongArrayOneBased(V);
		adj = new HashSet[V + 1];

		for(int i=1;i<=V;i++)
			adj[i] = new HashSet<>();

		int edge[][] = new int[V][];
		for(int i=1;i<V;i++){
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
			edge[i] = new int[]{u,v};
		}

		out.println(findProduct());
		for(int i=1;i<=V-1;i++){
			int idx = s1.nextInt();
			int u = edge[idx][0];
			int v = edge[idx][1];
			adj[u].remove(v);
			adj[v].remove(u);
			out.println(findProduct());
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