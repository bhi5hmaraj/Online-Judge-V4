import java.util.*;
import java.io.*;
public class Chords
{

	/************************ SOLUTION STARTS HERE ************************/
	
	private static boolean checkMajor(int i,int j,int k,int len)
	{
		return ((i+4)%len == j) && ((j+3)%len == k);
	}
	private static boolean checkMinor(int i,int j,int k,int len)
	{
		return ((i+3)%len == j) && ((j+4)%len == k);	
	}
	private static void solve(FastScanner s1, FastWriter out){

		String in[] = {"C", "C#", "D", "D#", "E", "F", "F#", "G", "G#", "A", "B", "H"};
		ArrayList<String> arl = new ArrayList<>();
		for(String s:in)
			arl.add(s);
		String arr[] = s1.nextLine().split(" ");
		int index[] = new int[3];
		for(int i=0;i<3;i++)index[i] = arl.indexOf(arr[i]);
		Arrays.sort(index);
		int len  = in.length;
		if(checkMajor(index[0], index[1], index[2], len) || checkMajor(index[1], index[2], index[0], len) || checkMajor(index[2], index[0], index[1], len))
			out.print("major");
		else if(checkMinor(index[0], index[1], index[2], len) || checkMinor(index[1], index[2], index[0], len) || checkMinor(index[2], index[0], index[1], len))
			out.print("minor");
		else
			out.print("strange");	
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
			try{writer.write(i);} catch(IOException e){e.printStackTrace();}
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
