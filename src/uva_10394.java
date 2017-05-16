import java.util.*;
import java.io.*;
public class uva_10394
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	public static BitSet isPrimeArray(int N) {// Sieve of Erathanoses
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
	static final int MAX = 20000000;
	static BitSet isPrime;
	static ArrayList<Integer> twinPrimes;
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		isPrime = isPrimeArray(MAX);
		twinPrimes = new ArrayList<>();
		
		for(int i=isPrime.nextSetBit(0);;i = isPrime.nextSetBit(i + 1)) {
			
			int next = isPrime.nextSetBit(i + 1);
			if(next == -1) 
				break;
			else if(next - i == 2)
				twinPrimes.add(i);
			
		}
		
		String line;
		while((line = s1.nextLine()) != null) {
			int N = Integer.parseInt(line);
			out.printf("(%d, %d)\n", twinPrimes.get(N - 1) , twinPrimes.get(N - 1) + 2);
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