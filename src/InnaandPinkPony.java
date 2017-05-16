import java.util.*;
import java.io.*;
public class InnaandPinkPony
{

	/************************ SOLUTION STARTS HERE ************************/
	private static int N,M;
	private static boolean isValid(int i,int j)
	{
		return (i>=1 && i<= N) && (j >= 1 && j<= M);
	}
	private static boolean reached(int x,int y)
	{
		return (x==1 && y==1)||(x==1&&y==M)||(x==N&&y==1)||(x==N&&y==M);
	}
	private static int lineRunner(int x,int y,int[] dx,int[] dy,int choice)
	{
		int ct = 0;
		if(choice < 0)
			return Integer.MAX_VALUE;
		while(isValid(x, y))
		{
			if(reached(x,y))			
				return ct;

			ct++;
			x += dx[choice];
			y += dy[choice];
		}
		return Integer.MAX_VALUE;
	}
	private static int isIntersecting(int x,int y)
	{
		if(N == M)
		{
			
		}
		else
		{

			if(x == y)
				return 0;
			else if((x + y) == (1 + M))
				return 2;
			else if((x - y) == (N - M))
				return 3;
			else if((x + y) == (1 + N))
				return 1;
			else 
				return -1;
		}
	}
	private static void solve(FastScanner s1, FastWriter out){

		N = s1.nextInt();
		M = s1.nextInt();
		int start_x = s1.nextInt();
		int start_y = s1.nextInt();
		int a = s1.nextInt();
		int b = s1.nextInt();
		int[] dx = {-a,a,-a,a};
		int[] dy = {-b,-b,b,b};	
		ArrayList<Integer> valid = new ArrayList<>();

		for(int i=0;i<4;i++)
		{
			int x = start_x,y=start_y;
			int ct=0;
			while(isValid(x, y))
			{
				if(reached(x,y))
				{
					valid.add(ct);
					break;
				}
				ct++;
				x += dx[i];
				y += dy[i];
			}
		}



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
