import java.util.*;
import java.io.*;
public class uva_507
{


    /************************ SOLUTION STARTS HERE ***********************/



    private static void solve(FastScanner s1, PrintWriter out){

	for(int r = 1, t = s1.nextInt()	 ; r <= t ;r++)
	{
	    int N = s1.nextInt();
	    int arr[] = s1.nextIntArray(N-1);
	    //System.out.println(Arrays.toString(arr));
	    int min = 0;
	    int prefixSum = 0;
	    int max = Integer.MIN_VALUE;
	    int min_idx = 0 , max_idx = 1;
	    int min_pos = 0;
	    int max_size = 0;
	    for(int i = 0;i<arr.length;i++)
	    { 
		prefixSum += arr[i];
		
		if(prefixSum - min >= max){
		    if(prefixSum - min > max){
			min_idx = min_pos;
			max_idx = i + 1;
			int size = (i + 1) - min_pos + 1;
			if(size > max_size)
			{
			    max_size = size;
			    min_idx = min_pos;
			    max_idx = i + 1;
			}
		    }
		    else{
			int size = (i + 1) - min_pos + 1;
			if(size > max_size)
			{
			    max_size = size;
			    min_idx = min_pos;
			    max_idx = i + 1;
			}
		    }
		    max = prefixSum - min;    		    
		}
		
		if(prefixSum < min){
		    min = prefixSum;
		    min_pos = i + 1;
		}
	    }
	    if(max > 0){
		out.println("The nicest part of route "+r+" is between stops "+ (min_idx+1) +" and "+(max_idx+1));
	    }
	    else
		out.println("Route "+r+" has no nice parts");
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