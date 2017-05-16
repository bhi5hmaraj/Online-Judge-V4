import java.util.*;
import java.io.*;
public class TrailingZeroes
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static int powOfFactor(int n, int factor)
    {
	int ct = 0;
	while(n % factor == 0)
	{
	    n /= factor;
	    ct++;
	}
	return ct;
    }
    private static final int MAX_LEN = (int)1e6 + 10;
    private static int dp5[] = new int[MAX_LEN];
    private static int dp2[] = new int[MAX_LEN];
    static
    {
	for(int i=1;i<MAX_LEN;i++)
	{
	    dp5[i] = dp5[i-1] + powOfFactor(i, 5);
	    dp2[i] = dp2[i-1] + powOfFactor(i, 2);
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();	
	for(int z=1;z<=t;z++)
	{
	    int n = s1.nextInt();
	    int r = s1.nextInt();
	    int p = s1.nextInt();
	    int q = s1.nextInt();
	    int numOf5 = ((dp5[n]-dp5[n-r])+(q * powOfFactor(p, 5))) - (dp5[r]);
	    int numOf2 = ((dp2[n]-dp2[n-r])+(q * powOfFactor(p, 2))) - (dp2[r]);
	    out.println("Case "+z+": "+Math.min(numOf5, numOf2));
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