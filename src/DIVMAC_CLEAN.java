import java.util.*;
import java.io.*;
/*
 * Segtrees are faster than any other DS (sparse table) for rmq . 
 * They just use 1D array . their build time is linear which is faster compared to the linearithmic sparse table.
 */
class DIVMAC_CLEAN    
{


	/************************ SOLUTION STARTS HERE ***********************/

	static final int MAX = (int)(1e6);
	static int MAX_POW;
	static boolean isPrime[];
	static int loPrime[];
	static SegmentTreeRMQ segTree[];
	static {
		isPrime = new boolean[MAX + 10];  // one is treated as a prime
		loPrime = new int[MAX + 10];
		Arrays.fill(isPrime, true);
		loPrime[1] = 1;
		for(int i=2;i*i<=MAX;i++){
			if(loPrime[i] == 0){
				loPrime[i] = i;
				for(int j = i*i ;j <= MAX;j += i){
					loPrime[j] = loPrime[j] == 0 ? i : loPrime[j];
					isPrime[j] = false;
				}
			}
		}
		for(int i=2;i <= MAX;i++)
			loPrime[i] = loPrime[i] == 0 ? i : loPrime[i];
	}
	static class SegmentTreeRMQ
	{
		int tree[];
		int size;
		int len;
		SegmentTreeRMQ(int arr[]) //arr is one based indexing
		{
			len = arr.length - 1;	    
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new int[size];
			build(arr, 1, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr)
		{
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}
		int getMaxInRange(int L,int R){
			return query(1, L, R, 1, len);
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

	static class SegmentTree
	{
		int tree[];
		int max[];
		int lazy[];
		boolean sync[];
		int len;
		int size;
		SegmentTree(int arr[]) //arr is one based indexing
		{
			len = arr.length - 1;	    
			for(size=1;size<len;size <<= 1)
				;
			size <<= 1;
			tree = new int[size];
			sync = new boolean[size];
			Arrays.fill(sync, true);
			lazy = new int[size];
			max = new int[size];
			build(arr, 1, 1, len);

		}
		boolean isInternal(int node){
			return 2*node < size && (2*node)+1 < size;
		}
		int query(int node , int L , int R, int nl, int nr)
		{
			upd(node , nl , nr);
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return max[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
		}

		void upd(int node , int nl , int nr){
			if(sync[node]){
				if(max[node] == 1 && tree[node] == MAX_POW - 1)
					return;
				if(isInternal(node)){ 
					if(tree[2*node] > tree[node])
						tree[node] = tree[2*node];
					lazy[2*node] += lazy[node];
					lazy[(2*node)+1] +=  lazy[node];
				}
				tree[node] += lazy[node];
				tree[node] = Math.min(tree[node],MAX_POW - 1);
				lazy[node] = 0;
				max[node] = segTree[tree[node]].getMaxInRange(nl, nr);
			}
		}

		int update(int node,int L, int R,int nl,int nr)
		{
			if(L == nl && R == nr)
			{
				if(sync[node]){
					if(max[node] == 1 && tree[node] == MAX_POW - 1)
						return 1;
					
					lazy[node]++;
					if(isInternal(node)){   //Not a leaf node
						lazy[2*node] += lazy[node];
						lazy[(2*node)+1] +=  lazy[node];
					}
					tree[node] += lazy[node];
					tree[node] = Math.min(tree[node],MAX_POW - 1);
					lazy[node] = 0;
					return max[node] = segTree[tree[node]].getMaxInRange(nl, nr);
				}
				else {
					int mid = (nl + nr) / 2;
					int lMax = update(2*node, nl, mid , nl , mid);
					int rMax = update((2*node) + 1, mid + 1, nr, mid + 1, nr);
					max[node] = Math.max(lMax,rMax);
					sync[node] = (sync[2*node] && sync[(2*node)+1]) ? (tree[2*node] == tree[(2*node) + 1]) : false;
					if(sync[node] && (tree[2*node] > tree[node]))
						tree[node] = tree[2*node];
					return max[node];
				}
			}
			int mid = (nl + nr) / 2;
			upd(node , nl , nr);
			upd(2 * node , nl , mid);
			upd((2 * node) + 1 , mid + 1, nr);
			int left = max[2*node], right = max[(2*node) + 1];
			if(R <= mid)
				left = update(2*node, L, R , nl , mid);
			else if(L > mid)
				right = update((2*node) + 1, L, R , mid + 1, nr);
			else
			{
				left = update(2*node, L, mid , nl , mid);
				right = update((2*node)+1, mid+1, R , mid+1,nr);
			}
			sync[node] = (sync[2*node] && sync[(2*node)+1]) ? (tree[2*node] == tree[(2*node) + 1]) : false;
			max[node] = Math.max(left,right);
			if(sync[node])
				tree[node] = tree[2*node] > tree[node] ? tree[2*node] : tree[node];
			return max[node];
		}
		void build(int arr[],int node,int L,int R)
		{
			if(L == R)
				max[node] = loPrime[arr[L]];
			else
			{
				int mid = L + ((R-L)/2);
				build(arr, 2 * node, L, mid);
				build(arr, (2 * node) + 1, mid + 1, R);
				max[node] = Math.max(max[2*node] , max[(2 * node) + 1]);
			}
		}
		
		// Debug the tree
		private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, int root , int nl , int nr) {

			int mid = (nl+nr)/2;
			if(nl <= nr){
				if (root == 0) {
					sb.append("Tree Empty\n");
					return sb;
				}
				if ((2*root+1) < size && max[(2*root)+1] != 0) {
					toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, (2*root)+1 , mid+1 , nr);
				}
				if(root < size)
					sb.append(prefix).append(isTail ? "└── " : "┌── ").append("max " + max[root] + " pos " + tree[root] + " lz "+lazy[root]+/* " sync " + sync[root] +*/ sync[root] + " [" + nl+", "+nr+"]").append("\n");
				if ((2*root) < size && max[2*root] != 0) {
					toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, (2*root),nl,mid);
				}
			}
			return sb;
		}
		@Override
		public String toString() {
			return toString(new StringBuilder(), true, new StringBuilder(), 1 , 1, len).toString();
		}
	}

	
	private static void solve(FastScanner s1, PrintWriter out){

		int t = s1.nextInt();				
		while(t-->0)
		{
			int N = s1.nextInt();
			int Q = s1.nextInt();
			int arr[] = s1.nextIntArrayOneBased(N);
			MAX_POW = 1;
			for(int i=1;i<=N;i++){
				int num = arr[i];
				int ct = 1;
				while(num != 1){
					num /= loPrime[num];
					ct++;
				}
				MAX_POW = Math.max(MAX_POW,ct);
			}
			segTree = new SegmentTreeRMQ[MAX_POW];
			SegmentTree tree = new SegmentTree(arr);
			int temp[] = new int[N + 1];
			for(int i=0;i<MAX_POW;i++){
				for(int j=1;j<=N;j++){
					temp[j] = loPrime[arr[j]];
					arr[j] /= loPrime[arr[j]];
				}
				segTree[i] = new SegmentTreeRMQ(temp);
			}
			while(Q-->0){
				int type = s1.nextInt();
				int L = s1.nextInt();
				int R = s1.nextInt();
				
				if(type == 0)
					tree.update(1, L, R, 1, N);
				else
					out.print(tree.query(1, L, R, 1, N) + " ");
			}
			out.println();
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
/*
  //Without stress tester this would'nt have been possible
	static String outputFile = "output_DIVMAC.txt";
	public static void main(String []args) throws IOException {
		FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
		PrintWriter out = 
				new PrintWriter(outputFile); 
		solve(in, out);
		in.close();
		out.close();
	}
*/	
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