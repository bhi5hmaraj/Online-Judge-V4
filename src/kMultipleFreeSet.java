import java.util.*;
import java.io.*;
public class kMultipleFreeSet
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static HashMap<Long , Long> adj ;
	static HashSet<Long> marked;
	
	static int dfs(long u) {
		marked.add(u);
		if(adj.get(u).longValue() == u)
			return 1;
		else
			return 1 + dfs(adj.get(u));
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long K = s1.nextLong();
		long arr[] = s1.nextLongArray(N);
		Arrays.sort(arr);
		
		HashSet<Long> set = new HashSet<>();
		for(long a : arr) set.add(a);
		
		adj = new HashMap<>();
		for(long a : arr) 
			if(set.contains(a * K))
				adj.put(a, a * K);
			else
				adj.put(a, a);
		
		marked = new HashSet<>();
		int cnt = 0;
		for(long a : arr)
			if(!marked.contains(a))
				cnt += (dfs(a) + 1) / 2;
		
		out.println(cnt);
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