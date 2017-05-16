import java.util.*;
import java.io.*;
public class BashBigDay
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int[] bigPrimeSieve(int N) { // Runs in less than a sec for 10^7
		int bigPrime[] = new int[N + 1];
		for(int i=2;i*i<=N;i++)
			if(bigPrime[i] == 0)
				for(int j=i*i;j<=N;j+=i)
					bigPrime[j] = i;
		
		for(int i=1;i<=N;i++)
			bigPrime[i] = bigPrime[i] == 0 ? i : bigPrime[i];
		
		return bigPrime;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int MAX = (int)(1e5);
		int bigPrime[] = bigPrimeSieve(MAX);
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		int freq[] = new int[MAX + 1];
		
		for(int a : arr)
			if(a == 1) freq[1] = 1;
		
		for(int a : arr) {
			while(a > 1) {
				int prime = bigPrime[a];
				freq[prime]++;
				while(a % prime == 0)
					a /= prime;
			}
		}
		
		int max = 0;
		for(int f : freq)
			max = Math.max(max,f);
		
		
		
		out.println(max);
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