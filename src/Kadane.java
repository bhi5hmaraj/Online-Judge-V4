import java.util.*;
import java.io.*;
class Kadane
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int len = s1.nextInt();
	    int arr[] = s1.nextIntArray(len);
	    int maxPos = Integer.MIN_VALUE;
	    int maxNeg = Integer.MIN_VALUE;
	    int sumOfPos = 0;
	    int local_max = 0;
	    for(int x:arr)
	    {
		sumOfPos += (x > 0)?x:0;
		local_max = Math.max(0,local_max + x);
		if(x <= 0)
		    maxNeg = Math.max(maxNeg,x);
		maxPos = Math.max(maxPos,local_max);
	    }
	    out.println(( (maxPos==0) ? maxNeg : maxPos ) + " " + ( (sumOfPos==0) ? maxNeg : sumOfPos));
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