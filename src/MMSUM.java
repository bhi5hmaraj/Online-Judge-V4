import java.util.*;
import java.io.*;
class MMSUM
{


    /************************ SOLUTION STARTS HERE ***********************/


    static class MultiSet<T> extends TreeMap<T, Integer> {
	public void add(T key) {
	    Integer q = super.get(key);
	    if (q == null)
		super.put(key, 1);
	    else
		super.put(key, q + 1);
	}

	@Override
	public Integer remove(Object key) {
	    Integer q = super.get(key);
	    if (q != null) {
		if (q == 1)
		    super.remove(key);
		else
		    super.put((T) key, q - 1);
	    } else
		throw new NullPointerException("The specified key does not exist");

	    return q;
	}
    }
    private static long kadane(long arr[]){

	long tempSum = 0;
	long maxSum = Long.MIN_VALUE;
	long maxNeg = Long.MIN_VALUE;
	for(long l : arr){

	    if(l <= 0)
		maxNeg = Math.max(maxNeg,l);

	    tempSum = Math.max(0,l + tempSum);
	    maxSum  = Math.max(maxSum,tempSum);
	}

	return maxSum == 0 ? maxNeg : maxSum;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    int len = s1.nextInt();
	    long arr[] = s1.nextLongArray(len);
	    long prefix[] = new long[len+1];
	    for(int i=1;i<=len;i++)
		prefix[i] += (prefix[i-1] + arr[i-1]);

	    MultiSet<Long> left = new MultiSet<>();
	    left.add(0L);
	    MultiSet<Long> right = new MultiSet<>();
	    for(int i=2;i<=len;i++)
		right.add(prefix[i]);

	    long maxSum = kadane(arr);
	    for(int i=1;i<len;i++){
		maxSum = Math.max(maxSum,right.lastKey() - left.firstKey() - arr[i-1]);
		right.remove(prefix[i+1]);
		left.add(prefix[i]);
	    }
	    out.println(maxSum);
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
    
    static String outputFile = "output_MMSUM_unknown.txt";
    /*
    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(new FileInputStream("input.txt"));
	PrintWriter out = 
		new PrintWriter(outputFile); 
	solve(in, out);
	in.close();
	out.close();
    }     
    */
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