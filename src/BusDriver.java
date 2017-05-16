import java.util.*;
import java.io.*;
public class BusDriver
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    static class vector
    {
	double x,y;
	vector(int x,int y)
	{
	    this.x = x;
	    this.y = y;
	}
	vector()
	{
	    this(0,0);
	}
	double magnitude()
	{
	    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
	}
	double angleTo(vector that)
	{
	    double cosTheta = ((this.x*that.x)+(this.y*that.y)) / (this.magnitude() * that.magnitude());
	    return Math.acos(cosTheta);
	}	
    }
        
    
    private static void solve(FastScanner s1, PrintWriter out){
	
	double epsilon = 1e-6;
	int t = s1.nextInt();
	while(t-->0)
	{
	    int N = s1.nextInt();

	    int points[][] = new int [N][2];
	    for(int i=0;i<N;i++)
		points[i] = s1.nextIntArray(2);
	    
	    vector arr[] = new vector[N];
	    arr[0] = new vector(points[0][0], points[0][1]);
	    for(int i=1;i<N;i++)
		arr[i] = new vector(points[i][0]-points[i-1][0], points[i][1]-points[i-1][1]);
	    int cnt = 0;
	    for(int i=0;i<N-1;i++)
		cnt += (Math.abs(arr[i].angleTo(arr[i+1]) -  (Math.PI/2.0)) <= epsilon) ? 1 : 0;
	    out.println(cnt);
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