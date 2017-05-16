import java.util.*;
import java.io.*;
public class TwoCharacters
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean isT(StringBuilder sb) {
		char first = sb.charAt(0);
		for(int i=0,len=sb.length();i<len;i++)
			if((i % 2 == 0 && sb.charAt(i) != first) || (i % 2 == 1 && sb.charAt(i) == first))
				return false;
		
		return true;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		String str = s1.nextLine();
		boolean marked[] = new boolean[26];
		char unique[] = new char[26];
		int size = 0;
		for(int i=0;i<N;i++)
			if(!marked[str.charAt(i) - 'a']) {
				marked[str.charAt(i) - 'a'] = true;
				unique[size++] = str.charAt(i);
			}
		
		StringBuilder ans = new StringBuilder();
		for(int i=0;i<size;i++) {
			for(int j=i+1;j<size;j++) {
				StringBuilder sb = new StringBuilder();
				for(int k=0;k<N;k++)
					if(str.charAt(k) == unique[i] || str.charAt(k) == unique[j])
						sb.append(str.charAt(k));
				
				if(isT(sb) && sb.length() > ans.length()) {
					ans = sb;
				}
			}
		}
		out.println(ans.length());
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