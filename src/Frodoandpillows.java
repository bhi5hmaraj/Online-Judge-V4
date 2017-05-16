/************************	      ௳  		   ************************/
import java.util.*;
import java.util.function.LongBinaryOperator;
import java.util.function.LongUnaryOperator;
import java.io.*;
public class Frodoandpillows
{


	/************************ SOLUTION STARTS HERE ***********************/

	static LongUnaryOperator sumTill = x -> (x * (x + 1)) / 2;
	static LongBinaryOperator sumBetween = (x , y) -> x > y ? 0 :sumTill.applyAsLong(y) - sumTill.applyAsLong(x - 1);

	static long bSearch(long n , long m , long k) { // bSearch on the number of pillows
		long lo = 1;
		long hi = (long) 1e9;
		long opt = 1;
		while(lo <= hi) {
			long mid = (lo + hi) / 2;
			long rightUsed = Math.max(n - k - mid + 1,0) + sumBetween.applyAsLong(Math.max(1,mid - (n - k)), mid);
			long leftUsed  = sumBetween.applyAsLong(Math.max(1,mid - k + 1), mid - 1) + Math.max(0,k - mid);
			if(rightUsed + leftUsed <= m) {
				opt = mid;
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}

		return opt;

	}

	private static void solve(FastScanner s1, PrintWriter out){

		long n = s1.nextLong();
		long m = s1.nextLong();
		long k = s1.nextLong();		

		out.println(bSearch(n, m, k));

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