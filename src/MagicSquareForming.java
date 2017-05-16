import java.util.*;
import java.io.*;
public class MagicSquareForming
{


	/************************ SOLUTION STARTS HERE ***********************/

	static boolean isCorner(int i, int j){
		return i != 1 && j != 1;	
	}
	
	private static void solve(FastScanner s1, PrintWriter out){

		int corner[][] = {
						 	{8,4,2,6},
						 	{4,8,6,2}
						 };
		
		int mid[][]    = {
							{1,3,9,7},
							{9,3,1,7}
						 };
		int pos[][] = { 
						{0 ,  0 , 3}, 
						{1 , -1 , 3},
						{1 ,  2 , 2}
					  };
		int arr[][] = new int[3][];
		for(int i=0;i<3;i++)
			arr[i] = s1.nextIntArray(3);
		
		int min = Integer.MAX_VALUE;
		for(int comp = 0; comp < 2;comp++){
		for(int rot = 0;rot < 4;rot++){
			int diff = 0;
			for(int i=0;i<3;i++){
				for(int j=0;j<3;j++){
					int magic;
					if(isCorner(i, j))
						magic = corner[comp][(pos[i][j] + rot) % 4];
					else if(i == 1 && j == 1)
						magic = 5;
					else
						magic = mid[comp][(pos[i][j] + rot) % 4];
						
					diff += Math.abs(arr[i][j] - magic);
				}
			}
			min = Math.min(min,diff);
		}
		}
		out.println(min);
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