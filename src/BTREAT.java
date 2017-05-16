import java.util.*;
import java.io.*;
class BTREAT
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){	
	int t = s1.nextInt();
	while(t-->0)
	{
	    long sum = 0;
	    int N = s1.nextInt();
	    long arr[] = s1.nextLongArray(N);
	    for(long a:arr)
		sum += a;
	    if(sum % N != 0)
		out.println("No Treat");
	    else
	    {
		long avg =(sum / N);
		Arrays.parallelSort(arr);
		int lo = 0, hi = N - 1;
		long cnt = 0;
		while(lo < hi && arr[lo]!=avg)
		{
		    long surplus = arr[hi--] - avg;
		    cnt += surplus;
		    while(surplus > 0)
		    {
			if(surplus >= avg-arr[lo])
			{
			    surplus -= (avg-arr[lo]); 
			    arr[lo++] = avg;
			}
			else
			{
			    arr[lo] += surplus;
			    surplus = 0;
			}
		    }
		}
		out.println(cnt);
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