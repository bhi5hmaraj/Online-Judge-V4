import java.util.*;
import java.io.*;
public class Bots
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class MM {   	// MM (Modular Math) class 
		static final int mod = (int) (1e9) + 7; // Default
		static int sub(int a, int b) {return (a  - b + mod) % mod;}
		static int mul(int a, int b) {return (int)(((long)(a % mod) * (b % mod)) % (long)mod);}
		static int add(int a, int b) {return (a  + b ) % mod;}
		static int div(int a, int b) {return mul(a, modInverse(b));}
		static int modInverse(int n)  {return modPow(n, mod - 2);} // Fermat's little theorem
		static int modPow(int a, int b) { // squared exponentiation
			if (b == 0 || a == 1)
				return 1;
			else if (b == 1)
				return a;
			else {
				if ((b & 1) == 0) // Checking whether b is even (fast)
					return modPow((int)(((long)a * a) % (long)mod), b >> 1);
				else
					return (int)(((long)a * modPow((int)(((long)a * a) % (long)mod), b >> 1)) % (long)mod);
			}
		}
	}

	private static int MAX = (int)(2e6 + 10); // (Change it to max N limit (be careful with the index)					  
	private static int fact[] = new int[MAX + 5];

	static {
		fact[1] = 1;
		fact[0] = 1;
		for (int i = 2; i <= MAX; i++)
			fact[i] = MM.mul(i, fact[i - 1]);
	}

	private static int nCr(int n, int r) {
		return MM.div(fact[n], MM.mul(fact[r], fact[n - r]));
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
/*		for(int p=0;p<=2*N;p++) {
			if(p <= N)
				for(int i=0;i<=p;i++)
					out.println(String.format("%d C %d", 2*N - p , N - i));
			else
				for(int i=p-N;i<=N;i++)
					out.println(String.format("%d C %d", 2*N - p , N - i));
		}
		*/
		
		int total = 0;
		int T = 1;
		for(int i=0;i<=2*N;i++) {
			// out.println("Curr level cnt " + T);
			if(i < N) {
				total = MM.add(total, T);
				T = MM.mul(T, 2);
			}
			else if(i == N)
				total = MM.add(total, T);
			else {
				int singles = MM.mul(nCr(i - 1, N), 2);
				T = MM.add(MM.mul(MM.sub(T, singles), 2), singles);
				total = MM.add(total, T);
			}
		}
		
		out.println(total);
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