import java.util.*;
import java.io.*;
public class CircularRMQ
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	
	static class SegmentTreeLazyProp { 
		// In this version the tree holds RMQ and range increments 
		long tree[];
		long lazy[];
		int len;
		int size;
		SegmentTreeLazyProp(long arr[]) { // zero based index
			len = arr.length;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // 2 ^ (ceil(log(len)) + 1)
			tree = new long[size];
			lazy = new long[size];
			build(1, 0, len - 1, arr);
		}

		long build(int n , int nl , int nr , long arr[]) {
			if(nl == nr) 
				return tree[n] = arr[nl];
			else {
				int mid = (nl + nr) / 2;
				return tree[n] = Math.min(build(2*n, nl, mid, arr) , build(2*n + 1, mid + 1, nr, arr));
			}
		}

		boolean isInternal(int n) {
			return 2*n < size && 2*n + 1 < size;
		}

		void upd(int n) {
			if(lazy[n] != 0) {
				if(isInternal(n)) {
					lazy[2*n] += lazy[n];
					lazy[2*n + 1] += lazy[n];
				}
				tree[n] += lazy[n];
				lazy[n] = 0;
			}
		}

		long update(int n , int nl , int nr , int L ,int R, long inc) {

			if(nl == L && nr == R) {
				lazy[n] += inc;
				upd(n);
				return tree[n];
			}
			else {
				upd(n);
				if(isInternal(n)) {
					upd(2*n);
					upd(2*n + 1);
				}

				long left = tree[2*n];
				long right = tree[2*n + 1];
				int mid = (nl + nr) / 2;

				if(R <= mid)
					left = update(2*n, nl, mid, L, R, inc);
				else if(L > mid)
					right = update(2*n + 1, mid + 1, nr, L, R, inc);
				else {
					left  = update(2*n, nl, mid, L, mid, inc);
					right = update(2*n + 1, mid + 1, nr, mid + 1, R, inc);
				}

				return tree[n] = Math.min(left,right);

			}
		}

		void update(int L , int R , long inc) {
			update(1, 0, len - 1, L, R, inc);
		}

		long query(int n , int nl , int nr , int L , int R) {
			upd(n);
			if(nl == L && nr == R)
				return tree[n];
			else {
				int mid = (nl + nr) / 2;
				if(R <= mid)
					return query(2*n, nl, mid, L, R);
				else if(L > mid)
					return query(2*n + 1, mid + 1, nr, L, R);
				else
					return Math.min(query(2*n, nl, mid, L, mid) , query(2*n + 1, mid + 1, nr, mid + 1, R));

			}
		}

		long query(int L , int R) {
			return query(1, 0, len - 1, L, R);
		}

	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N = s1.nextInt();
		long arr[] = s1.nextLongArray(N);
		SegmentTreeLazyProp segTree = new SegmentTreeLazyProp(arr);
		
		int Q = s1.nextInt();
		while(Q-->0) {
			int query[] = Arrays.stream(s1.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			if(query.length == 2) {
				if(query[0] <= query[1])
					out.println(segTree.query(query[0], query[1]));
				else
					out.println(Math.min(segTree.query(query[0], N - 1),segTree.query(0, query[1])));
			}
			else {
				if(query[0] <= query[1])
					segTree.update(query[0], query[1], query[2]);
				else {
					segTree.update(query[0], N - 1, query[2]);
					segTree.update(0 , query[1] , query[2]);
				}
			}
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