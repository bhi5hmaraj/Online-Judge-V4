import java.util.*;
import java.io.*;
public class Minimization
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int arr[] , N , K;
	static long memo[][];
	static void shuffleArray() {
		Random random = new Random();
		for (int i = N - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			int temp = arr[index];
			arr[index] = arr[i];
			arr[i] = temp;
		}
	}
	// L is the contigous subArray of size N/K + 1
	static long rec(int idx , int L , int S) { // Actually idx is not needed (can be calculated from L , S)
		
		if(L == 0 && S == 0)
			return 0;
		else if(memo[L][S] != -1)
			return memo[L][S];
		else {
			long min = Long.MAX_VALUE;
			if(S > 0)
				min = Math.min(min,arr[idx + (N / K) - 1] - arr[idx] + rec(idx + (N / K), L, S - 1));
			if(L > 0)
				min = Math.min(min,arr[idx + (N / K)] - arr[idx] + rec(idx + (N / K) + 1, L - 1, S));
			
			return memo[L][S] = min;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		K = s1.nextInt();
		
		arr = s1.nextIntArray(N);
		// shuffleArray();
		Arrays.sort(arr);
		
		memo = new long[(N % K) + 1][K - (N % K) + 1];
		for(long a[] : memo)
			Arrays.fill(a, -1);
		
		out.println(rec(0, N % K, K - (N % K)));
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