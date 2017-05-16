import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
public class FirstDate
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	@SuppressWarnings("deprecation")
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line = null;
		while((line = s1.nextLine()) != null) {
			
			String split[] = line.split("-");
			int year = Integer.parseInt(split[0]);
			int month = Integer.parseInt(split[1]);
			int day = Integer.parseInt(split[2]);
			
			GregorianCalendar cal = new GregorianCalendar();
			cal.setGregorianChange(new Date(year-1900, month-1, day));
			cal.set(year, month - 1, day);
			Date d = cal.getGregorianChange();
			cal.roll(Calendar.DATE, true);
			out.println("change date " + new SimpleDateFormat("yyyy-MM-dd").format(d));
			out.println("changed date " + new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime()));
			//System.out.printf("%d-%d-%d\n" , cal.get(Calendar.YEAR) , cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE));
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