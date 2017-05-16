import java.util.*;
import java.io.*;
public class GuesstheArray
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = new int[N + 1];
		for(int offset=0;offset<(N / 3);offset++) {
			int i = (3 * offset) + 1;
			int j = i + 1;
			int k = i + 2;
			out.printf("? %d %d\n", i , j); out.flush();
			int c1 = s1.nextInt();
			out.printf("? %d %d\n", i , k); out.flush();
			int c2 = s1.nextInt();
			out.printf("? %d %d\n", j , k); out.flush();
			int c3 = s1.nextInt();
			arr[j] = (c1 - c2 + c3) / 2;
			arr[i] = c1 - arr[j];
			arr[k] = c2 - arr[i];
		}
		
		if(N % 3 == 1) {
			out.printf("? %d %d\n", N , 1); out.flush();
			arr[N] = s1.nextInt() - arr[1];
		}
		else if(N % 3 == 2) {
			out.printf("? %d %d\n", N - 1 , 1); out.flush();
			arr[N - 1] = s1.nextInt() - arr[1];
			out.printf("? %d %d\n", N , 1); out.flush();
			arr[N] = s1.nextInt() - arr[1];
		}
		
		out.print("! ");
		for(int i=1;i<=N;i++)
			out.print(arr[i] + " ");
		out.println();
		out.flush();
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