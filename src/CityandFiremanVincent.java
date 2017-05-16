import java.util.*;
import java.io.*;
public class CityandFiremanVincent
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    static class DSU
    {
	private int parent[];
	private int size[];
	private int cnt;
	DSU(int length)
	{
	    this.cnt = length;
	    parent = new int[length + 10];
	    size = new int[length + 10];
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
	void union(int u,int v)
	{
	    if(!connected(u, v))
	    {
		cnt--;
		int rootU = root(u);
		int rootV = root(v);
		if(size[rootU] < size[rootV])
		{
		    parent[rootU] = rootV;
		    size[rootV] += size[rootU];
		}
		else
		{
		    parent[rootV] = rootU;
		    size[rootU] += size[rootV];
		}
	    }
	}
    }
    private static <Key> void frequency(Map<Key ,Integer> mp , Key k)
    {
    	//Finds frequency of of each element of generic type Key
    	Integer query = mp.get(k);
    	if(query == null)
    		mp.put(k, new Integer(1));
    	else	    	
    		mp.put(k, query + 1);	    	
    }
    
    
    private static void solve(FastScanner s1, PrintWriter out){
	
	
	final long mod = 1000000007;
	int N = s1.nextInt();
	int cost[] = s1.nextIntArrayOneBased(N);
	int E = s1.nextInt();
	DSU dsu = new DSU(N);
	while(E-->0)
	    dsu.union(s1.nextInt(), s1.nextInt());
	
	HashMap<Integer,TreeMap<Integer,Integer>> mp = new HashMap<>();
	for(int i=1;i<=N;i++)
	{
	    int root = dsu.root(i);
	    TreeMap<Integer, Integer> freq = mp.get(root);
	    if(freq == null)
		freq = new TreeMap<>();
	    frequency(freq, cost[i]);
	    mp.put(root, freq);
	}
	long ans = 1;
	for (Map.Entry<Integer,TreeMap<Integer,Integer>> e : mp.entrySet()) 	
	    ans = (ans * e.getValue().firstEntry().getValue()) % mod;
	
	out.print(ans);
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