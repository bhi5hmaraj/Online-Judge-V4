import java.util.*;
import java.awt.Point;
import java.io.*;
public class Watchmen
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    private static <Key> void frequency(Map<Key ,Integer> mp , Key k)
    {
    	//Finds frequency of of each element of generic type Key
    	Integer query = mp.get(k);
    	if(query == null)
    		mp.put(k, new Integer(1));
    	else	    	
    		mp.put(k, query + 1);	    	
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	HashMap<Integer,Integer> X = new HashMap<>();
	HashMap<Integer,Integer> Y = new HashMap<>();
	HashMap<Point,Integer> collide = new HashMap<>();
	int n = s1.nextInt();
	while(n-->0)
	{
	    int x = s1.nextInt();
	    int y = s1.nextInt();
	    frequency(collide, new Point(x, y));
	    frequency(X, x);
	    frequency(Y, y);
	}
	long cnt = 0;
	for (Map.Entry<Integer,Integer> e : X.entrySet()) 
	{
	    long freq = e.getValue().longValue();
	    cnt += (freq > 1)?((freq*(freq-1))/2):0;
	}
	for (Map.Entry<Integer,Integer> e : Y.entrySet()) 
	{
	    long freq = e.getValue().longValue();
	    cnt += (freq > 1)?((freq*(freq-1))/2):0;
	}
	for (Map.Entry<Point,Integer> e : collide.entrySet()) 
	{
	    long freq = e.getValue().longValue();
	    cnt -= (freq > 1)?((freq*(freq-1))/2):0;
	}
	out.println(cnt);
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