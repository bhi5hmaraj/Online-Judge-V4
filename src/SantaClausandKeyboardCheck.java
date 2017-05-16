import java.util.*;
import java.io.*;
public class SantaClausandKeyboardCheck
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		char str1[] = s1.nextLine().toCharArray();
		char str2[] = s1.nextLine().toCharArray();
		int N = str1.length;
		int map[] = new int[26];
		
		for(int i=0;i<26;i++) map[i] = i;
		//
		char change[][] = new char[13][2];
		int sz = 0;
		for(int i=0;i<N;i++) {
			if(map[str1[i] - 'a'] != (str2[i] - 'a')) {
				if(map[str1[i] - 'a'] == str1[i] - 'a' && map[str2[i] - 'a'] == str2[i] - 'a') {
					map[str1[i] - 'a'] = str2[i] - 'a';
					map[str2[i] - 'a'] = str1[i] - 'a';
					change[sz][0] = str1[i];
					change[sz][1] = str2[i];
					sz++;
				}
				else {
					out.println(-1);
					return;
				}
			}
		}
		
		for(int i=0;i<N;i++)
			if(str2[i] != (char)('a' + map[str1[i] - 'a'])) {
				out.println(-1);
				return;
			}
				
		
		out.println(sz);
		for(int i=0;i<sz;i++)
			out.println(change[i][0] + " " + change[i][1]);
		
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