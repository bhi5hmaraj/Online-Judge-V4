import java.util.*;
import java.io.*;
class DIVMAC_slow
{


	/************************ SOLUTION STARTS HERE ***********************/
	static final int MAX = (int)(1e6);
	static boolean isPrime[];
	static int loPrime[];

	static {
		isPrime = new boolean[MAX + 10];  // one is treated as a prime
		loPrime = new int[MAX + 10];
		Arrays.fill(isPrime, true);
		loPrime[1] = 1;
		for(int i=2;i*i<=MAX;i++){
			if(loPrime[i] == 0){
				loPrime[i] = i;
				for(int j = i*i ;j <= MAX;j += i){
					loPrime[j] = loPrime[j] == 0 ? i : loPrime[j];
					isPrime[j] = false;
				}
			}
		}
		for(int i=2;i <= MAX;i++)
			loPrime[i] = loPrime[i] == 0 ? i : loPrime[i];
	}


	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();
		while(t-->0)
		{
			int N = s1.nextInt();
			int Q = s1.nextInt();
			int arr[] = s1.nextIntArrayOneBased(N);
			while(Q-->0){
				int type = s1.nextInt();
				int L = s1.nextInt();
				int R = s1.nextInt();
				if(type == 0){
					for(int i=L;i<=R;i++)
						arr[i] /= loPrime[arr[i]];
				}
				else{
					int max = 1;
					for(int i=L;i<=R;i++)
						max = Math.max(max,loPrime[arr[i]]);

					out.print(max + " ");
				}
			}
			out.println();
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
	}    
	 */
	static String outputFile = "output_DIVMAC_correct.txt";
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}