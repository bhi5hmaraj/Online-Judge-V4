import java.util.*;
import java.io.*;
public class WattoandMechanism
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static long x = 73;

    static final class ModMath {
	static long mod = (long) (1e9) + 7L; // Default

	static long sub(long a, long b) {
	    long sub = ((a % mod) - (b % mod)) % mod;
	    return sub < 0 ? mod + sub : sub;
	}

	static long mul(long a, long b) {
	    return ((a % mod) * (b % mod)) % mod;
	}

	static long div(long a, long b) {
	    return mul(a, modInverse(b));
	}

	static long add(long a, long b) {
	    return ((a % mod) + (b % mod)) % mod;
	}

	static long modInverse(long n) {   // Fermat's little theorem	
	    return modPow(n, mod - 2L);
	}

	static long modPow(long a, long b) { // squared exponentiation	
	    if (b == 0L || a == 1L)
		return 1L;
	    else if (b == 1L)
		return a;
	    else {
		if ((b & 1L) == 0L) 	  // Checking whether b is even (fast)
		    return modPow((a * a) % mod, b >> 1);
		else
		    return (a * modPow((a * a) % mod, b >> 1)) % mod;
	    }
	}
    }
    private static long hash(String str)
    {
	long hash = 0;
	for(int i=str.length()-1;i>=0;i--)
	    hash = ModMath.add(ModMath.mul(hash, x), str.charAt(i));

	return hash;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int m = s1.nextInt();
	HashMap<Long,HashSet<String>> map = new HashMap<>();
	char alph[] = {'a','b','c'};
	while(n-->0)
	{
	    String str = s1.nextLine();
	    long hash = hash(str);
	    HashSet<String> arl = map.get(hash);
	    if(arl == null)arl = new HashSet<>();
	    arl.add(str);
	    map.put(hash, arl);
	}

	while(m-->0)
	{
	    String str = s1.nextLine();
	    long hash = hash(str);
	    boolean flag = false;
	    outer:
		for(char ch : alph)
		{
		    long curr_x = 1;
		    for(int i=0,len=str.length();i<len;i++,curr_x = ModMath.mul(curr_x, x))
		    {
			if(str.charAt(i) != ch)  //You costed me 5 WAs 
			{
			    long newHash = ModMath.add(ModMath.sub(hash, ModMath.mul(str.charAt(i), curr_x)), ModMath.mul(ch, curr_x));
			    if(map.containsKey(newHash))
			    {
				StringBuilder sb = new StringBuilder(str);
				sb.setCharAt(i, ch);
				if(map.get(newHash).contains(sb.toString()))
				{
				    flag = true;
				    break outer;
				}
			    }
			}
		    }
		}

	    out.println(flag?"YES":"NO");
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