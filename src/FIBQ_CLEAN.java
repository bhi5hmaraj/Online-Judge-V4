import java.util.*;
import java.io.*;
class FIBQ_CLEAN
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static long mod = (long)1e9 + 7L;
    private static long mul(long a,long b)
    {
	return ((a%mod)*(b%mod))%mod;
    }
    private static long add(long a,long b)
    {
	return ((a%mod)+(b%mod))%mod;
    }
    static class Matrix
    {
	long e00,e01,e10,e11;
	Matrix(long a,long b,long c,long d)
	{
	    e00=a;e01=b;e10=c;e11=d;
	}
	Matrix(Matrix that)
	{
	    e00=that.e00;e01=that.e01;e10=that.e10;e11=that.e11;
	}
	Matrix multiply(Matrix that)
	{
	    long a=add(mul(this.e00,that.e00),mul(this.e01,that.e10));
	    long b=add(mul(this.e00,that.e01),mul(this.e01,that.e11));
	    long c=add(mul(this.e10,that.e00),mul(this.e11,that.e10));
	    long d=add(mul(this.e10,that.e01),mul(this.e11,that.e11));
	    return new Matrix(a,b,c,d);
	}
	public String toString()
	{
	    return "|"+e00+" "+e01+"|\n|"+e10+" "+e11+"|";
	}
	Matrix ADD(Matrix that)
	{
	    return new Matrix(add(this.e00, that.e00), add(this.e01, that.e01), add(this.e10,that.e10), add(this.e11,that.e11));
	}

    }
    private static Matrix pow(Matrix m,long b)
    {
	if(b==1)
	    return m;
	else
	{
	    if((b&1) == 0)	    
		return pow(m.multiply(m),b>>1);
	    
	    else	    
		return m.multiply(pow(m.multiply(m),b>>1));	    
	}
    }

    private static Matrix M    = new Matrix(1, 1,
	    				    1, 0);   //Base matrix of the fibonacci sequence
    private static Matrix unit = new Matrix(1, 0,
	    				    0, 1);   

    static class Interval
    {
	int l,r,mid; //Both inclusive	
	Matrix mat;
	Interval(int l,int r,Matrix mat)
	{
	    this.l = l;
	    this.r = r;
	    this.mid = l + ((r-l)/2);
	    this.mat = mat;
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
    }

    static class SegmentTree
    {
	Interval tree[];
	int size;
	SegmentTree(long ar[]) //ar is one based indexing
	{
	    int len = ar.length-1;	    
	    for(size=1;size<len;)
		size <<= 1;
	    size <<= 1;
	    tree = new Interval[size];
	    int start = (size/2);
	    int ptr=1;
	    for (; ptr <= len; ptr++, start++)
		tree[start] = new Interval(ptr, ptr, unit.ADD(pow(M, ar[ptr])));
	    for (; start < size; start++, ptr++)
		tree[start] = new Interval(ptr, ptr, unit);

	    for (int i = (size - 1) / 2; i >= 1; i--)
		tree[i] = new Interval(tree[2 * i].l, tree[(2 * i) + 1].r,
			tree[2 * i].mat.multiply(tree[(2 * i) + 1].mat));	    
	}
	void update(int index,long val,long ar[])
	{
	    ar[index] = val;
	    int pos = (size / 2) + index - 1;
	    tree[pos].mat = unit.ADD(pow(M, ar[index]));
	    for (int i = pos >> 1; i > 0; i >>= 1)
		tree[i].mat = tree[2 * i].mat.multiply(tree[(2 * i) + 1].mat);	    
	}
	long query(int L,int R)
	{
	    Matrix ans = query(1, L, R);
	    return (ans.e10 % mod);
	}
	Matrix query(int index,int l,int r)
	{
	    if (tree[index].equals(l, r))
		return tree[index].mat;
	    else if (tree[index].isLeft(l, r))
		return query(2 * index, l, r);
	    else if (tree[index].isRight(l, r))
		return query((2 * index) + 1, l, r);
	    else
		return query(2 * index, l, tree[index].mid).multiply(query((2 * index) + 1, tree[index].mid + 1, r));
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){
	/**  O(log(n)) update and query **/
	/**  When you add the reccurence in a range you'll notice a pattern **/
	/**  
	 *   query(L,R) = ||1 1|^(a_L) + |1 0|| * ......  ||1 1|^(a_R) + |1 0||
	 * 		  ||1 0|         |0 1|| 	  ||1 0|         |0 1|| 
	 */
	int N = s1.nextInt();
	int Q = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);	
	SegmentTree segTree = new SegmentTree(arr);
	while(Q-->0)
	{
	    char type = s1.nextChar();
	    if(type == 'C')
		segTree.update(s1.nextInt(), s1.nextLong(), arr);
	    else
		out.println(segTree.query(s1.nextInt(), s1.nextInt()));
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