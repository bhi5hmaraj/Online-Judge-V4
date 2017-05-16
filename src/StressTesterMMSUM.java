import java.util.*;
import java.io.*;
import java.security.SecureRandom;
public class StressTesterMMSUM
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static void generator(PrintWriter input){   //Writes a single instance for a test case

	/*
	 * This is the random test generator which generates a single instance 
	 * use input object for writing into input.txt
	 * 
	 * 
	 */
	int t = test;
	input.println(t);
	SecureRandom rand = new SecureRandom();
	int set[] = new int[]{-100000,-200000,-300000,-400000,-500000};
	while(t-->0){
	    int n = /*rand.nextInt(len) + 2*/ len;
	    input.println(n);
	    long val = rand.nextLong() % MAX_A;
	    val = (val < 0) ? val + MAX_A : val;
	    input.print(val + " ");
	    for(int i=2;i<=n-1;i++){
		val = rand.nextLong() % MAX_A;
		val = (val < 0) ? val + MAX_A : val;
		input.print(( (rand.nextBoolean() ? (val) : set[rand.nextInt(set.length)]) ) + " ");
	    }
	    val = rand.nextLong() % MAX_A;
	    val = (val < 0) ? val + MAX_A : val;
	    input.println(val);
	}

    }

    private static int JUDGE(String correctFile, String toBeChecked)throws IOException
    {
	FastScanner key	      = new FastScanner(new FileInputStream(correctFile));
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




    static int runs = 1;   //Number of times to run the stress test bed
    static int test = 10;
    static int len  = (int)(1e5);
    static long MAX_A = (int)(1e9);

    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {


	String inputFile = "input.txt";

	while(runs-->0)	
	{	   

	    PrintWriter input = new PrintWriter(inputFile);
	    generator(input);
	    input.close();
	    
	    long start = System.nanoTime();	    
	    //MMSUMbrute.main(null);   // By default all solvers read from "input.txt"
	    long stop = System.nanoTime();
	    System.err.print("Brute Time : "+((stop-start)/1e9) + " s");
	    start = System.nanoTime();
	    MMSUM.main(null);    // By default all solvers write to a file whose location is stored in a static String called outputFile
	    stop = System.nanoTime();
	    System.err.println(" Fast Time : "+((stop-start)/1e9) + " s");

	    int verdict1 = JUDGE(MMSUMbrute.outputFile, MMSUM.outputFile);   

	    // Judge checks the two files in the argument and gives a verdict 
	    //  verdict > 0 	====> wrong answer at line verdict
	    //  verdict == -1   ====> PASS !!	     

	    if(verdict1 > 0)	
		System.err.println("Wrong answer (in class MMSUM) at line :" + verdict1);


	    if(verdict1 > 0 )
		return;

	    System.out.println("PASS");

	}
    }    

    /*
     	Things to do in the solvers :
       	static String outputFile = "output_AVL.txt";
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