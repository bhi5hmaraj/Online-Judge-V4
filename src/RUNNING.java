import java.util.*;
import java.io.*;
class RUNNING
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	

	static class RabinKarp {

		static final long p1 = (long) (1e9) + 7L;  // mod1
		static final long p2 = (long) (1e9) + 13L; // mod2
		static final long x1 = 46399; 				   // base1
		static final long x2 = 18757;				   // base2
		
		static long sub(long a, long b , long mod) {return ((a % mod) - (b % mod) + mod) % mod;}
		static long mul(long a, long b , long mod) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b , long mod) {return ((a % mod) + (b % mod)) % mod;}
		
		private long hash(int[] str , long mod , long x) {
			long hash = 0;
			for (int i = str.length - 1; i >= 0; i--)
				hash = add(mul(hash, x , mod), str[i] , mod);

			return hash;
		}
		
		int[] text;
		int t_len;

		RabinKarp(int[] text) {
			this.text = text;
			this.t_len = text.length;
		}

		int findOccurence(int[] pat) { // Finds all occurence of pattern in O(|text| + |pat|)
			
			if(pat.length > text.length) return 0;
			
			long pHash1 = hash(pat , p1 , x1);
			long pHash2 = hash(pat,  p2 , x2);
			int p_len = pat.length;
			long pow1 = 1 , pow2 = 1;
			int cnt = 0;
			for(int i=1;i<=p_len;i++) {
				pow1 = mul(pow1, x1, p1);
				pow2 = mul(pow2, x2, p2);
			}
			long curr_hash1 = 0 , curr_hash2 = 0;
			for (int i = t_len - 1; i >= t_len - p_len; i--) {
				curr_hash1 = add(mul(curr_hash1, x1 , p1), text[i] , p1);
				curr_hash2 = add(mul(curr_hash2, x2 , p2), text[i] , p2);
			}
			
			if(curr_hash1 == pHash1 && curr_hash2 == pHash2)
				cnt++;
			
			for (int i = t_len - p_len - 1; i >= 0; i--) {
				curr_hash1 = add(sub(mul(curr_hash1, x1 , p1), mul(text[(i + p_len)], pow1 , p1) , p1), text[i] , p1);
				curr_hash2 = add(sub(mul(curr_hash2, x2 , p2), mul(text[(i + p_len)], pow2 , p2) , p2), text[i] , p2);
				if (curr_hash1 == pHash1 && curr_hash2 == pHash2)
					cnt++;
			}
			
			return cnt;
			
		}

	}
	static int[] prefix(int[] str){
		int N = str.length;
		int border = 0;
		int prefix[] = new int[N];
		
		for(int i=1;i<N;i++){
			
			while(border != 0 && str[(border)] != str[(i)])
				border = prefix[border - 1];
			
			if(str[border] == str[i])
				prefix[i] = ++border;
		
		}
		
		return prefix;
	}

	static int findOccurence(int[] text , int[] pattern) {
		int patLen = pattern.length;
		int textLen = text.length;
		int concat[] = new int[patLen + textLen + 1];
		System.arraycopy(pattern, 0, concat, 0, patLen);
		concat[patLen] = -1;
		System.arraycopy(text, 0, concat, patLen + 1, textLen);
		int prefix[] = prefix(concat);
		int cnt = 0;
		for(int i=0;i<textLen;i++){
			int j = i + patLen + 1;
			if(prefix[j] == patLen)
				cnt++;
		}
		
		return cnt;
	}

	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			int K = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			int pat[] = s1.nextIntArray(K);
			int diff[] = new int[N - 1];
			for(int i=0;i<N-1;i++)
				diff[i] = arr[i + 1] - arr[i];
			
			// out.println(new RabinKarp(diff).findOccurence(pat));
			
			out.println(findOccurence(diff, pat));
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