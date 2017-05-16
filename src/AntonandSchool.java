import java.util.*;
import java.io.*;
public class AntonandSchool
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long b[] = s1.nextLongArray(N);
		long c[] = s1.nextLongArray(N);
		long d[] = new long[N];
		
		for(int i=0;i<N;i++)
			d[i] = b[i] + c[i];
		
		long sumOfD = 0;
		for(long di : d)
			sumOfD += di;
		
		if(sumOfD % (2 * N) != 0)
			out.println(-1);
		else {
			long S = sumOfD / (2 * N);
			long a[] = new long[N];
			for(int i=0;i<N;i++) {
				if(S > d[i] || (d[i] - S) % N != 0) {
					out.println(-1);
					return;
				}
				else
					a[i] = (d[i] - S) / N;
			}
			
			int cnt[] = new int[32];
			for(int i=0;i<N;i++) {
				long ai = a[i];
				while(ai > 0) {
					int pos = Long.numberOfTrailingZeros(ai);
					cnt[pos]++;
					ai -= (1L << pos);
				}
			}
			
			for(int i=0;i<N;i++) {
				long ai = a[i];
				long bi = 0 , ci = 0;
				for(int j=0;j<32;j++) {
					bi += (ai & (1L << j)) != 0 ? ((1L << j) * cnt[j]) : 0;
					ci += (ai & (1L << j)) != 0 ? ((1L << j) * N) : ((1L << j) * cnt[j]);
				}
				if(bi != b[i] || ci != c[i]) {
					out.println(-1);
					return;
				}
			}
			
			for(long ai : a)
				out.print(ai + " ");
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