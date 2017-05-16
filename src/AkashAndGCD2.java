import java.util.*;
import java.io.*;
public class AkashAndGCD2
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class SegmentTree
	{
		long tree[];
		int len;
		int size;
		SegmentTree(int arr[]) 
		{
			len = arr.length - 1;
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new long[size];
			build(arr, 1, 1, len);
		}
		void update(int node,int idx,int val,int nl,int nr)
		{
			if(nl == nr && nl == idx)
				tree[node] = sum[val];
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);
				tree[node] = ModMath.add(tree[2*node],tree[(2 * node) + 1]);
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		long query(int L , int R){
			return query(1, L, R, 1, len);
		}
		long query(int node , int L , int R, int nl, int nr)
		{
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return ModMath.add(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		void build(int arr[],int node,int L,int R)
		{
			if(L == R)
				tree[node] = sum[arr[L]];
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = ModMath.add(tree[2*node] , tree[(2 * node) + 1]);
			}
		}
	}
	static int gcd(int big, int small) {
		int b = Math.max(big, small);
		int s = Math.min(big, small);
		return (s == 0) ? b : gcd(s, b % s);
	}
	static class ModMath {
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
	static final int MAX = (int)(5e5);
	static int phi[];
	static long sum[];
	static  {
		phi = new int[MAX + 10];
		sum = new long[MAX + 10];
		for(int i=1;i<=MAX;i++)
			phi[i] = i;
		for(int i=2;i<=MAX;i++){
			if(phi[i] == i) {
				for(int j=i;j <= MAX;j += i)
					phi[j] = (phi[j] - (phi[j] / i));
			}
		}
		for(int i=1;i<=MAX;i++) {    // sum (n / gcd(n , i))
			for(int j=i;j<=MAX;j+=i) {
				sum[j] = ModMath.add(sum[j] , ModMath.mul(  (j / i) , phi[j / i] ));
			}
		}
		
		for(int i=1;i<=MAX;i++) // Thank you OEIS : http://oeis.org/A057661
			sum[i] = ModMath.div(ModMath.add(1, sum[i]), 2);
		
	}
	private static void solve(FastScanner s1, PrintWriter out){
		int N = s1.nextInt();
		int arr[] = s1.nextIntArrayOneBased(N);
		int Q = s1.nextInt();
		SegmentTree tree = new SegmentTree(arr);
		while(Q-->0) {
			if(s1.nextChar() == 'C')
				out.println((tree.query(s1.nextInt(), s1.nextInt())));
			else
				tree.update(s1.nextInt(), s1.nextInt());
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