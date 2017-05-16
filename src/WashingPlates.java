import java.util.*;
import java.io.*;
public class WashingPlates
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static void shuffleArray(long[] array) {
		Random random = new Random();
		for (int i = array.length - 1; i > 0; i--) {
			int index = random.nextInt(i + 1);
			long temp = array[index];
			array[index] = array[i];
			array[i] = temp;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int K = Math.min(s1.nextInt() , N);
		long sum[] = new long[N];
		long Dsum = 0;
		for(int i=0;i<N;i++) {
			long Pi = s1.nextLong();
			long Di = s1.nextLong();
			sum[i] = Pi + Di;
			Dsum += Di;
		}
		shuffleArray(sum);
		Arrays.sort(sum);
		long greedy = 0;
		for(int i=N-K;i<N;i++)
			greedy += sum[i];
		long ans = greedy - Dsum;
		ans = Math.max(ans,0);
		out.println(ans);
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