import java.util.*;
import java.io.*;
public class RyukloveforApples
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int x[] = {-1 , 0 , 1 , 0}; //up , right , down and left 
	static int y[] = {0 , 1 , 0 , -1};
	
	
	static int N , M , K;
	static boolean isDefective[][];

	static boolean isValid(int i , int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}

	static final int MOD = (int)(1e9) + 7;
	
	static int memo[][][][];
	
	static int rec(int px , int py , int change , int dir) {
		if(change > K)	
			return 0;
		else if(!isValid(px, py) || isDefective[px][py])
			return 0;
		else if(px == N - 1 && py == M - 1)
			return change == K ? 1 : 0;
		else if(memo[px][py][change][dir] != -1)
			return memo[px][py][change][dir];
		else {
			int ways = 0;
			for(int i=0;i<4;i++) 
				ways = ((ways % MOD) + (rec(px + x[i], py + y[i], i == dir ? change : change + 1, i) % MOD)) % MOD;
			
			return memo[px][py][change][dir] = ways;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		M = s1.nextInt();
		K = s1.nextInt();
		int D = s1.nextInt();
		isDefective = new boolean[N][M];
		
		while(D-->0)
			isDefective[s1.nextInt() - 1][s1.nextInt() - 1] = true;
		
		memo = new int[N][M][K + 1][4];
		for(int a[][][] : memo)
			for(int b[][] : a)
				for(int c[] : b)
					Arrays.fill(c, -1);
		
		int totalWays = (rec(0, 1, 0, 1) + rec(1, 0, 0, 2)) % MOD;
		
		out.println(totalWays);

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