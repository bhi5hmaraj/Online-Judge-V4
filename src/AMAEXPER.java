import java.util.*;
import java.io.*;
class AMAEXPER
{


    /************************ SOLUTION STARTS HERE ***********************/
    static class Edge implements Comparable<Edge>
    {
	int v,cost;
	Edge(int v,int cost)
	{
	    this.v = v;
	    this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
	    return this.cost - o.cost;
	}
	@Override
	public String toString() {
	    return "[v = "+v+" wt = "+cost+"]";
	}
    }
    private static int V;
    static class Graph
    {
	int root;
	ArrayList<Edge>[] adj;
	@SuppressWarnings("unchecked")
	Graph(int root)
	{
	    this.root = root;
	    adj = (ArrayList<Edge>[])new ArrayList[V+1];
	    adj[root] = new ArrayList<>();
	}
	void merge(Graph that, int wt)
	{
	    for(int i=1;i<=V;i++)
		if(that.adj[i] != null)
		    this.adj[i] = new ArrayList<>(that.adj[i]);

	    this.adj[this.root].add(new Edge(that.root, wt));
	    this.adj[that.root].add(new Edge(this.root, wt));
	}
	@Override
	public String toString() {
	    StringBuilder sb = new StringBuilder();
	    for(int i=1;i<=V;i++)
		sb.append(i + " ==> " + adj[i] + "\n");

	    return sb.toString();
	}
    }
    private static Graph[] gArr;
    private static ArrayList<Edge>[] orgGraph;
    private static boolean marked[];
    private static Graph constructGraphArr(int root)
    {
	gArr[root] = new Graph(root);
	marked[root] = true;
	for(Edge e:orgGraph[root])
	    if(!marked[e.v])
		gArr[root].merge(constructGraphArr(e.v), e.cost);

	return gArr[root];
    }
    private static int modifiedBFS(Graph g, int start)
    {
	boolean marked[] = new boolean[V+1];
	int distTo[] = new int[V+1];
	ArrayDeque<Integer> queue = new ArrayDeque<>();
	int max = 0;
	queue.add(start);
	while(!queue.isEmpty())
	{
	    int u = queue.remove();
	    marked[u] = true;
	    for(Edge e:g.adj[u])
		if(!marked[e.v])
		{
		    distTo[e.v] = distTo[u] + e.cost;
		    max = Math.max(max,distTo[e.v]);
		    queue.add(e.v);
		}
	}
	return max;
    }
    @SuppressWarnings("unchecked")
    private static void bruteSolve(PrintWriter out, int E[][]){

	gArr = new Graph[V+1];
	orgGraph = (ArrayList<Edge>[])new ArrayList[V+1];
	marked = new boolean[V+1];
	for(int i=1;i<=V;i++)
	    orgGraph[i] = new ArrayList<>();
	for(int i=0;i<V-1;i++)
	{
	    orgGraph[E[i][0]].add(new Edge(E[i][1], E[i][2]));
	    orgGraph[E[i][1]].add(new Edge(E[i][0], E[i][2]));
	}
	constructGraphArr(1);

	int stratCost[] = new int[V+1];
	Arrays.fill(stratCost, Integer.MAX_VALUE);
	for(int i=1;i<=V;i++)
	{
	    Graph curr = gArr[i];
	    for(int j=1;j<=V;j++)
		if(curr.adj[j] != null)
		    stratCost[i] = Math.min(stratCost[i],modifiedBFS(curr, j));
	}
	for(int i=1;i<=V;i++)
	    out.println(stratCost[i]);
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int t = s1.nextInt();
	while(t-->0)
	{
	    V = s1.nextInt();

	    int cost[] = new int[V+1];
	    int E = V-1;
	    int Ed[][] = new int[E][3];
	    for(int i=0;i<E;i++)
		Ed[i] = s1.nextIntArray(3);

	    boolean firstTask = true;

	    for(int i=0;i<E;i++)
	    {
		int u = Ed[i][0];
		int v = Ed[i][1];

		if(u != 1 && v != 1)
		{
		    firstTask = false;
		    break;
		}
		else
		{
		    if(u != 1)
			cost[u] = Ed[i][2];
		    else
			cost[v] = Ed[i][2];
		}
	    }
	    if(firstTask)
	    {
		int max = 0;
		for(int i=2;i<=V;i++)
		    max = Math.max(max,cost[i]);

		out.println(max);
		for(int i=2;i<=V;i++)	
		    out.println(0);
	    }
	    else
	    {
		bruteSolve(out, Ed);    
	    }
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