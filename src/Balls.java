import java.util.*;
import java.io.*;
public class Balls
{


    /************************ SOLUTION STARTS HERE ***********************/

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
	    else
		return null;

	    return q;
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int len = s1.nextInt();
	MultiSet<Long> set = new MultiSet<>();
	for(int i=1;i<=len;i++)
	    set.add(s1.nextLong());


	long cnt = 0;
	long i = 1;
	while(!set.isEmpty())
	{
	    Long lower = set.floorKey(i*i);
	    if(lower != null)
	    {
		cnt += ((i*i)-lower);
		set.remove(lower);
	    }
	    else	    
		cnt += (i*i);
	   	    
	    i++;
	}
	out.print(cnt);
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