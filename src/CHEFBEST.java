import java.util.*;
import java.io.*;
class CHEFBEST
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int arr[] , N;
	static boolean check() {
		for(int i=0;i<N-1;i++)
			if(arr[i] < arr[i + 1])
				return false;
		return true;
	}
	
	static int time() {
		int cnt = 0;
		System.out.println(Arrays.toString(arr));
		while(!check()) {
			cnt++;
			int ptr = 0;
			while(ptr < N) {
				if(ptr < N - 1 && arr[ptr] == 0 && arr[ptr + 1] == 1) {
					arr[ptr] = 1;
					arr[ptr + 1] = 0;
					ptr += 2;
				}
				else
					ptr++;
			}
			System.out.println(Arrays.toString(arr));
		}
		return cnt;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();			
			arr = s1.nextIntArray(N);
			out.println(time());
		}
/*		int MAX = 9;
		int freq[] = new int[MAX + 1];
		for(int i=0;i<(1 << MAX);i++) {
			
			N = MAX;
			arr = new int[N];
			for(int j=0;j<MAX;j++)
				arr[MAX - j - 1] = (i & (1 << j)) != 0 ? 1 : 0;
			
			out.print(Arrays.toString(arr) + " bit count " + Integer.bitCount(i));
			int TIME = time();
			freq[TIME]++;
			out.println(" ==> " + TIME);
		}
		
		for(int i=0;i<=MAX;i++)
			out.println(i + " ==> " + freq[i]);*/
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
