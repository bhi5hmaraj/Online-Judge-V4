import java.util.*;
import java.io.*;
public class uva_10827
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int Q = s1.nextInt();
		while(Q-->0) {
			
			int N = s1.nextInt();
			int arr[][] = new int[N][];
			for(int i=0;i<N;i++)
				arr[i] = s1.nextIntArray(N);
			
			int colSum[][][] = new int[N][N][N]; // ( dist, start , col)
			
			for(int i=0;i<N;i++)
				for(int j=0;j<N;j++)
					colSum[0][i][j] = arr[i][j];
			
			for (int dist = 1; dist < N; dist++)
				for (int start = 0; start < N; start++)
					for (int i = 0; i < N; i++)
						colSum[dist][start][i] = colSum[dist - 1][start][i] + arr[(start + dist) % N][i];
			
			/*for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					System.out.println("dist = " + i + " start " + j + " sum " + Arrays.toString(colSum[i][j]));
				}	
			}*/
			
			int max = Integer.MIN_VALUE;
			
			for(int dist=0;dist<N;dist++) {
				for(int start=0;start<N;start++) {
					int strip[] = colSum[dist][start];
					int prefixSum[] = new int[N];
					prefixSum[0] = strip[0];
					for(int i=1;i<N;i++)
						prefixSum[i] = prefixSum[i - 1] + strip[i];
					int minRight[] = new int[N];
					int min = Integer.MAX_VALUE;
					for(int i=N-1;i>=0;i--) {
						min = Math.min(min,prefixSum[i]);
						minRight[i] = min;
					}
					int endPrefixSum = prefixSum[N - 1];
					min = 0;
					int tempMax = Integer.MIN_VALUE;
					for(int i=0;i<N;i++) {
						max = Math.max(max,prefixSum[i] - min);
						min = Math.min(min,prefixSum[i]);
						tempMax = Math.max(tempMax,prefixSum[i]);
						max = Math.max(max,tempMax + endPrefixSum - minRight[i]);  // Takes care of the roll back cases
					}
				}
			}
			
			out.println(max);
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