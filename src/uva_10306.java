import java.util.*;
import java.io.*;
public class uva_10306
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int memo[][];
	static final int INF = (int)(1e7);
	
	static boolean isPerfectSquare(int N){
		int root = (int)Math.sqrt(N);
		return root * root == N;
	}
	
	static int minCost(int arr[][], int a , int b){
		
		if(a == 0 && b == 0)
			return 0;
		
		if(a < 0 || b < 0)
			return INF;
		
		if(memo[a][b] != -1)
			return memo[a][b];
		
		int min = INF;
		for(int i=0;i<arr.length;i++)
			min = Math.min(min, 1 + minCost(arr, a - arr[i][0], b - arr[i][1] ));
		
		return memo[a][b] = min;
	}
	

	private static void solve(FastScanner s1, PrintWriter out){
		
	
		
		int t = s1.nextInt();
		while(t-->0){
			
			s1.nextLine();
			int m = s1.nextInt();
			int S = s1.nextInt();
			int arr[][] = new int[m][];
			for(int i=0;i<m;i++)
				arr[i] = s1.nextIntArray(2);
			
			memo = new int[S + 1][S + 1];
			for(int i=0;i<=S;i++)
				Arrays.fill(memo[i], -1);
			
			int minCoins = INF;
			for(int i=0;i<=S;i++){
				int j = (S * S) - (i * i);
				if(isPerfectSquare(j)){
					minCoins = Math.min(minCoins,minCost(arr, i, (int)Math.sqrt(j)));
				}
			}
			
			out.println(minCoins == INF ? "not possible" : minCoins);	
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