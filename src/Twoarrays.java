import java.util.*;
import java.io.*;
class Twoarrays
{

	/************************ SOLUTION STARTS HERE ***********************/

	static class MultiSet <T> extends TreeMap<T,Integer>
	{
		public void add(T key)
		{
			Integer q = super.get(key);
			if(q == null)
				super.put(key, 1);
			else
				super.put(key, q+1);
		}
		@Override
		public Integer remove(Object key) {
			Integer q = super.get(key);
			if(q != null)
			{
				if(q == 1)
					super.remove(key);
				else
					super.put((T)key, q-1);
			}
			else
				throw new NullPointerException("The specified key cannot be removed from the map");

			return q;
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){
		int t = s1.nextInt();
		while(t-->0)
		{
			int len = s1.nextInt();
			int k = s1.nextInt();
			MultiSet<Integer> multiSet = new MultiSet<>();
			int arr[] = s1.nextIntArray(len);
			int temp_len = len;
			while(temp_len-->0)multiSet.add(s1.nextInt());

			boolean flag = true;

			for(int a:arr)
			{
				Integer ceil = multiSet.ceilingKey(k - a);
				if(ceil == null)
				{
					flag = false;
					break;
				}
				else				
					multiSet.remove(ceil);

			}
			
			out.println((flag)?"YES":"NO");
		}

	}

	/************************ SOLUTION ENDS HERE ************************/



	/************************ TEMPLATE STARTS HERE **********************/

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

		long[] nextLongArray(int n) {
			long[] arr = new long[n];
			for (int i = 0; i < n; i++) {
				arr[i] = nextLong();
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
		public void close(){	
			try{ reader.close(); } catch(IOException e){e.printStackTrace();}
		}
	}

	/************************ TEMPLATE ENDS HERE ************************/
}
