import java.util.*;
import java.io.*;
public class Mishkaandtrip
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean isCapital(int[] capital, int N){
		return Arrays.binarySearch(capital, N) >= 0;
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int K = s1.nextInt();
		long beauty[] = s1.nextLongArrayOneBased(N);
		int capitals[] = s1.nextIntArray(K);
		long sumCapital = 0;
		long sumNonCap = 0;
		for(int i=1;i<=N;i++){
			if(isCapital(capitals, i))
				sumCapital += beauty[i];
			else
				sumNonCap += beauty[i];
		}

		long sum = 0;
		for(int i=1;i<=N;i++){
			if(isCapital(capitals, i)){
				sum += (beauty[i] * (sumCapital - beauty[i]));
			}
		}
		sum /= 2L;  //Counted twice
		for(int i=1;i<=N;i++){
			if(isCapital(capitals, i)){
				sum += (beauty[i] * sumNonCap);
			}
		}
		for(int i=1;i<=N;i++){
			if(i != N){
				if(!isCapital(capitals, i) && !isCapital(capitals, i + 1))
					sum += (beauty[i] * beauty[i + 1]);
			}
			else{
				if(!isCapital(capitals, N) && !isCapital(capitals, 1))
					sum += (beauty[N] * beauty[1]);
			}
				
		}
		out.println(sum);
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