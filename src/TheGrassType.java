import java.util.*;
import java.io.*;
public class TheGrassType
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static HashMap<Integer , Integer> freq[];
	static ArrayList<Integer>[] adj;
	static int A[];
	static int size[]; // Stores the size of the subtree
	
	static long dfs(int u , int par) {
		long cnt = 0;
		size[u] = 1;
		int maxPos = -1;
		for(int v : adj[u])
			if(v != par) {
				cnt += dfs(v, u);
				size[u] += size[v];
			}
		
		for(int v : adj[u])	 // Find the sub tree with largest size
			if(v != par)
				maxPos = maxPos == -1 ? v : size[v] > size[maxPos] ? v : maxPos;
				
		if(maxPos == -1) { // Leaf node
			freq[u] = new HashMap<>();
			freq[u].put(A[u], 1);
			return 0;
		}
		else {
			freq[u] = freq[maxPos];
			for(int v : adj[u])
				if(v != par && v != maxPos) {
					for(Map.Entry<Integer, Integer> e : freq[v].entrySet()) {
						int num = e.getKey();
						if(A[u] % num == 0)
							cnt += (freq[u].getOrDefault(A[u] / num, 0) * e.getValue() * 1L);
					}
					
					for(Map.Entry<Integer, Integer> e : freq[v].entrySet()) 
						freq[u].put(e.getKey(), freq[u].getOrDefault(e.getKey(), 0) + e.getValue());
				}
			
			cnt += freq[u].getOrDefault(1, 0);
			freq[u].put(A[u], freq[u].getOrDefault(A[u], 0) + 1);
			
			return cnt;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int E = N - 1;
		adj  = new ArrayList[N + 1];
		freq = new HashMap[N + 1];
		size = new int[N + 1];
		for(int i=1;i<=N;i++)
			adj[i] = new ArrayList<>();
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		A = s1.nextIntArrayOneBased(N);
		
		out.println(dfs(1, 0));
		
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