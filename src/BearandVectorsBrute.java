import java.util.*;
import java.io.*;
public class BearandVectorsBrute
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static boolean isPerpendicular(long... val)
    {
	return ((val[0] * val[2]) + (val[1] * val[3])) == 0;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	long vector[][] = new long[N][2];
	for(int i=0;i<N;i++)
	    vector[i] = s1.nextLongArray(2);

	int cnt = 0;
	for (int i = 0; i < N; i++)
	{
	    for (int j = 0; j < N; j++)
	    {
		if(j != i)
		{
		    for (int k = 0; k < N; k++)
		    {
			if(k != i && k != j)
			{
			    long x = vector[j][0] + vector[k][0];
			    long y = vector[j][1] + vector[k][1]; 
			    if(!(x == 0 && y == 0))			    
				cnt += (isPerpendicular(vector[i][0], vector[i][1],  x , y)) ? 1 : 0;
			}
		    }
		}
	    }
	}

	out.print(cnt);
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
    static String outputFile = "bear_vector_correct.txt";

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