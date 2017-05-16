import java.util.*;
import java.io.*;
public class MonkandTreeCounting
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer> merge(ArrayList<Integer> arl1 , ArrayList<Integer> arl2) {
		
		int sz1 = arl1.size();
		int sz2 = arl2.size();
		int i = 0 , j = 0;
		ArrayList<Integer> mergeArl = new ArrayList<>();
		while(i < sz1 || j < sz2) {
			if(i == sz1)
				mergeArl.add(arl2.get(j++));
			else if(j == sz2)
				mergeArl.add(arl1.get(i++));
			else
				mergeArl.add(arl1.get(i) < arl2.get(j) ? arl1.get(i++) : arl2.get(j++));
		}
		
		return mergeArl;
	}
	
	
	static ArrayList<Integer>[] child , adj;
	static int A[] , K;
	static int dfs(int u) {
		int cnt = 0;
		for(int v : adj[u])
			cnt += dfs(v);
		
		ArrayList<Integer> merger = new ArrayList<>();
		for(int v : adj[u]) {
			merger = merge(merger, child[v]);
			child[v] = null;
		}
		
		for(int i=0 , sz = merger.size();i < sz;i++) {
			int lb = lowerBound(merger, K - A[u] - merger.get(i));
			cnt += (sz - lb) - (lb <= i ? 1 : 0);  
		}
		
		merger.add(lowerBound(merger, A[u]), A[u]);
		child[u] = merger;
		
		// System.out.println("u = " + u + child[u]);
		
		return cnt;
		
	}
	
	static int lowerBound(ArrayList<Integer> arl , int key) {
		int lo = 0 , hi = arl.size() - 1;
		int lb = arl.size();
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key <= arl.get(mid)) {
				lb = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return lb;
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		K = s1.nextInt();
		A = s1.nextIntArrayOneBased(N);
		
		adj = new ArrayList[N + 1];
		child = new ArrayList[N + 1];
		for(int i=1;i<=N;i++) {
			adj[i] = new ArrayList<>();
			child[i] = new ArrayList<>();
		}
		
		for(int i=2;i<=N;i++) 
			adj[s1.nextInt()].add(i);
		
		out.println(dfs(1) / 2);
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