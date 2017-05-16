import java.util.*;
import java.io.*;
public class SpartansLeonidasVsXerxes
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class Interval {
	int l, r, mid; // Both inclusive
	int prefix, suffix, ans;
	int size;
	Interval(int l, int r) {
	    this.l = l;
	    this.r = r;
	    this.size = r - l + 1;
	    this.mid = l + ((r - l) / 2);
	    prefix = suffix = ans = 1;
	}

	public boolean equals(int l, int r) {
	    return (this.l == l && this.r == r);
	}

	boolean isLeft(int l, int r) {
	    return r <= mid;
	}

	boolean isRight(int l, int r) {
	    return l > mid;
	}

	@Override
	public String toString() {
	    return "L " + l + " R " + r + "p "+prefix+" s "+suffix+" a "+ans;
	}
    }

    static class SegmentTree
    {
	Interval tree[];
	long arr[];
	int size;
	int len;
	SegmentTree(long arr[]) //arr is one based indexing
	{
	    len = arr.length - 1;	    
	    for(size=1;size<len;size <<= 1)
		;
	    size <<= 1;
	    tree = new Interval[size];
	    this.arr = arr;
	    build(1, 1, len);

	}

	Interval query(int node , int L , int R)
	{
	    if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
	    {
		if(tree[node].equals(L, R))
		    return tree[node];
		else if(tree[node].isLeft(L, R))
		    return query(2*node, L, R);
		else if(tree[node].isRight(L, R))
		    return query((2*node)+1, L, R);
		else{
		    Interval left = query(2*node, L, tree[node].mid);
		    Interval right = query((2*node)+1, tree[node].mid+1, R);
		    Interval join = new Interval(L, R);
		    join.mid = tree[node].mid;
		    updateNode(join, left, right);
		    return join;
		}
		     
	    }
	    return null;
	}

	void update(int node, int index , long inc)
	{
	    if(node >= 0 && node < size)
	    {
		if(tree[node].l == tree[node].r && tree[node].mid == index)
		    arr[index] += inc;
		else
		{
		    if(index > tree[node].mid)
			update((2 * node) + 1, index , inc);
		    else
			update(2 * node, index , inc);

		    updateNode(tree[node],tree[2*node],tree[(2*node)+1]);
		}
	    }
	}

	void updateNode(Interval join , Interval left , Interval right) {
	    join.ans = Math.max(left.ans, right.ans);
	    join.ans = Math.max(join.ans, arr[join.mid] < arr[join.mid + 1]
		    ? left.suffix + right.prefix : join.ans);

	    join.prefix = left.ans == left.size
		    && arr[join.mid] < arr[join.mid + 1]
			    ? left.prefix + right.prefix : left.prefix;

	    join.suffix = right.ans == right.size
		    && arr[join.mid] < arr[join.mid + 1]
			    ? left.suffix + right.suffix : right.suffix;
	}
	

	void build(int node,int L,int R)
	{
	    if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
	    {
		if(L == R)
		    tree[node] = new Interval(L, R);
		else
		{
		    int mid = L + ((R-L)/2);
		    build(2 * node, L, mid);
		    build((2 * node) + 1, mid + 1, R);
		    tree[node] = new Interval(L, R);
		    updateNode(tree[node],tree[2*node],tree[(2*node)+1]);
		}
	    }
	}
    }



    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    int Q = s1.nextInt();
	    long arr[] = s1.nextLongArrayOneBased(N);
	    SegmentTree st = new SegmentTree(arr);
	    while(Q-->0)
	    {
		int type = s1.nextInt();
		if(type == 0)
		    out.println(st.query(1, s1.nextInt(), s1.nextInt()).ans);
		else
		    st.update(1, s1.nextInt(), s1.nextLong());
		/*
		System.out.println(Arrays.toString(arr));
		System.out.println(st);
		*/
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