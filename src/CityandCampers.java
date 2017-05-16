import java.util.*;
import java.io.*;
public class CityandCampers
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
		throw new NullPointerException("The specified key cannot be removed from the map");

	    return q;
	}
    }

    static class DSU
    {
	private int parent[];
	private int size[];
	private int cnt;
	private int curr_ans;
	private MultiSet<Integer> mSet;
	DSU(int length)
	{
	    this.cnt = length;
	    parent = new int[length + 10];
	    size = new int[length + 10];
	    mSet = new MultiSet<>();
	    for(int i=1;i<=length;i++)mSet.add(1);
	    Arrays.fill(size, 1);
	    for (int i = 0; i < parent.length; i++)
		parent[i] = i;
	}
	int root(int p)
	{
	    while(parent[p] != p)
		p = parent[p];
	    return p;
	}
	int sizeOf(int p)
	{
	    return size[root(p)];
	}
	boolean connected(int u , int v)
	{
	    return root(u) == root(v);
	}
	int components()
	{
	    return cnt;
	}
	int query(int u,int v)
	{
	    if(connected(u, v))
		return curr_ans;
	    else
	    {
		union(u, v);
		if(components() == 1)
		    curr_ans = 0;
		else
		    curr_ans = mSet.lastKey() - mSet.firstKey();

		return curr_ans;
	    }
	}
	void union(int u,int v)
	{
	    if(!connected(u, v))
	    {
		cnt--;
		int rootU = root(u);
		int rootV = root(v);
		mSet.remove(size[rootV]);
		mSet.remove(size[rootU]);
		if(size[rootU] < size[rootV])
		{
		    parent[rootU] = rootV;
		    size[rootV] += size[rootU];
		    mSet.add(size[rootV]);
		}
		else
		{
		    parent[rootV] = rootU;
		    size[rootU] += size[rootV];
		    mSet.add(size[rootU]);
		}
	    }
	}
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int Q = s1.nextInt();
	DSU dsu = new DSU(N);
	while(Q-->0)
	    out.println(dsu.query(s1.nextInt(), s1.nextInt()));

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