import java.util.*;
import java.io.*;
public class PrintCheck
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair
    {
	int qID,color;
	Pair(int q,int i)
	{
	    qID = q;
	    color = i;
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int m = s1.nextInt();
	Pair row[] = new Pair[n+1];
	Pair col[] = new Pair[m+1];
	for(int i=1;i<=n;i++)row[i] = new Pair(0, 0);
	for(int i=1;i<=m;i++)col[i] = new Pair(0, 0);
	int Q = s1.nextInt();
	
	for(int q=1;q<=Q;q++)
	{
	    int ch = s1.nextInt();
	    int index = s1.nextInt();
	    int color = s1.nextInt();
	    switch(ch)
	    {
	    	case 1:
	    	    row[index].qID = q;
	    	    row[index].color = color;
	    	    break;
	    	case 2:
	    	    col[index].qID = q;
	    	    col[index].color = color;
	    	    break;
	    }
	}
	
	for(int i=1;i<=n;i++)
	{
	    for(int j=1;j<=m;j++)
	    {
		out.print(((row[i].qID > col[j].qID) ? row[i].color : col[j].color) + " ");
	    }
	    out.println();
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