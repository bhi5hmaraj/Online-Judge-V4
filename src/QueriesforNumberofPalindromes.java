import java.util.*;
import java.io.*;
public class QueriesforNumberofPalindromes
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String str = s1.nextLine();
		int N = str.length();
		int prefixSum[][] = new int[N][N];  // indexed [left][right]
		for(int i=0;i<N;i++)
			prefixSum[i][i] = 1;
		for(int i=0;i<N-1;i++)
			if(str.charAt(i) == str.charAt(i + 1))
				prefixSum[i][i + 1] = 1;
		
		for(int len = 3;len <= N;len++) 
			for(int i=0;i<=N-len;i++)
				prefixSum[i][i + len - 1] = str.charAt(i) == str.charAt(i + len - 1) && prefixSum[i + 1][i + len - 2] == 1 ? 1 : 0;
		
		for(int i=0;i<N;i++)
			for(int j=i+1;j<N;j++)
				prefixSum[i][j] += prefixSum[i][j - 1];
		
		
		
		/*		
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				out.print(String.format("%4d ", prefixSum[i][j]));
			out.println();
		}
		*/
		
		int cntPalin[][] = new int[N][N]; // indexed [right][left]
		
		for(int right=N-1;right>=0;right--) {
			cntPalin[right][right] = 1;
			for(int left=right - 1;left>=0;left--) 
				cntPalin[right][left] = cntPalin[right][left + 1]+ prefixSum[left][right];
		}
		/*
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++)
				out.print(String.format("%4d ", cntPalin[j][i]));
			out.println();
		}
		*/
		int Q = s1.nextInt();
		while(Q-->0) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			out.println(cntPalin[R - 1][L - 1]);
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}