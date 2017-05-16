import java.util.*;
import java.io.*;
public class uva_357 
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static long memo[][];
	private static int coins[];

	private static long numberOfWays(int sum,int index)
	{
		if(sum == 0)
			return 1;
		else if(sum < 0)
			return 0;
		else if(index < 0)
			return 0;
		else if(memo[index][sum] != -1)
			return memo[index][sum];
		else
			return memo[index][sum] = numberOfWays(sum, index-1) + numberOfWays(sum-coins[index], index);
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int M = 5;
		coins = new int[]{1,5,10,25,50};
		String in = null;
		while((in = s1.nextLine()) != null)
		{
			int N = Integer.parseInt(in);
			memo = new long[M][N+1];
			for (int i = 0; i < M; i++)
				Arrays.fill(memo[i], -1);

			long ways = numberOfWays(N, M-1);
			if(ways > 1)
				out.println("There are "+ways+" ways to produce "+N+" cents change.");
			else
				out.println("There is only 1 way to produce "+N+" cents change.");
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