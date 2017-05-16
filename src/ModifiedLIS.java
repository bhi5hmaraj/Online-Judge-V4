import java.util.*;
import java.io.*;
public class ModifiedLIS
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static final int OFFSET = (int)(1e5) + 1;
    
    static class Pair
    {
	final long ways;
	final int LIS_len;
	Pair(int len , long way){
	    ways = way;
	    LIS_len = len;
	}
	
	Pair merge(Pair that)
	{
	    if(this.LIS_len > that.LIS_len)
		return new Pair(this.LIS_len, this.ways);
	    else if(this.LIS_len < that.LIS_len)
		return new Pair(that.LIS_len, that.ways);
	    else
		return new Pair(this.LIS_len, add(this.ways, that.ways));
	}
	@Override
	public String toString() {
	    return "MAX LEN " + LIS_len + " WAYS " + ways;
	}
    }
    
    static class Interval {
	int l, r, mid; // Both inclusive
	Pair p;

	Interval(int l, int r) {
	    this.l = l;
	    this.r = r;
	    this.mid = l + ((r - l) / 2);
	    this.p = new Pair(0, 0);
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
	    return "L " + l + " R " + r + p;
	}
    }

    static class SegmentTree
    {
	Interval tree[];
	int size;
	int len;
	SegmentTree(int len) //ar is one based indexing
	{
	    this.len = len;	    
	    for(size=1;size<len;size <<= 1)
		;
	    size <<= 1;
	    tree = new Interval[size];
	    build( 1, 1, len);
	    
	}

	Pair query(int node , int L , int R)
	{
	    if(L >= 1 && L <= len && R >= 1 && R <= len && L <= R)
	    {
		if(tree[node].equals(L, R))
		    return tree[node].p;
		else if(tree[node].isLeft(L, R))
		    return query(2*node, L, R);
		else if(tree[node].isRight(L, R))
		    return query((2*node)+1, L, R);
		else
		    return query(2*node, L, tree[node].mid).merge(query((2*node)+1, tree[node].mid + 1, R));
	    }
	    return null;
	}

	void update(int node, int index ,Pair newPair)
	{
	    if(node >= 0 && node < size)
	    {
		if(tree[node].l == tree[node].r && tree[node].mid == index)
		    tree[node].p = tree[node].p.merge(newPair);
		else
		{
		    if(index > tree[node].mid)
			update((2 * node) + 1, index , newPair);
		    else
			update(2 * node, index , newPair);

		    tree[node].p = tree[2*node].p.merge(tree[(2*node)+1].p);
		}
	    }
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
		}
	    }
	}


    }

    static final long mod = (long) (1e9) + 7;

    static long add(long a, long b) {
	return ((a % mod) + (b % mod)) % mod;
    }

    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int arr[] = s1.nextIntArray(N);
	SegmentTree st = new SegmentTree((2 * OFFSET) + 10);
	st.update(1, arr[0] + OFFSET, new Pair(1, 1));
	for(int i=1;i<N;i++)
	{
	    Pair qry = null;
	    if(arr[i] > 0)
		qry = st.query(1, (-arr[i] + 1) + OFFSET, -1 + OFFSET);		
	    else
		qry = st.query(1, 1 + OFFSET, (-arr[i]) - 1 + OFFSET);
	 
	    qry = (qry == null) ? new Pair(0, 0) : qry;
	    st.update(1, arr[i] + OFFSET, new Pair(qry.LIS_len + 1, Math.max(qry.ways,1)));
	    
	}
	Pair ans = st.tree[1].p;
	out.println(ans.LIS_len + " " + ans.ways);
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