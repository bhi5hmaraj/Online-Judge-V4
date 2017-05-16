import java.util.*;
import java.io.*;
public class uva_10819
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int memo[][];
	static final int INF = Integer.MIN_VALUE;
	static int budget;
	static int[] rate , favour;

	static int knapsack(int idx , int cost){
		if(cost > budget && budget + 200 < 2000)  //pruning to maintain cost below 15K
			return INF;
		
		if(cost > budget + 200)
			return INF;

		if(idx >= rate.length){

			if(cost == 0)
				return INF;
			else if(cost > budget){
				if(cost > 2000)
					return cost <= budget + 200 ? 0 : INF;
				else
					return INF;
			}
			else{
				return 0;
			}
		}
		else{

			if(memo[idx][cost] != -1)
				return memo[idx][cost];


			return memo[idx][cost] = Math.max(knapsack(idx + 1, cost),
					knapsack(idx + 1, cost + rate[idx]) + favour[idx]);
		}

	}


	private static void solve(FastScanner s1, PrintWriter out){

		String line = null;
		while((line = s1.nextLine()).length() != 0)
		{
			String split[] = line.split(" ");
			budget = Integer.parseInt(split[0]);
			int n = Integer.parseInt(split[1]);

			memo = new int[n][15000];
			for(int i=0;i<n;i++)
				Arrays.fill(memo[i], -1);

			rate = new int[n];
			favour = new int[n];
			for(int i=0;i<n;i++){
				rate[i] = s1.nextInt();
				favour[i] = s1.nextInt();
			}	      

			int ans = knapsack(0, 0);
			out.println(ans < 0 ? 0 : ans);
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