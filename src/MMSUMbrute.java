import java.util.*;
import java.io.*;
class MMSUMbrute
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static long kadane(Iterable<Long> arl){

	long tempSum = 0;
	long maxSum = Long.MIN_VALUE;
	long maxNeg = Long.MIN_VALUE;
	for(long l : arl){

	    if(l <= 0)
		maxNeg = Math.max(maxNeg,l);

	    tempSum = Math.max(0,l + tempSum);
	    maxSum = Math.max(maxSum,tempSum);
	}

	return maxSum == 0 ? maxNeg : maxSum;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int len = s1.nextInt();
	    ArrayList<Long> arl = new ArrayList<>(len);
	    for(int i=0;i<len;i++)
		arl.add(s1.nextLong());

	    long maxSum = kadane(arl);
	    for(int i=0;i<len;i++){
		long val = arl.get(i);
		arl.remove(i);
		maxSum = Math.max(maxSum,kadane(arl));
		arl.add(i, val);
	    }

	    out.println(maxSum);
	}

    }



    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/
    /*
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve(in, out);
	in.close();
	out.close();
    }    
     */
    static String outputFile = "output_MMSUM_correct.txt";
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
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