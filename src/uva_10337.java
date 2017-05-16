import java.util.*;
import java.io.*;
public class uva_10337
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int memo[][];
	static int windCost[][];
	static int option[] = {20 , 30 , 60};
	static final int INF = (int)(1e7);
	
	static int findOpt(int idx , int alt , int X) {
		if(alt < 0 || alt > 9)
			return INF;
		else if(idx == X - 1) 
			return alt <= 1 ? (option[1 - alt] + windCost[alt][X - 1]) : INF; // Important observation we can descend to the destination from alt 1
		else if(memo[alt][idx] != -1)
			return memo[alt][idx];
		else {
			int minFuel = INF;
			for(int i=-1;i<=1;i++)
				minFuel = Math.min(minFuel,(option[i + 1] + windCost[alt][idx]) + findOpt(idx + 1, alt + i, X));
			return memo[alt][idx] = minFuel;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			String consume;
			while((consume = s1.nextLine()).isEmpty())
				;
			int X = Integer.parseInt(consume) / 100;
			windCost = new int[10][X];
			for(int i=9;i>=0;i--) 
				for(int j=0;j<X;j++)
					windCost[i][j] = -s1.nextInt();
				
			memo = new int[10][X];
			for(int i=0;i<10;i++)
				Arrays.fill(memo[i], -1);
			
			out.println(findOpt(0, 0, X) + "\n");
			
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