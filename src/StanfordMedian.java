import java.util.*;
import java.io.*;
public class StanfordMedian {
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	static void rebalance(PriorityQueue<Integer> left , PriorityQueue<Integer> right) {
		if(Math.abs(left.size() - right.size()) > 1) {
			if(left.size() > right.size())
				right.add(left.remove());
			else
				left.add(right.remove());
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = 10000;
		PriorityQueue<Integer> left = new PriorityQueue<>((i1 , i2) -> i2 - i1);
		PriorityQueue<Integer> right = new PriorityQueue<>();
		int sum = 0;
		int mod = 10000;
		int median;
		while(N-->0) {
			int num = s1.nextInt();

			if(left.isEmpty() && right.isEmpty())
				left.add(num);
			else if(num <= left.peek())
					left.add(num);
			else
				right.add(num);
			
			rebalance(left, right);
			if(left.size() >= right.size())
				median = left.peek();
			else
				median = right.peek();
			
			/*
			out.println(median);
			out.println("left " + left + " right " + right);
			out.println("set " + set);
			*/
			sum = (sum + median) % mod;
		}
		
		out.println(sum);
	}
	
	
	
	/************************ SOLUTION ENDS HERE ************************/
	
	
	
	
	
	/************************ TEMPLATE STARTS HERE *********************/
	
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(/*System.in*/new FileInputStream("Median.txt"));
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