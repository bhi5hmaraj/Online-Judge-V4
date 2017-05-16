import java.util.*;
import java.io.*;
public class GuestFromthePast
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static long[] cnt_glass(long n , long glass , long back){
		
		long buy_glass = 0;
		while(n >= glass){
			buy_glass += (n / glass);
			n = ((n / glass) * back) + (n % glass);
		}
		return new long[]{buy_glass , n};
	
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		long n = s1.nextLong();
		long plastic = s1.nextLong();
		long glass = s1.nextLong();
		long back = s1.nextLong();
		long buy_plastic = (n / plastic) + cnt_glass(n % plastic, glass, back)[0];
		long gl[] = cnt_glass(n, glass, back);
		long buy_glass = gl[0] + (gl[1] / plastic);
		out.println("buy with glass only " + gl[0]);
		out.println(Math.max(buy_glass,buy_plastic));
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