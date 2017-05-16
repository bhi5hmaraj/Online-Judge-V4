import java.util.*;
import java.io.*;
public class GenomeDecoding
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		String str = s1.nextLine();
		if(N % 4 != 0)
			out.println("===");
		else {
			int freq[] = new int[26];
			char genome[] = {'A','T','G','C'};
			for(int i = 0 , len = str.length();i < len;i++) {
				char ch =  str.charAt(i);
				boolean in = false;
				for(char g : genome)
					in |= g == ch;
				if(in)
					freq[ch - 'A']++;
			}
			int per = N / 4;
			int req[] = new int[26];
			for(char ch : genome) {
				if(freq[ch - 'A'] > per) {
					out.println("===");
					return;
				}
				else {
					req[ch - 'A'] = per - freq[ch - 'A'];
				}
			}
			char ans[] = str.toCharArray();
			for(int i=0;i<N;i++) {
				if(ans[i] == '?') {
					for(int j=0;j<26;j++)
						if(req[j] > 0) {
							ans[i] = (char)('A' + j);
							req[j]--;
							break;
						}
				}
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}