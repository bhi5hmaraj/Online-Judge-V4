import java.util.*;
import java.io.*;
class GOGL16T6
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int W , N;
	static long memo[][] , price[];
	static int weight[] , count[];
	
	static final long INF = (long)(1e16);
	
	static long knapsack(int idx , int w) {
		
		if(idx == N)
			return w <= W ? 0 : -INF;
		else if(w > W)
			return -INF;
		else if(memo[idx][w] != -1)
			return memo[idx][w];
		else {
			long max = -INF;
			for(int i=0;i<=count[idx];i++) {
				long curr_price = (i * price[idx]) + knapsack(idx + 1, w + (i * weight[idx]));
				if(curr_price < 0)
					break;
				else
					max = Math.max(max,curr_price);
			}
			
			return memo[idx][w] = max;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		W = s1.nextInt();
		
		price = new long[N];
		weight = new int[N];
		count = new int[N];
		memo = new long[N][W + 1];
		
		for(int i=0;i<N;i++) {
			price[i] = s1.nextLong();
			weight[i] = s1.nextInt();
			count[i] = s1.nextInt();
			Arrays.fill(memo[i], -1);
		}
		
		out.println(Math.max(0,knapsack(0, 0)));
		
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