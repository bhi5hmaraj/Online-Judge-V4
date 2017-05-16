import java.util.*;
import java.io.*;
public class RectangularGame
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
	
	public static int[] sieve(int N) // Sieve of Erathanoses dependency : isPrimeArray()
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
	
	static final int MAX = (int) (Math.sqrt(1e9) + 10);
	
	static ArrayList<Integer> primeFactors;
	static HashMap<Integer , Long> memo;
	
	static long maxScore(int N) {
		
		// System.out.println(N);
		if(memo.containsKey(N))
			return memo.get(N);
		
		for(int p : primeFactors)
			if(N % p == 0 && N / p == 1)
				return 1;
		
		long max = 0;
		for(int p : primeFactors)
			if(N % p == 0)
				max = Math.max(max,(N / p) + maxScore(N / p));
		
		memo.put(N, max);
		return max;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int primes[] = sieve(MAX);
		primeFactors = new ArrayList<>();
		int temp = N;
		for(int p : primes) {
			if(temp % p == 0) {
				primeFactors.add(p);
				while(temp % p == 0) 
					temp /= p;
			}
		}
		if(temp != 1)
			primeFactors.add(temp);

		memo = new HashMap<>();
		
		long score = N + maxScore(N);

		out.println(score);
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