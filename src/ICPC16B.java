import java.util.*;
import java.io.*;
class ICPC16B
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0)
		{
			int N = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			
			if(N == 1) {
				out.println("yes");
				continue;
			}
			
			int cntPos = 0 , cntNeg = 0; // > 1 & < -1
			int cntOne = 0 , cntMinOne = 0;
			boolean allModOne = true;
			for(int a : arr) {
				
				if(a != 0)
					allModOne &= Math.abs(a) == 1;
				
				if(a == 1)
					cntOne++;
				if(a == -1)
					cntMinOne++;
				if(a > 1)
					cntPos++;
				if(a < -1)
					cntNeg++;
			}
			
			if((cntPos == 0 && cntNeg == 1) ^ (cntNeg == 0 && cntPos == 1)) {
				if(cntPos > 0 && cntMinOne > 0)
					out.println("no");
				else if(cntNeg > 0 && cntMinOne > 0)
					out.println("no");
				else
					out.println("yes");
			}
			else if(allModOne) {
				if(cntMinOne > 1 && cntOne == 0)
					out.println("no");
				else
					out.println("yes");
			}
			else
				out.println("no");
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