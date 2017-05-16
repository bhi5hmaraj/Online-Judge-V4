import java.util.*;
import java.io.*;
public class uva_10324
{

	/************************ SOLUTION STARTS HERE ***********************/


	private static void solve(FastScanner s1, PrintWriter out){
		int runner = 1;
		while(true)
		{
			String input = s1.nextLine();
			if(/*input.length()*/ input == null)
				return;
			out.println("Case "+runner+":");
			runner++;
			int len = input.length();
			int dp0[] = new int[len+1];
			int dp1[] = new int[len+1];
			for(int i=1;i<=len;i++)
			{
				if(input.charAt(i-1) == '0')
				{
					dp0[i] = dp0[i-1] + 1;
					dp1[i] = dp1[i-1];
				}
				else
				{
					dp1[i] = dp1[i-1] + 1;
					dp0[i] = dp0[i-1];
				}
			}
			int q = s1.nextInt();
			while(q-->0)
			{
				int a = s1.nextInt();
				int b = s1.nextInt();
				int min = Math.min(a,b);
				int max = Math.max(a,b);
				int numOfOnes = dp1[max+1] - dp1[min];
				int numOfZeroes = dp0[max+1] - dp0[min];
				int intervalLength = max - min + 1;
				if(numOfOnes == intervalLength || numOfZeroes == intervalLength)
					out.println("Yes");
				else
					out.println("No");
			}
		}
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
		public char nextChar(){
			char ch = '0';
			try{ ch = (char)reader.read();} catch(IOException e){ e.printStackTrace();}
			return ch;
		}
		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextInt();
			}
			return arr;
		}
		char[] nextCharArray(int n){
			char buf[] = new char[n];
			try {
				for(int i=0;i<n;i++)
					buf[i] = (char)reader.read();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return buf;
		}
		int[] nextIntArrayOneBased(int n) {
			int[] arr = new int[n+1];
			for (int i = 1; i <= n; i++) {
				arr[i] = nextInt();
			}
			return arr;
		}

		long[] nextLongArrayOneBased(int n) {
			long[] arr = new long[n+1];
			for (int i = 1; i <= n; i++) {
				arr[i] = nextLong();
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
	
	/************************ TEMPLATE ENDS HERE ************************/
}
