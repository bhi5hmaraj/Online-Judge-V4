import java.util.*;
import java.io.*;
import java.math.BigInteger;
class BearandAllPermutations
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static long gcd(long big, long small) {
	long b = Math.max(big, small);
	long s = Math.min(big, small);
	if (s == 0)
	    return b;
	else
	    return gcd(s, b % s);
    }
    
    private static int n;
    private static BigInteger ans[], sum;
    private static void calcSum(String nums, int arr[], int index)
    {
	if(index <= n)
	{
	    for(int i=0,len=nums.length();i<len;i++)
	    {
		arr[index] = Character.getNumericValue(nums.charAt(i));
		calcSum(nums.substring(0, i) + nums.substring(i+1), arr, index+1);
	    }
	}
	else
	{
	    BigInteger prd = BigInteger.ONE;
	    for(int i=1;i<=n;i++)
		for(int j=i+1;j<=n;j++)
		{
		    BigInteger gcd = BigInteger.valueOf(gcd(j - i, Math.abs(arr[j] - arr[i])));
		    //System.out.println("j - i = " + (j - i) + " p[j] "+arr[j]+" p[i] "+arr[i]+" gcd "+gcd);
		    prd = prd.multiply(gcd);
		}
	    sum = sum.add(prd);
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){
	int MAX = 8;
	ans = new BigInteger[MAX + 1];
	for (n = 1; n <= MAX; n++) {
	    StringBuilder sb = new StringBuilder();
	    for (int j = 1; j <= n; j++)
		sb.append(j);
	    sum = BigInteger.valueOf(0);
	    int[] arr = new int[n + 1];
	    calcSum(sb.toString(), arr, 1);
	    ans[n] = sum;
	}
	
	int t = s1.nextInt();
	while (t-- > 0)
	    out.println(ans[s1.nextInt()].mod(BigInteger.valueOf(s1.nextLong())));
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