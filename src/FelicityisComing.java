import java.util.*;
import java.io.*;
public class FelicityisComing
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair {
		int prevClass , freq;
		Pair (int pc , int f) {
			prevClass = pc;
			freq = f;
		}
		@Override
		public int hashCode() {
			return Objects.hash(prevClass , freq);
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return prevClass == p.prevClass && freq == p.freq;
		}
		@Override
		public String toString() {
			return String.format("[prevClass = %d freq = %d", prevClass , freq);
		}
	}
	static class MM {   	// MM (Modular Math) class 
		static final long mod = (long) (1e9) + 7L; // Default
		static long sub(long a, long b) {return ((a % mod) - (b % mod) + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return ((a % mod) + (b % mod)) % mod;}
		static long div(long a, long b) {return mul(a, modInverse(b));}
		static long modInverse(long n)  {return modPow(n, mod - 2L);} // Fermat's little theorem
		static long modPow(long a, long b) { // squared exponentiation
			if (b == 0L || a == 1L)
				return 1L;
			else if (b == 1L)
				return a;
			else {
				if ((b & 1L) == 0L) // Checking whether b is even (fast)
					return modPow((a * a) % mod, b >> 1);
				else
					return (a * modPow((a * a) % mod, b >> 1)) % mod;
			}
		}
	}
	private static int MAX = 1000000; // (Change it to max N limit (be careful with the index)					  
	private static long fact[] = new long[MAX + 5];

	static {
		fact[1] = 1;
		fact[0] = 1;
		for (int i = 2; i <= MAX; i++)
			fact[i] = MM.mul(i, fact[i - 1]);
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int M = s1.nextInt();
		
		int grp[] = new int[M + 1];
		int sz = 0;
		
		while(N-->0) {
			
			int G = s1.nextInt();
			int arr[] = s1.nextIntArray(G);
			
			HashMap<Integer , Integer> freq = new HashMap<>();
			for(int a : arr)
				freq.put(a, freq.getOrDefault(a, 0) + 1);
			
			HashMap<Pair , ArrayList<Integer>> group = new HashMap<>();
			
			for(Map.Entry<Integer, Integer> e : freq.entrySet()) {
				Pair p = new Pair(grp[e.getKey()], e.getValue());
				ArrayList<Integer> arl = group.getOrDefault(p, new ArrayList<>());
				arl.add(e.getKey());
				group.put(p, arl);
			}
			// out.println(group);
			for(Map.Entry<Pair, ArrayList<Integer>> e : group.entrySet()) {
				sz++;
				for(int a : e.getValue())
					grp[a] = sz;
			}
		}
		
		HashMap<Integer , Integer> finalGroups = new HashMap<>();
		for(int i=1;i<=M;i++)
			finalGroups.put(grp[i], finalGroups.getOrDefault(grp[i], 0) + 1);
		
		long ways = 1;
		for(Map.Entry<Integer, Integer> e : finalGroups.entrySet())
			ways = MM.mul(ways, fact[e.getValue()]);
		
		out.println(ways);
		
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