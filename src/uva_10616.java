import java.util.*;
import java.io.*;
public class uva_10616
{


	/************************ SOLUTION STARTS HERE ***********************/

	static long memo[][][];

	static int add(int a , int b , int MOD){
		int add = ((a % MOD) + (b % MOD)) % MOD;
		return add >= 0 ? add : MOD + add;
	}
	static int cnt = 0;
	static long numOfWays(int arr[] , int idx , int sum , int D , int choosed , int M){

		if(choosed >= M || idx >= arr.length){
			return sum == 0 && choosed == M ? 1 : 0;
		}
		else{
			cnt++;
			//System.out.println("idx " + idx + " sum " + sum + " choosed " + choosed);
			if(memo[idx][sum][choosed] != -1)
				return memo[idx][sum][choosed];
			return memo[idx][sum][choosed] = numOfWays(arr, idx + 1, add(sum, arr[idx], D), D, choosed + 1, M) + 
					numOfWays(arr, idx + 1, sum, D, choosed, M); 
		}
	}



	private static void solve(FastScanner s1, PrintWriter out){
		int N , Q , set = 1;
		while((N = s1.nextInt())!= 0 && (Q = s1.nextInt()) != 0)
		{
			out.println("SET " + (set++) + ":");
			int arr[] = s1.nextIntArray(N);
			for(int q = 1;q <= Q ;q++){		
				int D = s1.nextInt();
				int M = s1.nextInt();
				memo = new long[N][D][M];

				for(int i=0;i<N;i++)
					for(int j=0;j<D;j++)
						Arrays.fill(memo[i][j], -1);

				cnt = 0;
				out.println("QUERY "+q+": " +numOfWays(arr, 0, 0, D, 0, M));
				//out.println("STEPS " + cnt);
			}
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