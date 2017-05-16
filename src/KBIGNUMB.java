import java.util.*;
import java.io.*;
class KBIGNUMB
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class MM {   	// MM (Modular Math) class 
		static long mod = (long) (1e9) + 7; // Default
		static long sub(long a, long b) {return (a - b  + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return (a + b) % mod;}
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

	static long sumOfGP(long n , long x) {
		if(n == 1)
			return 1;
		else {
			long half = n >> 1;
			if((n & 1L) == 0) // even
				return MM.mul(sumOfGP(half, x), MM.add(1, MM.modPow(x, half)));
			else {
				long middle = MM.modPow(x, half);
				return MM.add(middle, MM.mul(sumOfGP(half, x), MM.add(1, MM.mul(middle, x))));
			}
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			int A = s1.nextInt();
			long n = s1.nextLong();
			MM.mod = s1.nextLong();
			long x = 1;
			for(int rem = A;rem > 0;rem /= 10)
				x *= 10;
			
			out.println(MM.mul(A, sumOfGP(n, x)));
			
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