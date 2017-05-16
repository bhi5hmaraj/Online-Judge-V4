import java.util.*;
import java.io.*;
public class SimpleFunction
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N1 = s1.nextInt();
			int N2 = s1.nextInt();
			
			int mask1[] = new int[N1];
			int mask2[] = new int[N2];
			
			for(int i=0;i<N1;i++) {
				String str = s1.nextLine();
				for(int j = 0 , len = str.length();j < len;j++) {
					char ch =  str.charAt(j);
					mask1[i] |= (1 << (ch - '0' ));
				}
			}
			for(int i=0;i<N2;i++) {
				String str = s1.nextLine();
				for(int j = 0 , len = str.length();j < len;j++) {
					char ch =  str.charAt(j);
					mask2[i] |= (1 << (ch - '0'));
				}
			}
			
			int favour = 0;
			for(int i=0;i<N1;i++) {
				// out.println("mask1 "  + Integer.toBinaryString(mask1[i]));
				for(int j=0;j<N2;j++) {
					// out.println("mask2 "  + Integer.toBinaryString(mask2[j]));
					int both = mask1[i] & mask2[j];
					int last = 0;
					for(int k=1;k<=9;k++)
						if((both & (1 << k)) != 0)
							last = k;
					favour += 1 - (last % 2);
				}
			}
			
			double prob = ((double) favour) / ((double) (N1 * N2));
			
			out.printf("%.3f\n", prob);
			
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