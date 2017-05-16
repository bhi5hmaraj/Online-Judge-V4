import java.util.*;
import java.io.*;
class RECTLOVE
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    private static long R,C;
    private static long[] getCoordinate(long n)
    {
	long i = (n-1) / C;
	long j = (n-1) % C;
	return new long[]{i,j};
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    R = s1.nextLong();
	    C = s1.nextLong();
	    double expect = 0.0;
	    double total = (double) ((R * (R + 1)) / 2L) * (double) ((C * (C + 1)) / 2L);
	    int K = s1.nextInt();
	    while(K-->0)
	    {
		long pos[] = getCoordinate(s1.nextLong());
		double ways = (double) (((R - pos[0]) * (pos[0] + 1))) * (double) (((C - pos[1]) * (pos[1] + 1)));
		expect += (ways / total);
	    }
	    //out.printf("%.9f\n", expect);
	    out.println(expect);
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