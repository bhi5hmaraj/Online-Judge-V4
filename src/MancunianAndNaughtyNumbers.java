import java.util.*;
import java.io.*;
class MancunianAndNaughtyNumbers
{


	/************************ SOLUTION STARTS HERE ***********************/


	static int N = (int)(1e5);
	static int numOfPrime[] = new int[N + 10];
	static 
	{
		numOfPrime[1] = 1;
		for(int i=2;i<=N;i++)
			if(numOfPrime[i] == 0)
				for(int j=i;j<=N;j+=i)
					numOfPrime[j]++;
	}

	static boolean isNaughty(int num){
		return String.valueOf(num).length() == numOfPrime[num];
	}
	private static void solve(FastScanner s1, PrintWriter out){

		int preCal[] = new int[N + 10];
		preCal[1] = 1;
		for(int i=2;i<=N;i++)
			preCal[i] = (isNaughty(i) ? 1 : 0) + preCal[i - 1];


		int t = s1.nextInt();
		while(t-->0)
			out.println(-(preCal[s1.nextInt() - 1] - preCal[s1.nextInt()]));

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