import java.util.*;
import java.io.*;
public class uva_562
{


    /************************ SOLUTION STARTS HERE ***********************/
    
    static boolean cache[][];
    static void possibilities(int idx , int arr[] , int sum){

	if(!cache[idx][sum]){	
	    if(sum != 0)
		cache[idx][sum] = true;
	    if(idx < arr.length){
		possibilities(idx + 1, arr, sum);
		possibilities(idx + 1, arr, sum + arr[idx]);
	    }
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	
	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();
	    if(N == 0){
		out.println(0);
		continue;
	    }
	    int arr[] = s1.nextIntArray(N);
	    cache = new boolean[N + 1][(int)(1e5)];
	    
	    int total = 0;
	    for(int a:arr)
		total += a;
	    
	    possibilities(0, arr, 0);
	    int min = Integer.MAX_VALUE;
	    boolean poss[] = cache[N];
	    for(int i = 1;i <= total;i++)
		if(poss[i])
		    min = Math.min(min,Math.abs(total - (2 * i)));
	    
	    out.println(min);
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