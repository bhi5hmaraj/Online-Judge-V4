import java.util.*;
import java.io.*;
public class Draughts
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair {
		int r , c;
		Pair(int r , int c){
			this.r = r;
			this.c = c;
		}
		@Override
		public int hashCode() {
			return Objects.hash(r , c);
		}
		@Override
		public boolean equals(Object obj) {
			Pair p = (Pair) obj;
			return r == p.r && c == p.c;
		}
		@Override
		public String toString() {
			return "[" + r + ", " + c + "]";
		}
	}

	static char grid[][];
	static int R = 10, C = 10;
	static int x[] = {1,1,-1,-1};
	static int y[] = {1,-1,-1,1};
	static int sx , sy;
	static int rec(int cx , int cy) {
		int max = 0;
		// System.out.printf("cx = %d cy = %d , set = %s\n", cx , cy , blacks.toString());
		for(int i=0;i<4;i++) {	
			Pair p = new Pair(cx + x[i], cy + y[i]);
			if(isValid(cx + x[i], cy + y[i], 'B') && (isValid(cx + 2*x[i], cy + 2*y[i], '#') || (isValid(cx + 2*x[i], cy + 2*y[i], 'W') && cx + 2*x[i] == sx &&  cy + 2*y[i] == sy) ) 
					&& blacks.contains(p)) {
				blacks.remove(p);
				max = Math.max(max,1 + rec(cx + 2*x[i], cy + 2*y[i]));
				blacks.add(p);
			}
		}
		return max;
	}

	static boolean isValid(int i , int j , char ch) {
		return i >= 0 && i < C && j >= 0 && j < C && grid[i][j] == ch; 
	}

	static HashSet<Pair> blacks;

	private static void solve(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {
			s1.nextLine();
			grid = new char[R][];
			for(int i=0;i<R;i++)
				grid[i] = s1.nextLine().toCharArray();

			blacks = new HashSet<>();
			for(int i=0;i<R;i++){
				for(int j=0;j<C;j++){
					if(grid[i][j] == 'B')
						blacks.add(new Pair(i, j));
				}	
			}

			int max = 0;
			for(int i=0;i<R;i++){
				for(int j=0;j<C;j++){
					if(grid[i][j] == 'W') {
						sx = i;
						sy = j;
						max = Math.max(max,rec(i, j));
					}
				}	
			}

			out.println(max);
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