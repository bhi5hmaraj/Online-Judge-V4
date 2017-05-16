import java.util.*;
import java.io.*;
public class Urbanization
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static void shuffleArray(int[] array) {
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			int temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int N1 = s1.nextInt();
		int N2 = s1.nextInt();
		
		int s = Math.min(N1,N2);
		int b = Math.max(N1,N2);
		
		int arr[] = s1.nextIntArray(N);
		shuffleArray(arr);
		Arrays.sort(arr);
		
		long sum1 = 0 , sum2 = 0;
		for(int i = N - (N1 + N2);i < (N - s);i++)
			sum1 += arr[i];
		
		for(int i = N-s;i<N;i++)
			sum2 += arr[i];
		
		double maxMean = (sum1 / (double) b) + (sum2 / (double) s);
		
		out.println(maxMean);
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