import java.util.*;
import java.io.*;
public class Coins
{


    /************************ SOLUTION STARTS HERE ***********************/

    public static HashSet<Integer> sieve(int N)     //Sieve of Erathanoses
    {
	HashSet<Integer> primes = new HashSet<>();
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

    private static void solve(FastScanner s1, PrintWriter out){

	int n =s1.nextInt();
	HashSet<Integer> primes = sieve(n);
	while(true)
	{
	    out.print(n + " ");
	    if(n == 1)
		break;
	    if(primes.contains(n))
		n = 1;
	    else
	    {
		for(int i = 2;i*i<=n;i++)
		    if(n%i==0)
		    {
			n /= i;
			break;
		    }
	    }
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