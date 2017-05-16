import java.util.*;
import java.io.*;
class AMR15B
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final int MAX = (int)(1e5);
	static final long mod = (long)(1e9) + 7;
	static long sub(long a, long b , long mod) {return ((a % mod) - (b % mod) + mod) % mod;}
	static long mul(long a, long b , long mod) {return ((a % mod) * (b % mod)) % mod;}
	static long modPow(long a, long b , long mod) { // squared exponentiation
		if (b == 0L || a == 1L)
			return 1L;
		else if (b == 1L)
			return a;
		else {
			if ((b & 1L) == 0L) // Checking whether b is even (fast)
				return modPow((a * a) % mod, b >> 1 , mod);
			else
				return (a * modPow((a * a) % mod, b >> 1 , mod)) % mod;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0){
			
			int N = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			int freq[] = new int[MAX + 1];
			for(int a : arr)
				freq[a]++;
			
			int multCnt[] = new int[MAX + 1];
			for(int i=1;i<=MAX;i++)
				for(int j=i;j<=MAX;j+=i)
					multCnt[i] += freq[j];
			
			long DP[] = new long[MAX + 1]; // for each i , DP[i] = number of subsets which have gcd = i 
			
			for(int i=MAX;i >= 1;i--) {
				long total = sub(modPow(2, multCnt[i], mod - 1) , 1 , mod - 1); // Corollary of Fermat's litte theorem ( If
				for(int j = 2*i;j <= MAX;j += i)
					total = sub(total, DP[j], mod - 1);
				DP[i] = total;
			}
			
			long ans = 1;
			for(int i=1;i<=MAX;i++)
				ans = mul(ans, modPow(i, DP[i], mod), mod);
			
			out.println(ans);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}