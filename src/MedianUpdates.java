import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
class MedianUpdates
{


    /************************ SOLUTION STARTS HERE ***********************/

    @SuppressWarnings("serial")
    static class MultiSet <T> extends TreeMap<T,Integer>
    {
	public void add(T key)
	{
	    Integer q = super.get(key);
	    if(q == null)
		super.put(key, 1);
	    else
		super.put(key, q+1);
	}
	@SuppressWarnings("unchecked")
	@Override
	public Integer remove(Object key) {
	    Integer q = super.get(key);
	    if(q != null)
	    {
		if(q == 1)
		    super.remove(key);
		else
		    super.put((T)key, q-1);
	    }

	    return q;
	}
    }
    private static MultiSet<Long> left = new MultiSet<>();
    private static MultiSet<Long> right = new MultiSet<>();
    private static int l_size = 0 , r_size = 0;    
    private static void    addElem(long n)
    {
	if(l_size == 0)
	{
	    left.add(n);	
	    l_size++;
	}
	else if(r_size == 0)
	{
	    long l_max = left.pollLastEntry().getKey();
	    if(n < l_max)
	    {
		right.add(l_max);
		left.add(n);
	    }
	    else
	    {
		right.add(n);
		left.add(l_max);
	    }
	    r_size++;
	}
	else
	{
	    if(l_size <= r_size)
	    {
		long r_min = right.firstKey();
		right.remove(r_min);
		if(n > r_min)
		{
		    left.add(r_min);
		    right.add(n);
		}
		else
		{
		    left.add(n);
		    right.add(r_min);
		}
		l_size++;
	    }
	    else
	    {
		long l_max = left.lastKey();
		left.remove(l_max);
		if(n < l_max)
		{
		    right.add(l_max);
		    left.add(n);
		}
		else
		{
		    right.add(n);
		    left.add(l_max);
		}
		r_size++;
	    }
	}
    }  
    private static boolean removeElem(long n)
    {
	boolean isLeft = left.containsKey(n);
	boolean isRight = right.containsKey(n);
	if (!(isLeft || isRight))
	    return false;
	else
	{
	    if(isLeft)
	    {
		left.remove(n);
		l_size--;
	    }
	    else
	    {
		right.remove(n);
		r_size--;
	    }

	    if(l_size > r_size && r_size != 0)
	    {
		long l_max = left.lastKey();
		left.remove(l_max);
		l_size--;
		right.add(l_max);
		r_size++;
	    }
	    if(l_size < r_size)
	    {
		long r_min = right.firstKey();
		right.remove(r_min);
		r_size--;
		left.add(r_min);
		l_size++;
	    }

	    return true;
	}
    }
    private static String  computeMedian()
    {
	if(l_size == 0 && r_size == 0)
	    return "Wrong!";
	else
	{
	    if(l_size > r_size)
		return left.lastKey().toString();
	    else if(r_size > l_size)
		return right.firstKey().toString();
	    else
	    {
		long l_max = left.lastKey();
		long r_min = right.firstKey();
		double median = (double)(l_max + r_min)/(double)2;
		DecimalFormat format = new DecimalFormat("0.#");
		return format.format(median);
	    }
	}
    }


    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    char ch = s1.nextChar();
	    long n = s1.nextLong();
	    boolean flag = true;
	    if(ch == 'a')
		addElem(n);
	    else
		flag = removeElem(n);

	    //out.println("left "+left+" right "+right);
	    out.println(flag ? computeMedian() : "Wrong!");
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