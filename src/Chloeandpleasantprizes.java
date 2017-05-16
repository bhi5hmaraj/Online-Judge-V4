import java.util.*;
import java.io.*;
public class Chloeandpleasantprizes
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static long a[];
	static long maxInSub[];

	static long findMaxInSub(int u , int par) {
		maxInSub[u] = a[u];
		for(int v : adj[u])
			if(v != par)
				maxInSub[u] = Math.max(maxInSub[u],findMaxInSub(v, u));
		return maxInSub[u];
	}

	static long dfs(int u , int par) {
		for(int v : adj[u])
			if(v != par)
				a[u] += dfs(v, u);
		return a[u];
	}

	static long max = Long.MIN_VALUE;

	static void findOpt(int u , int par , long maxOther) {

		// System.out.println("u " + u + " par " + par + " maxOther " + maxOther);

		if(par != -1)
			max = (maxOther == Long.MIN_VALUE) ? max : Math.max(max,a[u] + maxOther);

		if((adj[u].size() <= 2 && par != -1) || (par == -1 && adj[u].size() == 1)) { 
			for(int v : adj[u])
				if(v != par)
					findOpt(v, u, maxOther);
		}
		else { // Atleast 2 children
			/*			
 			TreeMap<Long , Integer> multiSet = new TreeMap<>();
			for(int v : adj[u])
				if(v != par)
					multiSet.put(maxInSub[v], multiSet.getOrDefault(maxInSub[v], 0) + 1);

			for(int v : adj[u])
				if(v != par) {
					int freq = multiSet.get(maxInSub[v]);
					if(freq > 1)
						multiSet.put(maxInSub[v], freq - 1);
					else
						multiSet.remove(maxInSub[v]);

					findOpt(v, u, Math.max(maxOther,multiSet.lastKey()));
					multiSet.put(maxInSub[v], multiSet.getOrDefault(maxInSub[v], 0) + 1);
				}
			 */
			long big    = Long.MIN_VALUE;
			long secBig = Long.MIN_VALUE;
			int bigVertex = -1;
			for(int v : adj[u])
				if(v != par && maxInSub[v] > big) {
					big = maxInSub[v];
					bigVertex = v;
				}
			for(int v : adj[u])
				if(v != par && v != bigVertex && maxInSub[v] > secBig) 
					secBig = maxInSub[v];

			for(int v : adj[u])
				if(v != par) {
					if(v == bigVertex)
						findOpt(v, u, Math.max(maxOther,secBig));
					else
						findOpt(v, u, Math.max(maxOther,big));
				}
		}
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		a = s1.nextLongArrayOneBased(N);
		int E = N - 1;
		adj = new ArrayList[N + 1];
		for(int i=1;i<=N;i++) adj[i] = new ArrayList<>();
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		maxInSub = new long[N + 1];

		dfs(1, -1);
		findMaxInSub(1, -1);
		findOpt(1, -1, Long.MIN_VALUE);

		out.println(max == Long.MIN_VALUE ? "Impossible" : max);
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