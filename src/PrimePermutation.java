import java.util.*;
import java.io.*;
public class PrimePermutation
{


	/************************ SOLUTION STARTS HERE ***********************/

	public static boolean[] isPrimeArray(int N) // Sieve of Erathanoses
	{
		boolean num[] = new boolean[N + 1];
		Arrays.fill(num, true);
		num[1] = num[0]=  false;
		for (int i = 2; i * i <= N; i++)
			if (num[i])  // i is prime
				for (int j = i * i; j <= N; j += i)
					num[j] = false;


		return num;
	}

	private static void solve(FastScanner s1, PrintWriter out){

		String str = s1.nextLine();
		int N = str.length();
		boolean isPrime[] = isPrimeArray(N);
		boolean equiclass[] = new boolean[N + 1];
		for(int i=1;i<=N;i++)
			equiclass[i] = i % 2 == 0;
		for(int i=3;i<=N;i++) {
			if(isPrime[i]) {
				boolean flag = false;
				for(int j=i;j <= N;j += i)
					flag |= equiclass[j];
				for(int j=i;j <= N;j += i)
					equiclass[j] = flag;
			}
		}
		
		// System.out.println("equiclass " + Arrays.toString(equiclass));
		
		int cnt = 0;
		for(int i=1;i<=N;i++)
			cnt += equiclass[i] ? 1 : 0;
		
		int freq[] = new int[26];
		boolean flag = false;
		int charPos = 0;
		for(int i = 0 , len = str.length();i < len;i++) {
			char ch =  str.charAt(i);
			freq[ch - 'a']++;
			if(freq[ch - 'a'] >= cnt) {
				charPos = ch - 'a';
				flag = true;
			}
		}
		
		if(!flag) {
			out.println("NO");
			return;
		}
		
		char ans[] = new char[N + 1];
		for(int i=1;i<=N;i++)
			if(equiclass[i]) {
				ans[i] = (char) ('a' + charPos);
				freq[charPos]--;
			}
				
		for(int i=1;i<=N;i++)
			if(!equiclass[i]) {
				while(freq[charPos] == 0)
					charPos = (charPos + 1) % 26;
				ans[i] = (char)(charPos + 'a');
				freq[charPos]--;
			}
		
		out.println("YES");
		for(int i=1;i<=N;i++) out.print(ans[i]);
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