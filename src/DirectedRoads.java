import java.util.*;
import java.io.*;
public class DirectedRoads
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer>[] adj;
	static boolean marked[];
	static int comp[];
	static void dfs(int u , int id){
		marked[u] = true;
		comp[u] = id;
		for(int v:adj[u])
			if(!marked[v])
				dfs(v, id);
	}
	static class ModMath {
		static final long mod = (long) (1e9) + 7L; // Default
		static long sub(long a, long b) {return ((a % mod) - (b % mod) + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return ((a % mod) + (b % mod)) % mod;}
		static long div(long a, long b) {return mul(a, modInverse(b));}
		static long modInverse(long n) {return modPow(n, mod - 2L);} // Fermat's little theorem
		static long modPow(long a, long b) { // squared exponentiation
			if (b == 0L || a == 1L)
				return 1L;
			else if (b == 1L)
				return a;
			else {
				if ((b & 1L) == 0L) // Checking whether b is even (fast)
					return modPow((a * a) % mod, b >> 1);
				else
					return (a * modPow((a * a) % mod, b >> 1)) % mod;
			}
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArrayOneBased(N);
		
		adj = new ArrayList[N + 1];
		marked = new boolean[N + 1];
		comp = new int[N + 1];
		int vis[] = new int[N + 1];
		for(int i=1;i<=N;i++)
			adj[i] = new ArrayList<>();
		
		for(int i=1;i<=N;i++){
			adj[i].add(arr[i]);
			adj[arr[i]].add(i);
		}
		int id = 1;
		for(int i=1;i<=N;i++)
			if(!marked[i])
				dfs(i, id++);
		
		int size[] = new int[id];
		for(int i=1;i<=N;i++)
			size[comp[i]]++;
		
		boolean cycleMark[] = new boolean[id];
		long ans = 1;
		for(int i=1;i<=N;i++){
			if(!cycleMark[comp[i]]){
				cycleMark[comp[i]] = true;
				int curr = i;
				int compSize = size[comp[i]];
				int cycleSize = 0;
				
				vis[curr] = 1;
				while(true){
					if(vis[arr[curr]] != 0){
						cycleSize = vis[curr] - vis[arr[curr]] + 1;
						break;
					}
					else{
						vis[arr[curr]] = vis[curr] + 1;
						curr = arr[curr];
					}
						
				}
				// Not finding cycles in any of them is as same as (not in comp1) * (not in comp2) * .....
				// Number of subsets not forming cycles is total - forming cycles
				long nonCycle = ModMath.sub(ModMath.modPow(2, compSize), ModMath.modPow(2, compSize - cycleSize + 1));
				ans = ModMath.mul(ans, nonCycle);
			}
		}
		
		out.println(ans);
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