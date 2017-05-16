import java.util.*;
import java.io.*;
public class uva_10341
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    
    private static double eval(double coeff[],double x){
	
	return (coeff[0] * Math.exp(-x)) + (coeff[1] * Math.sin(x)) + (coeff[2] * Math.cos(x)) 
			+ (coeff[3] * Math.tan(x)) + (coeff[4] * x * x) + coeff[5];
	
    }
    
    private static final double epsilon = 1e-9;
    
    private static void solve(FastScanner s1, PrintWriter out){

	String line = null;
	while( (line = s1.nextLine()) != null)
	{
	    String split[] = line.split(" ");
	    double coeff[] = new double[6];
	    for(int i=0;i<6;i++)
		coeff[i] = Double.parseDouble(split[i]);
	    
	    if(eval(coeff, 0.0) * eval(coeff, 1.0) > 0)
		out.println("No solution");
	    else
	    {
		double lo  = 0.0;
		double hi  = 1.0;
		double mid = 0.5;
		while(lo + epsilon <= hi)
		{
		    mid = (lo + hi) / 2.0;		    
		    if(eval(coeff, mid) * eval(coeff, hi) <= 0)
			lo = mid;
		    else
			hi = mid;
		}
		mid = (lo + hi)/2.0;
		out.printf("%.4f\n", mid);
	    }
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