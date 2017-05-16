import java.util.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.*;
public class PeterandSnowBlower
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		Point2D.Double P = new Point2D.Double(s1.nextInt(), s1.nextInt());
		Point2D.Double arr[] = new Point2D.Double[N];
		for(int i=0;i<N;i++)
			arr[i] = new Point2D.Double(s1.nextInt(), s1.nextInt());
		
		double R = Double.MIN_VALUE;
		for(int i=0;i<N;i++)
			R = Math.max(R, P.distance(arr[i]));
		
		double r = Double.MAX_VALUE;
		for(int i=0;i<N;i++){
			Line2D.Double line = new Line2D.Double(arr[i], arr[(i + 1) % N]);
			r = Math.min(r, line.ptSegDist(P));
		}
		double ans = Math.PI * ((R * R) - (r * r));
		out.println(ans);
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