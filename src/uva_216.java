import java.util.*;
import java.awt.geom.Point2D;
import java.io.*;
public class uva_216
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static double cost[][];
	static double memo[][];
	static int V;
	static double TSP(int mask , int prev) {
		
		if(memo[mask][prev] != -1)
			return memo[mask][prev];
		else {
			double dist = Double.MAX_VALUE;
			for(int i = 0;i < V;i++)
				if(i != prev && (mask & (1 << i)) != 0)
					dist = Math.min(dist,cost[prev][i] + TSP(mask ^ (1 << prev), i));
			
			return memo[mask][prev] = dist;
		}
		
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int test = 0;
		while((V = s1.nextInt()) != 0) {
			Point2D.Double arr[] = new Point2D.Double[V];
			cost = new double[V][V];
			for(int i=0;i<V;i++)
				arr[i] = new Point2D.Double(s1.nextInt(), s1.nextInt());
			for(int i=0;i<V;i++)
				for(int j=0;j<V;j++)
					cost[i][j] = arr[i].distance(arr[j]);
			
			int mask = (1 << V) - 1;
			memo = new double[1 << V][V];
			for(int i=0;i < memo.length;i++)
				Arrays.fill(memo[i], -1);
			for(int i=0;i<V;i++)
				memo[1 << i][i] = 0;
			for(int i=0;i<V;i++)
				TSP(mask, i);
			
			int curr = 0;
			for(int i=0;i<V;i++)
				curr = memo[mask][i] < memo[mask][curr] ? i : curr;
			
			double minTotalDist = memo[mask][curr] + (16.0 * (V - 1));
			
			out.println("**********************************************************");
			out.println("Network #" + (++test));
			while((mask ^ (1 << curr)) != 0) {
				for(int i=0;i<V;i++) {
					if(memo[mask][curr] == memo[mask ^ (1 << curr)][i] + cost[i][curr]) {
						out.printf("Cable requirement to connect (%.0f,%.0f) to (%.0f,%.0f) is %.2f feet.\n", 
									arr[curr].x , arr[curr].y , arr[i].x , arr[i].y , (cost[curr][i] + 16.0));
						mask ^= (1 << curr);
						curr = i;
						break;
					}
				}
			}
			out.printf("Number of feet of cable required is %.2f.\n", minTotalDist);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}