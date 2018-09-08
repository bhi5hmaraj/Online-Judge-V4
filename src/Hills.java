import java.util.*;
import java.io.*;

public class Hills{
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	
	static int memo[][][];
	static int arr[];
	static final int INF = (int) 1e9;
	
	static int cost(int from, int to) {
		return Math.max(-1, from - to) + 1;
	}
	
	static int rec(int idx, int k, int didChoose) {
		if(idx < 0) 
			return k != 0 ? INF : 0;
		else if(k == 0)
			return 0;
		else if(memo[idx][k][didChoose] != -1)
			return memo[idx][k][didChoose];
		else {
			int left = idx == 0 ? 0 : cost(arr[idx - 1], arr[idx]);
			int right = idx == arr.length - 1 ? 0 
					   : cost((didChoose == 1 ? Math.min(arr[idx + 1], arr[idx + 2] - 1) : arr[idx + 1]), arr[idx]);
			
			return memo[idx][k][didChoose] = Math.min(rec(idx - 1, k, 0), 
											 left + right + rec(idx - 2, k - 1, 1));
		}
		
	}
	
	private static void solve() {
		
		int n = nextInt();
		arr = nextIntArray(n);
		
		int maxK = (n + 1) >> 1;
		memo = new int[n][maxK + 1][2];
		for(int[][] a : memo)
			for(int b[] : a)
				Arrays.fill(b, -1);
		
		for(int k = 1; k <= maxK; k++)
			print(rec(n - 1, k, 0) + " ");
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE **********************/
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		st     = null;
		solve();
		reader.close();
		writer.close();
	}
	
	static BufferedReader reader;
	static PrintWriter    writer;
	static StringTokenizer st;
	
	static String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
	static int    nextInt()   {return Integer.parseInt(next());}
	static long   nextLong()  {return Long.parseLong(next());}     
	static double nextDouble(){return Double.parseDouble(next());}
	static char   nextChar()  {return next().charAt(0);}
	static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
	static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
	static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
	static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
	static void   print(Object o)  { writer.print(o);  }
	static void   println(Object o){ writer.println(o);}
	
	/************************ TEMPLATE ENDS HERE ************************/
	
}