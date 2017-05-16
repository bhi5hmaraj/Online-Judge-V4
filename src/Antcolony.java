import java.util.*;
import java.io.*;
public class Antcolony
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Interval {
		int l, r, mid; // Both inclusive
		int gcd;
		int min;
		int min_cnt;
		int size;

		Interval(int l, int r, int key) {
			this.l = l;
			this.r = r;
			this.mid = l + ((r - l) / 2);
			size = r - l + 1;
			gcd = min = key;
			min_cnt = 1;
		}

		Interval(int l, int r) {
			this.l = l;
			this.r = r;
			this.mid = l + ((r - l) / 2);
			size = r - l + 1;
		}

		public boolean equals(int l, int r) {
			return (this.l == l && this.r == r);
		}

		boolean isLeft(int l, int r) {
			return r <= mid;
		}

		boolean isRight(int l, int r) {
			return l > mid;
		}
	}

	static class SegmentTree {
		Interval tree[];
		int size;
		int len;

		SegmentTree(int arr[]) // arr is one based indexing
		{
			len = arr.length - 1;
			for (size = 1; size < len; size <<= 1)
				;
			size <<= 1;
			tree = new Interval[size];
			build(arr, 1, 1, len);
		}

		Interval query(int node, int L, int R) {
			if (tree[node].equals(L, R))
				return tree[node];
			else if (tree[node].isLeft(L, R))
				return query(2 * node, L, R);
			else if (tree[node].isRight(L, R))
				return query((2 * node) + 1, L, R);
			else
				return merge(query(2 * node, L, tree[node].mid), query((2 * node) + 1, tree[node].mid + 1, R));
		}

		Interval merge(Interval a, Interval b) {
			Interval c = new Interval(a.l, b.r);
			c.gcd = gcd(a.gcd, b.gcd);
			c.min = Math.min(a.min, b.min);
			if (a.min == b.min)
				c.min_cnt = a.min_cnt + b.min_cnt;
			else if (a.min < b.min)
				c.min_cnt = a.min_cnt;
			else
				c.min_cnt = b.min_cnt;
			return c;
		}

		Interval build(int arr[], int node, int L, int R) {
			if (L == R)
				return tree[node] = new Interval(L, R, arr[L]);
			else {
				int mid = L + ((R - L) / 2);
				return tree[node] = merge(build(arr, 2 * node, L, mid), build(arr, (2 * node) + 1, mid + 1, R));
			}
		}
	}

	static int gcd(int a , int b){
		int min = Math.min(a,b);
		int max = Math.max(a,b);
		return min == 0 ? max : gcd(min, max % min);
	}

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int arr[] = s1.nextIntArrayOneBased(N);
		int Q = s1.nextInt();
		SegmentTree segTree = new SegmentTree(arr);
		
		while(Q-->0){
			Interval interval = segTree.query(1, s1.nextInt(), s1.nextInt());
			int eat = interval.size;
			if(interval.gcd == interval.min)
				eat -= interval.min_cnt;
			out.println(eat);
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}