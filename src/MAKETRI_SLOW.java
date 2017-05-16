import java.util.*;
import java.io.*;
class MAKETRI_SLOW
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static void solve(FastScanner s1, PrintWriter out){
		int tc = s1.nextInt();
		while(tc-->0) {
			int N = s1.nextInt();
			if(N > 100) throw new RuntimeException("work in progress");
			long L = s1.nextLong();
			long R = s1.nextLong();

			long arr[] = s1.nextLongArray(N);
			int cnt = 0;
			for(long i=L;i<=R;i++) {
				boolean flag = false;
				outer:
					for(int j=0;j<N;j++)
						for(int k=j+1;k<N;k++)
							if(arr[j] + arr[k] > i && arr[j] + i > arr[k] && arr[k] + i > arr[j]) {
								flag = true;
								break outer;
							}

				//if(flag) out.println(i + " AC");
				cnt += flag ? 1 : 0;
			}

			out.println(cnt);
		}
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/
	/*
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve(in, out);
		in.close();
		out.close();
	}    */
	static String outputFile = "output_MAKETRI_AC.txt";
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
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