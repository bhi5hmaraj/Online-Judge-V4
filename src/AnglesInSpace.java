import java.util.*;
import java.io.*;
public class AnglesInSpace
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class vector
    {
	double x,y,z;
	vector(int x,int y,int z)
	{
	    this.x = x;
	    this.y = y;
	    this.z = z;
	}
	vector()
	{
	    this(0,0,0);
	}
	double magnitude()
	{
	    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	double angleTo(vector that)
	{
	    double cosTheta = ((this.x*that.x)+(this.y*that.y)+(this.z*that.z)) / (this.magnitude() * that.magnitude());
	    return Math.acos(cosTheta);
	}	
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	vector arr[] = new vector[N];
	for(int i=0;i<N;i++)
	    arr[i] = new vector(s1.nextInt(), s1.nextInt(), s1.nextInt());
	
	vector p1 = new vector();
	vector p2 = new vector();
	
	double nume = 0.0;
	double deno = (N * (N-1) * (N-2) * 1.0)/6.0;
	for(int i=0;i<N-2;i++)
	{
	    for(int j=i+1;j<N-1;j++)
	    {
		p1.x = arr[i].x - arr[j].x;
		p1.y = arr[i].y - arr[j].y;
		p1.z = arr[i].z - arr[j].z;
		for(int k=j+1;k<N;k++)
		{
		    p2.x = arr[k].x - arr[j].x;
		    p2.y = arr[k].y - arr[j].y;
		    p2.z = arr[k].z - arr[j].z;
		    nume += p1.angleTo(p2);
		}
	    }
	}
	double expect = nume / deno;
	out.printf("%.12f", expect);
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