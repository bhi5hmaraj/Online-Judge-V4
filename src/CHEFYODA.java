import java.util.*;
import java.io.*;
class CHEFYODA
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int x90[] = { -1, 0, 1, 0 }; // up , right , down and left
	static int y90[] = { 0, 1, 0, -1 };

	static int x45[] = { 1, -1, -1, 1 };
	static int y45[] = { 1, -1, 1, -1 };

	static int N , M;
	static boolean isValid(int i , int j) {
		return i >= 0 && i < N && j >= 0 && j < M;
	}
	
	static void print() {
		for(boolean b[] : marked)
			System.out.println(Arrays.toString(b));
		System.out.println();
	}
	
	static boolean marked[][];
	static boolean rec1(int i , int j , boolean turn) { // true - chef , false - yoda
		// System.out.println("before");
		// print();
		marked[i][j] = true;
		// System.out.println("after");
		// print();
		if(turn) { // chef
			boolean res = false;
			for(int k = 0;k<4;k++) {
				int toX = i + x90[k];
				int toY = j + y90[k];
				if(isValid(toX, toY) && !marked[toX][toY])
					res |= rec1(toX, toY, !turn);
			}
			marked[i][j] = false;
			return res;
		}
		else {	// yoda
			boolean res = true;
			for(int k=0;k<4;k++) {
				int toX = i + x90[k];
				int toY = j + y90[k];
				if(isValid(toX, toY) && !marked[toX][toY]) 
					res &= rec1(toX, toY, !turn);
				
			}
			marked[i][j] = false;
			return res;
		}
	}
	static boolean rec2(int i , int j , boolean turn) { // true - chef , false - yoda
		// System.out.println("before");
		// print();
		marked[i][j] = true;
		// System.out.println("after");
		// print();
		if(turn) { // chef
			boolean res = false;
			for(int k = 0;k<4;k++) {
				int toX = i + x45[k];
				int toY = j + y45[k];
				if(isValid(toX, toY) && !marked[toX][toY]) 
					res |= rec2(toX, toY, !turn);
			}
			marked[i][j] = false;
			return res;
		}
		else {	// yoda
			boolean res = true;
			for(int k=0;k<4;k++) {
				int toX = i + x45[k];
				int toY = j + y45[k];
				if(isValid(toX, toY) && !marked[toX][toY]) 
					res &= rec2(toX, toY, !turn);
			}
			marked[i][j] = false;
			return res;
		}
	}
	

	static long fact(long n) {
		return n <= 1 ? 1 : n * fact(n - 1);
	}
	static long nCk(long n , long k) {
		return fact(n) / (fact(k) * fact(n - k));
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			M = s1.nextInt();
			int P = s1.nextInt();
			int K = s1.nextInt();
			if(!(N <= 5 && M <= 5 && K <= 5))
				throw new RuntimeException("Work in Progress");
			marked = new boolean[N][M];
			boolean move90 = rec1(0, 0, true);
			boolean move45 = rec2(0, 0, true);
			double prob;
			if(move90 && move45)
				prob = 1.0;
			else if(!move90 && !move45)
				prob = 0.0;
			else {
				long sum = 0;
				for(int i=P;i<=K;i++)
					sum += nCk(K, i);
				
				prob = sum / (1.0 * (1L << K));
			}
			
			if(!(prob >= 0 && prob <= 1))
				throw new RuntimeException("Wrong PROB");
			
			// out.println(prob);
			out.printf("N = %d M = %d P = %d K = %d , %.6f\n",N , M , P , K ,  prob);
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