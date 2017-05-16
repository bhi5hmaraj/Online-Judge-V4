import java.util.*;
import java.io.*;
public class PalindromicSubsets
{

	/************************ SOLUTION STARTS HERE ***********************/

	// all evens == (2^a-1 * 2^b-1 ...) - 1 
	// one odd others even 
	// let (2^a-1 * 2^b-1 ...) = ways
	// total palin subsets = (ways * (nonzero + 1)) - 1 
	
	static class MM {   	// MM (Modular Math) class 
		static final long mod = (long) (1e9) + 7L; // Default
		static long sub(long a, long b) {return ((a % mod) - (b % mod) + mod) % mod;}
		static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
		static long add(long a, long b) {return ((a % mod) + (b % mod)) % mod;}
		static long div(long a, long b) {return mul(a, modInverse(b));}
		static long modInverse(long n)  {return modPow(n, mod - 2L);} // Fermat's little theorem
		static long modPow(long a, long b) { // squared exponentiation
			if (b == 0L || a == 1L)
				return 1L;
			else if (b == 1L)
				return a;
			else {
				if ((b & 1L) == 0L) // Checking whether b is even (fast)
					return modPow((a * a) % mod, b >> 1);
				else
					return (a * modPow((a * a) % mod, b >> 1)) % mod;
			}
		}
	}
	static int aux[] = new int[26];
	static class Interval {
		int l, r, mid; // Both inclusive
		int freq[];
		int lazy;
		Interval(int l, int r, char ch) {
			this(l , r);
			freq[ch - 'a'] = 1;
		}
		Interval(int l, int r) {
			this.l = l;
			this.r = r;
			this.mid = l + ((r - l) / 2);
			this.lazy = 0;
			freq = new int[26];
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
		boolean isLeaf() {
			return l == r;
		}
		@Override
		public String toString() {
			return String.format("(%d, %d) , freq = %s", l , r , Arrays.toString(freq));
		}
	}

	static class SegmentTreeLazyProp {
		Interval tree[];
		int size;
		int len;

		SegmentTreeLazyProp(String str) 
		{
			len = str.length();
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new Interval[size];
			build(str, 1, 0, len - 1);
		}

		Interval query(int node, int L, int R) {

			upd(node);
			if (tree[node].equals(L, R))
				return tree[node];
			else if (tree[node].isLeft(L, R))
				return query(2 * node, L, R);
			else if (tree[node].isRight(L, R))
				return query((2 * node) + 1, L, R);
			else
				return merge(query(2 * node, L, tree[node].mid), query((2 * node) + 1, tree[node].mid + 1, R));
		}

		int[] query(int L , int R) {
			return query(1, L, R).freq;
		}
		
		void update(int L , int R , int t) {
			update(1, L, R, t % 26);
		}
		
		void upd(int node) {
			if(!tree[node].isLeaf() && tree[node].lazy != 0) {
				tree[2*node].lazy += tree[node].lazy;
				tree[2*node + 1].lazy += tree[node].lazy;
			}
			int shift = tree[node].lazy % 26;
			tree[node].lazy = 0;
			if(shift != 0) {
				System.arraycopy(tree[node].freq, 0, aux, 0, 26);
				for(int i=0;i<26;i++)
					tree[node].freq[i] = aux[(i - shift + 26) % 26];
			}
		}
		void update(int node , int L , int R , int t) {
			
			if(tree[node].equals(L, R)) {
				tree[node].lazy += t;
				upd(node);
			}
			else {
				upd(node);
				upd(2*node);
				upd(2*node + 1);
				int M = tree[node].mid;
				if(tree[node].isLeft(L, R))
					update(2*node, L, R, t);
				else if(tree[node].isRight(L, R))
					update(2*node + 1, L, R, t);
				else {
					update(2*node, L, M, t);
					update(2*node + 1, M + 1, R, t);
				}
				Interval a = tree[2*node];
				Interval b = tree[2*node + 1];
				Interval c = tree[node];
				for(int i=0;i<26;i++)
					c.freq[i] = a.freq[i] + b.freq[i];
			}
		}
		Interval merge(Interval a, Interval b) {
			Interval c = new Interval(a.l, b.r);
			for(int i=0;i<26;i++)
				c.freq[i] = a.freq[i] + b.freq[i];
			return c;
		}

		Interval build(String str, int node, int L, int R) {
			if (L == R)
				return tree[node] = new Interval(L, R, str.charAt(L));
			else {
				int mid = (L + R) / 2;
				return tree[node] = merge(build(str, 2 * node, L, mid), build(str, (2 * node) + 1, mid + 1, R));
			}
		}
	}
	
	static long numOfPalindromicSubsets(int freq[]) {
		long ways = 1;
		int nonzero = 0;
		for(int f : freq) 
			if(f > 0) {
				nonzero++;
				ways = MM.mul(ways, MM.modPow(2, f - 1));
			}
		
		return MM.sub(MM.mul(ways, MM.add(nonzero, 1)), 1);
	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		int N = s1.nextInt(); // Unused
		int Q = s1.nextInt();
		SegmentTreeLazyProp segTree = new SegmentTreeLazyProp(s1.nextLine());
		while(Q-->0) 
			if(1 == s1.nextInt())
				segTree.update(s1.nextInt(), s1.nextInt(), s1.nextInt());
			else
				out.println(numOfPalindromicSubsets(segTree.query(s1.nextInt(), s1.nextInt())));
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