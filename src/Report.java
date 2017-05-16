import java.util.*;
import java.io.*;
public class Report
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Pair
    {
	int index,time;
	boolean isAsec;
	Pair(int i,int t,boolean asec)
	{
	    index = i;
	    time = t;
	    isAsec = asec;
	}
	@Override
	public String toString() {
	    return "[i= "+index+" t= "+time+" asec= "+isAsec+"]";
	}
    }
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
	    else
		throw new NullPointerException("The specified key does not exist");

	    return (Integer)key;
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int len = s1.nextInt();
	int Q = s1.nextInt();
	Pair arr[] = new Pair[len + 1];
	int elem[] = s1.nextIntArrayOneBased(len);
	arr[0] = new Pair(0, Integer.MAX_VALUE, true);   //base case
	for (int q = 1; q <= Q; q++) 
	{
	    int choice = s1.nextInt();
	    int pos = s1.nextInt();
	    if (choice == 1)
		arr[pos] = new Pair(pos, q, true);
	    else
		arr[pos] = new Pair(pos, q, false);
	}

	ArrayList<Pair> arl = new ArrayList<>();
	int curr = 0;
	for (int i = len; i >= 0; i--)
	    if (arr[i] != null && arr[i].time > curr) 
	    {
		curr = arr[i].time;
		arl.add(arr[i]);
	    }

	MultiSet<Integer> mp = new MultiSet<>();
	for (int i = 1, end = arl.get(0).index; i <= end; i++)
	    mp.add(elem[i]);

	for (int i = 0, end = arl.size(); i < end - 1; i++) 
	{
	    for (int start = arl.get(i).index, stop = arl.get(i + 1).index; start > stop; start--) 
	    {
		if (arl.get(i).isAsec)
		    elem[start] = mp.remove(mp.lastKey());   //I made it return the argument itself ;)
		else
		    elem[start] = mp.remove(mp.firstKey());
	    }
	}

	for (int i = 1; i <= len; i++)
	    out.print(elem[i] + " ");
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