import java.util.*;
import java.io.*;
public class FindTheTriangle
{


    /************************ SOLUTION STARTS HERE ***********************/


    static void assign(long a,long b,long c,long arr[]){
	arr[0] = a;
	arr[1] = b;
	arr[2] = c;
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    long side[] = s1.nextLongArray(N);
	    long max_perimeter = 0;
	    long maxSide[] = new long[3];
	    if(N < 3)
		out.println(-1);
	    else
	    {
		Arrays.sort(side);
		for(int i=2;i<N;i++)
		{
		    long low1 = side[i-1];
		    long low2 = side[i-2];

		    if(low1 + low2 > side[i])  //Checking for Triangle Inequality
		    {
			long peri = low1 + low2 + side[i];
			if(peri > max_perimeter)
			    assign(low2, low1, side[i], maxSide);
			
			else if(peri == max_perimeter){
			    if(side[i] > maxSide[2])
				assign(low2, low1, side[i], maxSide);
			    else if(side[i] == maxSide[2] && low1 > maxSide[1])
				assign(low2, low1, side[i], maxSide);
			}
		    }
		}

		if(maxSide[0] == 0)
		    out.print(-1);
		else
		    for(long l:maxSide)
			out.print(l + " ");

		out.println();

	    }
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