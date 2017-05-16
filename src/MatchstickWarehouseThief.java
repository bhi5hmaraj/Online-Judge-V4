import java.util.*;
import java.io.*;
public class MatchstickWarehouseThief
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Crate implements Comparable<Crate> {
		long boxCnt , matchesPerBox;
		Crate(long a , long b) {
			boxCnt = a;
			matchesPerBox = b;
		}
		@Override
		public int compareTo(Crate o) {
			return Long.compare(matchesPerBox, o.matchesPerBox);
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		long capacity = s1.nextLong();
		int c = s1.nextInt();
		
		Crate arr[] = new Crate[c];
		
		for(int i=0;i<c;i++)
			arr[i] = new Crate(s1.nextLong(), s1.nextLong());
		
		Arrays.sort(arr);
		
		long matchSticks = 0;
		
		for(int i = c - 1;i >= 0;i--) {
			if(capacity > 0) {
				long removed = Math.min(capacity,arr[i].boxCnt);
				matchSticks += (removed * arr[i].matchesPerBox);
				capacity -= removed;
			}
		}
		
		out.println(matchSticks);
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