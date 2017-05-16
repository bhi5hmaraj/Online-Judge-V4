import java.util.*;
import java.io.*;
public class ISIProblem2 {
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = 20_172_017;
		if(N < 4)
			throw new RuntimeException("N should be atleast 4");
		
		long DP1[] = new long[N + 1];	// for multiplication
		long DP2[] = new long[N + 1];
		
		DP1[2] = DP1[3] = 1;
		DP2[2] = 1;
		DP2[3] = 3;
		
		for(int i=4;i<=N;i++) {
			int lastSetBit = Integer.lowestOneBit(i);
			 DP1[i] = DP1[lastSetBit - 1] + 1 + DP1[i - lastSetBit];
			 DP2[i] = DP2[lastSetBit - 1] + 2 + DP2[i - lastSetBit];
		}
		
		out.println("Multiplication : " + DP1[N]);
		out.println("Additions : " + DP2[N]);
		
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