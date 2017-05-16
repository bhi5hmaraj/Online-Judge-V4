import java.util.*;
import java.io.*;
public class uva_10557
{


	/************************ SOLUTION STARTS HERE ***********************/

	static BitSet memo[];
	static int energy[] , adj[][];
	static int MAX = (int)(1e4) + 10;
	static void rec(int idx , int cost) {
		if(cost <= 0)
			return;
		else if(cost >= MAX)
			return;
		else if(!memo[idx].get(cost)) {
			memo[idx].set(cost);
			for(int v : adj[idx])
				rec(v, cost + energy[v]);
		}
	}
	
	// BitSet - 0.57s boolean array - 1.25s
	
	private static void solve2(FastScanner s1, PrintWriter out){

		int V;
		while((V = s1.nextInt()) != -1) {
			energy = new int[V + 1];
			adj = new int[V + 1][];
			for(int i=1;i<=V;i++) {
				energy[i] = s1.nextInt();
				adj[i] = s1.nextIntArray(s1.nextInt());
			}
			
			memo = new BitSet[V + 1];
			for(int i=1;i<=V;i++)
				memo[i] = new BitSet(MAX);
			
			rec(1, 100);

			out.println(memo[V].cardinality() > 0 ? "winnable" : "hopeless");
		}
	}


	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/


	public static void main(String[] args) throws IOException {
		new Thread(null, new Runnable() {
			public void run() {
				new uva_10557().run();
			}
		}, "Increase Stack", 1 << 25).start();

	}

	void run(){	
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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