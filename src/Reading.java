import java.util.*;
import java.io.*;
public class Reading
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair implements Comparable<Pair>{
	
	int index,key;
	Pair(int k,int i){
	    index = i;
	    key = k;
	}
	@Override
	public int compareTo(Pair o) {
	    return Integer.compare(o.key, key);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt() , k = s1.nextInt();
	Pair arr[] = new Pair[n];
	for(int i=0;i<n;i++)
	    arr[i] = new Pair(s1.nextInt(), i + 1);
	
	Arrays.sort(arr);
	
	out.println(arr[k-1].key);
	for(int i=0;i<k;i++)
	    out.print(arr[i].index + " ");
    }


    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter("output.txt"); 
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