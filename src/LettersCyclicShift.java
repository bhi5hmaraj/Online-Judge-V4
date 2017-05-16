import java.util.*;
import java.io.*;
public class LettersCyclicShift
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String str = s1.nextLine();
		int N = str.length();
		char ans[] = new char[N];
		int start = -1 , end = -1;
		for(int i=0;i < N;i++)
			if(str.charAt(i) != 'a'){
				start = i;
				end = i;
				break;
			}
		
		while(end != -1 && end < N && str.charAt(end) != 'a')
			end++;
		
		if(end >= 0)
			end--;
		
		if(start == -1 && end == -1)
			start = end = N - 1;
		
		for(int i=0;i < N;i++){
			
			int ch = str.charAt(i) - 'a';
			if(i >= start && i <= end){
				int back = (26 + ch - 1) % 26;
				ans[i] = (char)('a' + back);
			}
			else
				ans[i] = (char)('a' + ch);
		}
		
		out.println(new String(ans));
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