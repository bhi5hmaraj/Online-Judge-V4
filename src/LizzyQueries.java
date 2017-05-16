import java.util.*;
import java.io.*;
public class LizzyQueries   //Kathukita motha vithayum erakitaen 
{


	/************************ SOLUTION STARTS HERE ***********************/


	static class Interval
	{
		int l,r,mid; //Both inclusive	
		long sum;
		int size;
		Interval(int l,int r,long sum)
		{
			this.l = l;
			this.r = r;
			this.mid = l + ((r-l)/2);
			this.sum = sum;
			size = r - l + 1;
		}
		public boolean equals(int l,int r) {
			return (this.l == l && this.r == r); 
		}
		boolean isLeft(int l,int r)
		{
			return r <= mid;
		}
		boolean isRight(int l,int r)
		{
			return l > mid;
		}
		@Override
		public String toString() {
			return "L "+l+" R "+r+" sum "+sum;
		}
	}

	static class SegmentTree
	{
		Interval tree[];
		int size;
		int len;
		SegmentTree(long hash[]) //ar is zero based indexing
		{
			len = hash.length;	    
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new Interval[size];
			build(hash, 1, 1, len);
		}

		long query(int node , int L , int R)
		{
			if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
			{
				if(tree[node].equals(L, R))
					return tree[node].sum;
				else if(tree[node].isLeft(L, R))
					return query(2*node, L, R);
				else if(tree[node].isRight(L, R))
					return query((2*node)+1, L, R);
				else
					return ModMath.add(query(2*node, L, tree[node].mid), query((2*node)+1, tree[node].mid+1, R));
			}
			return 0;
		}

		long query(int L , int R){
			return ModMath.div(query(1, L, R), ModMath.modPow(x, L - 1));
		}

		void update(int node, int index , long val)
		{
			if(node >= 0 && node < size)
			{
				if(tree[node].l == tree[node].r && tree[node].mid == index)
					tree[node].sum = val;
				else
				{
					if(index > tree[node].mid)
						update((2 * node) + 1, index , val);
					else
						update(2 * node, index,val);

					tree[node].sum = ModMath.add(tree[2*node].sum , tree[(2*node)+1].sum);
				}
			}
		}

		void build(long hash[],int node,int L,int R)
		{
			if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
			{
				if(L == R)
					tree[node] = new Interval(L, R, hash[L-1]);
				else
				{
					int mid = L + ((R-L)/2);
					build(hash, 2 * node, L, mid);
					build(hash, (2 * node) + 1, mid + 1, R);
					tree[node] = new Interval(L, R, ModMath.add(tree[2*node].sum, tree[(2*node)+1].sum));
				}
			}
		}

	}

	static long x = 31;
	static class ModMath {
		static final long mod = (long)(1e9) + 7L;

		static long sub(long a, long b) {
			long sub = ((a % mod) - (b % mod)) % mod;
			return sub < 0 ? mod + sub : sub;
		}

		static long mul(long a, long b) {
			return ((a % mod) * (b % mod)) % mod;
		}

		static long add(long a, long b) {
			return ((a % mod) + (b % mod)) % mod;
		}

		static long div(long a, long b) {
			return mul(a, modInverse(b));
		}

		static long modInverse(long n) { // Fermat's little theorem
			return modPow(n, mod - 2L);
		}

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

	private static void solve(FastScanner s1, PrintWriter out){


		int N = s1.nextInt();
		int Q = s1.nextInt();
		String str = s1.nextLine();

		long forw_hash[] = new long[N];
		long rev_hash[] = new long[N];

		long xx = 1;

		for(int i=0;i<N;i++){
			forw_hash[i] = ModMath.mul(str.charAt(i), xx);
			rev_hash[i] = ModMath.mul(str.charAt(N-i-1), xx);

			xx = ModMath.mul(x, xx);
		}

		SegmentTree forward = new SegmentTree(forw_hash);		
		SegmentTree reverse = new SegmentTree(rev_hash);

		while(Q-->0)
		{
			int type = s1.nextInt();
			if(type == 0)
			{
				int pos = s1.nextInt();
				char ch = s1.nextChar();
				forward.update(1, pos, ModMath.mul(ModMath.modPow(x, pos-1), ch));
				reverse.update(1, N - pos + 1, ModMath.mul(ModMath.modPow(x, N-pos), ch));
			}
			else
			{
				int L = s1.nextInt();
				int R = s1.nextInt();
				int rev_L = N - L + 1;
				int rev_R = N - R + 1;

				if(forward.query(L, R) == reverse.query(rev_R, rev_L))
					out.println("Yes");
				else
					out.println("No");
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
		int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
		long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
		int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
		long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}

	/************************ TEMPLATE ENDS HERE ************************/
}