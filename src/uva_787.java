import java.util.*;
import java.io.*;
import java.math.*;
public class uva_787
{


    /************************ SOLUTION STARTS HERE ***********************/


    static BigInteger getMaxProdInRange(BigInteger arr[],int start,int end)
    {
	int len = end - start + 1; 
	BigInteger max = BigInteger.valueOf(Integer.MIN_VALUE);
	if(len == 0)
	    return max;
	for(int i=1;i<=len;i++)
	{
	    BigInteger prod = BigInteger.valueOf(1);
	    for(int j=start;j< start + i;j++)
		prod = prod.multiply(arr[j]);

	    max = max.max(prod);
	    for(int j=start + i;j <= end;j++){
		prod = prod.multiply(arr[j]);
		prod = prod.divide(arr[j - i]);
		max = max.max(prod);
	    }
	}
	return max;
    }

    private static void solve(FastScanner s1, PrintWriter out){


	String line = null;
	while((line = s1.nextLine()) != null)
	{

	    String split[] = line.split(" ");
	    BigInteger arr[] = new BigInteger[split.length-1];
	    ArrayList<Integer> arl = new ArrayList<>();
	    arl.add(-1);
	    for(int i=0;i<arr.length;i++){
		arr[i] = new BigInteger(split[i]);
		if(split[i].equals("0"))
		    arl.add(i);
	    }
	    arl.add(arr.length);
	    BigInteger max = BigInteger.valueOf(Integer.MIN_VALUE);
	    
	    for(int i=1;i<arl.size();i++)
		max = max.max(getMaxProdInRange(arr, arl.get(i-1) + 1, arl.get(i) - 1));

	    out.println(arl.size() > 2 ? max.max(BigInteger.ZERO) : max);

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