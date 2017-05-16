import java.util.*;
import java.io.*;
class AkashandGCD1
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static int lp[];
	private static int MAX  = (int)5e5 + 10;
	private static int fi[];
	private static long mod = (long)1e9 + 7;
	private static long sum[];
	private static void calc_sieve()
	{
		lp = new int[MAX];		
		fi = new int[MAX];
		int pr[] = new int[MAX];
		int sz=0;
		fi[1] = 1;
		for (int i = 2; i < MAX; ++i)
		{
			if (lp[i] == 0)
			{
				lp[i] = i;
				fi[i] = i - 1;
				pr[sz]=i;
				sz++;
			}
			else
			{
				//Calculating phi
				if (lp[i] == lp[i / lp[i]])
					fi[i] = fi[i / lp[i]] * lp[i];
				else
					fi[i] = fi[i / lp[i]] * (lp[i] - 1);
			}
			for (int j = 0; j < sz && pr[j] <= lp[i] && i * pr[j] < MAX; ++j)
				lp[i * pr[j]] = pr[j];
		}
	}
	private static long add(long a,long b)
	{
		return ((a%mod)+(b%mod))%mod;
	}
	private static void calc_sum()
	{
		sum = new long[MAX];
		for(int i=1;i<MAX;i++)
		{
			for(int j=i;j<MAX;j+=i)
			{
				sum[j] = add(sum[j], (long)i * (long)fi[j/i]);
			}
		}
	}
	static class Interval
	{
		int l,r,mid; //Both inclusive	
		long curr_sum;
		Interval(int l,int r,long sum)
		{
			this.l = l;
			this.r = r;
			this.mid = l + ((r-l)/2);
			this.curr_sum = sum;
		}
		boolean equals(int l,int r) {
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
	}
	static class SegmentTree
	{
		Interval tree[];
		int size;
		SegmentTree(int ar[]) //ar is one based indexing
		{
			int len = ar.length-1;	    
			for(size=1;size<len;)
				size <<= 1;
			size <<= 1;
			tree = new Interval[size];
			int start = (size/2);
			int ptr=1;
			for (; ptr <= len; ptr++, start++)
				tree[start] = new Interval(ptr, ptr, sum[ar[ptr]]);
			for (; start < size; start++, ptr++)
				tree[start] = new Interval(ptr, ptr, 0);

			for (int i = (size - 1) / 2; i >= 1; i--)
				tree[i] = new Interval(tree[2 * i].l, tree[(2 * i) + 1].r,
						add(tree[2*i].curr_sum, tree[2*i + 1].curr_sum));	    
		}
		void update(int index,int val,int ar[])
		{
			ar[index] = val;
			int pos = (size / 2) + index - 1;
			tree[pos].curr_sum = sum[ar[index]];
			for (int i = pos >> 1; i > 0; i >>= 1)
				tree[i].curr_sum = add(tree[2*i].curr_sum, tree[2*i + 1].curr_sum);    
		}
		long query(int L,int R)
		{
			return query(1, L, R);
		}
		long query(int index,int l,int r)
		{
			if (tree[index].equals(l, r))
				return tree[index].curr_sum;
			else if (tree[index].isLeft(l, r))
				return query(2 * index, l, r);
			else if (tree[index].isRight(l, r))
				return query((2 * index) + 1, l, r);
			else
				return add(query(2 * index, l, tree[index].mid), query((2 * index) + 1, tree[index].mid + 1, r));	    
		}
	}
	private static void solve(FastScanner s1, PrintWriter out){

		calc_sieve();
		calc_sum();
		int N = s1.nextInt();
		int ar[] = s1.nextIntArrayOneBased(N);
		int query = s1.nextInt();
		SegmentTree segTree = new SegmentTree(ar);
		while(query-->0)
		{
			char choice = s1.nextChar();
			if(choice == 'C')
				out.println(segTree.query(s1.nextInt(), s1.nextInt()));
			else
				segTree.update(s1.nextInt(), s1.nextInt(), ar);
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