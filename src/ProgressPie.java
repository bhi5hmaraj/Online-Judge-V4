import java.util.*;
import java.awt.geom.Point2D;
import java.io.*;
public class ProgressPie
{


	/************************ SOLUTION STARTS HERE ***********************/

	/*	static final double EPS = 1e-6;
	static boolean less(double a , double b) {
		return b - a >= 0 &&  b - a <= EPS;
	}
	 */
	private static void solve(FastScanner s1, PrintWriter out){

		for(int tc = 1 , T = s1.nextInt();tc <= T;tc++) {
			int P = s1.nextInt();
			int x = s1.nextInt() - 50;
			int y = s1.nextInt() - 50;
			Point2D.Double origin = new Point2D.Double(0, 0);
			double angleMax = Math.toRadians((P / 100.0) * 360.0);
			double radiusSq = 2500.0;
			double distSq = origin.distanceSq(x, y);
			if(P == 0)
				out.println("Case #" + tc + ": white");
			else if(distSq == 0.0)
				out.println("Case #" + tc + ": " + (P > 0 ? "black" : "white"));
			else {
				double angle = Math.PI - Math.atan2(x, -y);
				// out.printf("radius sq = %f distsq = %f angleMax = %f angle = %f\n", radiusSq , distSq , angleMax , angle);
				out.println("Case #" + tc + ": " + ((distSq <= radiusSq) && (angle <= angleMax) ? "black" : "white"));
			}
		}

	}



	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
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