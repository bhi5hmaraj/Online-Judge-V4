import java.util.*;
import java.io.*;
public class UniqueSubarrays
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();
		while(t-->0){

			int N = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			HashMap<Integer,Integer> last = new HashMap<>();
			int max = -1;
			long cnt = 0;
			for(int i=0;i<N;i++){
				if(last.containsKey(arr[i])){
					max = Math.max(max,last.get(arr[i]));
				}
				last.put(arr[i], i);
				long len = i - max;
				cnt += (len * (len + 1)) / 2;
			}

			out.println(cnt);
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
	
/*	
	static String outputFile = "unique.txt";
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
		solve(in, out);
		in.close();
		out.close();
	}     
*/	
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