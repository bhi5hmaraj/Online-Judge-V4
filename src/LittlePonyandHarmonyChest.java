import java.util.*;
import java.io.*;
public class LittlePonyandHarmonyChest
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int N , arr[];
	static int memo[][];
	static int MAX = 60;
	static int primes[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59};
	static int inv[];
	static int primeFactors[];
	static int rec(int idx, int mask) {
		if(idx == N)
			return memo[idx][mask] = 0;
		else if(memo[idx][mask] != -1)
			return memo[idx][mask];
		else {
			int min = Math.abs(1 - arr[idx]) + rec(idx + 1, mask);

			for(int i=2;i<MAX;i++)
				if((primeFactors[i] & mask) == 0) 
					min = Math.min(min,Math.abs(i - arr[idx]) + rec(idx + 1, mask | primeFactors[i]));
			
			return memo[idx][mask] = min;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		arr = s1.nextIntArray(N);
		inv = new int[MAX];
		for(int i=0;i<primes.length;i++)
			inv[primes[i]] = i;
		
		primeFactors = new int[MAX];
		
		for(int p : primes) 
			for(int j=p;j<MAX;j+=p)
				primeFactors[j] |= 1 << inv[p];
		
		
		memo = new int[N + 1][1 << primes.length];
		for(int a[] : memo)
			Arrays.fill(a, -1);
		
		
		int currMask = 0;
		int minSum = rec(0, 0);
		
		for(int i=0;i<N;i++) {
			if(memo[i + 1][currMask] + Math.abs(1 - arr[i]) == minSum) {
				out.print("1 ");
				minSum -= Math.abs(1 - arr[i]);
			}
			else {
				for(int j=2;j<MAX;j++) {
					if((currMask & primeFactors[j]) == 0 && memo[i + 1][currMask | primeFactors[j]] + Math.abs(j - arr[i]) == minSum) {
						currMask |= primeFactors[j];
						minSum -= Math.abs(j - arr[i]);
						out.print(j + " ");
						break;
					}
				}
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