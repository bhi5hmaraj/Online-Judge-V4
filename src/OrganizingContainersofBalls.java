import java.util.*;
import java.io.*;
public class OrganizingContainersofBalls
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			long arr[][] = new long[N][];
			for(int i=0;i<N;i++)
				arr[i] = s1.nextLongArray(N);
			
			HashMap<Long , Integer> freq = new HashMap<>();
			for(int i=0;i<N;i++) {
				long sum = 0;
				for(long a : arr[i])
					sum += a;
				
				freq.put(sum, freq.getOrDefault(sum, 0) + 1);
			}
			
			boolean flag = true;
			
			for(int i=0;i<N;i++) {
				long sum = 0;
				for(int j=0;j<N;j++)
					sum += arr[j][i];
				
				if(!freq.containsKey(sum)) {
					flag = false;
					break;
				}
				else {
					freq.put(sum, freq.get(sum) - 1);
				}
			}
			
			for(Map.Entry<Long, Integer> e : freq.entrySet())
				flag &= e.getValue().intValue() == 0;
			
			out.println(flag ? "Possible" : "Impossible");
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}