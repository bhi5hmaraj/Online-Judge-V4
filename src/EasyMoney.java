import java.util.*;
import java.io.*;
public class EasyMoney
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final long mod = (long)1e9 + 7;
	
	static long modPow(long a, long b , long mod) { // squared exponentiation
		if (b == 0L || a == 1L)
			return 1L;
		else if (b == 1L)
			return a;
		else {
			if ((b & 1L) == 0L) // Checking whether b is even (fast)
				return modPow((a * a) % mod, b >> 1 , mod);
			else
				return (a * modPow((a * a) % mod, b >> 1, mod)) % mod;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		long N = s1.nextLong();
		long ans = modPow(2, modPow(2, N, mod - 1), mod); // We need to calculate ((2 ^ (2 ^N) ) - 1) mod M 
		/*
		 * But since exponent is also large we need to somehow reduce it 
		 * Using Fermat's little theorem ie. if M is prime 
		 * then (a ^ (M-1)) % M = 1
		 * We can use this property in our problem to compute (a^k) % M 
		 * If we divide k by (M-1) then k = q*(M-1) + r , r = (k % (M-1))
		 * a^k = a^(q*(M-1) + r) = a^r * (a^(M-1))^q = a^r * 1 ==> Corollary of Fermat's little theorem
		 * (a^k) % M = (a^(k % (M-1))) % M
		 */
		ans = (ans - 1 + mod) % mod;
		out.println(ans);
		
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