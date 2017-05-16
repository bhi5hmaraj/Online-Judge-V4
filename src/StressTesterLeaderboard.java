import java.util.*;
import java.io.*;
import java.security.SecureRandom;

public class StressTesterLeaderboard
{


    /************************ SOLUTION STARTS HERE ***********************/

    static int greater(long arr[], long n)
    {
	int cnt = 0;
	for(long l:arr)
	    cnt += (l > n)?1:0;
	return cnt;
    }
    static long hash(long arr[],long n)
    {
	return (n * (long)(greater(arr, n) + 1));
    }
    static long sumOfHash(long arr[])
    {
	long sum = 0;
	for(long n:arr)
	    sum += hash(arr, n);
	return sum;
    }
    private static void generator(PrintWriter input, PrintWriter out){   //Writes a single instance for a test case

	SecureRandom rand = new SecureRandom();

	int N = n_MAX; int Q = q_MAX;
	input.println(N + " " + Q);
	long score[] = new long[N+1];
	while(Q-->0)
	{
	    int index = rand.nextInt(N);
	    int inc = rand.nextInt(e_MAX) + 1;
	    score[index] += inc;
	    input.println((index+1) + " " + inc);
	    out.println(sumOfHash(score));
	}
    }

    private static int JUDGE(String correctFile, String toBeChecked)throws IOException
    {
	FastScanner key = new FastScanner(new FileInputStream(correctFile));
	FastScanner unchecked = new FastScanner(new FileInputStream(toBeChecked));
	String read = null;
	int line = 0;
	while((read = key.nextLine()) != null)
	{
	    String candidate = unchecked.nextLine();
	    line++;
	    if(!read.equals(candidate))
		return line;
	}
	return -1;
    }
    /************************ SOLUTION ENDS HERE ************************/



    static final int q_MAX = 500;
    static final int n_MAX = 500;
    static final int e_MAX = (int)(1e6);
    static int runs = 10;
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {

	String correctFile = "correct_output.txt";
	String inputFile = "input.txt";

	while(runs-->0)	
	{	   

	    PrintWriter input = new PrintWriter(inputFile);
	    PrintWriter out = new PrintWriter(correctFile);
	    generator(input, out);
	    input.close();
	    out.close();
	    BearandLeaderboard.main(null);
	    int verdict1 = JUDGE(correctFile, BearandLeaderboard.outputFile);
	    if(verdict1 > 0)	
	    {
		System.err.println("Wrong answer  at line :" + verdict1);
		return;
	    }
	    System.out.println("PASS");
	}
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