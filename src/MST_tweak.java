import java.util.*;
import java.io.*;
class MST_tweak   //Very clever way to handle large set of edges !!!
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
	    int i = p;
	    while(parent[p] != p)
		p = parent[p];
	    
	    parent[i] = p;
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
    
    static class Edge implements Comparable<Edge>
    {
	int u,v;
	long cost;
	Edge(int u,int v, long cost)
	{
	    this.u = u;
	    this.v = v;
	    this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
	    return Long.compare(this.cost, o.cost);
	}
    }
    
    private static ArrayList<Edge> global;
    private static int V;
    private static void KruskalMST()
    {
	Collections.sort(global);
	ArrayList<Edge> temp = new ArrayList<>();
	total = 0;
	DSU dsu = new DSU(V);
	for(int i=0,len=global.size();i<len;i++)
	{
	    int  u  = global.get(i).u;
	    int  v  = global.get(i).v;
	    long wt = global.get(i).cost;
	    if(!dsu.connected(u, v))
	    {
		dsu.union(u,v);
		total += wt;
		temp.add(global.get(i));
	    }	    
	}
	global = temp;
    }
    private static long X,A,C,MOD,total;
    private static int getNext() {
	X = ((X * A) % MOD + (C % MOD)) % MOD;
	return (int)X;
    }
    private static void solve(FastScanner s1, PrintWriter out){
 
	V = s1.nextInt();
	int E = s1.nextInt();
	X = s1.nextLong();
	A = s1.nextLong();
	C = s1.nextLong();
	MOD = s1.nextLong();
	global = new ArrayList<>(5*V);   // 5*V is the buffer capacity of the arraylist
	for(int i=0;i<E;i++)
	{
	    int u = getNext()%V;
	    int v = getNext()%V;
	    long cost = getNext();
	    Edge curr = new Edge(u,v,cost);
	    if(global.size() > 5*V)	// Once it overflows we need to run the mst() to weed out the unecessary edges 
		 KruskalMST();
	    
	    global.add(curr);
	}
	
	KruskalMST();                  // In case we have some more new edges
	
	out.print(total);
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