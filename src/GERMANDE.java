import java.util.*;
import java.io.*;
class GERMANDE
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int o1 = s1.nextInt();
			int o2 = s1.nextInt();
			int N = o1 * o2;
			int d[] = s1.nextIntArray(N);
			int cnt[][] = new int[o1][2];
			for(int i=0;i<N;i++)
				cnt[i / o2][d[i]]++;
			
			int president = 0;
			for(int i=0;i<o2;i++) {
				int ones = 0;
				for(int j=0;j<o1;j++)
					ones += cnt[j][1] > cnt[j][0] ? 1 : 0;
				if(ones > o1 / 2) {
					president = 1;
					break;
				}
				
				for(int j=0;j<o1;j++) {
					int offset = j * o2;
					cnt[j][d[(offset + i) % N]]--;
					cnt[j][d[(offset + o2 + i) % N]]++;
				}
			}
			
			out.println(president);
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