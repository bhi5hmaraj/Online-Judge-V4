import java.util.*;
import java.io.*;
public class Puzzle
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int R = s1.nextInt();
		int C = s1.nextInt();
		char grid[][] = new char[R][];
		for(int i=0;i<R;i++)
			grid[i] = s1.nextLine().toCharArray();
		
		int row[][] = new int[R][2];
		
		for(int r[] : row)
			Arrays.fill(r, -1);
		
		for(int i=0;i<R;i++){
			for(int j=0;j<C;j++){
				if(grid[i][j] == 'X') {
					row[i][0] = j;
					break;
				}
			}	
			
			for(int j=C-1;j>=0;j--){
				if(grid[i][j] == 'X') {
					row[i][1] = j;
					break;
				}
			}
		}	
		
		boolean flag = true;
		for(int i=0;i<R-1;i++) {
			if(row[i][0] != -1 && row[i + 1][0] != -1) {
				flag &= row[i][0] == row[i + 1][0];
				flag &= row[i][1] == row[i + 1][1];
			}
		}
		
				
		if(flag) {
			outer:
			for(int i=0;i<R;i++) {
				if(row[i][0] != -1) {
				for(int j=row[i][0];j <= row[i][1];j++)
					if(grid[i][j] == '.') {
						flag = false;
						break outer;
					}
				}
			}
		}
		
		
		out.println(flag ? "YES" : "NO");
			
			
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