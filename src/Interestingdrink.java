import java.util.*;
import java.io.*;
public class Interestingdrink
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int BIT[];
	static final int MAX = (int) (1e5);

	static void update(int idx, int val) {
		for (; idx <= MAX; idx += ((idx & ((~idx) + 1))))
			BIT[idx] += val;
	}

	static int sum(int idx) {
		int sum = 0;
		for (; idx > 0; idx -= ((idx & ((~idx) + 1))))
			sum += BIT[idx];

		return sum;
	}

	private static void solve(FastScanner s1, PrintWriter out) {

		int N = s1.nextInt();
		BIT = new int[MAX + 10];
		for (int i = 1; i <= N; i++)
			update(s1.nextInt(), 1);

		int Q = s1.nextInt();
		while (Q-->0)
			out.println(sum(Math.min(MAX, s1.nextInt())));

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