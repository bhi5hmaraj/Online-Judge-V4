import java.util.*;
import java.awt.geom.Point2D;
import java.io.*;
public class Archery
{


	/************************ SOLUTION STARTS HERE ***********************/



	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int cnt[] = new int[N+2];
		TreeMap<Double,Integer> map = new TreeMap<>();
		int radius[] = s1.nextIntArray(N);
		Arrays.parallelSort(radius);
		for(int i=0;i<N;i++)
			map.put((double)(radius[i]), i);

		int M = s1.nextInt();
		while(M-->0)
		{
			double d1 = Point2D.distance(s1.nextDouble(), s1.nextDouble(), 0.0, 0.0);
			double d2 = Point2D.distance(s1.nextDouble(), s1.nextDouble(), 0.0, 0.0);
			double L = Math.min(d1,d2);
			double R = Math.max(d1,d2);
			Map.Entry<Double,Integer> e1 = map.ceilingEntry(L); 
			Map.Entry<Double,Integer> e2 = map.floorEntry(R);
			if(e1 != null && e2 != null && e1.getKey().compareTo(e2.getKey()) <= 0)
			{
				cnt[e1.getValue()]++;
				cnt[e2.getValue() + 1]--;
			}
		}
		for(int i=1;i<=N;i++)
			cnt[i] += cnt[i-1];

		for(int i=1;i<=N;i++)
			cnt[i] += cnt[i-1];

		out.print(cnt[N-1]);
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