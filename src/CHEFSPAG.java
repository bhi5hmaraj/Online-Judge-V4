import java.util.*;
import java.awt.Polygon;
import java.io.*;
class CHEFSPAG
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static final long mod = (long)(1e9) + 9;
	
	static long add(long a , long b){
		return ((a % mod) + (b % mod)) % mod;
	}
	static long mul(long a , long b){
		return ((a % mod) * (b % mod)) % mod;
	}
	private static void solve(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		int MAX = 250;
		while(t-->0)
		{
			int N = s1.nextInt();
			int x[] = new int[N];
			int y[] = new int[N];
			long a = s1.nextLong();
			long b = s1.nextLong();
			long c = s1.nextLong();
			long d = s1.nextLong();
			long F[] = new long[MAX];
			boolean flag = true;
			for(int i=0;i<4;i++)
				F[i] = s1.nextLong();
			for(int i=4;i<MAX;i++)
				F[i] = add(add(mul(a, F[i - 1]), mul(b, F[i - 2])), add(mul(c, F[i - 3]), mul(d, F[i - 4])));
			
			
			for(int i=0;i<N;i++){
				x[i] = s1.nextInt();
				y[i] = s1.nextInt();
				flag &= (x[i] <= 100 && y[i] <= 100);
			}
			if(!flag)
				throw new RuntimeException("Work in progress");
			Polygon polygon = new Polygon(x, y, N);
			long total = 0;
			for(int i=0;i<=100;i++)
				for(int j=0;j<=100;j++){
					polygon.invalidate();
					if(polygon.contains(i, j)){
						out.println("("+i+", "+j+")");
						total = add(total, F[i + j]);
					}
				}
			out.println(total);
		}
		
	}
	
	static double areaOfPolygon(int x[] , int y[] , int N){
		double sum1 = 0 , sum2 = 0;
		for(int i=0;i<N;i++){
			sum1 += (x[i] * y[(i + 1) % N]);
			sum2 += (x[(i + 1) % N] * y[i]);
		}
		double area = /*0.5 * */Math.abs(sum1 - sum2);
		return area;
		
	}
	
	static final double EPS = 1e-8;
	
	static boolean inside(int x[] , int y[] , int N , int xx , int yy , double areaOfPolygon){
		
		double total = 0;
		int tri_x[] = new int[3];
		int tri_y[] = new int[3];
		tri_x[2] = xx;
		tri_y[2] = yy;
		for(int i=0;i<N;i++){
			tri_x[0] = x[i];
			tri_y[0] = y[i];
			tri_x[1] = x[(i + 1) % N];
			tri_y[1] = y[(i + 1) % N];
			total += areaOfPolygon(tri_x, tri_y, 3);
		}
		return Math.abs(total - areaOfPolygon) < EPS;
	}
	private static void solve2(FastScanner s1, PrintWriter out){
		
		int t = s1.nextInt();
		int MAX = 210;
		while(t-->0)
		{
			int N = s1.nextInt();
			int x[] = new int[N];
			int y[] = new int[N];
			long a = s1.nextLong();
			long b = s1.nextLong();
			long c = s1.nextLong();
			long d = s1.nextLong();
			long F[] = new long[MAX];
			boolean flag = true;
			for(int i=0;i<4;i++)
				F[i] = s1.nextLong();
			for(int i=4;i<MAX;i++)
				F[i] = add(add(mul(a, F[i - 1]), mul(b, F[i - 2])), add(mul(c, F[i - 3]), mul(d, F[i - 4])));
			
			
			for(int i=0;i<N;i++){
				x[i] = s1.nextInt();
				y[i] = s1.nextInt();
				flag &= (x[i] <= 100 && y[i] <= 100);
			}
			if(!flag)
				throw new RuntimeException("Work in progress");
			
			long total = 0;
			
			double polygonArea = areaOfPolygon(x, y, N);
			for(int i=0;i<=100;i++)
				for(int j=0;j<=100;j++){
					if(inside(x, y, N, i, j, polygonArea)){
						// out.println("("+i+", "+j+")");
						total = add(total, F[i + j]);
					}
				}
			
			out.println(total);
		}
		
	}
	
	private static void solve3(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		int MAX = 210;
		while(t-->0)
		{
			int N = s1.nextInt();
			int x[] = new int[N];
			int y[] = new int[N];
			long a = s1.nextLong();
			long b = s1.nextLong();
			long c = s1.nextLong();
			long d = s1.nextLong();
			long F[] = new long[MAX];
			boolean flag = true;
			for(int i=0;i<4;i++)
				F[i] = s1.nextLong();
			for(int i=4;i<MAX;i++)
				F[i] = add(add(mul(a, F[i - 1]), mul(b, F[i - 2])), add(mul(c, F[i - 3]), mul(d, F[i - 4])));
			
			
			for(int i=0;i<N;i++){
				x[i] = s1.nextInt();
				y[i] = s1.nextInt();
				flag &= (x[i] <= 100 && y[i] <= 100);
			}
			if(!flag)
				throw new RuntimeException("Work in progress");
			
			long total = 0;
			
		}
	}
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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