import java.util.*;
import java.io.*;
public class SimilarStrings
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static String str;
	static int N;
	static String hash(int L , int R) {
		char ch[] = str.substring(L, R+1).toCharArray();
		char map[] = new char[10];
		char curr = 'a';
		for(int i=0;i<ch.length;i++) {
			if(map[ch[i] - 'a'] == 0)
				map[ch[i] - 'a'] = curr++;
			ch[i] = map[ch[i] - 'a'];
		}
		return new String(ch);
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		HashMap<String , Integer> freq = new HashMap<>();
		N = s1.nextInt();
		int Q = s1.nextInt();
		str = s1.nextLine();
		for(int i=0;i<N;i++)
			for(int j=i;j<N;j++){
				String hash = hash(i, j);
				freq.put(hash, freq.getOrDefault(hash, 0) + 1);
			}
		while(Q-->0)
			out.println(freq.get(hash(s1.nextInt() - 1, s1.nextInt() - 1)));

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