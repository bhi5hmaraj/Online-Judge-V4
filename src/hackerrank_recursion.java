import java.util.*;
import java.io.*;
public class hackerrank_recursion
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    
    private static int findMax(long arr[],long sum)   //Complexity O(n * log(n))
    {
	if(arr.length < 2)
	    return 0;
	else
	{
	    long left[] , right[];
	    int len = arr.length;
	    long curr_sum = 0;
	    int pos = -1;
	    for(int i=0;i<len;i++)
	    {
		curr_sum += arr[i];
		if (sum - curr_sum == curr_sum)
		{
		    pos = i;
		    break;
		}
	    }
	    left = new long[pos+1];
	    System.arraycopy(arr, 0, left, 0, pos+1);
	    right = new long[len-pos-1];
	    System.arraycopy(arr, pos+1, right, 0, len-pos-1);
	    
	    if(pos == -1)
		return 0;
	    else
		return 1 + Math.max(findMax(left, curr_sum),findMax(right, curr_sum));
	}
    }
    
    
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int len = s1.nextInt();
	    long arr[] = s1.nextLongArray(len);
	    long sum = 0;
	    for(long l:arr)
		sum += l;
	    
	    out.println(findMax(arr, sum));
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