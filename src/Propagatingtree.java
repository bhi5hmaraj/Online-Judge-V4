import java.util.*;
import java.io.*;
public class Propagatingtree
{
	
	
	/************************ SOLUTION STARTS HERE ***********************/
	
	static class SegmentTree  { // point update and range query
		int tree[];
		int len;
		int size;
		SegmentTree(int len) { 
			this.len = len;
			size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
			tree = new int[size];
		}
		void update(int node,int idx,int val,int nl,int nr) {
			if(nl == nr && nl == idx)
				tree[node] += val;
			else {
				int mid = (nl + nr) / 2;
				if(idx <= mid)
					update(2*node, idx , val ,nl , mid);
				else
					update((2*node) + 1, idx ,val , mid + 1, nr);

				tree[node] = (tree[2*node] + tree[(2 * node) + 1]);
			}
		}
		void update(int idx , int val){
			update(1, idx, val, 1, len);
		}
		int query(int L , int R){
			return query(1, L, R, 1, len);
		}
		int query(int node , int L , int R, int nl, int nr) {
			int mid = (nl + nr) / 2;
			if(nl == L && nr == R)
				return tree[node];
			else if(R <= mid)
				return query(2 * node, L, R, nl, mid);
			else if(L > mid)
				return query((2*node)+1, L, R, mid + 1 , nr);
			else
				return (query(2*node, L, mid , nl , mid) +  query((2*node)+1, mid+1, R , mid+1,nr));
		}
	}


	static void buildHLD(int u , int par , int hd) {
		
		head[u] = hd;
		stamp[u] = time++;

		int maxSize = 0;
		int next = -1;
		for(int v : isEven[u] ? evenLevelTree[u] : oddLevelTree[u]) 
			if(v != par && size[v] > maxSize) {
				maxSize = size[v];
				next = v;
			}
		
		if(next != -1)
			buildHLD(next, u, hd); // Continue the current chain for the maximum branch
		
		for(int v : isEven[u] ? evenLevelTree[u] : oddLevelTree[u])
			if(v != par && v != next) 
				buildHLD(v, u, v); // start a new chain for others
		
	}

	static int precompute(int u , int par) {
		size[u] = 1;
		parent[u] = par;
		for(int v : isEven[u] ? evenLevelTree[u] : oddLevelTree[u])
			if(v != par)
				size[u] += precompute(v, u);
		
		return size[u];
	}
	
	static void initTrees(int u , int par , int grandPar , boolean isItEven) {
		ArrayList<Integer>[] tree = isItEven ? evenLevelTree : oddLevelTree;
		originalParent[u] = par;
		if(grandPar != -1) {
			isEven[u] = isItEven;
			tree[u].add(grandPar);
			tree[grandPar].add(u);
		}
		for(int v : adj[u])
			if(v != par)
				initTrees(v, u, par, !isItEven);
	}

	static ArrayList<Integer>[] adj;
	static ArrayList<Integer>[] oddLevelTree;
	static ArrayList<Integer>[] evenLevelTree;
	static int size[] , head[] , parent[] ,originalParent[], stamp[] , time = 1 , V;
	static boolean isEven[];
	static SegmentTree segTree;

	static int queryUp(int curr) {
		int sum = 0;
		while(curr != -1) {
			sum += segTree.query(stamp[head[curr]], stamp[curr]);
			curr = parent[head[curr]];
		}
		return sum;
	}


	static void update(int u , int inc) {
		segTree.update(stamp[u] , inc);
	}

	
	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){
		
		V = s1.nextInt();
		int E = V - 1;
		int Q = s1.nextInt();
		
		adj            = new ArrayList[V + 1];
		evenLevelTree  = new ArrayList[V + 1];
		oddLevelTree   = new ArrayList[V + 1];
		size           = new int[V + 1];
		originalParent = new int[V + 1];
		head    = new int[V + 1];
		parent  = new int[V + 1];
		stamp   = new int[V + 1];
		isEven  = new boolean[V + 1];
		segTree = new SegmentTree(V + 10);
		
		int A[] = s1.nextIntArrayOneBased(V);
		
		for(int i=1;i<=V;i++) {
			adj[i] 			 = new ArrayList<>();
			evenLevelTree[i] = new ArrayList<>();
			oddLevelTree[i]  = new ArrayList<>();
		}
		
		while(E-->0) {
			int u = s1.nextInt();
			int v = s1.nextInt();
			adj[u].add(v);
			adj[v].add(u);
		}
		
		initTrees(1, -1, -1, false);
		
		isEven[1] = false;
		precompute(1, -1);
		buildHLD(1, -1, 1);
		
		for(int e : adj[1]) {
			isEven[e] = true;
			precompute(e, -1);
			buildHLD(e, -1, e);
		}
		/*
		out.println("odd level");
		for(int i=1;i<=V;i++)
			out.println(i + " ==> " + oddLevelTree[i]);
		
		out.println("even level");
		for(int i=1;i<=V;i++)
			out.println(i + " ==> " + evenLevelTree[i]);
		
		out.println("size " + Arrays.toString(size));
		out.println("parent " + Arrays.toString(parent));
		*/
		while(Q-->0) {
			if(1 == s1.nextInt())
				update(s1.nextInt(), s1.nextInt());
			else {
				int x = s1.nextInt();
				out.println(A[x] + queryUp(x) - queryUp(originalParent[x]));
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
		int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
		long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}	
		int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}	    	
		long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}	    	
		void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
	}
	
	/************************ TEMPLATE ENDS HERE ************************/
}