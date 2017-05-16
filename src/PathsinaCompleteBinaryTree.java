import java.util.*;
import java.io.*;
public class PathsinaCompleteBinaryTree {


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		long N = s1.nextLong();
		int Q = s1.nextInt();
		while(Q-->0) {
			long u = s1.nextLong();
			String move = s1.nextLine();

			long down = (N + 1) >> 2;
			long lo = 1 , hi = N;
			ArrayDeque<Integer> stack = new ArrayDeque<>();

			while(lo <= hi) {
				long mid = (lo + hi) >> 1;
				if(u < mid) {
					hi = mid - 1;
					stack.push(1);
				}
				else if(u > mid) {
					lo = mid + 1;
					stack.push(-1);
				}
				else
					break;
	
				down >>= 1;
			}

			for(int i = 0 , len = move.length();i < len;i++) {
				char ch =  move.charAt(i);
				// System.out.print("from = " + u);
				switch(ch) {
				case 'L':
					if(down > 0) {
						u -= down;
						down >>= 1;
						stack.push(1);
					}
					break;
				case 'R':
					if(down > 0) { 
						u += down;
						down >>= 1;
						stack.push(-1);
					}
					break;
				case 'U':
					if(!stack.isEmpty()) {
						down = down == 0 ? 1 : down << 1;
						u += (stack.pop() * down);
					}
					break;
				}
				// System.out.println(" to = "  + u);
				// System.out.println(stack + " down " + down);
			}

			out.println(u);
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