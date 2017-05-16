import java.util.*;
import java.io.*;
public class Grid
{

    
    /************************ SOLUTION STARTS HERE ***********************/


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
    
    private static int MAX = 1000000;   //You costed me 10 Acs
    private static long fact[] = new long[MAX+5];
    static {
	fact[1] = 1;
	fact[0] = 1;
	for(int i=2;i<=MAX;i++)
	    fact[i] = ModMath.mul(i, fact[i-1]);
    }
    
    private static long nCr(int n,int r)
    {
	return ModMath.div(fact[n], ModMath.mul(fact[r], fact[n-r]));
    }
    
    private static void solve2(FastScanner s1, PrintWriter out){
	int t = s1.nextInt();
	while(t-->0)
	{
	    int n = s1.nextInt();
	    int m = s1.nextInt();
	    int h = s1.nextInt();
	    int k = s1.nextInt();	    
	    if(h == 1 || k == m)
	    {
		out.println(0);
		return;
	    }
	    long arr[][] = new long[2][m];
	    for (int i = k; i < m; i++)
		arr[0][i] = nCr(i + h - 2, i);
	    for (int i = k; i < m; i++)
		arr[1][i] = nCr((m - i - 1) + (n - h), n - h);

	    long ways = 0;
	    for (int i = k; i < m; i++)
		ways = ModMath.add(ways, ModMath.mul(arr[0][i], arr[1][i]));

	    out.println(ways);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	long mod = (long)(1e9) + 7;
	while(t-->0)
	{
	    int n = s1.nextInt();
	    int m = s1.nextInt();
	    long arr[][];
	    try{
	    arr = new long[n+1][m+1];
	    }catch(Error e)
	    {
		out.println("Insufficient Memory");
		return;
	    }
	    int h = s1.nextInt();
	    int k = s1.nextInt();
	    for (int i = 1; i <= m; i++)
		arr[1][i] = 1;
	    for (int i = 1; i <= n; i++)
		arr[i][1] = 1;

	    for (int i = 2; i < h; i++)
		for (int j = 2; j <= m; j++)
		    arr[i][j] = ((arr[i][j - 1] % mod) + (arr[i - 1][j] % mod)) % mod;

	    for (int i = h; i <= n; i++)
		for (int j = k + 1; j <= m; j++)
		    arr[i][j] = ((arr[i][j - 1] % mod) + (arr[i - 1][j] % mod)) % mod;
	    
	    out.println(arr[n][m] % mod);
	}

    }
    
    

    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
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