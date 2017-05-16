import java.util.*;
import java.io.*;
public class SashaandArray
{


	/************************ SOLUTION STARTS HERE ***********************/
	static final Matrix fib = new Matrix(1, 1, 1, 0);
	static final int mod = (int) (1e9) + 7; // Default
	/*	
	 * This caused TLE
	static int mul(long a, long b) {return (int) (((a % mod) * (b % mod)) % mod);}
	static int add(int a, int b) {return ((a % mod) + (b % mod)) % mod;}
	*/
	static Matrix DP0[] , DP1[] ;
	static final int MAX = (1 << 16);
	static {
		DP0 = new Matrix[MAX];
		DP1 = new Matrix[MAX];
		DP0[0] = new Matrix(1, 0, 0, 1);
		for(int i=1;i<MAX;i++) {
			Matrix back = DP0[i - 1];
			DP0[i] = new Matrix(back.e00, back.e01, back.e10, back.e11);
			DP0[i].multiply(fib);
		}
		for(int i=0;i<MAX;i++)
			DP1[i] = Matrix.pow(DP0[i], MAX);
	}
	static class HalfMatrix {

		long e10, e11;

		HalfMatrix(long c, long d) {
			e10 = c;
			e11 = d;
		}

		void multiply(Matrix that) {
			long t1 = this.e10;
			long t2 = this.e11;
			this.e10 = (( ( (t1 * that.e00) % mod ) + ( (t2 * that.e10) % mod) ) % mod);
			this.e11 = (( ( (t1 * that.e01) % mod ) + ( (t2 * that.e11) % mod) ) % mod);
		}

		static HalfMatrix addMat(HalfMatrix a , HalfMatrix b) {
			return new HalfMatrix((a.e10 + b.e10) % mod, (a.e11 + b.e11) % mod);
		}
		void update(HalfMatrix left , HalfMatrix right) {
			e10 = (left.e10 + right.e10) % mod;
			e11 = (left.e11 + right.e11) % mod;
		}
	}
	static class Matrix {

		long e00, e01, e10, e11;

		Matrix(long a, long b, long c, long d) {
			e00 = a;
			e01 = b;
			e10 = c;
			e11 = d;
		}
		Matrix() {}
		void setUnit() {
			e00 = e11 = 1;
			e01 = e10 = 0;
		}
		boolean isUnit() {
			return e00 == 1 && e11 == 1 && e01 == 0 && e10 == 0;
		}
		
		void multiply(Matrix t) {
			long a = (((e00 * t.e00) % mod) + ((e01 * t.e10) % mod)) % mod;
			long b = (((e00 * t.e01) % mod) + ((e01 * t.e11) % mod)) % mod;
			long c = (((e10 * t.e00) % mod) + ((e11 * t.e10) % mod)) % mod;
			long d = (((e10 * t.e01) % mod) + ((e11 * t.e11) % mod)) % mod;
			e00 = a;
			e01 = b;
			e10 = c;
			e11 = d;
		}
		public String toString() {
			return e00 + " " + e01 + "\n" + e10 + " " + e11 + "\n";
		}
		public static Matrix pow(Matrix mat , int b) {
			Matrix ans = new Matrix();
			ans.setUnit();
			Matrix m   = new Matrix(mat.e00, mat.e01, mat.e10, mat.e11);
			while(b > 0) {
				if((b & 1) == 1)
					ans.multiply(m);
				m.multiply(m);
				b >>= 1;
			}
			return ans;
		}

	}

	static class SegmentTree {
		Matrix lazy[];
		HalfMatrix tree[];
		int size;
		int len;

		SegmentTree(int arr[]) // arr is one based indexing
		{
			len = arr.length - 1;
			for (size = 1; size < len; size <<= 1)
				;
			size <<= 1;
			tree = new HalfMatrix[size];
			lazy = new Matrix[size];
			for(int i=0;i<size;i++) {
				lazy[i] = new Matrix();
				lazy[i].setUnit();
			}
			build(arr, 1, 1, len);
		}
		private boolean isInternal(int node){
			return 2*node < size && (2*node)+1 < size;
		}

		private long query(int node , int L , int R, int nl, int nr)
		{
			upd(node);
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node].e10;
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return (query(2*node, L, mid , nl , mid) +  query((2*node)+1, mid+1, R , mid+1,nr)) % mod;
		}
		public long query(int L , int R) {
			return query(1, L, R, 1, len);
		}
		public void update(int L , int R , int x) {
			update(1, L, R, 1, len, fibonacci(x));
		}
		private void upd(int node){
			if(!lazy[node].isUnit()) {
				if(isInternal(node)){ 
					lazy[2*node].multiply(lazy[node]);
					lazy[(2*node)+1].multiply(lazy[node]);
				}
				tree[node].multiply(lazy[node]);
				lazy[node].setUnit();
			}
		}

		private void update(int node,int L, int R,int nl,int nr ,Matrix inc)
		{
			// System.out.println("L " + L + " R " + R + " nl "+ nl + " nr " + nr);

			if(L == nl && R == nr) {
				lazy[node].multiply(inc);
				upd(node);
				return;
			}
			int mid = (nl + nr) / 2;
			
			upd(node);
			upd(2 * node);
			upd((2 * node) + 1);

			if(R <= mid)
				update(2*node, L, R , nl , mid , inc);
			else if(L > mid)
				update((2*node) + 1, L, R , mid + 1, nr , inc);
			else
			{
				update(2*node, L, mid , nl , mid , inc);
				update((2*node)+1, mid+1, R , mid+1,nr , inc);
			}
			
			tree[node].update(tree[2*node], tree[2*node + 1]);
		}

		private void build(int arr[], int node, int L, int R) {
			if (L == R) {
				Matrix f = fibonacci(arr[L]);
				tree[node] = new HalfMatrix(f.e10, f.e11);
			}
			else {
				int mid = L + ((R - L) / 2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1 , R);
				tree[node] = HalfMatrix.addMat(tree[2*node] , tree[(2*node)+1]);
			}
		}
	}


	static Matrix fibonacci(int n) {
		Matrix ans = new Matrix();
		ans.setUnit();
		ans.multiply(DP0[n % MAX]);
		n /= MAX;
		ans.multiply(DP1[n % MAX]);
		return ans;
	}


	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		int M = s1.nextInt();
		int arr[] = s1.nextIntArrayOneBased(N);
		SegmentTree tree = new SegmentTree(arr);
		while(M-->0) {
			if(s1.nextInt() == 1)
				tree.update(s1.nextInt(), s1.nextInt(), s1.nextInt());
			else
				out.println(tree.query(s1.nextInt(), s1.nextInt()));
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