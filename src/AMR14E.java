import java.util.*;
import java.io.*;
class AMR14E
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	public static boolean[] isPrimeArray(int N) // Sieve of Erathanoses
	{
		boolean num[] = new boolean[N + 1];
		Arrays.fill(num, true);
		num[1] = num[0]=  false;
		for (int i = 2; i * i <= N; i++)
			if (num[i])  // i is prime
				for (int j = i * i; j <= N; j += i)
					num[j] = false;
		
			
		return num;
	}
	
	public static int[] sieve(int N) // Sieve of Erathanoses
	{
		
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
	
	@SuppressWarnings("unchecked")
	private static void solve1(FastScanner s1, PrintWriter out){ // Very Slow 3.5s could have caused TLE
		
		final int MAX = (int)(1e9);
		int sqrt = (int)Math.sqrt(MAX) + 1;
 
		boolean isPrime[] = isPrimeArray(2000); // to check wether d(n) is prime
		int primes[] = sieve(sqrt);
		
		// System.out.println(Arrays.toString(primes));
		
		int T = s1.nextInt();
		while(T-->0) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			
			ArrayList<Integer>[] pDivs = new ArrayList[R - L + 1];
			for(int i=0;i<(R-L+1);i++)
				pDivs[i] = new ArrayList<>();
			
			for(int p : primes)
				for(int i = L % p == 0 ? L : L + (p - (L % p));i <= R;i += p)
					pDivs[i - L].add(p);
			
			int cnt = 0;
			
			// System.out.println(pDivs[0]);
			
			for(int i=0;i<(R-L+1);i++) {
				int dN = 1;
				int num = L + i;
				
				for(int pd : pDivs[i]) {
					int pow = 0;
					while(num % pd == 0) {
						num /= pd;
						pow++;
					}
					dN *= (pow + 1);
				}
				
				if(num != 1)
					dN *= 2; // prime factor bigger than sqrt
				
				// out.println("num " + (L + i) + " dN " + dN + " isP " + isPrime[dN]);
				
				cnt += isPrime[dN] ? 1 : 0;
				
			}
			
			out.println(cnt);
		}
		
	}

	private static void solve2(FastScanner s1, PrintWriter out) { // Very Fast ran in 0.45s
		
		final int MAX = (int)(1e9);
		int sqrt = (int)Math.sqrt(MAX) + 1;

		boolean isPrime[] = isPrimeArray(2000); // to check wether d(n) is prime
		int primes[] = sieve(sqrt);
		
		// System.out.println(Arrays.toString(primes));
		
		int T = s1.nextInt();
		while(T-->0) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			
			int dN[] = new int[R - L + 1];
			Arrays.fill(dN, 1);
			int num[] = new int[R - L + 1];
			for(int i=0;i<(R-L+1);i++)
				num[i] = i + L;
			
			for(int p : primes)
				for(int i = L % p == 0 ? L : L + (p - (L % p));i <= R;i += p) {
					int pow = 0;
					while(num[i - L] % p == 0) {
						pow ++;
						num[i - L] /= p;
					}
					dN[i - L] *= (pow + 1);
				}
			
			for(int i=0;i<(R-L+1);i++)
				if(num[i] != 1)
					dN[i] *= 2;
			
			int cnt = 0;
			for(int i=0;i<(R-L+1);i++)
				cnt += isPrime[dN[i]] ? 1 : 0;
			
			out.println(cnt);
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}