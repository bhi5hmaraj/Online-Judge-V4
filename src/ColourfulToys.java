import java.util.*;
import java.io.*;
public class ColourfulToys
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static long memo[][][];
	static long P ,Q ,R ,S;
	static long findOpt(int x , int y , int z) {
		
		if(x == 0 && y == 0 && z == 0)
			return 0;
		
		if(memo[x][y][z] != -1)
			return memo[x][y][z];
		
		long max = 0;
		if(x > 0)
			max = Math.max(max,S + findOpt(x - 1, y, z));
		if(y > 0)
			max = Math.max(max,S + findOpt(x, y - 1, z));
		if(z > 0)
			max = Math.max(max,S + findOpt(x, y, z - 1));
		if(x > 0 && y > 0)
			max = Math.max(max,P + findOpt(x - 1, y - 1, z));
		if(y > 0 && z > 0)
			max = Math.max(max,Q + findOpt(x, y - 1, z - 1));
		if(x > 0 && y > 0 && z > 0)
			max = Math.max(max,R + findOpt(x - 1, y - 1, z - 1));
		
		return memo[x][y][z] = max;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){ // Slow , Memory inefficient DP
		
		int T = s1.nextInt();
		while(T-->0) {
			int x = s1.nextInt();
			int y = s1.nextInt();
			int z = s1.nextInt();
			
			memo = new long[x + 1][y + 1][z + 1];
			for(long a[][] : memo)
				for(long b[] : a)
					Arrays.fill(b, -1);
			
			P = s1.nextLong();
			Q = s1.nextLong();
			R = s1.nextLong();
			S = s1.nextLong();
			
			out.println(findOpt(x, y, z));
		}
		
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int x = s1.nextInt();
			int y = s1.nextInt();
			int z = s1.nextInt();
			long cost[] = s1.nextLongArray(4);
			
			long maxProfit = 0;
			for(int t1 = 0;t1 <= Math.min(x,y);t1++) {
				for(int t2 = 0;t2 <= Math.min(y - t1,z);t2++) {
					int newX = x - t1;
					int newY = y - t1 - t2;
					int newZ = z - t2;
					int min = Math.min(Math.min(newX,newY),newZ);
					long profit = (t1 * cost[0]) + (t2 * cost[1]) + Math.max(3 * min * cost[3],min * cost[2]) + ((newX - min) * cost[3]) + ((newY - min) * cost[3]) + ((newZ - min) * cost[3]);
					// out.printf("t1 = %d t2 = %d profit = %d\n", t1 , t2 , profit);
					maxProfit = Math.max(maxProfit,profit);
				}
			}
			
			out.println(maxProfit);
		}
		
	}
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/

	
	public static void main(String[] args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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