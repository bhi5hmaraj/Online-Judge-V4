import java.util.*;
import java.io.*;
public class ASimpleTask
{


	/************************ SOLUTION STARTS HERE ***********************/
	
	static long memo[][];
	static int graph[];
	static long hamiltonianPath(int mask , int u) {
	    if(memo[mask][u] != -1) 
	        return memo[mask][u];
	    else if(u == Integer.numberOfTrailingZeros(mask)) // according to our convention A simple path is not allowed to end at the lowest vertex
            return 0;
	    else {
			long sum = 0;
			for(int fromSet = mask ^ (1 << u);fromSet > 0; fromSet ^= Integer.lowestOneBit(fromSet)) {
				int v = Integer.numberOfTrailingZeros(fromSet);
				// System.out.printf("mask = %s , u = %d , v = %d\n" , Integer.toBinaryString(mask) , u , v);
				if((graph[u] & (1 << v)) != 0) 
					sum += hamiltonianPath(mask ^ (1 << u), v);
			}
			
			return /*memo[mask][u] = */sum;
		}
	}

	private static void solveBottomUp(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = s1.nextInt();
		graph = new int[V];
		long DP[][] = new long[1 << V][V];

		while(E-->0) {
			int u = s1.nextInt() - 1;
			int v = s1.nextInt() - 1;
			graph[u] |= (1 << v);
			graph[v] |= (1 << u);
		}

		for(int i=0;i<V;i++)
			DP[1 << i][i] = 1;

		for(int mask = 1 , end = 1 << V;mask < end;mask++) {
			for(int set = mask;Integer.bitCount(set) > 1;set ^= Integer.highestOneBit(set)) {
				int u = Integer.numberOfTrailingZeros(Integer.highestOneBit(set));
				for(int fromSet = mask ^ (1 << u);fromSet > 0; fromSet ^= Integer.lowestOneBit(fromSet)) {
					int v = Integer.numberOfTrailingZeros(fromSet);
					// System.out.printf("mask = %s , u = %d , v = %d\n" , Integer.toBinaryString(mask) , u , v);
					if((graph[u] & (1 << v)) != 0) 
						DP[mask][u] += DP[mask ^ (1 << u)][v];
					
				}
			}
		}

		long totalCycles = 0;
		for(int mask = 1 , end = 1 << V;mask < end;mask++) {
			if(Integer.bitCount(mask) >= 3) {
				int start = Integer.numberOfTrailingZeros(mask);
				for(int set = mask;Integer.bitCount(set) > 1;set ^= Integer.highestOneBit(set)) {
					int u = Integer.numberOfTrailingZeros(Integer.highestOneBit(set));
					if((graph[u] & (1 << start)) != 0)
						totalCycles += DP[mask][u];
				}
			}
		}

		totalCycles /= 2;
/*		for(long l[] : DP)
			out.println(Arrays.toString(l));*/
		out.println(totalCycles);
	}

	private static void solveTopDown(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = s1.nextInt();
		graph = new int[V];
		memo = new long[1 << V][V];
		
		for(long l[] : memo)
			Arrays.fill(l, -1);
		
		while(E-->0) {
			int u = s1.nextInt() - 1;
			int v = s1.nextInt() - 1;
			graph[u] |= (1 << v);
			graph[v] |= (1 << u);
		}

		for(int i=0;i<V;i++)
			memo[1 << i][i] = 1;
		
		long totalCycles = 0;
		for(int mask = 1 , end = 1 << V;mask < end;mask++) {
			if(Integer.bitCount(mask) >= 3) {
				int start = Integer.numberOfTrailingZeros(mask);
				for(int set = mask;Integer.bitCount(set) > 1;set ^= Integer.highestOneBit(set)) {
					int u = Integer.numberOfTrailingZeros(Integer.highestOneBit(set));
					if((graph[u] & (1 << start)) != 0)
						totalCycles += hamiltonianPath(mask, u);
				}
			}
		}
		totalCycles /= 2;
		
		out.println(totalCycles);
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solveTopDown(in, out);
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