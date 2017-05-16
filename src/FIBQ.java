import java.util.*;
import java.io.*;
class FIBQ
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
    private static long sub(long a,long b)
    {
	return ((a%mod)-(b%mod))%mod;
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
	    {
		return pow(m.multiply(m),b>>1);
	    }
	    else
	    {
		return m.multiply(pow(m.multiply(m),b>>1));
	    }
	}
    }
    private static Matrix adj(Matrix m)
    {
	Matrix ans = new Matrix(m.e11,-m.e01,-m.e10, m.e00);
	return ans;
    }
    private static long det(Matrix m)
    {
	long d = sub(mul(m.e00, m.e11), mul(m.e01, m.e10));
	return d > 0 ? d : mod + d;  //very very important
    }
    private static long modPow(long a,long b)   /* Modular exponentiation  */
    {
	if(b == 0L || a == 1L)
	    return 1L;
	else if(b == 1L)
	    return a;
	else
	{
	    if((b & 1L) == 0L)  		//Checking whether b is even (fast)  
		return modPow((a * a) % mod,b >> 1);
	    else
		return (a * modPow((a * a) % mod,b >> 1)) % mod ;
	}
    }
    private static long inverseMod(long n) /* Fermat's little theorem , used it to find the modular inverse of the denominator*/
    {
	return modPow(n,mod - 2L);
    }
    private static void solve1/*no upd and O(N) per query */(FastScanner s1, PrintWriter out){


	int N = s1.nextInt();
	int Q = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);	    
	Matrix preCal[] = new Matrix[N+1];    //From 4sec to 0.38sec salute !!
	for(int i=1;i<=N;i++)
	    preCal[i] = pow(M, arr[i]);
	while(Q-->0)
	{
	    char type = s1.nextChar();
	    if(type == 'C')
		throw new UnsupportedOperationException("Work in progress");
	    else
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		Matrix mat[] = new Matrix[R-L+1];
		mat[0] = pow(M, arr[L]);
		for(int i=L+1;i<=R;i++)
		{
		    Matrix mTemp = preCal[i];
		    Matrix temp = (unit.ADD(mTemp)).multiply(mat[i-L-1]);
		    mat[i-L] = temp.ADD(mTemp);
		}
		out.println(mat[mat.length-1].e10 % mod);
	    }
	}



    }
    /*
     * 
     * Square root decomposition
     * 
     */
    private static void solve2/* update and O(sqrt(N)) per query*/(int N,int Q,String query[],long arr[], PrintWriter out){


	int size = (int) Math.sqrt(N);
	int rows = (N % size == 0) ? (N / size) : ((N + size) / size);
	Matrix[][] preCal = new Matrix[rows][size];
	Matrix fibo[] = new Matrix[N];

	for (int i = 0; i < N; i++)
	    fibo[i] = pow(M, arr[i]);

	for (int i = 0; i < N; i += size)
	    preCal[i / size][0] = unit.ADD(fibo[i]);

	for (int i = 0; i < N; i++)
	    if (i % size != 0)
		preCal[i / size][i % size] = preCal[i / size][(i % size) - 1].multiply(unit.ADD(fibo[i]));

	for(int q=0;q<Q;q++)
	{
	    char type = query[q].charAt(0);
	    String splitter[] = query[q].split(" ");
	    if(type == 'C')
	    {

		int index = Integer.parseInt(splitter[1]) - 1;
		long val = Long.parseLong(splitter[2]);
		arr[index] = val;
		fibo[index] = pow(M, arr[index]);
		int buck = index / size;
		if ((index % size) == 0)
		    preCal[buck][index % size] = unit.ADD(fibo[index]);
		else
		    preCal[buck][index % size] = preCal[buck][(index % size) - 1].multiply(unit.ADD(fibo[index]));

		for (int i = (index % size) + 1; i < size; i++)
		{
		    if (preCal[buck][i] != null)
			preCal[buck][i] = preCal[buck][i - 1].multiply(unit.ADD(fibo[(buck * size) + i]));
		    else
			break;
		}
	    }
	    else
	    {
		int L = Integer.parseInt(splitter[1]) - 1;
		int R = Integer.parseInt(splitter[2]) - 1;

		int l_buck = L / size;
		int r_buck = R / size;

		if(L%size==0)
		{

		    Matrix start = new Matrix(unit);
		    for (int i = l_buck; i < r_buck; i++)
			start = start.multiply(preCal[i][size - 1]);

		    start = start.multiply(preCal[r_buck][R % size]);
		    out.println(start.e10 % mod);
		}
		else
		{

		    Matrix remove = new Matrix(preCal[l_buck][(L % size) - 1]);
		    Matrix start = new Matrix(unit);
		    for (int i = l_buck; i < r_buck; i++)
			start = start.multiply(preCal[i][size - 1]);

		    start = start.multiply(preCal[r_buck][R % size]);
		    Matrix temp = start.multiply(adj(remove));
		    long det = det(remove);
		    long val = mul(temp.e10, inverseMod(det));
		    out.println(val > 0 ? val : mod + val);

		}
	    }
	}
    }
    private static Matrix M    = new Matrix(1, 1,
	    				    1, 0);
    private static Matrix unit = new Matrix(1, 0,
	    				    0, 1);

    private static void solve4(FastScanner s1, PrintWriter out){
	Matrix M    = new Matrix(1, 1,
				 1, 0);
	Matrix unit = new Matrix(1, 0,
				 0, 1);
	int N = s1.nextInt();
	int Q = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);	    
	Matrix preCal[] = new Matrix[N+1];    //From 4sec to 0.38sec salute !!
 
	preCal[1] = unit.ADD(pow(M, arr[1]));
	for(int i=2;i<=N;i++)
	    preCal[i] = preCal[i-1].multiply(unit.ADD(pow(M, arr[i])));
 
	while(Q-->0)
	{
	    char type = s1.nextChar();
	    if(type == 'C')
		throw new UnsupportedOperationException("Work in progress");
	    else
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		if(L==1)
		    out.println(preCal[R].e10 % mod);
		else
		{
		    Matrix left = new Matrix(preCal[L-1]);
		    Matrix right = new Matrix(preCal[R]);
		    Matrix temp = right.multiply(adj(left));
		    long det = det(left);
		    long val = mul(temp.e10, inverseMod(det));   //very very important
		    out.println(val > 0 ? val : mod + val);      //very very important
		    //out.println(val);
		}
	    }
	}
    }
 
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
	    for (; ptr <= len; ptr++,start++)
		tree[start] = new Interval(ptr, ptr, unit.ADD(pow(M, ar[ptr])));
	    for (; start < size; start++, ptr++)
		tree[start] = new Interval(ptr, ptr, unit);
	    
	    for(int i=(size-1)/2;i>=1;i--)
		tree[i] = new Interval(tree[2*i].l, tree[(2*i)+1].r, tree[2*i].mat.multiply(tree[(2*i)+1].mat));	    
	}
	void update(int index,long val,long ar[])
	{
	    ar[index] = val;
	    int pos = (size/2)+index-1;
	    tree[pos].mat = unit.ADD(pow(M, ar[index]));
	    for(int i=pos>>1;i>0;i>>=1)
		tree[i].mat = tree[2*i].mat.multiply(tree[(2*i)+1].mat);	    
	}
	long query(int L,int R)
	{
	    Matrix ans = query(1, L, R);
	    return (ans.e10 % mod);
	}
	Matrix query(int index,int l,int r)
	{
	    if(tree[index].equals(l, r))
		return tree[index].mat;
	    else if(tree[index].isLeft(l, r))
		return query(2*index, l, r);
	    else if(tree[index].isRight(l, r))
		return query((2*index)+1, l, r);
	    else
		return query(2*index, l, tree[index].mid).multiply(query((2*index)+1, tree[index].mid+1, r));
	}
    }
    private static void solve5(FastScanner s1, PrintWriter out){

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
    private static void solveB(FastScanner s1, PrintWriter out){
	 
	 
	Matrix M    = new Matrix(1, 1, 1, 0);
	Matrix unit = new Matrix(1, 0, 0, 1);
	int N = s1.nextInt();
	int Q = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);	    
 
	while(Q-->0)
	{
	    char type = s1.nextChar();
	    if(type == 'C')
		arr[s1.nextInt()] = s1.nextLong();
	    else
	    {
		int L = s1.nextInt();
		int R = s1.nextInt();
		Matrix mat[] = new Matrix[R-L+1];
		mat[0] = pow(M, arr[L]);
		for(int i=L+1;i<=R;i++)
		{
		    Matrix mTemp = pow(M, arr[i]);
		    Matrix temp = (unit.ADD(mTemp)).multiply(mat[i-L-1]);
		    mat[i-L] = temp.ADD(mTemp);
		}
		out.println(mat[mat.length-1].e10 % mod);
	    }
	}
    }
    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve4(in, out);
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