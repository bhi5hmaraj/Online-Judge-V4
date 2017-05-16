import java.util.*;
import java.io.*;
public class GreenandBlackTea
{


	/************************ SOLUTION STARTS HERE ***********************/

	static String rec (int idx , int N , int K , int a , int b , int streak , char ch , String curr) {

		if(streak > K)
			return null;
		if(idx == N)
			return curr;
		else {
			if(a > 0) {
				String s = rec(idx + 1, N, K, a - 1, b, ch == 'G' ? streak + 1 : 1, 'G', curr + 'G');
				if(s != null)
					return s;
			}
			if(b > 0) {
				String s = rec(idx + 1, N, K, a, b - 1, ch == 'B' ? streak + 1 : 1, 'B', curr + 'B');
				if(s != null)
					return s;
			}
			return null;
		}
		
	}

	private static void solve(FastScanner s1, PrintWriter out){ // Recursive brute force

		int N = s1.nextInt();
		int K = s1.nextInt();
		int a = s1.nextInt();
		int b = s1.nextInt();

		String s = rec(0, N, K, a, b, 1, '*', "*");
		
		if(s == null)
			out.println("NO");
		else
			out.println(s.substring(1));
	}
	
	private static void solve2(FastScanner s1, PrintWriter out){ // Incorrect greedy approach

		int N = s1.nextInt();
		int K = s1.nextInt();
		int a = s1.nextInt();
		int b = s1.nextInt();
		
		char sm , bi;
		int min , max;
		if(a <= b) {
			min = a;
			max = b;
			sm = 'G';
			bi = 'B';
		}
		else {
			min = b;
			max = a;
			sm = 'B';
			bi = 'G';
		}
		
		StringBuilder sb1 = new StringBuilder();
		int streak = 1;
		sb1.append(bi);
		for(int i=1;i<max;i++) {
			if(streak == K) {
				if(min > 0) {
					min--;
					sb1.append(sm);
					streak = 0;
				}
				else {
					out.println("NO");
					return;
				}
			}
			sb1.append(bi);
			streak++;
		}
		
		if(min == 0) {
			out.println(sb1);
			return;
		}
		
		StringBuilder sb2 = new StringBuilder();
		int ptr = 0;
		
		// out.println("sb1 " + sb1);
		
		while(ptr < sb1.length() && min > 0) {
			if(sb1.charAt(ptr) == bi) {
				for(int i=1;i<=Math.min(K,min);i++)
					sb2.append(sm);
				sb2.append(bi);
				ptr++;
				min -= Math.min(min,K);
			}
			else {
				for(int i=1;i<=Math.min(K,min);i++)
					sb2.append(sm);
				ptr++;
				if(ptr < sb1.length()) {
					sb2.append(bi);
					ptr += 2;
				}
				min -= Math.min(min,K);
			}
		}
		
		if(ptr < sb1.length())
			sb2.append(sb1.substring(ptr));
		
		// out.println(sb2);
		
		if(min > 0)
			out.println("NO");
		else
			out.println(sb2);
		
	}

	private static void solve3(FastScanner s1, PrintWriter out){ // Correct greedy approach

		int N = s1.nextInt();
		int K = s1.nextInt();
		int a = s1.nextInt();
		int b = s1.nextInt();
		char sm , bi;
		int min , max;
		if(a <= b) {
			min = a;
			max = b;
			sm = 'G';
			bi = 'B';
		}
		else {
			min = b;
			max = a;
			sm = 'B';
			bi = 'G';
		}
		
		if(max * 1L > 1L * (min + 1) * K)
			out.println("NO");
		else {
			int bins = min + 1;
			StringBuilder ans = new StringBuilder(); // 30 ms faster than buffer
			StringBuilder rep = new StringBuilder();
			
			for(int i=1;i<=(max / bins);i++)
				rep.append(bi);
			
			for(int i=1;i<=(max % bins);i++)
				ans.append(rep).append(bi).append(sm);
			for(int i=max % bins;i < bins - 1;i++)
				ans.append(rep).append(sm);
			ans.append(rep);
			
			out.println(ans);
		}
		
	}
		/************************ SOLUTION ENDS HERE ************************/





		/************************ TEMPLATE STARTS HERE *********************/

		public static void main(String []args) throws IOException {
			FastScanner in  = new FastScanner(System.in);
			PrintWriter out = 
					new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
			solve3(in, out);
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