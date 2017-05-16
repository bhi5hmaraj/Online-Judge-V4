import java.util.*;
import java.io.*;
public class SimpleRecurrence
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class MM {   	// MM (Modular Math) class 
		static final long mod = (long) (1e9) + 7; // Default
		static long sub(long a, long b) {return (a - b  + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return (a + b) % mod;}
		static long div(long a, long b) {return mul(a, modInverse(b));}
		static long modInverse(long n)  {return modPow(n, mod - 2);} // Fermat's little theorem
		static long modPow(long a , long b) {
			long pow = 1;
			while(b > 0) {
				if((b & 1L) == 1)
					pow = ((pow * a) % mod);

				a = ((a * a) % (mod));
				b >>= 1;
			}
			return pow;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		final int MAX_D = 4000;
		final int N = 4000;
		long DP[][] = new long[MAX_D + 1][N];
		for(int i=1;i<=MAX_D;i++)
			DP[i][0] = MM.modInverse(i);
		
		for(int i=1;i<=MAX_D;i++)
			for(int j=1;j<N;j++)
				DP[i][j] = MM.div(j	+ 1, MM.add(DP[i][j - 1], 1));
		
		int Q = s1.nextInt();
		while(Q-->0) {
			int type = s1.nextInt();
			long prod = 1;
			if(type == 1) {
				int L = s1.nextInt();
				int R = s1.nextInt();
				int d = s1.nextInt();
				for(int i = L;i <= R;i++)
					prod = MM.mul(prod, DP[d][i]);
			}
			else {
				int n = s1.nextInt();
				int m = s1.nextInt();
				int d = s1.nextInt();
				for(int i=0;i<=m;i++)
					prod = MM.mul(prod, DP[d + i][n]);
			}
			
			out.println(prod);
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