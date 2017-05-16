import java.util.*;
import java.io.*;
class LISDIGIT
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int assign[];
	static int dp[];
	static int arr[];
	static int n;

	static boolean fill(int idx) {
		// System.out.println(Arrays.toString(assign));
		if(idx >= n)
			return true;
		else {
			
			for(int i = idx == 0 ? 1 : 0;i <= 9;i++) {
				dp[idx] = 1;
				for(int j=0;j<idx;j++)
					dp[idx] = assign[j] < i ? Math.max(dp[idx],1 + dp[j]) : dp[idx];
					
				if(dp[idx] == arr[idx]) {
					assign[idx] = i;
					if(fill(idx + 1))
						return true;
				}
			}
			
			return false;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			n = s1.nextInt();
			arr = s1.nextIntArray(n);
			long num = 0;
			assign = new int[n];
			dp = new int[n];
			fill(0);
			for(int d : assign)
				num = (num * 10) + d;
			out.println(num);
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