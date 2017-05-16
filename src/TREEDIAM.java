import java.util.*;
import java.io.*;
class TREEDIAM
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
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
	static class DSU {
		private int parent[];
		private int size[];
		private int cnt;
		
		DSU(int length) {
			this.cnt = length;
			parent = new int[length + 10];
			size = new int[length + 10];
			Arrays.fill(size, 1);
			for (int i = 0; i < parent.length; i++)
				parent[i] = i;
		}

		int root(int p) {
			return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
		}

		int sizeOf(int p) {
			return size[root(p)];
		}

		boolean connected(int u, int v) {
			return root(u) == root(v);
		}

		int components() {
			return cnt;
		}

		void union(int u, int v) {
			if (!connected(u, v)) {
				cnt--;
				int rootU = root(u);
				int rootV = root(v);
				if (size[rootU] < size[rootV]) {
					parent[rootU] = rootV;
					size[rootV] += size[rootU];
				} else {
					parent[rootV] = rootU;
					size[rootU] += size[rootV];
				}
			}
		}
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long W[] = s1.nextLongArrayOneBased(N);
		int edge[][] = new int[N][];
		for(int i=1;i<N;i++)
			edge[i] = s1.nextIntArray(2);
		int order[] = s1.nextIntArray(N - 1);

		DSU dsu = new DSU(N);
		long diameter[] = new long[N + 5];
		long max[] = new long[N + 5];
		long ans[] = new long[N];
		long prod = 1;
		for(int i = 1;i <= N;i++){
			prod = ModMath.mul(prod, W[i]);
			diameter[i] = max[i] = W[i];
		}
		ans[N - 1] = prod;
		
		for(int i=N-2;i>=0;i--){
			int u = edge[order[i]][0];
			int v = edge[order[i]][1];
			long du = diameter[dsu.root(u)];
			long dv = diameter[dsu.root(v)];
			dsu.union(u, v);
			int root = dsu.root(u);
			diameter[root] = Math.max(Math.max(du,dv),max[u] + max[v]);
			long temp = max[u];
			max[u] = Math.max(max[u],W[u] + max[v]);
			max[v] = Math.max(max[v],W[v] + temp);
			prod = ModMath.mul(prod, ModMath.mul(diameter[root], ModMath.mul(ModMath.modInverse(du), ModMath.modInverse(dv))));
			System.out.println("max " + Arrays.toString(max));
			System.out.println("diameter " + Arrays.toString(diameter));
			ans[i] = prod;
		}
		
		for(long l : ans)
			out.println(l);
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