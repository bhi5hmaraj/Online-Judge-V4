import java.util.*;
import java.io.*;
public class uva_11085
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int rowsPlaced[];
	static int assigned[];

	static int minSteps(int idx) {
		if(idx > 8)
			return 0;
		else {
			int min = (int)(1e5);
			for(int i=1;i<=8;i++) {
				boolean flag = true;
				for(int j=1;j<idx;j++)
					flag &= assigned[j] != i && j + assigned[j] != idx + i && j - assigned[j] != idx - i;
				
				if(flag) {
					assigned[idx] = i;
					min = Math.min(min,(i == rowsPlaced[idx - 1] ? 0 : 1) + minSteps(idx + 1)); 
				}
			}
				
			return min;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		String line;
		int tc = 1;
		while((line = s1.nextLine()) != null) {
			rowsPlaced = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
			assigned = new int[9];
			out.println("Case " + (tc++) + ": " + minSteps(1));
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