/**
 * Sorting Benchmark (N = 10^5)
 * Arrays.stream(price).parallel().sorted().toArray(); ===> 0.56s
 * Arrays.stream(price).sorted().toArray(); 	       ===> 0.56s
 * Arrays.parallelSort(price);                         ===> 0.39s
 * Arrays.sort(price);				       ===> 0.36s
 * Arrays.sort(price); (shuffle)		       ===> 0.41s
 * 
 */

import java.util.*;
import java.io.*;
public class MarkandToys
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int K = s1.nextInt();
	int price[] = s1.nextIntArray(N);
	Arrays.sort(price);
	int total = 0 , cnt = 0;
	while(total + price[cnt] <= K)
	    total += price[cnt++];
	out.print(cnt);
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