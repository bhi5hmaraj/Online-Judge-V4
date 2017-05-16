import java.util.*;
import java.io.*;
public class ElementalOrbs
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int threshold[] , N , M;
	static final int mod = (int)(1e9) + 7;
	static int memo[][][];
	
	static int rec(int idx , int color , int streak) {
		if(streak > threshold[color])
			return 0;
		else if(idx == N)
			return 1;
		else if(memo[idx][color][streak] != -1)
			return memo[idx][color][streak];
		else {
			int ways = 0;
			for(int i=0;i<M;i++)
				ways = ((ways % mod) + (rec(idx + 1, i, i == color ? streak + 1 : 1) % mod)) % mod;
			
			return memo[idx][color][streak] = ways;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			M = s1.nextInt();
			threshold = s1.nextIntArray(M);
			memo = new int[N][M][N + 1];
			
			for(int a[][] : memo)
				for(int b[] : a)
					Arrays.fill(b, -1);
			
			int totalWays = 0;
			for(int i=0;i<M;i++)
				totalWays = ((totalWays % mod) + (rec(1, i, 1) % mod)) % mod;
			
			out.println(totalWays);
		}
		
	}
	static class MM {   	// MM (Modular Math) class 
		static final int mod = (int) (1e9) + 7; // Default
		static int sub(int a, int b) {return (a - b  + mod) % mod;}
		static int add(int a, int b) {return (a + b) % mod;}
	}
	private static void solve2(FastScanner s1, PrintWriter out){
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			M = s1.nextInt();
			threshold = s1.nextIntArray(M);
			int DP[][] = new int[N + 1][M]; 
			/*
			 * Base case DP[1][*] = 1
			 * prefixsum[1] = M
			 * color[*][1] = 1;
			 * 
			 */
			int prefixSum[] = new int[N + 1]; // prefixSum[i] has number of ways for prefix 
			int colorSum[][] = new int[M][N + 1]; // It is the DP matrix inverted and with prefixSums
			
			prefixSum[0] = 1;
			prefixSum[1] = M + 1;
			Arrays.fill(DP[1], 1);
			for(int i=0;i<M;i++) colorSum[i][1] = 1;
			
			for(int len = 2;len <= N;len++) {
				for(int color = 0;color < M;color++) {
					DP[len][color] = MM.sub(MM.sub(prefixSum[len - 1], len - threshold[color] - 1 < 0 ? 0 : prefixSum[len - threshold[color] - 1]) ,
											MM.sub(colorSum[color][len - 1] , colorSum[color][Math.max(len - threshold[color] - 1,0)]));
					
					colorSum[color][len] = MM.add(colorSum[color][len - 1], DP[len][color]);
					prefixSum[len] = MM.add(prefixSum[len], DP[len][color]);
				}
				prefixSum[len] = MM.add(prefixSum[len], prefixSum[len - 1]);
			}
			/*
			System.out.println(Arrays.toString(prefixSum));
			out.println("DP");
			for(int a[] : DP) 
				out.println(Arrays.toString(a));
			out.println("colorSum");
			for(int a[] : colorSum) 
				out.println(Arrays.toString(a));
			*/
			out.println(MM.sub(prefixSum[N], prefixSum[N - 1]));
		}
	}
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
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
