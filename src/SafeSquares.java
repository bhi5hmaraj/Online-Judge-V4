import java.util.*;
import java.io.*;
public class SafeSquares
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean grid[][];

	static boolean check(int x , int y , int w , int h){
		for(int i=x;i<x+h;i++)
			for(int j=y;j<y+w;j++)
				if(grid[i][j])
					return false;

		// System.out.println("x " + x +" y " +y+ " w " + w + " h " + h); 

		return true;
	}

	private static void solve(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		for(int T = 1;T <= t;T++){
			int cnt = 0;
			int R = s1.nextInt();
			int C = s1.nextInt();
			int K = s1.nextInt();
			grid = new boolean[R][C];
			while(K-->0)
				grid[s1.nextInt()][s1.nextInt()] = true;

			for(int i=0;i<R;i++) 
				for(int j=0;j<C;j++) 
					for(int n=1;n <= Math.min(C-j,R-i);n++)
						cnt += check(i, j, n, n) ? 1 : 0;

			out.println("Case #"+T+": " + cnt);
		}
	}

	private static void solve2(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		for(int T = 1;T <= t;T++){
			long cnt = 0;
			int R = s1.nextInt();
			int C = s1.nextInt();
			int K = s1.nextInt();
			grid = new boolean[R][C];
			while(K-->0)
				grid[s1.nextInt()][s1.nextInt()] = true;
			int DP[][] = new int[R][C];
			for(int i=0;i<R;i++)
				DP[i][0] = grid[i][0] ? 0 : 1;
			for(int i=0;i<C;i++)
				DP[0][i] = grid[0][i] ? 0 : 1;
			for(int i=1;i<R;i++)
				for(int j=1;j<C;j++)
					if(!grid[i][j])
						DP[i][j] = Math.min(DP[i - 1][j - 1],Math.min(DP[i][j - 1],DP[i - 1][j])) + 1;
			for(int i=0;i<R;i++)
				for(int j=0;j<C;j++)
					cnt += DP[i][j];
			
			out.println("Case #"+T+": " + cnt);
		}
	}
	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("in.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/ "out.txt"); 
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}