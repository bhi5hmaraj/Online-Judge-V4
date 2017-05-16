import java.util.*;
import java.io.*;
public class MonkandOtakuland
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Interval
    {
	int l,r,mid; //Both inclusive	
	int cnt;
	int size;
	Interval(int l,int r,int sum)
	{
	    this.l = l;
	    this.r = r;
	    this.mid = l + ((r-l)/2);
	    this.cnt = sum;
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
	    return "[("+l+", "+r+"),"+cnt+"]";
	}
    }

    static class SegmentTree
    {
	Interval tree[];
	int size;
	boolean lazy[];
	int len;
	SegmentTree(int arr[]) //ar is one based indexing
	{
	    len = arr.length;	    
	    for(size=1;size<len;size <<= 1)
		;
	    size <<= 1;
	    tree = new Interval[size];
	    lazy = new boolean[size];
	    build(arr, 1, 1, arr.length);

	}
	
	int query(int node , int L , int R)
	{
	    if(isValid(L, R))
	    {
		updateNode(node);

		if(tree[node].equals(L, R))
		    return tree[node].cnt;
		else if(tree[node].isLeft(L, R))
		    return query(2*node, L, R);
		else if(tree[node].isRight(L, R))
		    return query((2*node)+1, L, R);
		else
		    return query(2*node, L, tree[node].mid) +  query((2*node)+1, tree[node].mid+1, R);
	    }
	    return 0;
	}
	
	boolean isValid(int L , int R)
	{
	    return L >= 1 && L <= len && R >= 1 && R <= len && L <= R;
	}


	int updateNode(int node)
	{

	    if(lazy[node])
	    {
		if(tree[node].l != tree[node].r){
		    lazy[2*node] = !lazy[2*node]; 
		    lazy[(2*node)+1] = !lazy[(2*node)+1];
		}
		tree[node].cnt = tree[node].size - tree[node].cnt;
	    }
	    lazy[node] = false;
	    return tree[node].cnt;
	}

	int update(int node,int L, int R)
	{
	    if(isValid(L , R))
	    {

		if(tree[node].equals(L, R))
		{
		    if(tree[node].l != tree[node].r){
			lazy[2*node] = !lazy[2*node]; 
			lazy[(2*node)+1] = !lazy[(2*node)+1];
		    }
		    if(!lazy[node])
			tree[node].cnt = tree[node].size - tree[node].cnt;
		    lazy[node] = false;
		    return  tree[node].cnt;
		}

		int left = updateNode(2*node) , right = updateNode((2*node)+1);
		if(tree[node].isLeft(L, R))
		    left = update(2*node, L, R);
		else if(tree[node].isRight(L, R))
		    right = update((2*node) + 1, L, R);
		else
		{
		    left  = update(2*node, L, tree[node].mid);
		    right = update((2*node)+1, tree[node].mid+1, R);
		}

		return tree[node].cnt = left + right;
	    }
	    return 0;
	}

	void build(int arr[],int node,int L,int R)
	{
	    if(isValid(L, R))
	    {
		if(L == R)
		    tree[node] = new Interval(L, R, arr[L-1]);
		else
		{
		    int mid = L + ((R-L)/2);
		    build(arr, 2 * node, L, mid);
		    build(arr, (2 * node) + 1, mid + 1, R);
		    tree[node] = new Interval(L, R, tree[2*node].cnt + tree[(2*node)+1].cnt);
		}
	    }
	}
	private StringBuilder toString(StringBuilder prefix, boolean isTail, StringBuilder sb, int root) {

	    if (root == 0) {
		sb.append("Tree Empty\n");
		return sb;
	    }
	    if (2*root < size && tree[2*root] != null) {
		toString(new StringBuilder().append(prefix).append(isTail ? "│   " : "    "), false, sb, (2*root)+1);
	    }
	    if(root < size)
		sb.append(prefix).append(isTail ? "└── " : "┌── ").append(tree[root] + "lz "+lazy[root]).append("\n");
	    if ((2*root)+1 < size && tree[(2*root)+1] != null) {
		toString(new StringBuilder().append(prefix).append(isTail ? "    " : "│   "), true, sb, 2*root);
	    }
	    return sb;
	}
	@Override
	public String toString() {
	    return toString(new StringBuilder(), true, new StringBuilder(), 1).toString();
	}
    }




    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int Q = s1.nextInt();
	//SegmentTree st = new SegmentTree(arr);
	String str = s1.nextLine();
	int arr[] = new int [N-1];
	for(int i=0;i<N-1;i++)
	    arr[i] = str.charAt(i) == '>' ? 1 : 0;
	
	SegmentTree st = new SegmentTree(arr);

	while(Q-->0)
	{
	    int type = s1.nextInt();
	    if(1 == type)
		st.update(1, s1.nextInt(), s1.nextInt() - 1);
	    else
	    {
		int f = s1.nextInt();
		int t = s1.nextInt();
		if(f == t)
		    out.println(0);
		else if(f < t)
		    out.println((t - f) - st.query(1, f, t-1));
		else
		    out.println(st.query(1, t, f-1));
			
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