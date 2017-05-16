import java.util.*;
import java.io.*;
public class KthAncestor
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int log(int N){
		return 31 - Integer.numberOfLeadingZeros(N);
	}
	
	static int DP[][];
	static final int MAX = (int)(1e5) + 10;
	
	static void buildSparse() {
		for (int i = 1; i < DP.length; i++) {
			for (int j = 1; j < MAX; j++) {
				DP[i][j] = DP[i - 1][DP[i - 1][j]];
			}
		}
	}

	static void updateSparse(int idx, int newParent) {
		DP[0][idx] = newParent;
		for (int i = 1; i < DP.length; i++) {
			DP[i][idx] = DP[i - 1][DP[i - 1][idx]];
		}
	}
	static int findAncestor(int idx , int k){
		while(k > 0){
			int log = log(k);
			idx = DP[log][idx];
			k -= (1 << log);
		}
		return idx;
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		while(t-->0){
			
			int E = s1.nextInt();
			DP = new int[log(MAX) + 1][MAX + 1];
			
			
			while(E-->0)
				DP[0][s1.nextInt()] = s1.nextInt();
			
			buildSparse();
			int Q = s1.nextInt();
			while(Q-->0){
				int type = s1.nextInt();
				if(type == 0){
					int par = s1.nextInt();
					updateSparse(s1.nextInt(), par);
				}
				else if(type == 1)
					updateSparse(s1.nextInt(), 0);
				else
					out.println(findAncestor(s1.nextInt(), s1.nextInt()));
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