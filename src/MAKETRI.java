import java.util.*;
import java.io.*;
class MAKETRI
{


	/************************ SOLUTION STARTS HERE ***********************/


	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		long L = s1.nextLong();
		long R = s1.nextLong();

		long arr[] = s1.nextLongArray(N);
		Arrays.sort(arr);

		ArrayDeque<long[]> stack = new ArrayDeque<>();

		for(int i=N-1;i>0;i--) {
			long right = arr[i - 1] + arr[i] - 1;
			long left = arr[i] - arr[i - 1] + 1;
			if(stack.isEmpty())
				stack.push(new long[]{left , right});
			else {
				long top[] = stack.getFirst();
				if(right >= top[0] && right <= top[1]) {
					top[0] = Math.min(top[0],left);
					top[1] = Math.max(top[1],right);
				}
				else
					stack.push(new long[]{left , right});
			}
		}
		
		
		long cnt = 0;
		for(long a[] : stack) {
			// out.println(Arrays.toString(a));
			if(!(a[1] < L || a[0] > R))
				cnt += Math.min(R,a[1]) - Math.max(L,a[0]) + 1;
		}
		out.println(cnt);
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
	/*
	static String outputFile = "MAKETRI_UNKNOWN.txt";
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
		solve(in, out);
		in.close();
		out.close();
	}
	*/
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