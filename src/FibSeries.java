import java.util.*;
import java.io.*;
class FibSeries
{

    /************************ SOLUTION STARTS HERE ************************/

    static long mod ;
    public static long mul(long a,long b)
    {
	return ( (a%mod) * (b%mod) ) % mod;
    }
    public static long add(long a,long b)
    {
	return ( (a%mod) + (b%mod) ) % mod;
    }
    static class Matrix
    {
	long e00,e01,e10,e11;
	Matrix(long a,long b,long c,long d)
	{
	    e00=a;e01=b;e10=c;e11=d;
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
	    return e00+" "+e01+"\n"+e10+" "+e11+"\n";
	}
    }
    public static Matrix pow(Matrix m,long b)
    {
	if(b==1)
	    return m;
	else
	{
	    if((b&1L) == 0)
	    {
		return pow(m.multiply(m),b/2);
	    }
	    else
	    {
		return m.multiply(pow(m.multiply(m),(b-1)/2));
	    }
	}
    }


    private static void solve(FastScanner s1, PrintWriter out)/* This is the actual solution */{		
	/*
	 * 
	 * |1  1| ^ n x | f(1) 0 |  = | f(n+1) 0 |
	 * |1  0|       | f(0) 0 |    |  f(n)  0 | 
	 * 
	 * where f(n) is the nth Fibonacci number.
	 */
	Matrix mat = new Matrix(1, 1,
		1, 0);
	Matrix baseCase = new Matrix(1, 0, 
		0, 0);

	mod = 1000000007;
	int t = s1.nextInt();
	while(t-->0)
	{
	    long n = s1.nextLong();
	    Matrix ans = pow(mat,n).multiply(baseCase);		
	    out.print(ans.e10 % mod + " ");	
	}
    }


    /************************ SOLUTION ENDS HERE ************************/

    /************************ TEMPLATE STARTS HERE ************************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter o = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
	solve(in, o);
	in.close();
	o.close();
    }

    static class FastScanner{
	public BufferedReader reader;
	public StringTokenizer st;
	public FastScanner(InputStream stream){
	    reader = new BufferedReader(new InputStreamReader(stream));
	    st = null;
	}
	public String next(){
	    while(st == null || !st.hasMoreTokens()){
		try{
		    String line = reader.readLine();
		    if(line == null) return null;
		    st = new StringTokenizer(line);
		}catch (Exception e){
		    throw (new RuntimeException());
		}
	    }
	    return st.nextToken();
	}
	public String nextLine(){
	    String str = null;
	    try {
		str = reader.readLine();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return str;
	}
	public int nextInt(){
	    return Integer.parseInt(next());
	}
	public long nextLong(){
	    return Long.parseLong(next());
	}
	public double nextDouble(){
	    return Double.parseDouble(next());
	}
	int[] nextIntArray(int n) {
	    int[] arr = new int[n];
	    for (int i = 0; i < n; i++) {
		arr[i] = nextInt();
	    }
	    return arr;
	}

	long[] nextLongArray(int n) {
	    long[] arr = new long[n];
	    for (int i = 0; i < n; i++) {
		arr[i] = nextLong();
	    }
	    return arr;
	}
	public void close(){	
	    try{ reader.close(); } catch(IOException e){e.printStackTrace();}
	}
    }
    /************************ TEMPLATE ENDS HERE ************************/
}
