import java.util.*;
import java.io.*;
public class Ilyaandtictactoegame
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static boolean isValid(int i , int j) {
		return i >= 0 && i < 4 && j >= 0 && j < 4;
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		char grid[][] = new char[4][];
		for(int i=0;i<4;i++)
			grid[i] = s1.nextLine().toCharArray();
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				int freq[] = new int[500];
				freq[grid[i][j]]++;
				freq[grid[i][j + 1]]++;
				freq[grid[i][j + 2]]++;
				if(freq[(int)'x'] == 2 && freq[(int)('.')] == 1) {
					out.println("YES");
					return;
				}
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=0;j<2;j++) {
				int freq[] = new int[500];
				freq[grid[j][i]]++;
				freq[grid[j + 1][i]]++;
				freq[grid[j + 2][i]]++;
				if(freq[(int)'x'] == 2 && freq[(int)('.')] == 1) {
					out.println("YES");
					return;
				}
			}
		}
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(isValid(i + 1, j + 1) && isValid(i + 2, j + 2)) {
					int freq[] = new int[500];
					freq[grid[i][j]]++;
					freq[grid[i + 1][j + 1]]++;
					freq[grid[i + 2][j + 2]]++;
					if(freq[(int)'x'] == 2 && freq[(int)('.')] == 1) {
						out.println("YES");
						return;
					}
				}
				if(isValid(i + 1, j - 1) && isValid(i + 2, j - 2)) {
					int freq[] = new int[500];
					freq[grid[i][j]]++;
					freq[grid[i + 1][j - 1]]++;
					freq[grid[i + 2][j - 2]]++;
					if(freq[(int)'x'] == 2 && freq[(int)('.')] == 1) {
						out.println("YES");
						return;
					}
				}
			}
		}
		
		out.println("NO");
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