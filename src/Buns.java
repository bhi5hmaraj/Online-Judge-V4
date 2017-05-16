import java.util.*;
import java.io.*;
public class Buns
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int[] weight , value;
	static int memo[][];
	static int knapsack(int idx , int dough) {
		if(idx < 0)
			return 0;
		else if(memo[idx][dough] != -1)
			return memo[idx][dough];
		else {
			int max = knapsack(idx - 1, dough);
			if(dough >= weight[idx])
				max = Math.max(max,value[idx] + knapsack(idx - 1, dough - weight[idx]));

			return memo[idx][dough] = max;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int n = s1.nextInt();
		int m = s1.nextInt();
		int c0 = s1.nextInt();
		int d0 = s1.nextInt();
		int sz = 0;
		int arr[][] = new int[m][];
		for(int i=0;i<m;i++)
			arr[i] = s1.nextIntArray(4);
		for(int i=0;i<m;i++)
			sz += (arr[i][0] / arr[i][1]);
		weight = new int[sz];
		value = new int[sz];
		int ptr = 0;
		for(int i=0;i<m;i++)
			for(int j=0;j < (arr[i][0] / arr[i][1]);j++) {
				weight[ptr] = arr[i][2];
				value[ptr] = arr[i][3];
				ptr++;
			}
		
		// System.out.println("weight " + Arrays.toString(weight));
		// System.out.println("value " + Arrays.toString(value));
		memo = new int[sz][n + 1];
		for(int a[] : memo)
			Arrays.fill(a, -1);

		int maxProfit = 0;
		
		for(int used = n;used >= 0;used--) {
			int left = n - used;
			int profit = knapsack(sz - 1, used) + ((left / c0) * d0);
			maxProfit = Math.max(maxProfit,profit);
		}

		out.println(maxProfit);

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