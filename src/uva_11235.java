import java.util.*;
import java.io.*;
public class uva_11235
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static int ceil(int arr[] , int key) {
		int lo = 0 , hi = arr.length - 1 , ceil = -1;
		while(lo <= hi) {
			int mid = (lo + hi) / 2;
			if(key <= arr[mid]) {
				hi = mid - 1;
				ceil = mid;
			}
			else
				lo = mid + 1;
		}
		return ceil;
	}

	static class SegmentTree
	{
		int tree[];
		int len;
		int size;
		SegmentTree(int arr[]) 
		{
			len = arr.length;
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new int[size];
			build(arr, 1, 0, len - 1);
		}
		int getMax(int L , int R){
			if(L > R)
				return Integer.MIN_VALUE;
			else 
				return query(1, L, R, 0, len - 1);
		}
		int query(int node , int L , int R, int nl, int nr)
		{
			int mid = (nl + nr) / 2;
			// System.out.printf("L = %d R = %d nl = %d nr = %d \n", L , R , nl , nr);
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		void build(int arr[],int node,int L,int R)
		{
			if(L == R)
				tree[node] = arr[L];
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				tree[node] = Math.max(tree[2*node] , tree[(2 * node) + 1]);
			}
		}
	}
	static class SparseTable1D  // < O(Nlog(N)) , O(1) >
	{
		int sparse[][];
		int len;
		
		static int log(int N) {
			return 31 - Integer.numberOfLeadingZeros(N);
		}
		
		SparseTable1D(int arr[]){
			len = arr.length;
			int k = log(len) + 1;
			sparse = new int[k][len];
			for (int i = 0; i < len; i++)
				sparse[0][i] = arr[i];

			for(int i=1;i<k;i++)
				for(int j=0;j+(1<<i) <= len;j++)
					sparse[i][j] = Math.max(sparse[i-1][j],sparse[i-1][j+(1<<(i-1))]);
		
		}

		int getMax(int L,int R) {
			if(L > R) return Integer.MIN_VALUE;
			
			int sz = R - L + 1;
			int k  = log(sz);
			int v1 = sparse[k][L];
			int v2 = sparse[k][L + (sz - (1 << k))];
			
			return Math.max(v1,v2);
		}

	}
	
	private static void solve(FastScanner s1, PrintWriter out){
		
		int N;
		while((N = s1.nextInt()) != 0) {
			
			int Q = s1.nextInt();
			int arr[] = s1.nextIntArray(N);
			int unique = 0;
			for(int i=1;i<N;i++)
				unique += (arr[i] == arr[i - 1]) ? 0 : 1;
			unique++;
			int freq[] = new int[unique];
			int curr = 0 , sz = 1;
			for(int i=1;i<N;i++) {
				if(arr[i] != arr[i - 1]) {
					freq[curr++] = sz;
					sz = 1;
				}
				else 
					sz++;
			}
			freq[curr] = sz;
			
			// System.out.println("freq " + Arrays.toString(freq));
			
			int cumuFreq[] = new int[unique];
			cumuFreq[0] = freq[0];
			for(int i=1;i<unique;i++)
				cumuFreq[i] = freq[i] + cumuFreq[i - 1];
			
			// System.out.println("cumuFreq " + Arrays.toString(cumuFreq));
			
			// SparseTable1D sparseTable = new SparseTable1D(freq);
			SegmentTree segmentTree = new SegmentTree(freq);
			while(Q-->0) {
				int L = s1.nextInt();
				int R = s1.nextInt();
				int LL = ceil(cumuFreq, L);
				int RR = ceil(cumuFreq, R);	
				// System.out.println("L " + L + " R " + R + " LL " + LL + " RR " + RR);
				out.println((LL == RR ) ? (R - L + 1) : Math.max(Math.max(cumuFreq[LL] - L + 1,freq[RR] - (cumuFreq[RR] - R)),segmentTree.getMax(LL + 1, RR - 1)));
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