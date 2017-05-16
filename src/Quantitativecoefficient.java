import java.util.*;
import java.io.*;
class Quantitativecoefficient
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    static class Edge implements Comparable<Edge>
    {
	int v;
	long cost;
	Edge(int v, long cost)
	{
	    this.v = v;
	    this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
	    return Long.compare(this.cost, o.cost);
	}
    }
    
    private static ArrayList<Edge>[] adj;
    private static int V;    
    private static long mod = (long)1e9 + 7L; 
    
    private static long PrimMST()
    {
	PriorityQueue<Edge> pq = new PriorityQueue<>();
	boolean marked[] = new boolean[V+1];
	long coeff = 1;
	pq.add(new Edge(1, 1));
	while(!pq.isEmpty())
	{
	    Edge curr = pq.remove();
	    if(!marked[curr.v])
	    {
		marked[curr.v] = true;
		coeff = (coeff * curr.cost) % mod;
		for(Edge e:adj[curr.v])
		    if(!marked[e.v])
			pq.add(e);
	    }
	}
	return coeff;
    }
    
    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    V = s1.nextInt();
	    adj = (ArrayList<Edge>[]) new ArrayList[V+1];
	    for(int i=1;i<=V;i++)
		adj[i] = new ArrayList<>();
	    int E = s1.nextInt();	  
	    while(E-->0)
	    {
		int u = s1.nextInt();
		int v = s1.nextInt();
		long wt = s1.nextInt();
		adj[u].add(new Edge(v, wt));
		adj[v].add(new Edge(u, wt));
	    }
	    out.println(PrimMST());
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