/*
 * 
 * System.out.println() 		--- 7.67s (3 TLEs)
 * PrintWriter(BufferedWriter())--- 2.77s (AC)
 * FastWriter(Stack)      		--- 2.74s (AC)
 * FastWriter(ArrayDeque)		--- 2.57s (AC)
 * 
 * PrintWriter(BufferedWriter()) + FastScanner ---- 2.55s (AC)
 * FastWriter + FastScanner 				   ---- 2.61s (AC)
 * 
 */
import java.util.*;
import java.io.*;
public class Pass
{
	public static void main(String args[])throws IOException
	{
		//MyScanner2 s1=new MyScanner2();    
		PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);      //Close the output stream after use  
		FastScanner s1  = new FastScanner(System.in);
		//OutputWriter out = new OutputWriter(System.out);
		//FastWriter out = new FastWriter(System.out);		
		int t;
		t = s1.nextInt();
		long n,m;
		int count = 0;

		ArrayList<String> arl;   
		while(t-->0)
		{
			arl = new ArrayList<>();
			n = s1.nextLong();
			count = 0;
			m = s1.nextLong();
			while(m>0)
			{
				if((m & 1) != 0)
				{
					arl.add("(" + n + "<<" + count + ")"); 
				}
				count++;
				m >>= 1;
			}
			
			for(int i=arl.size()-1;i >= 0;i--)
				out.print(arl.get(i) + ((i != 0)?" + ":""));
			
			out.println();
		}
		out.close();
		s1.close();
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
	static class MyScanner2 {
		BufferedReader br;
		StringTokenizer st;

		public MyScanner2() {
			br = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() {
			while (st == null || !st.hasMoreElements()) {
				try {
					st = new StringTokenizer(br.readLine());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return st.nextToken();
		}

		int nextInt() { return Integer.parseInt(next()); }
		long nextLong() { return Long.parseLong(next()); }
		double nextDouble() { return Double.parseDouble(next()); }

		String nextLine(){
			String str = "";
			try {
				str = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return str;
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
		public void print(char i) {
			try{writer.write(Character.toString(i));} catch(IOException e){e.printStackTrace();}
		}
		public void print(Object o){
			print(o.toString());
		}
		public void println(Object o){
			print(o.toString());
			print('\n');
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
	static class OutputWriter{
		BufferedWriter writer;
		public OutputWriter(OutputStream stream){
			writer = new BufferedWriter(new OutputStreamWriter(stream));
		}
		public void print(int i) throws IOException {
			writer.write(i);
		}
		public void print(Object o)throws IOException {
			writer.write(o.toString());
		}
		public void print(String s) throws IOException {
			writer.write(s);
		}
		public void print(char []c) throws IOException {
			writer.write(c);
		}
		public void close() throws IOException {
			writer.close();
		}
	}
}