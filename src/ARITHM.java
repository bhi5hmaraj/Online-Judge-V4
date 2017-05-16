import java.util.*;
import java.io.*;
class ARITHM
{


    /************************ SOLUTION STARTS HERE ***********************/

    static long gcd(long big, long small) {
	long b = Math.max(big, small);
	long s = Math.min(big, small);
	if (s == 0)
	    return b;
	else
	    return gcd(s, b % s);
    }
    static long d, x, y;
    static void extendedEuclid(long A, long B) {
        if(B == 0) {
            d = A;
            x = 1;
            y = 0;
        }
        else {
            extendedEuclid(B, A%B);
            long temp = x;
            x = y;
            y = temp - (A/B)*y;
        }
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    long N = s1.nextLong();
	    long C = s1.nextLong();

	    long a = ((N - 1L) * N) / 2L;
	    long n = N;
	    long gcd = gcd(a, n);

	    long req = (N * (N + 1)) / 2;

	    if(C < req)
		out.println("No");
	    else	    
	    {
		if(C % gcd != 0)
		    out.println("No");
		else
		{
		    /*
		    a /= gcd;   // a
		    n /= gcd;   // mod 
		    C /= gcd;   // b
		    */
		    extendedEuclid(n, a);
		    System.out.println("x "+x+" y "+y);
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
     
    /*
    static String outputFile = "output_ARITHM.txt";
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
	solve(in, out);
	in.close();
	out.close();
    }    
    */
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