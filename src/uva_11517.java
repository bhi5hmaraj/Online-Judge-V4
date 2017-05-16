import java.util.*;
import java.io.*;
public class uva_11517
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final int INF = (int)(1e6);
	static int value[];
	static int MAX = (10000 * 2);
	static int memo[][];
	static int findOpt(int idx , int ammount) {
		
		if(idx == 0) {
			if(ammount == 0)
				return 0;
			else if(ammount == value[0])
				return 1;
			else
				return INF;
		}
		else if(memo[idx][ammount] != -1)
			return memo[idx][ammount];
		else 
			return memo[idx][ammount] = Math.min(findOpt(idx - 1, ammount),
										(ammount >= value[idx]) 
										? (1 + findOpt(idx - 1, ammount - value[idx])) : INF);
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int ammount = s1.nextInt();
			int M = s1.nextInt();
			memo = new int[M][MAX];
			for(int i=0;i<M;i++)
				Arrays.fill(memo[i], -1);
			
			value = s1.nextIntArray(M);
			for(int i = ammount;i<=MAX;i++){
				int coinsNeeded = findOpt(M - 1, i);
				if(coinsNeeded < INF) {
					out.println(i + " " + coinsNeeded);
					break;
				}
			}
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