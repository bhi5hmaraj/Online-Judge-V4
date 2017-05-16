import java.util.*;
import java.io.*;
public class GoodSubstrings 
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int H = 2; // 2 is optimum , more than 2 TLEs
	
	static final int p[] = {(int)(1e9) + 7 , (int)(1e9) + 13};  
	static final int x[] = {46399 , 18757}; 
	static int sub(int a, int b , int mod) {return ((a % mod) - (b % mod) + mod) % mod;}
	static int mul(int a, int b , int mod) {return (int)(((long)(a % mod) * (long)(b % mod)) % (long)mod);}
	static int add(int a, int b , int mod) {return ((a % mod) + (b % mod)) % mod;}

	private static void solve(FastScanner s1, PrintWriter out){

		String str = s1.nextLine();
		int N = str.length();
		int bad[] = new int[26];
		{
			int i = 0;
			for(String s = s1.nextLine();i < s.length();i++)
				bad[i] = s.charAt(i) == '0' ? 1 : 0;
		}
		int K = s1.nextInt();
		int[] pow = new int[H];
		Arrays.fill(pow, 1);
		
		int cntGood = 0;
		HashSet<Integer>[] sets = new HashSet[H];

		for(int len = 1;len <= N;len++) {
			
			for(int i=0;i<H;i++) {
				pow[i] = mul(pow[i], x[i], p[i]);
				sets[i] = new HashSet<>();
			}
			
			int[] curr_hash = new int[H];

			int cnt = 0;

			for(int i=0;i<len;i++) {
				for(int j=0;j<H;j++)
					curr_hash[j] = add(mul(curr_hash[j], x[j], p[j]), str.charAt(i), p[j]);
				cnt += bad[str.charAt(i) - 'a'];
			}
			
			boolean flag = false;
			for(int i=0;i<H;i++)
				flag |= !sets[i].contains(curr_hash[i]); // You saved me !!!

			if(cnt <= K && flag) { // Good
				// System.out.printf("str = %s hash1 = %d hash2 = %d \n", str.substring(0, len) , curr_hash1 , curr_hash2);
				for(int i=0;i<H;i++)
					sets[i].add(curr_hash[i]);
				
				cntGood++;
			}

			for(int i=len;i<N;i++) {
				cnt += bad[str.charAt(i) - 'a'];
				cnt -= bad[str.charAt(i - len) - 'a'];
				for(int j=0;j<H;j++)
					curr_hash[j] = add(sub(mul(curr_hash[j], x[j], p[j]), mul(str.charAt(i - len), pow[j], p[j]), p[j]), str.charAt(i), p[j]);
				
				flag = false;
				
				for(int j=0;j<H;j++)
					flag |= !sets[j].contains(curr_hash[j]);
				
				if(cnt <= K && flag) { // Good
					// System.out.printf("str = %s hash1 = %d hash2 = %d\n", str.substring(i - len + 1, i + 1) , curr_hash1 , curr_hash2 );
					for(int j=0;j<H;j++)
						sets[j].add(curr_hash[j]);
					
					cntGood++;
				}
			}

			// out.println(set1);
			// out.println(set2);

		}

		out.println(cntGood);

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
		BufferedReader reader;
		StringTokenizer st;
		FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
		String next()
		{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
		st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
		String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
		int    nextInt()   {return Integer.parseInt(next());}
		long   nextLong()  {return Long.parseLong(next());}		
		double nextDouble(){return Double.parseDouble(next());}
		char   nextChar()  {return next().charAt(0);}
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}