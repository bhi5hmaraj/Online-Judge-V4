import java.util.*;
import java.io.*;
import java.security.SecureRandom;
public class Generator_CHSQARR 
{


    /************************ SOLUTION STARTS HERE ***********************/

    private static void generator(PrintWriter input){   //Writes a single instance for a test case

	/*
	 * This is the random test generator which generates a single instance 
	 * use input object for writing into input.txt
	 * 
	 * 
	 */
	input.println(R + " " + C);
	SecureRandom rand = new SecureRandom();
	for(int i=0;i<R;i++){
	    for(int j=0;j<C;j++){
		input.print((1 + rand.nextInt(10)) + " ");
	    }
	    input.println();
	}
	int q = Q;
	input.println(q);
	while(q-->0)
	{
	    //input.println((1 + rand.nextInt(R)) + " " + (1 + rand.nextInt(C)));
	    input.println((1+rand.nextInt(3)) + " " + (1 + rand.nextInt(3)));
	}
    }

    /************************ SOLUTION ENDS HERE ************************/




    static int runs = 10;   //Number of times to run the stress test bed
    static int R = 1000;
    static int C = 1000;
    static int Q = 50;

    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {


	String inputFile = "input.txt";
	long sum = 0;	
	for(int t = 1;t <= runs ;t++)	
	{	   
	    PrintWriter input = new PrintWriter(inputFile);
	    generator(input);
	    input.close();
	    long start = System.nanoTime();
	    CHSQARR_sparse.main(null);
	    long stop = System.nanoTime();
	    sum += (stop - start);
	    System.err.println("Time : "+((stop-start)/1e9) + " s");
	}
	System.err.println("Average time "+ (sum / (runs * 1e9)) + "s");
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