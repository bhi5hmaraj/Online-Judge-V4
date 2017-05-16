import java.util.*;
import java.io.*;
public class Stresstest_DIVMAC  // A big thank you!!
{


	/************************ SOLUTION STARTS HERE ***********************/
	static boolean marked[];
	static ArrayList<Integer> primes;
	static {
		int MAX = (int)(1e6);
		marked = new boolean[MAX + 10];
		primes = new ArrayList<>();
		for(int i=2;i*i<=MAX;i++)
			for(int j=i*i;j<=MAX;j+=i)
				marked[j] = true;

		for(int i=1;i<=MAX;i++)
			if(!marked[i])
				primes.add(i);

	}
	private static void generator(PrintWriter input){   //Writes a single instance for a test case

		/*
		 * This is the random test generator which generates a single instance 
		 * use input object for writing into input.txt
		 * 
		 * 
		 */
		Random r = new Random();
		int q = 10;
		input.println(q);
		while(q-->0){
			input.println(MAX_N + " " + MAX_Q);
			int N = MAX_N;
			int Q = MAX_Q;
			int primes_len = primes.size();
			while(N-->0)
				input.print(/*primes.get(r.nextInt(primes_len))*/(1 + r.nextInt(MAX_Ai)) + " ");
			input.println();
			while(Q-->0){
				int L = 1 + r.nextInt(MAX_N);
				int R = L + r.nextInt(MAX_N - L + 1);
				input.println((r.nextInt(2)) + " " + L + " " + R);
			}
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




	static int runs = 5;   //Number of times to run the stress test bed
	static int MAX_N = (int)(1e5);
	static int MAX_Q = (int)1e5;
	static int MAX_Ai = (int)(1e6);
	/************************ TEMPLATE STARTS HERE *********************/

	public static void main(String []args) throws IOException {


		String inputFile = "input.txt";

		while(runs-->0)	
		{	   

			PrintWriter input = new PrintWriter(inputFile);
			generator(input);
			input.close();
			//long t1 = System.nanoTime();
			//DIVMAC_slow.main(null);   // By default all solvers read from "input.txt"
			long t2 = System.nanoTime();
			DIVMAC.main(null);    // By default all solvers write to a file whose location is stored in a static String called outputFile
			long t3 = System.nanoTime();

			//int verdict1 = JUDGE(DIVMAC_slow.outputFile, DIVMAC.outputFile);   

			// Judge checks the two files in the argument and gives a verdict 
			//  verdict > 0 	====> wrong answer at line verdict
			//  verdict == -1   ====> PASS !!	     

			//if(verdict1 > 0)	
			//System.err.println("Wrong answer (in class DIVMAC) at line :" + verdict1);


			//if(verdict1 > 0 )
			//return;

			//System.out.println("PASS Time Slow : " + ((t2 - t1) / 1e9) +" s Time Fast : "+ ((t3-t2) / (1e9)) + " s" );
			System.out.println("Time Fast : "+ ((t3-t2) / (1e9)) + " s" );
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