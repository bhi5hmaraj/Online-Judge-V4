import java.util.*;
import java.io.*;
public class uva_1237
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	final int MAX = 1000000 + 10;
	while(t-->0)
	{
	    int D = s1.nextInt();
	    String company[] = new String[D];
	    int range[][] = new int[D][2];
	    int cnt[] = new int[MAX];
	    for(int i=0;i<D;i++)
	    {
		company[i] = s1.next();
		int L = s1.nextInt();
		int R = s1.nextInt();
		cnt[L]++;
		cnt[R+1]--;
		range[i][0] = L; range[i][1] = R;
	    }
	    for(int i=1;i<MAX;i++)
		cnt[i] += cnt[i-1];
	    
	    int Q = s1.nextInt();
	    while(Q-->0)
	    {
		int P = s1.nextInt();
		if(cnt[P] != 1)
		    out.println("UNDETERMINED");
		else
		{
		    for(int i=0;i<D;i++)
		    {
			if(P >= range[i][0] && P <= range[i][1])
			{
			    out.println(company[i]);
			    break;
			}
		    }
		}
	    }
	    if(t != 0)
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