import java.util.*;
import java.io.*;
import java.math.*;
class PRIME1
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve1(FastScanner s1, PrintWriter out) { //Using BigInteger.isProbablePrime()

	int t = s1.nextInt();
	while(t-->0)
	{
	    int m = s1.nextInt();
	    int n = s1.nextInt();
	    for(long i=m;i<=n;i++)
		if(BigInteger.valueOf(i).isProbablePrime(10))
		    out.println(i);
	    
	    out.println();
	}
	
    }
    public static ArrayList<Integer> sieve(int N)     //Sieve of Erathanoses
    {
	ArrayList<Integer> primes = new ArrayList<>();
	boolean num[] = new boolean[N+1];
	for(int i=2;i*i<=N;i++)
	{
	    if(!num[i])			
		for(int j=i*i;j<num.length;j+=i)
		    num[j] = true;			
	}
	for(int i=2;i<num.length;i++)
	    if(!num[i])
		primes.add(i);

	return primes;
    }

    private static void solve2(FastScanner s1, PrintWriter out) { //Using bounded sieve (HashMap)
	int t = s1.nextInt();
	ArrayList<Integer> primes= sieve(32000);
	while(t-->0)
	{
	    int m = s1.nextInt();
	    int n = s1.nextInt();
	    int cnt = 0;
	    HashMap<Integer,Boolean> arr = new HashMap<>();
	    for (int i = m; i <= n; i++)
		arr.put(i, true);
	    
	    if(arr.get(1) != null)
	    {
		arr.put(1, false);
	    }
	    for(int i:primes)
	    {	
		for (int j = (m % i == 0) ? m : (((m / i) + 1) * i); j <= n; j += i)
		{
		    if(j != i)
		    {
			arr.put(j, false);
		    }
		}
	    }
	    
	    for(int i=m;i<=n;i++)
		if(arr.get(i))
		    out.println(i);
	    

	    out.println();
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