import java.util.*;
import java.io.*;
public class MonkandMatchMaking
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static long x = 31L; // Random multiplier used in polynomial hash
    
    static class ModMath
    {
	private static long mod = (long)(1e9) + 7L;   	// Default 
	static long sub(long a,long b)
	{
	    long sub = ((a % mod) - (b % mod)) % mod;
	    return sub < 0 ? mod + sub : sub;
	}
	static long mul(long a,long b)
	{
	    return ((a % mod) * (b % mod)) % mod;
	}
	static long div(long a,long b)
	{
	    return mul(a, inverseMod(b));
	}
	static long add(long a,long b)
	{
	    return ((a % mod) + (b % mod)) % mod;
	}
	static long inverseMod(long n)      // Fermat's little theorem , used it to find the modular inverse
	{
	    return modPow(n, mod - 2L);
	}
	static long modPow(long a,long b)   // squared exponentiation  
	{
	    if(b == 0L || a == 1L)
		return 1L;
	    else if(b == 1L)
		return a;
	    else
	    {
		if ((b & 1L) == 0L) 		//Checking whether b is even (fast)  
		    return modPow((a * a) % mod,b >> 1); 
		else
		    return (a * modPow((a * a) % mod,b >> 1)) % mod ;
	    }
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){

	String text = s1.nextLine();
	int len = text.length();
	long hash[] = new long[len + 10];

	hash[1] = text.charAt(0);
	long curr_x = x;
	for (int i = 2; i <= len; i++, curr_x = ModMath.mul(curr_x, x))
	    hash[i] = ModMath.add(hash[i - 1], ModMath.mul(text.charAt(i - 1), curr_x));


	int query = s1.nextInt();
	while(query-->0)
	{
	    int L1 = s1.nextInt();
	    int R1 = s1.nextInt();
	    int L2 = s1.nextInt();
	    int R2 = s1.nextInt();
	    long hash1 = ModMath.div(ModMath.sub(hash[R1], hash[L1 - 1]), ModMath.modPow(x, L1 - 1));
	    long hash2 = ModMath.div(ModMath.sub(hash[R2], hash[L2 - 1]), ModMath.modPow(x, L2 - 1));
	    out.println( hash1 == hash2 ? "Yes" : "No");
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