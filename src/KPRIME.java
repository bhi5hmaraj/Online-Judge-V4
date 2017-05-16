import java.util.*;
import java.io.*;
class KPRIME
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static int bigPrime[];
    private static int MAX;
    private static void preCalBigPrimeSieve() //instead of the loPrimesieve you could use bigprimeSieve which has the same performance and its a lot more intutive
    {
	bigPrime[1] = 1;
	for(int i = 2;i<=MAX;i++)
	{
	    if(bigPrime[i] == 0)
	    {
		bigPrime[i] = i;
		for(int j = 2*i;j<=MAX;j += i)
		    bigPrime[j] = i;
	    }
	}
    }
    static int primeFactorize(int N)  
    {
	int ct = 0,prime;	
	while(N!=1)
	{
	    prime = bigPrime[N];
	    while(N % prime == 0)	    
		N /= prime;	
	    
	    ct++;
	}
	return ct;
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	MAX = 100000;
	bigPrime = new int[MAX+2];
	preCalBigPrimeSieve();	
	int cache[][] = new int[6][MAX+1];
	for(int i=2;i<=MAX;i++)
	{
	    int cnt = primeFactorize(i);
	    if(cnt <= 5)
		cache[cnt][i] = 1;
	}
	for(int i=1;i<=5;i++)
	    for(int j=1;j<=MAX;j++)
		cache[i][j] += cache[i][j-1];
	while(t-->0)
	{
	    int L = s1.nextInt();
	    int R = s1.nextInt();
	    int K = s1.nextInt();
	    out.println(cache[K][R] - cache[K][L-1]);
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