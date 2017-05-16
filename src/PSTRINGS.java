import java.util.*;
import java.io.*;
class PSTRINGS
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int mod = (int)(1e9) + 7; 
	static int memo[][];

	static boolean isPalin(int mask , int len) {

		for(int i=0;i<len;i++)
			if((((mask & (1 << i)) != 0) ^ ( (mask & (1 << (len - i - 1))) != 0)))
				return false;

		return true;

	}
	static int N , K;

	static int ways(int idx , int mask) {
		// System.out.println("idx " + idx + " mask " + String.format("%10s", Integer.toBinaryString(mask)).replace(' ', '0'));
		if(idx == K  && isPalin(mask, K))
			return 0;
		else if(idx > K && (isPalin(mask, K) || isPalin(mask, K + 1)))
			return 0;
		if(idx == N) {
			// System.out.println("Solution "  + curr);
			return 1;
		}
		else if(memo[idx][mask] != -1)
			return memo[idx][mask];
		else {
			int allSet = (1 << (K + 1)) - 1;
			return memo[idx][mask] = (ways(idx + 1, (mask << 1) & allSet) + ways(idx + 1, ((mask << 1) | 1) & allSet)) % mod;
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {

			N = s1.nextInt();
			K = s1.nextInt();
			memo = new int[N][1 << (K + 1)];

			if(K > N) {
				out.println(1 << N);
			}
			else {	
				for(int m[] : memo)
					Arrays.fill(m, -1);

				out.println(ways(0, 0));
			}
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