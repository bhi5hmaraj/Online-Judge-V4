import java.util.*;
import java.io.*;
public class NewYearTree
{


	/************************ SOLUTION STARTS HERE ***********************/

	static ArrayList<Integer>[] adj;
	static int L[] , R[] , time = 1, newToOld[];

	static void dfs(int u , int par) {
		L[u] = time;
		newToOld[time] = u;
		time++;
		for(int v : adj[u])
			if(v != par)
				dfs(v, u);

		R[u] = time - 1;
	}

	static class SegmentTreeLazyProp {

		long tree[];
		int lazy[];
		int len;
		int size;
		SegmentTreeLazyProp(int arr[]) { // one based index
			len = arr.length - 1;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // 2 ^ (ceil(log(len)) + 1)
			tree = new long[size];
			lazy = new  int[size];
			build(1, 1, len, arr);
		}

		long build(int n , int nl , int nr , int arr[]) {
			if(nl == nr) 
				return tree[n] = 1L << arr[newToOld[nl]];
			else {
				int mid = (nl + nr) / 2;
				return tree[n] = build(2*n, nl, mid, arr) | build(2*n + 1, mid + 1, nr, arr);
			}
		}

		boolean isInternal(int n) {
			return 2*n < size && 2*n + 1 < size;
		}

		void upd(int n) {
			if(lazy[n] > 0) {
				if(isInternal(n)) {
					lazy[2*n] = lazy[n];
					lazy[2*n + 1] = lazy[n];
				}
				tree[n] = 1L << lazy[n];
				lazy[n] = 0;
			}
		}

		long update(int n , int nl , int nr , int L ,int R, int c) {

			if(nl == L && nr == R) {
				if(isInternal(n)) {
					lazy[2*n] = c;
					lazy[2*n + 1] = c; 
				}
				lazy[n] = 0;
				return tree[n] = 1L << c;
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
					left = update(2*n, nl, mid, L, R, c);
				else if(L > mid)
					right = update(2*n + 1, mid + 1, nr, L, R, c);
				else {
					left = update(2*n, nl, mid, L, mid, c);
					right = update(2*n + 1, mid + 1, nr, mid + 1, R, c);
				}

				return tree[n] = left | right;

			}
		}

		void update(int v , int c) {
			update(1, 1, len, L[v], R[v], c);
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
					return query(2*n, nl, mid, L, mid) | query(2*n + 1, mid + 1, nr, mid + 1, R);

			}
		}

		int query(int v) {
			return Long.bitCount(query(1, 1, len, L[v], R[v]));
		}

	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = V - 1;
		int Q = s1.nextInt();

		int colors[] = s1.nextIntArrayOneBased(V);
		adj = new ArrayList[V + 1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();

		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}

		L = new int[V + 1];
		R = new int[V + 1];
		newToOld = new int[V + 1];

		dfs(1, 0);

		SegmentTreeLazyProp segmentTree = new SegmentTreeLazyProp(colors);

		while(Q-->0) {
			int type = s1.nextInt();
			if(1 == type)
				segmentTree.update(s1.nextInt(), s1.nextInt());
			else
				out.println(segmentTree.query(s1.nextInt()));
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