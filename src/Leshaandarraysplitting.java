import java.util.*;
import java.io.*;
public class Leshaandarraysplitting
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair {
		int l , r;
		Pair(int l , int r){
			this.l = l;
			this.r = r;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int arr[] = s1.nextIntArray(N);
		
		ArrayList<Pair> arl = new ArrayList<>();
		int i = 0;
		while(i < N) {
			int sum = 0;
			int L = i;
			while(sum == 0 && i < N)
				sum += arr[i++];
			
			if(i == N &&  sum == 0) {
				if(arl.size() == 0) {
					out.println("NO");
					return;
				}
				else
					arl.get(arl.size() - 1).r = N;
			}
			else
				arl.add(new Pair(L + 1, i));
		}
		
		
		out.println("YES");
		out.println(arl.size());
		for(Pair p : arl)
			out.println(p.l + " " + p.r);
		
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