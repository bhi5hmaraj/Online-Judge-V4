import java.util.*;
import java.io.*;
public class Triangle
{

	/************************ SOLUTION STARTS HERE ************************/


	private static boolean isRight(int[] p1,int[] p2,int[] p3)
	{
		int dist[] = new int [3];
		int mat[][] = new int[3][2];
		mat[0] = p1;
		mat[1] = p2;
		mat[2] = p3;
		for(int i=0;i<3;i++)
			for(int j=0;j<3;j++)
				if(i != j)
				{
					if(mat[i][0]==mat[j][0] && mat[i][1]==mat[j][1])
						return false;
				}
		dist[0] = (int)Math.pow(p1[0]-p2[0], 2) + (int)Math.pow(p1[1]-p2[1], 2);
		dist[1] = (int)Math.pow(p1[0]-p3[0], 2) + (int)Math.pow(p1[1]-p3[1], 2);
		dist[2] = (int)Math.pow(p2[0]-p3[0], 2) + (int)Math.pow(p2[1]-p3[1], 2);
		Arrays.sort(dist);
		return dist[0]+dist[1] == dist[2];		 
	}

	private static void solve(FastScanner s1, FastWriter out){

		int x[] = {0,1,0,-1};
		int y[] = {1,0,-1,0};
		int mat[][] = new int[3][2];
		for(int i=0;i<3;i++)
		{
			mat[i][0] = s1.nextInt();
			mat[i][1] = s1.nextInt();
		}
		if(isRight(mat[0], mat[1], mat[2]))
			out.print("RIGHT");
		else
		{
			int temp[] = new int[2];

			for(int i=0;i<3;i++)
			{
				int bored[] = new int[2];
				int ct=0;
				for(int z=0;z<3;z++)
					if(z!=i)
						bored[ct++] = z;
				for(int j=0;j<4;j++)
				{
					temp[0] = mat[i][0] + x[j];
					temp[1] = mat[i][1] + y[j];
					if(isRight(temp, mat[bored[0]], mat[bored[1]]))
					{
						out.print("ALMOST");
						return;
					}
				}
			}

			out.print("NEITHER");
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
