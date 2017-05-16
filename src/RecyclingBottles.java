import java.util.*;
import java.awt.geom.Point2D;
import java.io.*;
public class RecyclingBottles
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    
    private static void solve(FastScanner s1, PrintWriter out){

	Point2D.Double A = new Point2D.Double(s1.nextInt(), s1.nextInt());
	Point2D.Double B = new Point2D.Double(s1.nextInt(), s1.nextInt());
	Point2D.Double recycle = new Point2D.Double(s1.nextInt(), s1.nextInt());
	int N = s1.nextInt();
	ArrayList<Point2D.Double> arl = new ArrayList<>(N);
	for(int i=1;i<=N;i++)
	    arl.add(new Point2D.Double(s1.nextInt(), s1.nextInt()));
	
	int index = -1;

	double min = Double.MAX_VALUE;
	for(int i=0,len = arl.size();i<len;i++){
	    double dist = A.distance(arl.get(i)) + arl.get(i).distance(recycle);
	    if(dist < min){
		min = dist;
		index = i;
	    }
	}
	
	arl.remove(index);
	double totalDist = min;
	System.out.println(totalDist);
	index = -1;
	min = Double.MAX_VALUE;
	for(int i=0,len=arl.size();i<len;i++){
	    double dist = B.distance(arl.get(i)) + arl.get(i).distance(recycle);
	    if(dist < min){
		min = dist;
		index = i;
	    }
	}
	
	if(index != -1){
	    System.out.println(arl);
	    System.out.println(index);
	    arl.remove(index);
	    System.out.println(min);
	    totalDist += min;
	}
	
	for(Point2D.Double p : arl)
	    totalDist += (2.0 * recycle.distance(p));
	
	out.printf("%.9f", totalDist);
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