import java.util.*;
import java.io.*;
public class Productdivisors
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static int bigPrime[];
    static int MAX = (int)(1e6);

    static  //Precalculate big prime till MAX
    {
	bigPrime = new int[MAX + 10];
	bigPrime[1] = 1;
	for (int i = 2; i <= MAX; i++) {
	    if (bigPrime[i] == 0) {
		bigPrime[i] = i;
		for (int j = 2 * i; j <= MAX; j += i)
		    bigPrime[j] = i;
	    }
	}
    }

    static HashMap<Integer, Integer> primeFactorize(int N) // Dependency : A
    {
	HashMap<Integer, Integer> mp = new HashMap<>();
	int ct, prime;
	while (N != 1) {
	    prime = bigPrime[N];
	    ct = 0;
	    while (N % prime == 0) {
		N /= prime;
		ct++;
	    }
	    mp.put(prime, ct);
	}
	return mp;
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	final long mod = (long)(1e9) + 7L;
	int N = s1.nextInt();
	HashMap<Integer,Integer> prod = new HashMap<>();
	while(N-->0){
	    HashMap<Integer,Integer> factorized = primeFactorize(s1.nextInt());
	    for (Map.Entry<Integer,Integer> e : factorized.entrySet()) 
	    {
		Integer pow = prod.get(e.getKey());
		prod.put(e.getKey(), pow == null ? e.getValue() : pow + e.getValue());
	    }
	}
	
	long numOfDivisor = 1;
	for (Map.Entry<Integer,Integer> e : prod.entrySet()) 
	    numOfDivisor = ((numOfDivisor % mod) * ((e.getValue().longValue() + 1) % mod)) % mod;
	
	out.print(numOfDivisor);
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