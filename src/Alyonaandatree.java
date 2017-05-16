import java.util.*;
import java.io.*;
public class Alyonaandatree
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class FenwickTree {  // In this variant of BIT each query is **NOT** a cumulative sum , it is the actual freq
		int tree[];
		int len;
		FenwickTree(int len) {
			this.len = len;
			tree = new int[len + 10];
		}
		void update(int idx , int val) {
			for(;idx <= len;idx += (idx & -idx))
				tree[idx] += val;
		}
		int query(int idx) {
			int sum = 0;
			for(;idx > 0;idx -= (idx & -idx))
				sum += tree[idx];

			return sum;
		}
		void update(int L , int R , int val) {
			if(L <= R && L > 0) {
				update(L, val);
				update(R + 1, -val);
			}
		}
	}

	static class Edge {
		int v;
		long cost;
		Edge(int vv , long ww) {
			v = vv;
			cost = ww;
		}
	}

	static ArrayList<Edge>[] adj;
	static long prefixSum[];
	static long A[];
	static int cnt[];
	static FenwickTree fenwickTree;
	
	static int ceil(int lo , int hi , long key) {
		int ceil = (int)1e6; // infinity
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key <= prefixSum[mid]) {
				ceil = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ceil;
	}

	static void dfs(int u , int level , long currSum) {
		prefixSum[level] = currSum;
		fenwickTree.update(ceil(1, level - 1, currSum - A[u]), level - 1, 1);

		for(Edge e : adj[u])
			dfs(e.v, level + 1, currSum + e.cost);
		
		cnt[u] = fenwickTree.query(level);
		fenwickTree.update(level, level, -cnt[u]);
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		A = s1.nextLongArrayOneBased(N);
		adj = new ArrayList[N + 1];
		for(int i=1;i<=N;i++)
			adj[i] = new ArrayList<>();

		for(int i=2;i<=N;i++) 
			adj[s1.nextInt()].add(new Edge(i, s1.nextLong()));

		prefixSum = new long[N + 1];
		cnt = new int[N + 1];
		fenwickTree = new FenwickTree(N);
		dfs(1, 1, 0);
		
		for(int i=1;i<=N;i++)
			out.print(cnt[i] + " ");
		
		// Arrays.stream(cnt).forEach(a -> out.print(a + " ")); // Adds 250 ms
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