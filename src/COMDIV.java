import java.util.*;
import java.io.*;
class COMDIV
{


    /************************ SOLUTION STARTS HERE ***********************/
    private static int bigPrime[]; 
    private static void preCalBigPrimeSieve(int N) //instead of the loPrimesieve you could use bigprimeSieve which has the same performance and its a lot more intutive
    {
	bigPrime[1] = 1;
	for(int i = 2;i<=N;i++)
	{
	    if(bigPrime[i] == 0)
	    {
		bigPrime[i] = i;
		for(int j = 2*i;j<=N;j += i)
		    bigPrime[j] = i;
	    }
	}
    }

    static HashMap<Integer,Integer> primeFactorize(int N)   //Dependency : A sieve (loPrime[]) which contains the lowest prime divisor for each number
    {
	HashMap<Integer,Integer> mp = new HashMap<>();
	int ct,prime;
	while(N!=1)
	{
	    prime = bigPrime[N];
	    ct = 0;
	    while(N % prime == 0)
	    {
		N /= prime;	
		ct++;
	    }
	    mp.put(prime, ct);
	}
	return mp;
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	int MAX = (int)(1e6);
	bigPrime  = new int[MAX + 10];
	preCalBigPrimeSieve(MAX);
	while(t-->0)
	{
	    int m = s1.nextInt();
	    int n = s1.nextInt();
	    HashMap<Integer,Integer> m1 = primeFactorize(m);
	    HashMap<Integer,Integer> m2 = primeFactorize(n);
	    int comdiv = 1;
	    for (Map.Entry<Integer,Integer> e : m1.entrySet()) 
	    {
		Integer p1 = e.getValue();
		Integer p2 = m2.get(e.getKey());
		if(p2 != null)
		    comdiv *= (Math.min(p1,p2) + 1);
	    }
	    out.println(comdiv);
	}

    }

    static int gcd(int big,int small)
    {
	int b = Math.max(big, small);
	int s = Math.min(big, small);
	if(s == 0)
	    return b;
	else
	    return gcd(s,b%s);
    }
    private static void solve2(FastScanner s1, PrintWriter out){
	int t = s1.nextInt();
	while(t-->0)
	{
	    int m = s1.nextInt();
	    int n = s1.nextInt();
	    int gcd = gcd(m, n);
	    int comdiv = 0;
	    for (int i = 1; i * i <= gcd; i++)
		comdiv += (gcd % i == 0) ? ((i * i == gcd) ? 1 : 2) : 0;
	    
	    out.println(comdiv);
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