import java.util.*;
import java.io.*;
public class JeffandRounding
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final double INF = 1e10;
	static double memo[][];
	static int N;
	static double arr[];
	static double rec(int idx , int roundedUp) {
		if(roundedUp > N)
			return INF;
		else if(idx == 2*N)
			return roundedUp == N ? 0 : INF;
		else if(memo[idx][roundedUp] >= 0)
			return memo[idx][roundedUp];
		else {
			double opt1 = arr[idx] - Math.ceil(arr[idx]) + rec(idx + 1, roundedUp + 1);
			double opt2 = arr[idx] - Math.floor(arr[idx]) + rec(idx + 1, roundedUp);
			
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		arr = new double[2*N];
		for(int i=0;i<2*N;i++)
			arr[i] = s1.nextDouble();
		
		memo = new double[2*N][N + 1];
		for(double d[] : memo)
			Arrays.fill(d, -1);
		
		out.printf("%.3f\n", rec(0, 0));
		
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