import java.util.*;
import java.io.*;
class MOD
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve1(FastScanner s1, PrintWriter out){ // Brute force solution

	int len = s1.nextInt();
	long arr[] = s1.nextLongArray(len);
	long max = Long.MIN_VALUE;
	for(int i=0;i<len;i++)
	    for(int j=0;j<len;j++)
		max = Math.max(max,arr[i] % arr[j]);
	out.print(max);
    }
    
    private static void solve2(FastScanner s1, PrintWriter out){ // Fast solution (The second biggest number is the max mod)

	int len = s1.nextInt();
	int arr[] = s1.nextIntArray(len);
	int max1 = -1, max2 = -1;
	for (int a : arr)
	    max1 = Math.max(max1, a);
	for (int a : arr)
	    max2 = (a != max1) ? Math.max(max2, a) : max2;
	out.print(max2 == -1 ? 0 : max2);
    }
    

    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve2(in, out);
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