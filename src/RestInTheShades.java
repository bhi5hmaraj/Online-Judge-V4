import java.util.*;
import java.util.function.BiFunction;

import java.io.*;

public class RestInTheShades {
	
	
	
	/************************ SOLUTION STARTS HERE ************************/
	
	static final double EPS = 1e-9;
    
    static class MyDouble implements Comparable<MyDouble> {
    	double d;
    	public MyDouble(double dd) {
    		d = dd;
    	}
    	
    	@Override
		public int compareTo(MyDouble arg0) {
//    		return d <= arg0.d - EPS ? -1 : d >= arg0.d + EPS ? 1 : 0;
    		return Double.compare(d, arg0.d);
    	}
    }
    
	private static void solve() {
		
		int sy = nextInt();
		int a = nextInt();
		int b = nextInt();
		
		int n = nextInt();

		
		int prefixSum[] = new int[n + 1];
		BiFunction<Integer, Integer, Integer> getSum = (l, r) -> {
			l++;
			r++;
			return r < l ? 0 : prefixSum[r] - prefixSum[l - 1];
		};
		
		TreeMap<MyDouble, int[]> rightPts = new TreeMap<>();	// pair - (index, left end point)
		TreeMap<MyDouble, int[]> leftPts = new TreeMap<>();	// pair - (index, right end point)
		
		int segments[][] = new int[n + 1][2];
		
		for(int i = 1; i <= n; i++) {
			int l = nextInt();
			int r = nextInt();
			segments[i][0] = l;
			segments[i][1] = r;
			prefixSum[i] = prefixSum[i - 1] + r - l;
			rightPts.put(new MyDouble(r), new int[] {i, l});
			leftPts.put(new MyDouble(l), new int[] {i, r});
		}
		
		int q = nextInt();
		while(q-->0) {
			int x = nextInt();
			int y = nextInt();
			
			double leftIntercept = (1.0 * x * sy - 1.0 * y * a) / (sy - y);
			double rightIntercept = (1.0 * x * sy - 1.0 * y * b) / (sy - y);
			/*
			System.out.println("x " + x + " y " + y);
			System.out.println("leftInter = " + leftIntercept + " rightInter = " + rightIntercept);
			*/
			Map.Entry<MyDouble, int[]> ceilEntry = rightPts.ceilingEntry(new MyDouble(leftIntercept));
			Map.Entry<MyDouble, int[]> floorEntry = leftPts.floorEntry(new MyDouble(rightIntercept));
			
			double shadowTime = 0;
			
			if(ceilEntry != null && floorEntry != null && ceilEntry.getValue()[0] <= floorEntry.getValue()[0]) {
				int ceil[] = ceilEntry.getValue();
				int floor[] = floorEntry.getValue();
				/*
				System.out.println("ceil " + String.format("(%f, %f, index = %d)", (double)ceil[1], ceilEntry.getKey().d, ceil[0]));
				System.out.println("floor " + String.format("(%f, %f, index = %d)", floorEntry.getKey().d, (double)floor[1], floor[0]));
				*/
				if(floor[0] == ceil[0])
					shadowTime += Math.min(rightIntercept, floor[1]) - Math.max(leftIntercept, ceil[1]);
				else
					shadowTime += (ceilEntry.getKey().d - Math.max(ceil[1], leftIntercept)) + 
								  (Math.min(rightIntercept, floor[1]) - floorEntry.getKey().d) + 
								  getSum.apply(ceil[0] + 1, floor[0] - 1);
			}
			
//			System.out.println("region length covered = " + shadowTime);
			
			shadowTime *= (y - sy) / (double) y;
			println(String.format("%.15f", shadowTime));
		}
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE **********************/
	
	public static void main(String[] args) throws IOException {
		reader = new BufferedReader(new InputStreamReader(System.in));
		writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
		st     = null;
		solve();
		reader.close();
		writer.close();
	}
	
	static BufferedReader reader;
	static PrintWriter    writer;
	static StringTokenizer st;
	
	static String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
	static int    nextInt()   {return Integer.parseInt(next());}
	static long   nextLong()  {return Long.parseLong(next());}     
	static double nextDouble(){return Double.parseDouble(next());}
	static char   nextChar()  {return next().charAt(0);}
	static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
	static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
	static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
	static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
	static void   print(Object o)  { writer.print(o);  }
	static void   println(Object o){ writer.println(o);}
	
	/************************ TEMPLATE ENDS HERE ************************/
	
}