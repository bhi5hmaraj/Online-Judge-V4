import java.util.*;
import java.io.*;
public class uva_11790 
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	for(int z=1, t = s1.nextInt(); z<=t;z++)
	{
	    
	    int N = s1.nextInt();
	    int ht[] = s1.nextIntArray(N);
	    int wd[] = s1.nextIntArray(N);
	    int LIS[] = new int[N];
	    int LDS[] = new int[N];
	    
	    for(int i=0;i<N;i++)
	    {
		for(int j=0;j<i;j++)
		{
		    LIS[i] = ht[j] < ht[i] && LIS[j] > LIS[i] ? LIS[j] : LIS[i];
		    LDS[i] = ht[j] > ht[i] && LDS[j] > LDS[i] ? LDS[j] : LDS[i];
		}
		LIS[i] += wd[i];
		LDS[i] += wd[i];
	    }
	    
	    int maxLIS = Arrays.stream(LIS).max().getAsInt();
	    int maxLDS = Arrays.stream(LDS).max().getAsInt();
	    
	    if(maxLIS >= maxLDS)
		out.println("Case "+z+". Increasing ("+maxLIS+"). Decreasing ("+maxLDS+").");
	    else
		out.println("Case "+z+". Decreasing ("+maxLDS+"). Increasing ("+maxLIS+").");
	    
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