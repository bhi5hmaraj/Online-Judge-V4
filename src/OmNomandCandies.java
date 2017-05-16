import java.util.*;
import java.io.*;
public class OmNomandCandies
{


    /************************ SOLUTION STARTS HERE ***********************/


    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	long arr[] = s1.nextLongArrayOneBased(N);


	ArrayList<Integer>[] divisors = (ArrayList<Integer>[]) new ArrayList[N + 1];
	for(int i=1;i<=N;i++)
	    divisors[i] = new ArrayList<>();


	for(int i=1;i<=N;i++)
	    for(int j=i * 2;j<=N;j+=i)
		divisors[j].add(i);


	long DP[] = new long[N+1];

	for(int i=1;i<=N;i++)
	{
	    for(int divisor : divisors[i])
		DP[i] = Math.max(DP[i],DP[divisor]);

	    DP[i] += arr[i];
	}

	for(int i=1;i<=N;i++)
	    out.print(DP[i] + " ");
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