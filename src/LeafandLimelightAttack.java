import java.util.*;
import java.io.*;
class LeafandLimelightAttack
{


    /************************ SOLUTION STARTS HERE ***********************/

    static final long mod = (long)(1e9) + 9;
    static long add(long a,long b){
	return ((a % mod) + (b % mod)) % mod;
    }
    static long sub(long a, long b) {
	long sub = ((a % mod) - (b % mod)) % mod;
	return sub < 0 ? mod + sub : sub;
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int MAX = (int)(1e7 + 5);

	long dp1[] = new long[MAX];
	long dp2[] = new long[MAX];
	dp1[1] = dp2[1] = 1;
	dp1[2] = 6; dp2[2] = 4;

	for(long i=3;i<MAX;i++){
	    dp1[(int)i] = add(dp1[(int)(i-2)], ((2L * i * i) - (2L * (i - 1L)) ));
	    dp2[(int)i] = add(dp2[(int)(i-1)], (i * i) - (i - 1L));
	}

	int t = s1.nextInt();
	while(t-->0)
	{
	    int n = s1.nextInt();
	    long ans = add(dp1[n], dp2[n]);
	    out.println(n % 2 == 0 ? ans : sub(ans, 1));
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