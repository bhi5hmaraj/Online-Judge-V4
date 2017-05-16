import java.util.*;
import java.io.*;
public class TwoVersusThree
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
		SegmentTree(String bin) //ar is one based indexing
		{
			len = bin.length();	    
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new Interval[size];
			build(bin, 1, 1, bin.length());

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
					return add(query(2*node, L, tree[node].mid)*modPow(2, R-tree[node].mid), query((2*node)+1, tree[node].mid+1, R));
			}
			return 0;
		}

		void update(int node, int index)
		{
			if(node >= 0 && node < size)
			{
				if(tree[node].l == tree[node].r && tree[node].mid == index)
					tree[node].sum = 1;
				else
				{
					if(index > tree[node].mid)
						update((2 * node) + 1, index);
					else
						update(2 * node, index);

					tree[node].sum = add(tree[2*node].sum * modPow(2, tree[(2*node)+1].size), tree[(2*node)+1].sum);
				}
			}
		}

		void build(String bin,int node,int L,int R)
		{
			if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
			{
				if(L == R)
					tree[node] = new Interval(L, R, Character.getNumericValue(bin.charAt(L-1)));
				else
				{
					int mid = L + ((R-L)/2);
					build(bin, 2 * node, L, mid);
					build(bin, (2 * node) + 1, mid + 1, R);
					tree[node] = new Interval(L, R, add(tree[2*node].sum * modPow(2, tree[(2*node)+1].size), tree[(2*node)+1].sum));
				}
			}
		}

	}

	static long mod = 3;
	static long add(long a,long b)
	{
		return ((a % mod) + (b % mod)) % mod;
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

	private static void solve(FastScanner s1, PrintWriter out){

		int N = s1.nextInt();
		String bin = s1.nextLine();
		SegmentTree st = new SegmentTree(bin);
		int Q = s1.nextInt();

		while(Q-->0)
		{
			int type = s1.nextInt();
			if(type == 1)
				st.update(1, s1.nextInt() + 1);
			else
				out.println(st.query(1, s1.nextInt() + 1, s1.nextInt() + 1));
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