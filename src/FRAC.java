import java.util.*;
import java.io.*;
class FRAC
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static String solve1(int a[],int b[], int N, PrintWriter out){

	double func = Double.MIN_VALUE;
	int MAX = (1 << N);
	String pos = "";
	for (int i = 1; i < MAX; i++)
	{
	    long sumNumer = 0;
	    long sumDenom = 0;
	    for (int j = 0; j < N; j++)
	    {
		if ((i & (1 << j)) != 0)
		{
		    sumNumer += a[j];
		    sumDenom += b[j];
		}
	    }
	    double frac = (double) sumNumer / (double) sumDenom;
	    if(frac > func)
	    {
		func = frac;
		pos = Integer.toBinaryString(i);
	    }
	}
	//System.out.println(pos);
	return String.format("%.8f", func);
    }


    private static void solve3(int a[],int b[],int N, PrintWriter out){
	double frac = Double.MIN_VALUE;
	for (int i = 0; i < N; i++)
	    frac = Math.max(frac, (double) a[i] / (double) b[i]);

	out.printf("%.8f", frac);
    }
    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/
    
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	int N   = in.nextInt();
	int a[] = in.nextIntArray(N);
	int b[] = in.nextIntArray(N);
	solve3(a, b, N, out);
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