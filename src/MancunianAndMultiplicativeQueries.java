import java.util.*;
import java.io.*;
public class MancunianAndMultiplicativeQueries
{


	/************************ SOLUTION STARTS HERE ***********************/

	static void join(HashMap<Integer,Integer> m1 , HashMap<Integer,Integer> m2){
		for(Map.Entry<Integer, Integer> e:m2.entrySet())
			m1.put(e.getKey(), m1.getOrDefault(e.getKey(), 0) + e.getValue());
	}
	static class ModMath {
		static final long mod = (long) (1e9) + 7L; // Default

		static long sub(long a, long b) {
			long sub = ((a % mod) - (b % mod)) % mod;
			return sub < 0 ? mod + sub : sub;
		}

		static long mul(long a, long b) {
			return ((a % mod) * (b % mod)) % mod;
		}

		static long add(long a, long b) {
			return ((a % mod) + (b % mod)) % mod;
		}

		static long div(long a, long b) {
			return mul(a, modInverse(b));
		}

		static long modInverse(long n) { // Fermat's little theorem
			return modPow(n, mod - 2L);
		}

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
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int C = s1.nextInt();
		int Q = s1.nextInt();
		int root = (int)(Math.sqrt(N));
		HashMap<Integer,Integer>[] sqrt = (HashMap<Integer,Integer>[]) new HashMap[root + 1];
		for(int i=0;i<=root;i++)
			sqrt[i] = new HashMap<>();

		int A[] = s1.nextIntArrayOneBased(N);
		int freq[] = s1.nextIntArray(N + 1);
		long cnt[] = new long[N + 1];
		cnt[0] = (long)(C) * (long)(Q);
		/*
		for(int i=1;i<=N;i++)
			sqrt[i / root].put(A[i], sqrt[i / root].getOrDefault(A[i], 0) + 1);
		 */
		while(Q-->0){

			HashMap<Integer,Integer> collect = new HashMap<>();
			int L = s1.nextInt();
			int R = s1.nextInt();
			/*
			if(L / root == R / root){
				for(int i=L;i<=R;i++)
					collect.put(A[i], collect.getOrDefault(A[i], 0) + 1);
			}
			else{
				for(int i=(L/root) + 1,end=(R/root) - 1;i<=end;i++)
					join(collect, sqrt[i]);

				for(int i=L,end = ((L/root) + 1) * root;i<end;i++)
					collect.put(A[i], collect.getOrDefault(A[i], 0) + 1);

				for(int i=R - (R % root);i <= R;i++)
					collect.put(A[i], collect.getOrDefault(A[i], 0) + 1);
			}
			*/
			for(int i=L;i<=R;i++)
				collect.put(A[i], collect.getOrDefault(A[i], 0) + 1);
			for(Map.Entry<Integer, Integer> e:collect.entrySet()){
				cnt[0]--;
				cnt[e.getValue()]++;
			}
		}
		
		long prod = 1;
		for(int i=0;i<=N;i++)
			prod = ModMath.mul(prod, ModMath.modPow(freq[i], cnt[i]));
		
		out.println(prod);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}