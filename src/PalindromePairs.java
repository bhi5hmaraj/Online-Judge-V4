import java.util.*;
import java.io.*;
public class PalindromePairs
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static void solve(FastScanner s1, PrintWriter out){

		String str = s1.nextLine();
		int N = str.length();
		
		boolean isPalin[][] = new boolean[N][N];
		
		for(int i=0;i<N;i++)
			isPalin[i][i] = true;
		for(int i=0;i<N-1;i++)
			if(str.charAt(i) == str.charAt(i + 1))
				isPalin[i][i + 1] = true;
		
		for(int len = 3;len <= N;len++) 
			for(int i=0;i<=N-len;i++)
				isPalin[i][i + len - 1] = str.charAt(i) == str.charAt(i + len - 1) && isPalin[i + 1][i + len - 2];
		
		int suffixCnt[] = new int[N];
		for(int i=N-1;i>=0;i--) {
			for(int j=i;j<N;j++)
				suffixCnt[i] += isPalin[i][j] ? 1 : 0;
			
			if(i < N - 1)
				suffixCnt[i] += suffixCnt[i + 1];
		}

		long cnt = 0;
		for(int i=0;i<N-1;i++) {
			int prefixCnt = 0;
			for(int j=0;j<=i;j++)
				prefixCnt += isPalin[j][i] ? 1 : 0;
			
			cnt += (1L * prefixCnt * suffixCnt[i + 1]);
		}
		
		out.println(cnt);
		
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