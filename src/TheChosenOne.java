import java.util.*;
import java.io.*;
public class TheChosenOne
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
	
	static class SparseTable1D  { // < O(Nlog(N)) , O(1) >  0 based indexing
		long sparse[][];
		int len;
		static int log(int N) { return 31 - Integer.numberOfLeadingZeros(N); }

		SparseTable1D(long arr[]) {
			len = arr.length;
			int k = log(len) + 1;
			sparse = new long[k][len];
			for (int i = 0; i < len; i++)
				sparse[0][i] = arr[i];

			for(int i=1;i<k;i++)
				for(int j=0;j+(1<<i) <= len;j++)
					sparse[i][j] = gcd(sparse[i-1][j],sparse[i-1][j+(1<<(i-1))]);

		}
		long getGCD(int L,int R) {
			
			if(L > R) return 0;
			
			int sz = R - L + 1;
			int k  = log(sz);
			long v1 = sparse[k][L];
			long v2 = sparse[k][L + (sz - (1 << k))];
			return gcd(v1,v2);
		}

	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long arr[] = s1.nextLongArray(N);
		
		if(N == 1)
			out.println(arr[0] + 1);
		else {
			SparseTable1D sparseTable = new SparseTable1D(arr);
			for(int i=0;i<N;i++) {
				long reqGCD = gcd(sparseTable.getGCD(0, i - 1), sparseTable.getGCD(i + 1, N - 1));
				if(arr[i] % reqGCD != 0) {
					out.println(reqGCD);
					return;
				}
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}