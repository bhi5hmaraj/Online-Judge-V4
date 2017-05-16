import java.util.*;
import java.io.*;
public class ParliamentofBerland
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int r = s1.nextInt();
	int c = s1.nextInt();
	if(r * c < n)
	    out.print(-1);
	else
	{
	    if(c % 2 == 1)
	    {
		for(int i=0;i<r;i++)
		{
		    for(int j=1;j<=c;j++)
		    {
			int curr = (i*c) + j;
			if(curr > n)
			    out.print("0 ");
			else
			    out.print(curr + " ");
		    }
		    out.println();
		}
	    }
	    else
	    {
		int even[] = new int[n + 1];
		int odd[] = new int[n + 1];
		int p1 = 0,p2 = 0;
		for(int i=1;i<=n;i++)
		{
		    if(i%2==0)
			even[p1++] = i;
		    else
			odd[p2++] = i;
		}
		p1 = 0;p2 = 0;
		for(int i=0;i<r;i++)
		{
		    for(int j=1;j<=c/2;j++)
		    {
			int curr = (i*c) + j;
			if(curr > n)
			    out.print("0 0 ");
			else
			{
			    if(i%2==0)
				out.print(odd[p1++] + " " + even[p2++] + " ");
			    else
				out.print(even[p2++] + " " + odd[p1++] + " ");
			}
		    }
		    out.println();
		}
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