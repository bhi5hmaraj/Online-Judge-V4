import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.*;
public class NumberofParallelograms
{


    /************************ SOLUTION STARTS HERE ***********************/


    private static <Key> void frequency(Map<Key ,Integer> mp , Key k)
    {
	//Finds frequency of of each element of generic type Key
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);	  	
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	Point arr[] = new Point[n];
	for(int i=0;i<n;i++)
	    arr[i] = new Point(s1.nextInt(), s1.nextInt());

	HashMap<Point,Integer> freq = new HashMap<>();
	for(int i=0;i<n;i++)
	    for(int j=i+1;j<n;j++)
		frequency(freq, new Point(arr[i].x+arr[j].x,arr[i].y+arr[j].y));

	long cnt = 0;
	for (Map.Entry<Point,Integer> e : freq.entrySet()) 
	{
	    long f = e.getValue().longValue();
	    cnt += (f * (f-1))/2;
	}
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