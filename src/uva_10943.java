import java.util.*;
import java.io.*;
public class uva_10943
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int mod = (int)(1e6);
	static int memo[][];
	static int rec(int idx , int N) {
		if(memo[idx][N] != -1)
			return memo[idx][N];
		else {
			int ways = 0;
			for(int i=0;i<=N;i++)
				ways = (ways + rec(idx - 1, N - i)) % mod;
			return memo[idx][N] = ways;
		}
	}
	
	/*
	 * There is a mathematical solution for this problem but
	 * it won't work here because the mod is not prime so we can't find the modular inverse.
	 * 
	 * ways = (N + K - 1) C (N)
	 */
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		memo = new int[101][101];
		for(int i=0;i<memo.length;i++)
			Arrays.fill(memo[i], -1);
		Arrays.fill(memo[1], 1);
		for(int i=2;i<=100;i++)
			for(int j=1;j<=100;j++)
				rec(i, j);
		
		String line;
		while(!(line = s1.nextLine()).equals("0 0")) {
			int arr[] = Arrays.stream(line.split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
			int N = arr[0];
			int K = arr[1];
			out.println(memo[K][N]);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}