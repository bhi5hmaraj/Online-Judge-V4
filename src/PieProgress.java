import java.util.*;
import java.io.*;
public class PieProgress
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int N ,M;
	static long cost[][] , memo[][];
	static final long INF = (long) 1e12;
	static long findOpt(int idx , int bought) {
		if(idx == N)
			return bought == N ? 0 : INF;
		else if(bought < idx)
			return INF;
		else if(memo[idx][bought] != -1)
			return memo[idx][bought];
		else {
			long min = INF;
			for(int i=0;i<=Math.min(N - bought,M);i++)
				min = Math.min(min,((i * i) + cost[idx][i]) + findOpt(idx + 1, bought + i));
			
			return memo[idx][bought] = min;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		for(int tc = 1 , T = s1.nextInt(); tc <= T;tc++) {
			
			N = s1.nextInt();
			M = s1.nextInt();
			cost = new long[N][];
			memo = new long[N][N + 1];
			for(int i=0;i<N;i++) {
				cost[i] = s1.nextLongArrayOneBased(M);
				Arrays.sort(cost[i]);
				for(int j=1;j<=M;j++)
					cost[i][j] += cost[i][j - 1];
				
				Arrays.fill(memo[i], -1);
			}
			
			
			
			out.println("Case #" + tc + ": " + findOpt(0, 0));
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("pie_progress.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/"out.txt"); 
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