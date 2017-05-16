import java.util.*;
import java.io.*;
public class BobandBen
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	// true is ben and false is bob turn
	
	static void rec(int state[] , boolean flag , String operation) {
		
		boolean lost = true;
		for(int a : state)
			lost &= a == 0;
		
		if(lost)
			System.out.println(" OPERATIONS : " + operation + " " + (flag ? "BOB" : "BEN") + " WON");
		else {
			
			for(int i=0;i<state.length;i++) {
				if(state[i] >= 1) {
					int state1[] = state.clone();
					state1[i] = 0;
					rec(state1, !flag, operation + (flag ? "BEN" : "BOB") + " deleted whole tree " + i + " ==> ");
					int state2[] = state.clone();
					state2[i]--;
					rec(state2, !flag, operation + (flag ? "BEN" : "BOB") + " deleted a leaf from tree " + i + " ==> ");
				}
			}
			
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			int size[] = new int[N];
			for(int i=0;i<N;i++) {
				size[i] = s1.nextInt();
				s1.nextInt();
			}
			rec(size, false, "");
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