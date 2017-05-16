import java.util.*;
import java.io.*;
public class uva_11057
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
 	Integer query = mp.get(k);
 	mp.put(k, query == null ? 1 : query + 1);
     }
    private static void solve(FastScanner s1, PrintWriter out){

	String line = null;
	while((line = s1.nextLine())!= null)
	{
	    int n = Integer.parseInt(line);
	    int arr[] = s1.nextIntArray(n);
	    int sum = s1.nextInt();
	    int minDiff = Integer.MAX_VALUE;
	    int i=0,j=0;
	    HashMap<Integer,Integer> freq = new HashMap<>();
	    for(int a:arr)
		frequency(freq, a);
	    for(int a:arr){
		int remain = sum - a;
		if(freq.containsKey(remain)){
		    if((remain == a && freq.get(remain) > 1) || remain != a){
			int diff = Math.abs(remain - a);
			if(diff < minDiff){
			    minDiff = diff;
			    i = Math.min(a,remain);
			    j = Math.max(a,remain);
			}
		    }
		}
	    }
	    out.println("Peter should buy books whose prices are "+i+" and "+j+".");
	    out.println();
	    s1.nextLine();
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