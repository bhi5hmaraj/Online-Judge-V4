import java.util.*;
import java.io.*;
public class uva_10140
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
	
	static BitSet segmentedSieve(long L , long R) {
		int len = (int) (R - L + 1);
		BitSet bitSet = new BitSet(len);
		bitSet.set(0, len);
		for(long p : primes)
			for(long i = L % p == 0 ? L : L + (p - (L % p));i <= R;i += p)
				if(i != p)
					bitSet.set((int) (i - L) , false);
		
		if(L == 1)
			bitSet.set(0, false);
		
		return bitSet;
	}
	static int primes[];
	private static void solve(FastScanner s1, PrintWriter out){

		String line;
		final int MAX = (int)(Math.sqrt(Integer.MAX_VALUE)) + 1;

		primes = sieve(MAX);

		while((line = s1.nextLine()) != null) {

			String sp[] = line.split(" ");
			long L = Integer.parseInt(sp[0]);
			long R = Integer.parseInt(sp[1]);
			
			BitSet bitSet = segmentedSieve(L, R);		
			
			long closeL = -1, closeR = -1, farL = -1, farR = -1;
			// System.out.println(bitSet);
			for (int i = bitSet.nextSetBit(0); ; i = bitSet.nextSetBit(i+1)) {
				
				int next = bitSet.nextSetBit(i + 1);
				if(next == -1) break;
				
				if(closeL == -1) {
					closeL = farL = L + i;
					closeR = farR = L + next;
				}
				else {
					if(next - i < closeR - closeL) {
						closeL = L + i;
						closeR = L + next;
					}
					if(next - i > farR - farL) {
						farL = L + i;
						farR = L + next;
					}
				}
			}
			
			if(closeL == -1) 
				out.println("There are no adjacent primes.");
			else
				out.printf("%d,%d are closest, %d,%d are most distant.\n", closeL , closeR , farL , farR);
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