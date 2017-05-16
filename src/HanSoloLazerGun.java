import java.util.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.io.*;
public class HanSoloLazerGun
{

    
    /************************ SOLUTION STARTS HERE ***********************/
    
    private static double slopeTo(Point2D.Double p1, Point2D.Double p2)
    {
	if(p1.getY() == p2.getY())
	    return 0.0;
	else if(p1.getX() == p2.getX())
	    return Double.POSITIVE_INFINITY;
	else
	    return (p2.getY()-p1.getY())/(p2.getX()-p1.getX());
    }
    
    private static void solve(FastScanner s1, PrintWriter out){
	HashSet<Double> set = new HashSet<>();
	int n = s1.nextInt();
	Point2D.Double solo = new Point2D.Double(s1.nextInt(), s1.nextInt());
	while(n-->0)
	    set.add(slopeTo(solo, new Point2D.Double(s1.nextInt(), s1.nextInt())));
	
	out.print(set.size());
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