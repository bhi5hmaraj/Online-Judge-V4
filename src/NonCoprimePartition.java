import java.util.*;
import java.io.*;

public class NonCoprimePartition{
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	
	static long isPrime(long n) {
		
		for(long i = 2; i * i <= n; i++)
			if(n % i == 0)
				return i;
		
		return -1;
	}
	
	
	private static void solve() {
		
		long n = nextLong();
		long check = isPrime((n * (n + 1)) / 2);
		
		if(check == -1)
			println("No");
		else {
			println("Yes");
			println("1 " + check);
			print(n - 1);
			for(int i = 1; i <= n; i++)
				print(i != check ? " " + i : "");
			
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE **********************/
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		st     = null;
		solve();
		reader.close();
		writer.close();
	}
	
	static BufferedReader reader;
	static PrintWriter    writer;
	static StringTokenizer st;
	
	static String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
	static int    nextInt()   {return Integer.parseInt(next());}
	static long   nextLong()  {return Long.parseLong(next());}     
	static double nextDouble(){return Double.parseDouble(next());}
	static char   nextChar()  {return next().charAt(0);}
	static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
	static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
	static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
	static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
	static void   print(Object o)  { writer.print(o);  }
	static void   println(Object o){ writer.println(o);}
	
	/************************ TEMPLATE ENDS HERE ************************/
	
}