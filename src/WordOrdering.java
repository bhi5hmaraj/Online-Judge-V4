import java.util.*;
import java.io.*;
public class WordOrdering
{

	/************************ SOLUTION STARTS HERE ***********************/
	
	private static HashMap<Character,Integer> permutation = new HashMap<>();
	
	static class CustomCompare implements Comparator<String>
	{
		@Override
		public int compare(String s1, String s2) {
			
			int minLen = Math.min(s1.length(),s2.length());
			int len1 = s1.length();
			int len2 = s2.length();
			for(int i=0;i<minLen;i++)
			{
				char ch1 = s1.charAt(i);
				char ch2 = s2.charAt(i);
				if((Character.isUpperCase(ch1) && Character.isUpperCase(ch2)) || (Character.isLowerCase(ch1) && Character.isLowerCase(ch2)))
				{
					ch1 = Character.toLowerCase(ch1);
					ch2 = Character.toLowerCase(ch2);
					if(permutation.get(ch1) > permutation.get(ch2))
						return 1;
					if(permutation.get(ch1) < permutation.get(ch2))
						return -1;
				}
				else
				{
					if(Character.isUpperCase(ch1))
						return 1;
					else
						return -1;
				}
			}
			return len1 - len2;
		}
	}

	private static void solve(FastScanner s1, PrintWriter out){

		char order[] = s1.nextLine().toCharArray();
		for(int i=0;i<26;i++)
			permutation.put(order[i], i);
		
		int len = s1.nextInt();
		String arr[] = new String[len];
		for(int i=0;i<len;i++)
			arr[i] = s1.nextLine();
		
		Arrays.sort(arr, new CustomCompare());
		for(String s : arr)
			out.println(s);
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
