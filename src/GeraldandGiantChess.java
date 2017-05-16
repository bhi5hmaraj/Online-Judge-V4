import java.util.*;
import java.io.*;
public class GeraldandGiantChess
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class MM {   	// MM (Modular Math) class 
		static final long mod = (long) (1e9) + 7; // Default
		static long sub(long a, long b) {return (a - b  + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return (a + b) % mod;}
		static long div(long a, long b) {return mul(a, modInverse(b));}
		static long modInverse(long n) {return modPow(n, mod - 2);} // Fermat's little theorem
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

	private static int MAX = 200_000; // (Change it to max N limit (be careful with the index)					  
	private static long fact[] = new long[MAX + 5];
	private static long invFact[] = new long[MAX + 5];
	static {
		fact[1] = 1;
		fact[0] = 1;
		for (int i = 2; i <= MAX; i++)
			fact[i] = MM.mul(i, fact[i - 1]);
		for(int i=0;i<=MAX;i++)
			invFact[i] = MM.modInverse(fact[i]);
	}

	private static long nCr(int n, int r) {
		return MM.mul(fact[n], MM.mul(invFact[r], invFact[n - r]));
	}
	

	private static void solve(FastScanner s1, PrintWriter out){
		
		int R = s1.nextInt();
		int C = s1.nextInt();
		int n = s1.nextInt();
		int pt[][] = new int[n][2];
		long DP[] = new long[n];
		for(int i=0;i<n;i++)
			pt[i] = s1.nextIntArray(2);
		
		Arrays.sort(pt, (p1 , p2) -> (p1[0] != p2[0] ? Integer.compare(p1[0], p2[0]) : Integer.compare(p1[1], p2[1])));
		long totalWays = nCr(R - 1 + C - 1, R - 1);
		
		for(int i=0;i<n;i++) {
			long incomming = nCr(pt[i][0] - 1 + pt[i][1] - 1, pt[i][0] - 1);
			
			for(int j=0;j<i;j++) 
				if(pt[j][1] <= pt[i][1]) 
					incomming = MM.sub(incomming, MM.mul(DP[j], nCr(pt[i][0] - pt[j][0] + pt[i][1] - pt[j][1], pt[i][0] - pt[j][0])));

			DP[i] = incomming;
			totalWays = MM.sub(totalWays, MM.mul(incomming, nCr(R - pt[i][0] + C - pt[i][1], R - pt[i][0])));
		}
		
		
		
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