import java.util.*;
import java.io.*;
public class uva_583
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	public static boolean[] isPrimeArray(int N) { // Sieve of Erathanoses
		boolean num[] = new boolean[N + 1];
		Arrays.fill(num, true);
		num[1] = num[0]=  false;
		for (int i = 2; i * i <= N; i++)
			if (num[i])  // i is prime
				for (int j = i * i; j <= N; j += i)
					num[j] = false;


		return num;
	}

	public static int[] sieve(int N) { // Sieve of Erathanoses dependency : isPrimeArray()

		boolean isPrime[] = isPrimeArray(N);
		int sz = 0;
		for(boolean b : isPrime)
			sz += b ? 1 : 0;
		int arr[] = new int[sz];
		int ptr = 0;
		for (int i = 2; i <= N; i++)
			if (isPrime[i])
				arr[ptr++] = i;

		return arr;
	}
	
	static final int MAX_PRIME = (int)(Math.sqrt(Integer.MAX_VALUE)) + 1;
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int primes[] = sieve(MAX_PRIME);
		int N;
		while((N = s1.nextInt()) != 0) {
			out.print(N + " = " + (N < 0 ? "-1 x " : ""));
			N = N < 0 ? -N : N;
			for(int p : primes) {
				while(N % p == 0) {
					out.print(N / p == 1 ? p : (p + " x "));
					N /= p;
				}
			}
			out.println(N == 1 ? "" : N);
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