import java.util.*;
import java.io.*;
public class ModuloSum
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean memo[][];
	static int cnt = 0;
	static void isDivisible(int arr[] , int idx , int M , int sum , int choose){

		if(!memo[sum][idx]){
			if(choose >= 1)
				memo[sum][idx] = true;
			if(idx < arr.length){
				isDivisible(arr, idx + 1, M, sum, choose);
				isDivisible(arr, idx + 1, M, ((sum % M) + (arr[idx] % M)) % M, choose + 1);
			}
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int M = s1.nextInt();

		if(N > M)
			out.println("YES");
		else
		{
			int arr[] = s1.nextIntArray(N);
			memo = new boolean[M + 1][N + 1];
			isDivisible(arr, 0, M, 0, 0);
			boolean flag = false;
			for(boolean f:memo[0])
				flag |= f;

			out.println(flag ? "YES" : "NO");
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}