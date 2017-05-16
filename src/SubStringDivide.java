import java.util.*;
import java.io.*;
public class SubStringDivide
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			String str = s1.nextLine();
			int arr[] = new int[N];
			for(int i=0;i<N;i++)
				arr[i] = str.charAt(i) - '0';
			
			long[] cntSub8      = new long[8];
			long[] cntSub24     = new long[24];
			long[] currSuffix8  = null;
			long[] currSuffix24 = null;
			for(int a : arr) {
				
				long[] temp8 = new long[8];
				for(int i=0;i<8 && currSuffix8 != null;i++) {
					int newSuffix = ((i * 10) + a) % 8;
					temp8[newSuffix] += currSuffix8[i];
				}
				temp8[a % 8] += 1;
				
				for(int i=0;i<8;i++)
					cntSub8[i] += temp8[i];
				
				currSuffix8 = temp8;
				
				long[] temp24 = new long[24];
				for(int i=0;i<24 && currSuffix24 != null;i++) {
					int newSuffix = ((i * 10) + a) % 24;
					temp24[newSuffix] += currSuffix24[i];
				}
				temp24[a % 24] += 1;
				
				for(int i=0;i<24;i++)
					cntSub24[i] += temp24[i];
				
				currSuffix24 = temp24;
				
			}
			
			out.println(cntSub8[0] - cntSub24[0]);
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