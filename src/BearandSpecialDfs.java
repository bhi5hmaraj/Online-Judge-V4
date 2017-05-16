import java.util.*;
import java.io.*;
public class BearandSpecialDfs
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static ArrayList<Integer>[] adj;
    private static long vis[],A[];    
    private static int L[],R[] , clock = 1;
    static class ModMath {
	static final long mod = (long) (1e9) + 7L; // Default

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
    private static void dfs(int u,int p){
	L[u] = clock++;

	for(int v:adj[u])
	    if(v != p)
		dfs(v, u);

	R[u] = clock - 1;
    }

    private static void dfs1(int u, int  p){

	if(u == 1)
	    vis[1] = 1;
	else
	    vis[L[u]] = ModMath.mul(vis[L[p]], A[p]);

	for(int v:adj[u])
	    if(v != p)
		dfs1(v, u);

    }


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
	    return "[("+l+", "+r+"),"+sum+"]";
	}
    }

    static class SegmentTree
    {
	Interval tree[];
	int size;
	long lazy[];
	int len;
	SegmentTree(long arr[]) //ar is one based indexing
	{
	    len = arr.length - 1;	    
	    for(size=1;size<len;size <<= 1)
		;
	    size <<= 1;
	    tree = new Interval[size];
	    lazy = new long[size];
	    Arrays.fill(lazy, 1);
	    build(arr, 1, 1, len);

	}

	long query(int node , int L , int R)
	{
	    if(isValid(L, R))
	    {
		updNode(node);
		if(tree[node].equals(L, R))
		    return tree[node].sum;
		else if(tree[node].isLeft(L, R))
		    return query(2*node, L, R);
		else if(tree[node].isRight(L, R))
		    return query((2*node)+1, L, R);
		else
		    return ModMath.add(query(2*node, L, tree[node].mid) , query((2*node)+1, tree[node].mid+1, R));
	    }
	    return 0;
	}

	boolean isValid(int L , int R)
	{
	    return L >= 1 && L <= len && R >= 1 && R <= len && L <= R;
	}
	void updNode(int node){
	    if(tree[node].l != tree[node].r){
		lazy[2*node] =  ModMath.mul(lazy[node], lazy[2*node]);
		lazy[(2*node)+1] =  ModMath.mul(lazy[node] , lazy[(2*node)+1]);
	    }
	    tree[node].sum = ModMath.mul(tree[node].sum, lazy[node]);		 
	    lazy[node] = 1;
	}
	long update(int node,int L, int R ,long mulVal)
	{
	    if(isValid(L , R))
	    {
		if(tree[node].equals(L, R))
		{
		    lazy[node] = ModMath.mul(lazy[node], mulVal);
		    updNode(node);
		    return tree[node].sum;
		}
		updNode(node);
		updNode(2*node);
		updNode((2*node)+1);
		long left = tree[2*node].sum;
		long right = tree[(2*node)+1].sum;
		if(tree[node].isLeft(L, R))
		    left = update(2*node, L, R, mulVal);
		else if(tree[node].isRight(L, R))
		    right = update((2*node) + 1, L, R, mulVal);
		else
		{
		    left = update(2*node, L, tree[node].mid, mulVal);
		    right = update((2*node)+1, tree[node].mid+1, R, mulVal);
		}

		return tree[node].sum = ModMath.add(left, right);
	    }
	    else
		return 0;
	}

	void build(long arr[],int node,int L,int R)
	{
	    if(isValid(L, R))
	    {
		if(L == R)
		    tree[node] = new Interval(L, R, arr[L]);
		else
		{
		    int mid = L + ((R-L)/2);
		    build(arr, 2 * node, L, mid);
		    build(arr, (2 * node) + 1, mid + 1, R);
		    tree[node] = new Interval(L, R, ModMath.add(tree[2*node].sum , tree[(2*node)+1].sum));
		}
	    }
	}

    }






    @SuppressWarnings("unchecked")
    private static void solve2(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int E = N - 1;
	int Q = s1.nextInt();
	vis = new long[N+1];
	A   = new long[N+1];
	L = new int[N+1];
	R = new int[N+1];
	adj = (ArrayList<Integer>[])  new ArrayList[N+1];
	for(int i=1;i<=N;i++)adj[i] = new ArrayList<>();

	while(E-->0){
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    adj[u].add(v);
	    adj[v].add(u);
	}
	A = s1.nextLongArrayOneBased(N);
	dfs(1, -1);
	dfs1(1, -1);
	
	SegmentTree st = new SegmentTree(vis);
	while(Q-->0)
	{
	    int type = s1.nextInt();
	    if(type == 1)
	    {
		int u = s1.nextInt();
		long x = s1.nextLong();
		st.update(1, L[u] + 1, R[u], ModMath.div(x, A[u]));
		A[u] = x;
	    }
	    else
	    {
		int u = s1.nextInt();
		out.println(st.query(1, L[u], R[u]));
	    }
	}
    }


    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/
    /*
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve(in, out);
	in.close();
	out.close();
    }    
     */

    public static void main(String[] args) throws IOException {
	new Thread(null, new Runnable() {
	    public void run() {
		new BearandSpecialDfs().run();
	    }
	}, "Increase Stack Plzz", 1L << 25).start();

    }

    public void run() {
	FastScanner in = new FastScanner(System.in);
	PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
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
	int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
	long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
	int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
	long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
	void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
    }

    /************************ TEMPLATE ENDS HERE ************************/
}