import java.util.*;
import java.io.*;
class INTERVAL 
{


	/************************ SOLUTION STARTS HERE ***********************/


	static class SegmentTree  { // Implemented to store min in a range , point update and range query
		long maxTree[];
		long minTree[];
		int len;
		int size;
		SegmentTree(int len) { 
			this.len = len;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			maxTree = new long[size];
			minTree = new long[size];
		}
		void update(int node,int idx,long val,int nl,int nr) {
			if(nl == nr && nl == idx) 
				minTree[node] = maxTree[node] = val;
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				maxTree[node] = Math.max(maxTree[2*node],maxTree[(2 * node) + 1]);
				minTree[node] = Math.min(minTree[2*node],minTree[(2 * node) + 1]);

			}
		}
		void update(int idx , long val){
			update(1, idx, val, 0, len - 1);
		}
		long query(int L , int R , boolean min){
			return query(1, L, R, 0, len - 1 , min);
		}
		long query(int node , int L , int R, int nl, int nr , boolean min) {
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return min ? minTree[node] : maxTree[node];
				else if(R <= mid)
					return query(2 * node, L, R, nl, mid , min);
				else if(L > mid)
					return query((2*node)+1, L, R, mid + 1 , nr , min);
				else
					return min ? Math.min(query(2*node, L, mid , nl , mid , min) ,  query((2*node)+1, mid+1, R , mid+1,nr , min)) : Math.max(query(2*node, L, mid , nl , mid , min) ,  query((2*node)+1, mid+1, R , mid+1,nr , min));
		}
	}
	static long rec(int idx , int pos) {
		if(pos == M)
			return 0;
		else if(memo[idx][pos] != -1)
			return memo[idx][pos];
		else {
			int end = idx - 1 + interval[pos - 1] - 1 - 1;
			long score = pos % 2 == 1 ? Long.MAX_VALUE : Long.MIN_VALUE;

			for(int i=idx;i + interval[pos] - 1 <= end;i++) {
				if(pos % 2 == 1)
					score = Math.min(score,-(prefixSum[i + interval[pos]] - prefixSum[i]) + rec(i + 1, pos + 1));
				else
					score = Math.max(score,(prefixSum[i + interval[pos]] - prefixSum[i]) + rec(i + 1, pos + 1));
			}

			return memo[idx][pos] = score;
		}
	}
	static int interval[];
	static long memo[][];
	static int N , M;
	static long prefixSum[];
	private static void solve1(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {

			N = s1.nextInt();
			M = s1.nextInt();
			long arr[] = s1.nextLongArray(N);
			interval = s1.nextIntArray(M);
			prefixSum = new long[N + 1];

			memo = new long[N][M];
			for(long a[] : memo)
				Arrays.fill(a, -1);

			for(int i=1;i<=N;i++)
				prefixSum[i] = prefixSum[i - 1] + arr[i - 1];

			long optScore = Long.MIN_VALUE;
			for(int i=0;i<=N - interval[0];i++)
				optScore = Math.max(optScore,(prefixSum[i + interval[0]] - prefixSum[i]) + rec(i + 1, 1));

			out.println(optScore);

		}
	}
	private static void solve2(FastScanner s1, PrintWriter out){

		int T = s1.nextInt();
		while(T-->0) {

			N = s1.nextInt();
			M = s1.nextInt();
			long arr[] = s1.nextLongArray(N);
			interval = s1.nextIntArray(M);
			prefixSum = new long[N + 1];
			SegmentTree segTree = new SegmentTree(N);

			for(int i=1;i<=N;i++)
				prefixSum[i] = prefixSum[i - 1] + arr[i - 1];

			for(int i=0;i<=N-interval[M - 1];i++)
				segTree.update(i, (M % 2 == 0 ? -1 : 1) * (prefixSum[i + interval[M - 1]] - prefixSum[i]));

			for(int d = M - 1;d >= 1;d--)  
				for(int i=0;i<=N - interval[d - 1];i++)  // This i denotes starting point of previous depth
					segTree.update(i, ((d % 2 == 0 ? -1 : 1) * (prefixSum[i + interval[d - 1]] - prefixSum[i])) + segTree.query(i + 1, i + interval[d - 1] - interval[d] - 1, d % 2 == 1));

			out.println(segTree.query(0, N - interval[0], false));

		}

	} 

	/*** Sliding Window Courtesy : http://articles.leetcode.com/sliding-window-maximum/ _/\_ ***/

	private static void solve3(FastScanner s1, PrintWriter out){
		int T = s1.nextInt();
		while(T-->0) {
			N = s1.nextInt();
			M = s1.nextInt();
			long arr[] = s1.nextLongArray(N);
			interval = s1.nextIntArray(M);
			prefixSum = new long[N + 1];
			for(int i=1;i<=N;i++)
				prefixSum[i] = prefixSum[i - 1] + arr[i - 1];

			long DP[] = new long[N];
			for(int i=0;i<=N-interval[M - 1];i++)
				DP[i] = ((M % 2 == 0) ? -1 : 1) * (prefixSum[i + interval[M - 1]] - prefixSum[i]);

			for(int d = M - 1;d >= 1;d--) {
				int intervalSize = interval[d - 1] - interval[d] -1;
				ArrayDeque<Integer> deque = new ArrayDeque<>(intervalSize);
				// starts at 1 and ends at N - interval[d - 1] + 1
				if(d % 2 == 0) { // Max
					for(int i=1;i <= intervalSize;i++) {
						while(!deque.isEmpty() && DP[deque.getLast()] <= DP[i])
							deque.removeLast();
						deque.addLast(i);
					}
					for(int i=0;i <= N - interval[d - 1] ;i++) {
						DP[i] = -(prefixSum[i + interval[d - 1]] - prefixSum[i]) + DP[deque.getFirst()];
						while(!deque.isEmpty() && DP[i + 1 + intervalSize] >= DP[deque.getLast()])
							deque.removeLast();
						while(!deque.isEmpty() && deque.getFirst() <= i + 1)
							deque.removeFirst();

						deque.addLast(i + 1 + intervalSize);
					}
				}
				else {	// Min
					for(int i=1;i<=intervalSize;i++) {
						while(!deque.isEmpty() && DP[i] <= DP[deque.getLast()])
							deque.removeLast();
						deque.addLast(i);
					}
					for(int i=0;i <= N - interval[d - 1];i++) {
						DP[i] = (prefixSum[i + interval[d - 1]] - prefixSum[i]) + DP[deque.getFirst()];
						while(!deque.isEmpty() && DP[i + 1 + intervalSize] <= DP[deque.getLast()])
							deque.removeLast();
						while(!deque.isEmpty() && deque.getFirst() <= i + 1)
							deque.removeFirst();

						deque.addLast(i + 1 + intervalSize);
					}
				}
			}

			long maxScore = Long.MIN_VALUE;
			for(int i=0;i<=N - interval[0];i++)
				maxScore = Math.max(maxScore,DP[i]);

			out.println(maxScore);
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