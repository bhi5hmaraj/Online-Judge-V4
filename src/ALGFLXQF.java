import java.util.*;
import java.io.*;
class ALGFLXQF
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int MAX = (int) 1e7;
	static int sum[];
	public static void isPrimeArray(int N) // Sieve of Erathanoses
	{
		boolean num[] = new boolean[N + 1];
		Arrays.fill(num, true);
		num[1] = num[0]=  false;
		for (int i = 2; i * i <= N; i++)
			if (num[i])  // i is prime
				for (int j = i * i; j <= N; j += i)
					num[j] = false;
		
			
		for(int i=2;i<=N;i++) {
			if(num[i]) {
				for(int j=i;j<=N;j+=i)
					sum[j] += i;
			}
		}
		
		
	}
	

	static class FenwickTree {  // In this variant of BIT each query is **NOT** a cumulative sum , it is the actual freq
		// Point Query and Range update
		long tree[];
		int len;
		FenwickTree(int len) {
			this.len = len;
			tree = new long[len + 10];
		}
		void update(int idx , long val) {
			idx += 1;
			for(;idx <= len;idx += (idx & -idx)) 
				tree[idx] += val;
		}
		long query(int idx) {
			long sum = 0;
			idx += 1;
			for(;idx > 0;idx -= (idx & -idx))
				sum += tree[idx];
			
			return sum;
		}
		void update(int L , int R , int val) {
			if(L <= R && L > 0) {
				update(L, val);
				update(R + 1, -val);
			}
		}
	}
	
	static class Pair {
		int K , idx;
		boolean isRight;
		Pair(int idx  , int K , boolean b) {
			this.idx = idx;
			this.K = K;
			isRight = b;
		}
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		sum = new int[MAX + 10];
		isPrimeArray(MAX);

		int N = s1.nextInt();
		int Q = s1.nextInt();
		
		int A[] = s1.nextIntArrayOneBased(N);
		
		ArrayList<Pair>[] arl = new ArrayList[N + 1];
		for(int i = 1;i<=N;i++) arl[i] = new ArrayList<>();
		
		for(int i=0;i<Q;i++) {
			int L = s1.nextInt();
			int R = s1.nextInt();
			int K = s1.nextInt();
			Pair p = new Pair(i, K, false);
			Pair q = new Pair(i, K, true);
			arl[L].add(p);
			arl[R].add(q);
		}
		
		long ans[] = new long[Q];
		int MAX_POSS = MAX + 10;
		FenwickTree BIT = new FenwickTree(MAX_POSS);
		
		for(int i=1;i<=N;i++) {
			
			for(Pair p : arl[i])
				if(!p.isRight)
					ans[p.idx] = BIT.query(MAX_POSS) - BIT.query(p.K);
			
			BIT.update(sum[A[i]], 1L * sum[A[i]] * i);
			
			for(Pair p : arl[i]) 
				if(p.isRight) 
					ans[p.idx] = (BIT.query(MAX_POSS) - BIT.query(p.K)) - ans[p.idx];
			
		}
		
		for(int i =0;i<Q;i++)
			out.println(ans[i]);
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