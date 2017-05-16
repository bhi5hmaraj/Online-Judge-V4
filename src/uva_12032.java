import java.util.*;
import java.io.*;
public class uva_12032
{


    /************************ SOLUTION STARTS HERE ***********************/

    static boolean simulate(int arr[] , int K)
    {
	for(int i=1;i<arr.length;i++)
	{
	    int diff = arr[i] - arr[i-1];
	    if(diff > K)
		return false;
	    else if(diff == K)
		K--;
	}

	return true;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	for(int z=1;z<=t;z++)
	{
	    int N = s1.nextInt();
	    int arr[] = s1.nextIntArrayOneBased(N);
	    int lo = 1;
	    int hi = (int)(1e8);
	    int minK = -1;
	    while(lo <= hi)
	    {
		int K = (lo + hi) / 2;
		if(simulate(arr, K)){
		    minK = K;
		    hi = K - 1;
		}
		else
		    lo = K + 1;
	    }

	    out.println("Case "+z+": "+minK);
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