import java.util.*;
import java.io.*;
public class EasyLinearRecurrences
{

    
    /************************ SOLUTION STARTS HERE ***********************/

	static int mod = 1000000007;
	private static int mul(int a,int b)
	{
		return ((a%mod)*(b%mod))%mod;
	}
	private static int add(int a,int b)
	{
		return ((a%mod)+(b%mod))%mod;
	}
	static class Matrix
	{
		int e00,e01,e10,e11;
		Matrix(int a,int b,int c,int d)
		{
			e00=a;e01=b;e10=c;e11=d;
		}
		Matrix multiply(Matrix that)
		{
			int a=add(mul(this.e00,that.e00),mul(this.e01,that.e10));
			int b=add(mul(this.e00,that.e01),mul(this.e01,that.e11));
			int c=add(mul(this.e10,that.e00),mul(this.e11,that.e10));
			int d=add(mul(this.e10,that.e01),mul(this.e11,that.e11));
			return new Matrix(a,b,c,d);
		}
		public String toString()
		{
			return e00+" "+e01+"\n"+e10+" "+e11+"\n";
		}
	}
	public static Matrix pow(Matrix m,int b)
	{
		if(b==1)
			return m;
		else
		{
			if((b&1) == 0)
			{
				return pow(m.multiply(m),b/2);
			}
			else
			{
				return m.multiply(pow(m.multiply(m),(b-1)/2));
			}
		}
	}

    
    private static void solve(FastScanner s1, PrintWriter out){

		

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