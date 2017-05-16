import java.util.*;
import java.io.*;
public class MagicalGCD
{


	/************************ SOLUTION STARTS HERE ***********************/

	static long gcd(long a , long b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	static class SegmentTree  // Implemented to store min in a range , point update and range query
	{
		long tree[];
		int len;
		int size;
		SegmentTree(long arr[]) 
		{
			len = arr.length;
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new long[size];
			build(arr, 1, 0, len - 1);
		}
		
		long query(int L , int R){
			return query(1, L, R, 0, len - 1);
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
				return gcd(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		void build(long arr[],int node,int L,int R)
		{
			if(L == R)
				tree[node] = arr[L];
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = gcd(tree[2*node] , tree[(2 * node) + 1]);
			}
		}
	}

	static class SparseTable1D  // < O(Nlog(N)) , O(1) >  0 based indexing
	{
		long sparse[][];
		int len;
		static int log(int N) { return 31 - Integer.numberOfLeadingZeros(N); }

		SparseTable1D(long arr[])
		{
			len = arr.length;
			int k = log(len) + 1;
			sparse = new long[k][len];
			for (int i = 0; i < len; i++)
				sparse[0][i] = arr[i];

			for(int i=1;i<k;i++)
				for(int j=0;j+(1<<i) <= len;j++)
					sparse[i][j] = gcd(sparse[i-1][j],sparse[i-1][j+(1<<(i-1))]);

		}

		long getGCD(int L,int R)
		{
			int sz = R - L + 1;
			int k  = log(sz);
			long v1 = sparse[k][L];
			long v2 = sparse[k][L + (sz - (1 << k))];
			return gcd(v1, v2);
		}

	}

	private static void solve(FastScanner s1, PrintWriter out){  // O(N^2 log N) is too slow)

		int T = s1.nextInt();
		while(T-->0) {
			int N = s1.nextInt();
			long arr[] = s1.nextLongArray(N);
			// SparseTable1D st = new SparseTable1D(arr);
			SegmentTree segTree = new SegmentTree(arr);
			
			long max = 0;
			for(int i=0;i<N;i++)
				for(int j=i;j<N;j++)
					max = Math.max(max,(j - i + 1) * segTree.query(i, j));

			out.println(max);

		}

	}

	private static void solve2(FastScanner s1, PrintWriter out){
		
		int T = s1.nextInt();
		while(T-->0) {
			
			HashMap<Long , Integer> map = new HashMap<>();
			long max = 0;
			
			int N = s1.nextInt();
			long arr[] = s1.nextLongArray(N);
			
			for(int i=0;i<N;i++) {
				
				HashMap<Long, Integer> temp = new HashMap<>();
				
				for(Map.Entry<Long, Integer> e : map.entrySet()) {
					long key = gcd(arr[i], e.getKey());
					if(temp.containsKey(key))
						temp.put(key, Math.min(temp.get(key),e.getValue()));
					else
						temp.put(key, e.getValue());
				}
				
				if(!temp.containsKey(arr[i]))
					temp.put(arr[i], i);
				
				for(Map.Entry<Long, Integer> e : temp.entrySet()) 
					max = Math.max(max,(i - e.getValue() + 1) * e.getKey());
				
				map = temp;

			}
			
			out.println(max);
		}
		
	}

	/************************ SOLUTION ENDS HERE ************************/





	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(System.in);
		PrintWriter out = 
				new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
		solve2(in, out);
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