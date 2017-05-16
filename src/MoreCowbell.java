import java.util.*;
import java.io.*;
public class MoreCowbell
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean isPossible(int arr[] , int K , int S) {
		
		int L = 0;
		int R = arr.length - 1;
		int cnt = 0;
		while(L <= R) {
			if(L == R ) {
				if(arr[L] <= S) {
					cnt++;
					L++;
					R--;
				}
				else
					return false;
			}
			else if(arr[L] + arr[R] <= S) {
				cnt++;
				L++;
				R--;
			}
			else if(arr[R] <= S) {
				cnt++;
				R--;
			}
			else
				return false;
		}
		return cnt <= K;
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int K = s1.nextInt();
		int S[] = s1.nextIntArray(N);
		
		int lo = 0;
		for(int s : S)
			lo = Math.max(lo,s);
		
		int hi = (int)(2e6) + 10;
		int opt = -1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			
			// out.println("S " + mid + " K " + K + " isPoss " + isPossible(S, K, mid));
			
			if(isPossible(S, K, mid)) {
				opt = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		
		out.println(opt);
		
		
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