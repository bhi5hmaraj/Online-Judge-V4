import java.util.*;
import java.io.*;
public class AntiprimeNumbers
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int MAX    = (int)(2e7);
	static int numDivisor[] = new int[MAX + 10];
	static int bigPrime[]   = new int[MAX + 10];
	static void preCalculateDivisors(){

		for (int i = 1; i <= MAX; i++)
			for (int j = i; j <= MAX; j += i)
				numDivisor[j]++;

	}

	static void mineAntiPrimes() {
		preCalculateDivisors();
		int max = 0;
		for (int i = 1; i <= MAX; i++)
			if (numDivisor[i] > max) {
				System.out.println(i);
				max = numDivisor[i];
			}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int antiPrimes[] = {1,2,4,6,12,24,36,48,60,120,180,240,360,720,840,1260,1680,2520,5040,7560,10080,15120,20160,25200,27720,45360,50400,55440,83160,110880,166320,221760,277200,332640,498960,554400,665280,720720,1081080,1441440,2162160,2882880,3603600,4324320,6486480,7207200,8648640,10810800,14414400,17297280};
		//Used the mineAntiPrimes() function
		TreeSet<Integer> set = new TreeSet<>();
		for(int p:antiPrimes)
			set.add(p);

		int Q = s1.nextInt();
		while(Q-->0)        
			out.println(set.ceiling(s1.nextInt()));
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