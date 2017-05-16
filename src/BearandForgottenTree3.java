import java.util.*;
import java.io.*;
public class BearandForgottenTree3
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int diameter = s1.nextInt();
	int height = s1.nextInt();
	if (diameter > 2 * height || (diameter == 1 && height == 1 && n != 2))
	    out.print(-1);
	else
	{
	    for (int i = 1; i <= height; i++)
		out.println(i + " " + (i + 1));

	    if(diameter > height)
	    {
		int curr = height + 2;
		out.println("1 " + curr);
		for (int i = 1; i <= diameter - height - 1; i++)
		    out.println((curr++) + " " + curr);

		for (int i = diameter + 2; i <= n; i++)
		    out.println("1 " + i);
	    }
	    else
	    {
		for (int i = diameter + 2; i <= n; i++)
		    out.println("2 " + i);
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