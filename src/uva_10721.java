import java.util.*;
import java.io.*;
public class uva_10721
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int n , k , m;
	static long memo[][][][];
	static long ways(int idx , int color , int streak ,int bars) {
		if(streak > m || bars > k) 
			return 0;
		else if(idx == n - 1) 
			return bars == k ? 1 : 0;
		else if(memo[idx][color][streak][bars] != -1)
			return memo[idx][color][streak][bars];
		else 
			return memo[idx][color][streak][bars] = (ways(idx + 1, color ^ 1, 1, bars + 1) + 
													 ways(idx + 1, color, streak + 1, bars));
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line;
		
		while((line = s1.nextLine()) != null) {
			
			int input[] = Arrays.stream(line.split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
			n = input[0]; k = input[1]; m = input[2];
			memo = new long[n][2][m + 1][k + 1];
			for(long one[][][] : memo)
				for(long two[][] : one)
					for(long three[] : two)
						Arrays.fill(three, -1);
			
			out.println(ways(0, 1, 1, 1));
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