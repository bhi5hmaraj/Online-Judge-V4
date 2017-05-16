import java.util.*;
import java.io.*;
public class RoutineProblem
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static int gcd(int big, int small) {
	int b = Math.max(big, small);
	int s = Math.min(big, small);
	if (s == 0)
	    return b;
	else
	    return gcd(s, b % s);
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int a = s1.nextInt(),b = s1.nextInt(), c = s1.nextInt(), d = s1.nextInt();
	int cmp = Double.compare((double)a/(double)b, (double)c/(double)d);
	int num = 0,den = 1;	
	if(cmp < 0){
	    num = ((b * c) - (a * d)) ;
	    den = b * c;
	}
	else if(cmp > 0){
	    num = ((a * d) - (b * c));
	    den = a * d;
	}
	int gcd = gcd(num, den);
	num /= gcd;
	den /= gcd;
	out.print(num + "/" + den);
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