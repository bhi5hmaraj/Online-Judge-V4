import java.util.*;
import java.io.*;
public class GudiandtheMagicalOrbs
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean memo[][][];
	static int grid[][];
	static int N , M;
	static int x[] = {0,1,1};
	static int y[] = {1,1,0};
	static boolean isValid(int i , int j){
		return i >= 1 && i <= N && j >= 1 && j<=M;
	}
	static void findOpt(int i , int j , int cost , int K){
		if(cost <= K && !memo[i][j][cost]) {
			memo[i][j][cost] = true;
			for(int way=0;way<3;way++){
				int ii = i + x[way];
				int jj = j + y[way];
				if(isValid(ii, jj))
					findOpt(ii, jj, cost + grid[ii][jj], K);
			}
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t  = s1.nextInt();
		while(t-->0){
			N = s1.nextInt();
			M = s1.nextInt();
			int K = s1.nextInt();
			memo = new boolean [N + 1] [M + 1] [K + 1];
			grid = new int[N + 1][];
			for(int i=1;i<=N;i++)
				grid[i] = s1.nextIntArrayOneBased(M);
			
			findOpt(1, 1, grid[1][1], K);
			
			int min_cost = -1;
			for(int i=1;i<=K;i++)
				if(memo[N][M][i])
					min_cost = i;
			
			out.println(min_cost);
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