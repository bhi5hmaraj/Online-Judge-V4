import java.util.*;
import java.io.*;
public class uva_11849
{

	/************************ SOLUTION STARTS HERE ***********************/
	/*
	static class MyBitSet
	{
		long bits[];
		MyBitSet(int MAX)
		{
			bits = new long[MAX];
		}
		void set(int n,boolean f)
		{
			int index = n/64;
			if(f == true)		
				bits[index] |= (1L<<(n%64));
			else
				bits[index] ^= (1L<<(n%64));
		}
		boolean get(int n)
		{
			return ((bits[n/64])&(1L<<(n%64))) != 0;
		}
	}
	*/
	private static void solve(FastScanner s1, PrintWriter out){

		while(true)
		{
			int M = s1.nextInt();
			int N = s1.nextInt();
			if(M == 0 && N == 0)
				return;
			/*
			HashSet<Integer> jack = new HashSet<>(); // Time : 1.1s
			HashSet<Integer> jill = new HashSet<>();
			*/
			BitSet jack = new BitSet();             // Time : 0.5s
			int ct = 0;
			while(M-->0)jack.set(s1.nextInt(), true);
			while(N-->0)			
				if(jack.get(s1.nextInt()))
					ct++;
			
			out.println(ct);
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
			return next().charAt(0);
		}
		int[] nextIntArray(int n) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextInt();
			}
			return arr;
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
