import java.util.*;
import java.io.*;
public class uva_516
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int[] loPrimeSieve(int N) { // stores lowest prime factor of each number used for calculating phi
		int[] loPrime = new int[N + 1];
		int pr[] = new int[N];
		int sz = 0;
		for (int i = 2; i <= N; ++i) {
			if (loPrime[i] == 0) {
				loPrime[i] = i;
				pr[sz] = i;
				sz++;
			}
			for (int j = 0; j < sz && pr[j] <= loPrime[i] && i * pr[j] <= N; ++j)
				loPrime[i * pr[j]] = pr[j];
		}
		return loPrime;
	}
	static TreeMap<Integer, Integer> primeFactorize(int N) { // Dependency : A sieve (loPrime[]) which contains the lowest prime divisor for each number
		TreeMap<Integer, Integer> mp = new TreeMap<>((i1 , i2) -> Integer.compare(i2, i1));
		int ct, prime;
		while (N != 1) {
			prime = loPrime[N];
			ct = 0;
			while (N % prime == 0) {
				N /= prime;
				ct++;
			}
			mp.put(prime, ct);
		}
		return mp;
	}
	
	static final int MAX = 32767;
	static int loPrime[] = loPrimeSieve(MAX);
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line;
		while(!(line = s1.nextLine()).equals("0")) {
			StringTokenizer st = new StringTokenizer(line);
			int num = 1;
			while(st.hasMoreTokens()) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				while(b-->0) num *= a;
			}
			num--;
			TreeMap<Integer, Integer> factorize = primeFactorize(num);
			boolean first = true;
			for(Map.Entry<Integer, Integer> e : factorize.entrySet()) {
				out.print((first ? "" : " ") + e.getKey() + " " + e.getValue());
				first = false;
			}
			out.println();
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