import java.util.*;
import java.io.*;
public class AntonandChess
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Pair {
		int x , y;
		char type;
		Pair(char type , int x , int y){
			this.x = x;
			this.y = y;
			this.type = type;
		}
		@Override
		public String toString() {
			return "TYPE : " + type + "(" + x + ", " + y +")";
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out) {

		int N = s1.nextInt();
		int Kx = s1.nextInt();
		int Ky = s1.nextInt();
		
		Pair arr[][] = new Pair[3][3];
		
		while(N-->0) {
			Pair curr = new Pair(s1.nextChar(), s1.nextInt(), s1.nextInt());
			if(curr.x == Kx) {
				if(curr.y > Ky && (arr[1][2] == null || curr.y < arr[1][2].y))
					arr[1][2] = curr;
				else if(curr.y < Ky && (arr[1][0] == null || curr.y > arr[1][0].y))
					arr[1][0] = curr;
			}
			else if(curr.y == Ky) {
				if(curr.x > Kx && (arr[2][1] == null || curr.x < arr[2][1].x))
					arr[2][1] = curr;
				else if(curr.x < Kx && (arr[0][1] == null || curr.x > arr[0][1].x))
					arr[0][1] = curr;
			}
			else if(curr.x - curr.y == Kx - Ky) {
				if(curr.x > Kx && (arr[2][2] == null || curr.x < arr[2][2].x))
					arr[2][2] = curr;
				else if(curr.x < Kx && (arr[0][0] == null || curr.x > arr[0][0].x))
					arr[0][0] = curr;
			}
			else if(curr.x + curr.y == Kx + Ky) {
				if(curr.x > Kx && (arr[2][0] == null || curr.x < arr[2][0].x))
					arr[2][0] = curr;
				else if(curr.x < Kx && (arr[0][2] == null || curr.x > arr[0][2].x))
					arr[0][2] = curr;
			}
		}
		
		char NO[][] = {{'R' , 'B' , 'R'} , {'B' , '*' , 'B'} , {'R' , 'B' , 'R'}};
		boolean flag = true;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++) 
				flag &= (arr[i][j] == null || arr[i][j].type == NO[i][j]); // a => b (a implies b)
		
		out.println(flag ? "NO" : "YES");
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