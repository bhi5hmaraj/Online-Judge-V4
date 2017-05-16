import java.util.*;
import java.io.*;
public class USBvsPS2
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int type[] = s1.nextIntArray(3); // 0 - USB , 1 - PS/2 , 2 - Both
		
		int m = s1.nextInt();
		
		int arr[][] = new int[m][2];
		
		for(int i=0;i<m;i++) {
			arr[i][0] = s1.nextInt();
			arr[i][1] = s1.next().equals("USB") ? 0 : 1;
		}
		
		Arrays.sort(arr , (p1 , p2) -> Integer.compare(p1[0], p2[0]));
		
		boolean marked[] = new boolean[m];
		long cost = 0;
		int cnt   = 0;
		
		for(int i=0;i<m;i++) {
			if(type[arr[i][1]] > 0) {
				marked[i] = true;
				cnt++;
				cost += arr[i][0];
				type[arr[i][1]]--;
			}
		}
		
		for(int i=0;i<m;i++)
			if(!marked[i] && type[2] > 0) {
				cost += arr[i][0];
				cnt++;
				type[2]--;
			}
		
		out.println(cnt + " " + cost);
		
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