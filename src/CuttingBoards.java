import java.util.*;
import java.io.*;
class CuttingBoards
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    
    private static void reverse(long arr[])
    {
	for (int i = 0; i < arr.length / 2; i++)
	{
	    long temp = arr[i];
	    arr[i] = arr[arr.length - i - 1];
	    arr[arr.length - i - 1] = temp;
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){
	final long mod = (long)1e9 + 7;
	int t = s1.nextInt();
	while(t-->0)
	{
	    int m = s1.nextInt();
	    int n = s1.nextInt();
	    long cy[] = s1.nextLongArray(m - 1);
	    long cx[] = s1.nextLongArray(n - 1);
	    Arrays.sort(cy);
	    Arrays.sort(cx);
	    reverse(cy);
	    reverse(cx);
	    long cost = 0;
	    int cntX = 0, cntY = 0;
	    for (int i = 1; i <= m + n - 2; i++)
	    {
		long max;
		if (cntY < m - 1 && cntX < n - 1)
		    max = Math.max(cy[cntY], cx[cntX]);
		else if (cntY == m - 1)
		    max = cx[cntX];
		else
		    max = cy[cntY];
		
		
		if (cntY < m - 1 && max == cy[cntY])
		    cost += ((cy[cntY++] * (cntX + 1))) % mod;
		else
		    cost += ((cx[cntX++] * (cntY + 1))) % mod;
	    }
	    out.println(cost % mod); //Final mod is very very important
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