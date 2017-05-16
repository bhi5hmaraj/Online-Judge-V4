import java.util.*;
import java.io.*;
public class uva_11466
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	public static BitSet isPrimeBitSet(int N) {// Sieve of Erathanoses
		BitSet bs = new BitSet(N + 1);
		bs.set(0, bs.size());
		bs.set(0, false);
		bs.set(1, false);
		for (int i = 2; i * i <= N; i++)
			if (bs.get(i))  // i is prime
				for (int j = i * i; j <= N; j += i)
					bs.set(j, false);

		return bs;
	}
	
	static final int MAX = (int)(1e7);
	public static int[] sieve(int N) { // Sieve of Erathanoses dependency : isPrimeArray()
		BitSet isPrime = isPrimeBitSet(N);
		int sz = isPrime.cardinality();
		int arr[] = new int[sz];
		int ptr = 0;
		for(int i=isPrime.nextSetBit(0);i >= 0;i = isPrime.nextSetBit(i + 1))
			arr[ptr++] = i;
		return arr;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		// long start = System.nanoTime();
		int primes[] = sieve(MAX);
		// long stop = System.nanoTime();
		// out.println("Time Taken : " + ((stop - start) / 1e9));
		long N;
		while((N = s1.nextLong()) != 0) {
			N = N < 0 ? -N : N;
			long LPD = -1;
			int cnt = 0;
			for(long p : primes) {
				if(p > N) break; // Nice optimization reduces by 1 sec
				
				if(N % p == 0) {
					cnt++;
					LPD = p;
					while(N % p == 0)
						N /= p;
				}
			}
			if(N != 1) {
				cnt++;
				LPD = N;
			}
			out.println(cnt == 1 ? -1 : LPD);
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