import java.util.*;
import java.io.*;
class GOGL16T3
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int N;
	static ArrayList<Integer>[] adj;
	
	static boolean isBoy(int i) { return i <= N; }
	static boolean marked[];
	static int cnt_girls[]; // no. of girls in a component
	static int id[]; // marking a component
	
	static void cnt(int u , int grp) {
		marked[u] = true;
		if(isBoy(u)) 
			id[u] = grp;
		else
			cnt_girls[grp]++;
		
		for(int v : adj[u])
			if(!marked[v])
				cnt(v, grp);
	}
	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		int M = s1.nextInt();
		
		adj = new ArrayList[2*N + 1];
		for(int i=0;i<adj.length;i++)
			adj[i] = new ArrayList<>();
		
		marked = new boolean[2*N + 1];
		
		long ways = 0;
		
		while(M-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			char t1 = s1.nextChar();
			char t2 = s1.nextChar();
			u = t1 == 'B' ? u : N + u;
			v = t2 == 'B' ? v : N + v;
			adj[u].add(v);
			adj[v].add(u);
		}
		
		cnt_girls = new int[N + 1];
		id = new int[N + 1];
		int grp = 1;
		
		for(int i=1;i<=N;i++)
			if(!marked[i])
				cnt(i, grp++);
		
		for(int i=1;i<=N;i++)
			ways += (N - cnt_girls[id[i]]);
		
		out.println(ways);
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