import java.util.*;
import java.io.*;
public class DependencyHell
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static ArrayList<Integer>[] adj;
	static boolean needed[];
	static int inDegree[];
	static boolean marked[];
	
	static void findAllNeeded(int u) {
		marked[u] = true;
		for(int v : adj[u]) {
			if(!marked[v])
				findAllNeeded(v);
			needed[u] |= needed[v];
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			int M = s1.nextInt();
			adj = new ArrayList[N + 1];
			needed = new boolean[N + 1];
			marked = new boolean[N + 1];
			inDegree = new int[N + 1];
			
			for(int i=1;i<=N;i++)
				adj[i] = new ArrayList<>();
			
			for(int i=1;i<=N;i++) {
				inDegree[i] = s1.nextInt();
				for(int j=0;j<inDegree[i];j++)
					adj[s1.nextInt()].add(i);
			}
			
			while(M-->0)
				needed[s1.nextInt()] = true;
			
			for(int i=1;i<=N;i++)
				if(inDegree[i] == 0)
					findAllNeeded(i);
			
			ArrayList<Integer> ans = new ArrayList<>();
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for(int i = 1;i<=N;i++)
				if(needed[i] && inDegree[i] == 0)
					pq.add(i);
			
			while(!pq.isEmpty()) {
				int u = pq.remove();
				ans.add(u);
				for(int v : adj[u]) {
					if(needed[v]) {
						inDegree[v]--;
						if(inDegree[v] == 0)
							pq.add(v);
					}
				}
			}
			
			out.println(ans.size());
			for(int a : ans) out.print(a + " ");
			out.println();
			
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