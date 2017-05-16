import java.util.*;
import java.io.*;
public class WinterIsComing
{


	/************************ SOLUTION STARTS HERE ***********************/

	static int N; 

	static final int INF = (int)(1e6);

	static int memo[][][];
	static int temp[] , maxK;
	static int rec(int idx , int K , int type/*, String curr */) { // summer 0 , winter 1

		// System.out.println("idx " + idx + " K " + K + " type " + type);

		if(type == 1 && K <= 0)
			return INF;
		else if(temp[idx] < 0 && type == 0)
			return INF;
		else if(idx == N - 1) {
			// System.out.println(curr);
			return 0;
		}
		else if(memo[idx][K][type] != -1)
			return memo[idx][K][type];
		else {
			if(type == 0)
				return Math.min(rec(idx + 1, K, type/*, curr + "0 "*/),1 + rec(idx + 1, K - 1, 1/*, curr + "1 "*/));
			else
				return Math.min(rec(idx + 1, K - 1, type/*, curr + "1 "*/),1 + rec(idx + 1, K, 0/*, curr + "0 "*/));
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		maxK = s1.nextInt();
		temp = s1.nextIntArray(N);
		memo = new int[N][maxK + 1][2];
		for(int a[][] : memo)
			for(int b[] : a)
				Arrays.fill(b, -1);

		int ans = Math.min(rec(0, maxK, 0 /*,"0 "*/) , 1 + rec(0, maxK, 1 /*, "1 "*/));

		out.println(ans == INF ? -1 : ans);

	}
	
/*	static class Pair {
		int len;
		boolean isEnd;
		Pair(int l , boolean b) {
			len = l;
			isEnd = b;
		}
	}*/
	
	private static void solve2(FastScanner s1, PrintWriter out){

		N = s1.nextInt();
		maxK = s1.nextInt();
		temp = s1.nextIntArray(N);
		int neg = 0;
		for(int t : temp)
			neg += t < 0 ? 1 : 0;

		if(neg > maxK)
			out.println(-1);
		else {
			int min = 0;
			maxK -= neg;
			boolean sign = true;
			for(int t : temp) {
				min += (sign ^ (t >= 0)) ? 1 : 0;
				sign = t >= 0;
			}

			int curr = 0;
			for(int i=0;i<N;i++)
				if(temp[i] < 0) {
					curr = i;
					break;
				}
			
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			int end = -1;
			boolean include = false;
			while(curr < N) {
				if(temp[curr] < 0) {
					include = true;
					while(curr < N && temp[curr] < 0) curr++;
				}
				else {
					int len = 0;
					while(curr < N && temp[curr] >= 0) {
						curr++;
						len++;
					}
					if(curr == N)
						end = len;
					else
						pq.add(len);
				}
			}
			
			while(!pq.isEmpty()) {
				int len = pq.remove();
				if(len > maxK)
					break;
				else{
					maxK -= len;
					min -= 2;
				}
			}
			
			if(include && end > 0 && maxK >= end)
				min -= 1;
			
			out.println(min);
		}
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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