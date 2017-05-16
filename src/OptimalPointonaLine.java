import java.util.*;
import java.io.*;
public class OptimalPointonaLine
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
		long arr[] = s1.nextLongArray(N);
		
		//shuffleArray(arr);
		Arrays.sort(arr);
		
		long prefix[] = new long[N + 1];
		for(int i=1;i<=N;i++)
			prefix[i] = prefix[i - 1] + arr[i - 1];
		
		long x = -1;
		long minDist = Long.MAX_VALUE;
		
		for(int i = 1;i <= N;i++){
			long dist = (((i - 1) * arr[i - 1]) - prefix[i - 1]) + ((prefix[N] - prefix[i]) - ((N - i) * arr[i - 1]));
			if(dist < minDist){
				minDist = dist;
				x = arr[i - 1];
			}
		}
		out.println(x);
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