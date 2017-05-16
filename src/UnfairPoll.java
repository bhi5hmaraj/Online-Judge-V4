import java.util.*;
import java.io.*;
public class UnfairPoll
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int n = s1.nextInt();
		int m = s1.nextInt();
		long k = s1.nextLong();
		int x = s1.nextInt();
		int y = s1.nextInt();
		
		if(n == 1) {
			long q = k / (long) m;
			out.println((q + (k % m != 0 ? 1 : 0)) + " " + q + " " + (y <= k % (long) m ? q + 1 : q));
			return;
		}
		
		long arr[][] = new long[n][m];
		outer:
			for(int i=0;i<n;i++)
				for(int j=0;j < m;j++){
					if(k == 0)
						break outer;
					else {
						arr[i][j]++;
						k--;
					}
				}

		k--;
		if(k >=0) {
			long inc = k / (2L * m * (n - 1));
			for(int j=0;j<m;j++) {
				arr[0][j] += inc;
				arr[n - 1][j] += inc;
			}
			for(int i=1;i<n-1;i++)
				for(int j=0;j<m;j++)
					arr[i][j] += 2*inc;
			
			k %= (2L * m * (n - 1));
			int ii = n - 2;
			int jj = 0;
			int dir = -1;
			while(k-->=0) {
				if(jj == m) {
					if(ii == 0)
						dir = 1;
					jj = 0;
					ii += dir;
				}
				
				arr[ii][jj++]++;
			}
		}
		long min = Long.MAX_VALUE;
		long max = Long.MIN_VALUE;
		for(int i=0;i<n;i++)
			for(int j=0;j<m;j++) {
				min = Math.min(min,arr[i][j]);
				max = Math.max(max,arr[i][j]);
			}
				
		out.println(max + " " + min + " " + arr[x - 1][y - 1]);
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