import java.util.*;
import java.io.*;
public class LazyLoading
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		for(int tc = 1 , T = s1.nextInt();tc <= T;tc++) {
			
			int N = s1.nextInt();
			int W[] = s1.nextIntArray(N);
			Arrays.sort(W);
			
			int left = 0 , right = N - 1;
			int cnt = 0;
			while(left <= right) {
				int need = W[right] >= 50 ? 0 : ((50 - W[right]) % W[right] == 0 ? (50 - W[right]) / W[right] : 50 / W[right]);
				if(left + need <= right) 
					cnt++;
				left += need;
				right--;
			}
			
			out.printf("Case #%d: %d\n", tc, cnt);
		}
		
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("lazy_loading.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/ "out.txt"); 
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