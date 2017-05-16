import java.util.*;
import java.io.*;
class ASTRING
{


	/************************ SOLUTION STARTS HERE ***********************/



	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();
		while (t-- > 0) {
			TreeSet<Integer>[] pos = (TreeSet<Integer>[]) new TreeSet[26];
			for (int i = 0; i < 26; i++)
				pos[i] = new TreeSet<>();

			String str = s1.nextLine();
			int K = s1.nextInt();
			int len = str.length();
			
			
			for (int i = 0; i < len; i++)
				pos[str.charAt(i) - 'a'].add(i + 1);

			char ans[] = new char[K];
			int curr = 0;

			for (int i = 0, rem = K - 1; i < K; i++, rem--) {

				char small = '*';  //Just a dummy character
				for (int j = 0; j < 26; j++) {
					Integer ceil = pos[j].higher(curr);
					if (ceil != null && (len - ceil.intValue()) >= rem) {
						small = (char) ('a' + j);
						curr = ceil;
						break;
					}
				}
				ans[i] = small;

			}

			out.println(new String(ans));
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