import java.util.*;
import java.io.*;
public class AbilityToConvert
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static long num[];
	static long base;
	static long memo[][];
	static int maxPow;
	static final long INF = (long) 2e18;
	static final long LIMIT = (long) 1e18;
	
	static long rec(int idx , int pow /*, String str*/) {
		// System.out.println("idx " + idx + " pow " + pow + " str " + str);
		if(idx < 0) {
			// System.out.println(str);
			return 0;
		}
		else if(pow > maxPow)
			return INF;
		else if(memo[idx][pow] != -1)
			return memo[idx][pow];
		else {
			long min = INF;
			long tenPow = 1;
			long curr = 0;
			
			
			long basePow = 1;
			for(int i=0;i<pow;i++) basePow *= base;
			
			if(num[idx] == 0) {
				long next = rec(idx - 1, pow + 1 /*, str + curr + " "*/);
				if(next != INF && (curr <= LIMIT / basePow) && (curr * basePow) + next <= LIMIT)
					min = Math.min(min,(curr * basePow) + next);
			}
			
			for(int i=0;idx - i >= 0 && tenPow < base;i++) {
				
				if(num[idx - i] != 0)
					curr += (num[idx - i] * tenPow);

				tenPow *= 10L;
				
				// System.out.println("curr " + curr + " idx "+ idx);
				
				if(curr >= base)
					break;
				
				if(num[idx - i] != 0) {
					long next = rec(idx - i - 1, pow + 1/*, str + curr + " "*/);
					if(next != INF && (curr <= LIMIT / basePow) && (curr * basePow) + next <= LIMIT)
						min = Math.min(min,(curr * basePow) + next);
				}
				
			}
			
			return memo[idx][pow] = min;
		}
			
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		base = s1.nextLong();
		String k = s1.nextLine();
		
		num = new long[k.length()];
		for(int i = 0 , len = k.length();i < len;i++) {
			char ch =  k.charAt(i);
			num[i] = Character.getNumericValue(ch);
		}
		
		
		maxPow = (int) (Math.log10(LIMIT) / Math.log10(base));
		memo = new long[k.length()][maxPow + 1];
		for(long a[] : memo)
			Arrays.fill(a, -1);
		
		// out.println("max Pow " + maxPow);
		// out.println("min value " + rec(k.length() - 1, 0, ""));
		out.println(rec(k.length() - 1, 0));
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