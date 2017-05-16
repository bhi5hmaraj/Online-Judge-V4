import java.util.*;
import java.io.*;
class CDRSANJ
{


    /************************ SOLUTION STARTS HERE ***********************/

    public static boolean[] isPrimeSieve(int N)     //Sieve of Erathanoses
    {
	boolean num[] = new boolean[N+1];
	num[1]=false;
	Arrays.fill(num, true);
	for(int i=2;i * i<=N;i++)
	{
	    if(num[i])			
		for(int j=i*i;j<num.length;j+=i)
		    num[j] = false;			
	}
	return num;
    }

    private static void solve(FastScanner s1, PrintWriter out){
	
	int input = s1.nextInt();
	final int MAX = input + 10;
	boolean isPrime[] = isPrimeSieve(MAX);
	int F[] = new int[MAX];
	F[0] = 0; F[1] = 1; F[2] = 2;
	for(int i=3;i<MAX;i++)
	{
	    if(isPrime[i])
		F[i] = 0;
	    else
	    {
		int root = (int)Math.sqrt(i);   //It is maximum at sqrt(i) 
		while(i % root != 0){root--;}
		int a = root;
		int b = i / root;
		F[i] = F[a] + F[b];
	    }
	}
	out.print(F[input]);
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