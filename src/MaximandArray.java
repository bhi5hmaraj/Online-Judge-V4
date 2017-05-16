import java.util.*;
import java.io.*;
public class MaximandArray
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class Pair implements Comparable<Pair>{
		int idx ;
		long val;
		Pair(int idx , long val){
			this.idx = idx;
			this.val = val;
		}
		@Override
		public int compareTo(Pair o) {
			return Long.compare(Math.abs(this.val), Math.abs(o.val));
		}
	}
	
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		int K = s1.nextInt();
		long x = s1.nextLong();
		long arr[]  = s1.nextLongArray(N);
		int neg = 0 ;
		for(long l : arr) 
			neg += (l < 0 ) ? 1 : 0;
		
		if(neg % 2 == 0) {   // This is to bring the least number to negative
			int min_abs_idx = 0;
			for(int i=0;i<N;i++) 
				min_abs_idx = Math.abs(arr[i]) < Math.abs(arr[min_abs_idx]) ? i : min_abs_idx;
			
			long sign = Long.signum(arr[min_abs_idx]);
			sign = sign == 0 ? 1 : sign;
			while(K > 0 && (sign * arr[min_abs_idx] >= 0)) {
				arr[min_abs_idx] -= (sign * x);
				K--;
			}
		}
		
		// The below part is to maximize the negative
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		for(int i=0;i<N;i++)
			pq.add(new Pair(i, arr[i]));
		while(K-->0) {
			Pair p = pq.remove();
			long sign = Long.signum(p.val);
			sign = (sign == 0) ? 1 : sign;
			p.val += (sign * x);
			pq.add(p);
		}
		
		for(Pair p:pq)
			arr[p.idx] = p.val;
		
		for(long a : arr)
			out.print(a + " ");
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