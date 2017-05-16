import java.util.*;
import java.io.*;
public class ABTwoTeams
{

	/************************ SOLUTION STARTS HERE ***********************/


	private static void solve(FastScanner s1, PrintWriter out){

		int len = s1.nextInt();
		long strength[] = s1.nextLongArray(len);
		char arr[] = s1.nextLine().toCharArray();  //String is slower than char array !!

		
		long base = 0;
		for(int i=0;i<len;i++)
			if(arr[i] == 'B')
				base += strength[i];
		
		//Calculate prefix max
		
		long prefixMax = base;
		long prefix = base;
		for(int i=0;i<len;i++)
		{
			prefix = (arr[i] == 'B') ? prefix - strength[i] : prefix + strength[i];
			prefixMax = Math.max(prefixMax,prefix);
		}
		
		
		//Calculate suffix max
		
		long suffixMax = base;
		long suffix = base;
		for(int i=len-1;i>=0;i--)
		{
			suffix = (arr[i] == 'B') ? suffix - strength[i] : suffix + strength[i];
			suffixMax = Math.max(suffixMax,suffix);
		}
		
		out.println(Math.max(suffixMax,prefixMax));
		

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
