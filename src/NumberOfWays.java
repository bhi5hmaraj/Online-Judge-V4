import java.util.*;
import java.io.*;
public class NumberOfWays
{

	/************************ SOLUTION STARTS HERE ************************/


	private static void solve(FastScanner s1, PrintWriter out){

		int len = s1.nextInt();		
		long arr[] = s1.nextLongArray(len);
		if(len < 3)
		{
			out.print(0);
			return;
		}
		long sum = 0;
		for(long a:arr)sum += (a);
		if(sum % 3L != 0)
			out.print(0);
		else
		{
			long requiredSum = sum / 3L;
			long prefix[] = new long[len + 1];
			for(int i=1;i<=len;i++)
				prefix[i] = prefix[i-1] + arr[i-1];
			int cnt[] = new int[len];
			for(int i=0;i<len;i++)
				cnt[i] = ((prefix[len] - prefix[i]) == requiredSum )? 1 : 0 ;
			int possible [] = new int [len];
			possible[len-1] = cnt[len-1];
			for(int i=len-2;i>=0;i--)
				possible[i] = possible[i+1] + cnt[i];

			long total = 0;
			for(int i=0;i<len-2;i++)
			{
				if(prefix[i+1] == requiredSum)
				{
					total += (long)(possible[i+2]);
				}
			}
			/*
			//out.print("total"+total);
			System.out.println("pre "+Arrays.toString(prefix));
			System.out.println("cnt "+Arrays.toString(cnt));
			System.out.println("arr "+Arrays.toString(arr));
			System.out.println("pos "+Arrays.toString(possible));
			 */
			out.print(total);

		}
	}

	/************************ SOLUTION ENDS HERE ************************/



	/************************ TEMPLATE STARTS HERE ************************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		//FastWriter  out = new FastWriter(System.out);
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
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
