import java.util.*;
import java.io.*;
public class HiddenWord
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		String str = s1.nextLine();
		int pos[] = new int[26];
		Arrays.fill(pos, -1);
		int start = -1 , end = -1;
		for(int i = 0 , len = str.length();i < len;i++) {
			char ch =  str.charAt(i);
			if(pos[ch - 'A'] != -1) {
				start = pos[ch - 'A'];
				end = i;
				break;
			}
			else
				pos[ch - 'A'] = i;
		}

		if(end - start == 1)
			out.println("Impossible");
		else {
			char grid[][] = new char[2][13];
			String sandWich = str.substring(start + 1, end);
			int size = sandWich.length();
			int r = 0 , c = 13 - (size / 2);
			int dir = 1;
			grid[0][c - 1] = str.charAt(start);
			if(size == 1) {
				grid[1][c - 1] = sandWich.charAt(0);
				dir = -1;
				r = 1;
				c = 11;
			}
			else {
				for(int i=0;i<size;i++) {   // Fill sand wich
					grid[r][c] = sandWich.charAt(i);
					//out.println("r " + r + " c " + c + " char " + grid[r][c]);
					if(c == 12 && dir == 1) {
						dir = -1;
						r = 1;
					}
					else
						c += dir;
				}
			}
			String before = str.substring(0, start);
			for(int i=before.length()-1;i >= 0;i--) {
				grid[r][c] = before.charAt(i);
				//out.println("r " + r + " c " + c + " char " + grid[r][c]);
				if(c == 0 && dir == -1) {
					dir = 1;
					r = 0;
				}
				else
					c += dir;
			}
			String after = str.substring(end + 1);
			for(int i = after.length() - 1;i >= 0;i--) {
				grid[r][c] =  after.charAt(i);
				//out.println("r " + r + " c " + c + " char " + grid[r][c]);
				if(c == 0 && dir == -1) {
					dir = 1;
					r = 0;
				}
				else
					c += dir;
			}
			
			
			out.println(new String(grid[0]));
			out.println(new String(grid[1]));
		}

	}
// ADEFGHIJKLMNOPBQRSCTUVWXYZA


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