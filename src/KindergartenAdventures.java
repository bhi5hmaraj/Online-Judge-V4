import java.util.*;
import java.io.*;
public class KindergartenAdventures
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int DP[];
	static int N;
	static void inc(int L , int R) {
		if(L <= R) {
			DP[L]++;
			DP[R + 1]--;
		}
		else {
			DP[L]++;
			DP[N]--;
			DP[0]++;
			DP[R + 1]--;
		}
	}
	
	static int sub(int a , int b) {
		return (a - b + N) % N;
	}
	static int add(int a , int b) {
		return (a + b) % N;
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		DP = new int[N + 1];
		for(int i=0;i<N;i++) 
			if(arr[i] != N)
				inc(add(i, 1), sub(i, arr[i]));
		
		for(int i=1;i<=N;i++)
			DP[i] += DP[i - 1];
		
		
		// System.out.println(Arrays.toString(DP));
		
		int pos = 0;
		for(int i=0;i<N;i++)
			pos = DP[i] > DP[pos] ? i : pos;
			
		out.println(pos + 1);
		
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