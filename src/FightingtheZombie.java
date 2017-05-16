import java.util.*;
import java.io.*;
public class FightingtheZombie
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int MAX_DICE = 6;
		int sides[] = {4, 6, 8, 10, 12, 20} ;
		int MAX_X = 20;
		double DP[][][] = new double[MAX_DICE][][]; 
		// DP[i][j][k] = In dice with side 2*i + 4 , it holds the probability of getting sum greater than k with j rolls 
		for(int i=0;i<MAX_DICE;i++) {
			int y = sides[i];
			DP[i] = new double[MAX_X + 1][MAX_X*y + 1];
			DP[i][0][0] = 1.0; // Base Case
		}

		for(int i=0;i<MAX_DICE;i++) { // Calculate Probability
			int y = sides[i];
			for(int rolls = 1;rolls <= MAX_X;rolls++) {
				for(int sum = y * rolls;sum >= 0;sum--) {
					for(int poss = 1;poss <= y;poss++) {
						DP[i][rolls][sum] += (sum - poss >= 0) ? ((1.0 / y) * DP[i][rolls - 1][sum - poss]) : 0.0;
					}
				}
			}
		}
		
		for(int i=0;i<MAX_DICE;i++) { // Calculate Suffix Sums
			int y = sides[i];
			for(int rolls = 1;rolls <= MAX_X;rolls++) {
				for(int sum = y * rolls - 1;sum >= 0;sum--) {
					DP[i][rolls][sum] += DP[i][rolls][sum + 1];
				}
			}
			
			/*
			out.println("Total Sides of the dice : " + y);
			for(double d[] : DP[i]) {
				for(double prob : d)
					out.printf("%.14f ", prob);
				out.println();
			}
			*/
		}

		for(int tc = 1 , T = s1.nextInt();tc <= T;tc++) {

			int H = s1.nextInt();
			int S = s1.nextInt();
			double maxProb = 0.0;

			while(S-->0) {
				String str = s1.next();
				int sign = str.indexOf('-') >= 0 ? -1 : 1;
				StringTokenizer st = new StringTokenizer(str, "d|+|-");
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				int Z = !st.hasMoreTokens() ? 0 : sign * Integer.parseInt(st.nextToken());
				// System.out.printf("X = %d Y = %d Z = %d\n", X,Y,Z);
				int dice = Y == 20 ? 5 : (Y - 4) / 2;
				int reqSum = Math.max(H - Z,0);
				double prob = reqSum > X * Y ? 0.0 : DP[dice][X][reqSum];

				maxProb = Math.max(maxProb,prob);
			}

			out.printf("Case #%d: %f\n", tc , maxProb);
		}
		
	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/ new FileInputStream("fighting_the_zombie.txt"));
		PrintWriter out = 
				new PrintWriter(/*new BufferedWriter(new OutputStreamWriter(System.out)), false*/ "out.txt"); 
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