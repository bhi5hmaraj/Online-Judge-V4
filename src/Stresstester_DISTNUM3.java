import java.util.*;
import java.io.*;
public class Stresstester_DISTNUM3
{


	/************************ SOLUTION STARTS HERE ***********************/

	private static void generator(PrintWriter input) throws Exception{   //Writes a single instance for a test case

		/*
		 * This is the random test generator which generates a single instance 
		 * use input object for writing into input.txt
		 * 
		 * 
		 */

		input.println(MAX_V + " " + MAX_Q);
		Random rnd = new Random();
		for(int a :rnd.ints(MAX_V, 0, MAX_NUM1 + 1).toArray())
			input.print(a + " ");
		input.println();
		// Random graph generation adapted from testlib.h
		int perm[] = new int[MAX_V];
		int p[] = new int[MAX_V];
		for(int i=1;i<MAX_V;i++)
			p[i] = rnd.nextInt(i);

		for(int i=0;i<MAX_V;i++)
			perm[i] = i;

		for (int i = perm.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int temp = perm[index];
			perm[index] = perm[i];
			perm[i] = temp;
		}

		for(int i=1;i<MAX_V;i++)
			input.println((perm[i] + 1) + " " + (perm[p[i]] + 1));

		/*		for(int i=1;i<=MAX_V-1;i++)
			input.println(i + " " + (i + 1));
		 */
		/*		int ptr = 0;

		for(;ptr < MAX_Q/4;ptr++)
			input.println("1 " + (1 + rnd.nextInt(MAX_V)) + " " + (1 + rnd.nextInt(MAX_V)));
		for(;ptr < (3*MAX_Q)/4;ptr++)
			input.println("2 " + (1 + rnd.nextInt(MAX_V)) + " " + rnd.nextInt(MAX_NUM2 + 1));
		for(;ptr < MAX_Q;ptr++)
			input.println("1 " + (1 + rnd.nextInt(MAX_V)) + " " + (1 + rnd.nextInt(MAX_V)));
		 */

		for(int i=0;i<MAX_Q;i++) {
			if(rnd.nextBoolean()) 
				input.println("1 " + (1 + rnd.nextInt(MAX_V)) + " " + (1 + rnd.nextInt(MAX_V)));
			else
				input.println("2 " + (1 + rnd.nextInt(MAX_V)) + " " + rnd.nextInt(MAX_NUM2 + 1));
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




	static int MAX_V = (int) 1e3;
	static int MAX_Q = (int) 2e3;
	static int MAX_NUM1 = (int) 1e9;
	static int MAX_NUM2 = (int) 1e9;

	/************************ TEMPLATE STARTS HERE *********************/
	static String inputFile = "input.txt";
	public static void main(String []args) throws Exception {

		int start = (int)5e4;
		int end = (int) 8e4;
		int step = 250;
		PrintWriter collect = new PrintWriter("collect.txt");
		for(int N = start;N <= end;N += step) {
			MAX_Q = MAX_V = N;
			PrintWriter input = new PrintWriter(inputFile);
			generator(input);
			input.close();
			long st;
			long stop ;
			/*		st = System.nanoTime();
		DISTNUM3.main(null);   // By default all solvers read from "input.txt"
		stop = System.nanoTime();
		System.out.println("Time for naive : " + ((stop - st) / 1e9));
			 */
			st = System.nanoTime();
			DISTNUM3_MO.main(null);    // By default all solvers write to a file whose location is stored in a static String called outputFile
			/*			Thread t = new Thread(null, new Runnable() {
				public void run() {
					try {
						new DISTNUM3_MO().run();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}, "Increase Stack", 1 << 25);
			t.setName("DISTNUM_MO");
			t.start();
			t.join();*/
			stop = System.nanoTime();
			//System.out.println("Time for mo : " + ((stop - st) / 1e9));
			collect.printf("%d\t%.3f\n", N,((stop - st) / 1e9));

			// Judge checks the two files in the argument and gives a verdict 
			//  verdict > 0 	====> wrong answer at line verdict
			//  verdict == -1   ====> PASS !!	     
			if(N % 5000 == 0) 
				System.out.println("At " + N);
			
		}
		collect.close();

		System.out.println("DONE!!");
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