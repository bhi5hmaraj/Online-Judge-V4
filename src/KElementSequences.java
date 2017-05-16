import java.util.*;
import java.io.*;
public class KElementSequences
{


    /************************ SOLUTION STARTS HERE ***********************/
    static final long mod = ((long)1e9) + 7L;
    private static long mul(long a,long b)      /* Modular multiplication */
    {
	return ((a%mod)*(b%mod))%mod;
    }
    private static long inverseMod(long n) /* Fermat's little theorem , used it to find the modular inverse of the denominator*/
    {
	return modPow(n,mod - 2L);
    }
    private static long modPow(long a,long b)   // squared exponentiation  
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
    private static long MAX = (long)(2e6);
    private static long cache[] = new long[(int)MAX+10];
    static
    {
	cache[0] = 1;
	for(int i=1;i<=MAX;i++)
	    cache[i] = mul(cache[i-1], i);
    }
    private static long comb(int n,int k)
    {
	return k < 0 ? 1 : mul(cache[n], inverseMod(mul(cache[k], cache[n-k])));
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	    out.println(comb(s1.nextInt()-1, s1.nextInt()-1));

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