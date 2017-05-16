import java.util.*;
import java.io.*;
import java.math.BigInteger;
class FRJUMP_brute
{


    /************************ SOLUTION STARTS HERE ***********************/

    static final long mod = (long) (1e9) + 7L; // Default
    static final BigInteger MOD = BigInteger.valueOf(mod);
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);
	int Q = s1.nextInt();
	while(Q-->0){
	    int choice = s1.nextInt();
	    if(choice == 1){
		arr[s1.nextInt()] = s1.nextLong();
	    }
	    else{
		BigInteger prod = BigInteger.ONE;
		int R = s1.nextInt();
		for(int i=0;1 + (i * R) <= N;i++)
		    prod = prod.multiply(BigInteger.valueOf(arr[1 + (i * R)]));

		out.println(String.valueOf(prod).charAt(0) + " " + (prod.mod(MOD)));

	    }
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
    static String outputFile = "brute_FRJUMP.txt";
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