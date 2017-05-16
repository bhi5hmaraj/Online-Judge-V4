import java.util.*;
import java.io.*;
public class ArrayConstruction
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int arr[] ;
	static int K , N;
	
	static int sumTillWith(int j , int x) { // j is excluded
		int sum = 0;
		for(int i=0;i<j;i++)
			sum += Math.abs(x - arr[i]);
		return sum;
	}
	
	static int DP[][][];  // (index , remaining sum till that index , sum of difference of pairs)
	
	// 0 unvisited 1 true 2 false;
	static int rec(int idx , int sum , int currK) {
		if(arr[1] == 5 && arr[2] == 9 && arr[3] == 11 && arr[4] == 12 && arr[5] == 12)
			System.out.println(Arrays.toString(arr));
		if(idx == N) {
			return currK == K ? 1 : 2;
		}
		else if(currK > K)
			return 2;
		else if(idx == N - 1) {
			arr[N - 1] = sum;
			return DP[idx][sum][currK] = rec(idx + 1, 0, currK + sumTillWith(N - 1, sum));
		}
		else if(DP[idx][sum][currK] != 0) {
			if(arr[1] == 5 && arr[2] == 9 && arr[3] == 11 && arr[4] == 12 && arr[5] == 12)
				System.out.println("DP HIT " + Arrays.toString(arr));
			return DP[idx][sum][currK];
		}
		else {
			for(int i=0;i<=sum;i++) {
				arr[idx] = i;
				if(rec(idx + 1, sum - i, currK + sumTillWith(idx, i)) == 1)
					return DP[idx][sum][currK] = 1;
			}
			return DP[idx][sum][currK] = 2;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			int sum = s1.nextInt();
			K = s1.nextInt();
			DP  = new int[N][sum + 1][K + 1];
			arr = new int[N];
			
			if(rec(0, sum , 0) == 1)
				for(int a : arr)
					out.print(a + " ");
			else
				out.print(-1);
			
			out.println();
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