import java.util.*;
import java.io.*;
class DIGITLIS
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final int mod = (int) 1e9 + 7;
	static final int BASE = 10;
	static int memo[][];
	static int N;
	static int LIS[];
	static int rec(int idx , int mask) {
		if(idx == N) {
			// System.out.println("Accepted " + curr);
			return 1;
		}
		else if(memo[idx][mask] != -1)
			return memo[idx][mask];
		else {
			int ways = 0;
			for(int i = idx == 0 && N > 1 ? 1 : 0;i < BASE;i++) {
				if(((mask & (1 << i)) != 0) && (Integer.bitCount(mask & ((1 << (i + 1)) - 1)) == LIS[idx])) // Check num of bits less than equal to i == LIS[idx]
					ways = (ways + rec(idx + 1, mask)) % mod;
				else if(((mask & (1 << i)) == 0) && (Integer.bitCount(mask & ((1 << (i + 1)) - 1)) == LIS[idx] - 1)){
					int rem = Integer.lowestOneBit(mask & (~((1 << (i + 1)) - 1))); // Get bits greater than i
					ways = (ways + rec(idx + 1, (mask ^ rem) | (1 << i))) % mod;
				}
			}
			return memo[idx][mask] = ways;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			LIS = s1.nextIntArray(N);
			memo = new int[N][1 << BASE];
			for(int a[] : memo)
				Arrays.fill(a, -1);
			// out.println("Ways = " + rec(0, 0, ""));
			out.println(rec(0, 0));
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