import java.util.*;
import java.io.*;
public class LIS
{

	/************************ SOLUTION STARTS HERE ************************/
	static int CeilIndex(double A[], int l, int r, double key)
	{
	    while (r - l > 1)
	    {
	        int m = l + (r - l)/2;
	        if (A[m]>=key)
	            r = m;
	        else
	            l = m;
	    }

	    return r;
	}

	static double[] LongestIncreasingSubsequenceLength(double A[], int size)
	{
	    // Add boundary case, when array size is one

	    double[] tailTable   = new double[size];
	    int len; // always points empty slot

	    tailTable[0] = A[0];
	    len = 1;
	    double max = Double.MIN_VALUE;
	    double sum = 0.0;
	    for (int i = 1; i < size; i++)
	    {
	        if (A[i] < tailTable[0])
	        {
	            // new smallest value
	            tailTable[0] = A[i];
	            if(sum == 0.0)
	            	sum = A[i];
	            else
	            	sum += (-tailTable[0] + )
	            max = Math.max(max, sum);
	        }
	        else if (A[i] > tailTable[len-1])
	        {
	            // A[i] wants to extend largest subsequence
	            tailTable[len++] = A[i];
	            sum += A[i];
	            max = Math.max(max, sum);
	        }
	        else
	        {
	            // A[i] wants to be current end candidate of an existing
	            // subsequence. It will replace ceil value in tailTable
	            max = Math.max(max,sum);
	        	int index = CeilIndex(tailTable, -1, len-1, A[i]);
	        	sum -= tailTable[index];
	        	sum += A[i];
	        	max = Math.max(max, sum);
	            tailTable[index] = A[i];
	        }
	    }
	    System.out.print("len"+len);
	    if(len == 1)
	    	tailTable[0] = max;
	    return tailTable;
	}

	private static void solve(FastScanner s1, FastWriter out){

		int len = s1.nextInt();
		double arr[] = new double[len];
		for(int i=0;i<len;i++)
		{
			long r = s1.nextLong();
			long h = s1.nextLong();
			arr[i] = Math.PI * (Math.pow(r, 2)) * (double)h;
		}
		double ans[] = LongestIncreasingSubsequenceLength(arr, len);
		System.out.println(Arrays.toString(ans));
		double total = 0.0;
		for(double a:ans)
			total += a;
		out.print(total);
	}

	/************************ SOLUTION ENDS HERE ************************/



	/************************ TEMPLATE STARTS HERE ************************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		FastWriter  out = new FastWriter(System.out);
		solve(in, out);
		in.close();
		out.close();
	}    

	static class FastScanner{
		public BufferedReader reader;
		public StringTokenizer st;
		public FastScanner(InputStream stream){
			reader = new BufferedReader(new InputStreamReader(stream));
			st = null;
		}
		public String next(){
			while(st == null || !st.hasMoreTokens()){
				try{
					String line = reader.readLine();
					if(line == null) return null;
					st = new StringTokenizer(line);
				}catch (Exception e){
					throw (new RuntimeException());
				}
			}
			return st.nextToken();
		}
		public String nextLine(){
			String str = null;
			try {
				str = reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
		}
		public int nextInt(){
			return Integer.parseInt(next());
		}
		public long nextLong(){
			return Long.parseLong(next());
		}
		public double nextDouble(){
			return Double.parseDouble(next());
		}
		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextInt();
			}
			return arr;
		}

		long[] nextLongArray(int n) {
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextLong();
			}
			return arr;
		}
		public void close(){	
			try{ reader.close(); } catch(IOException e){e.printStackTrace();}
		}
	}
	static class FastWriter{
		BufferedWriter writer;
		public FastWriter(OutputStream stream){
			writer = new BufferedWriter(new OutputStreamWriter(stream));
		}
		public void print(int i) {
			print(Integer.toString(i));
		}
		public void println(int i) {
			print(Integer.toString(i));
			print('\n');
		}
		public void print(long i) {
			print(Long.toString(i));
		}
		public void println(long i) {
			print(Long.toString(i));
			print('\n');
		}
		public void print(double i) {
			print(Double.toString(i));
		}
		public void print(boolean i) {
			print(Boolean.toString(i));
		}
		public void print(Object o){
			print(o.toString());
		}
		public void println(Object o){
			print(o.toString());
			print('\n');
		}
		public void print(char i) {
			try{writer.write(Character.toString(i));} catch(IOException e){e.printStackTrace();}
		}
		public void print(String s){
			try{writer.write(s);} catch(IOException e){e.printStackTrace();}
		}
		public void println(String s){
			try{writer.write(s);writer.write('\n');} catch(IOException e){e.printStackTrace();}
		}
		public void println(){
			try{writer.write('\n');} catch(IOException e){e.printStackTrace();}
		}
		public void print(int arr[])
		{
			for (int i = 0; i < arr.length; i++) {
				print(arr[i]);
				print(' ');
			}
		}
		public void close(){
			try{writer.close();} catch(IOException e){e.printStackTrace();}
		}
	}

	/************************ TEMPLATE ENDS HERE ************************/
}

