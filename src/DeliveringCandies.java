import java.util.*;
import java.io.*;
public class DeliveringCandies
{

    
    /************************ SOLUTION STARTS HERE ***********************/

    private static boolean marked[];
    private static long distTo[];
    private static ArrayList<Edge>[] adj;

    static class Edge implements Comparable<Edge>
    {
	int v;
	long cost;
	Edge(int v,long cost)
	{
	    this.v = v;
	    this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
	    return Long.compare(this.cost , o.cost);
	}
    }

    private static void dijkstra(int start)
    {
	PriorityQueue<Edge> pq = new PriorityQueue<>(); 
	pq.add(new Edge(start,0));
	while(!pq.isEmpty())
	{
	    Edge min = pq.remove();
	    int u = min.v;
	    if(!marked[u])
	    {
		marked[u] = true;
		distTo[u] = min.cost;
		for(Edge e:adj[u])
		{
		    if(!marked[e.v])
		    {
			pq.add(new Edge(e.v, e.cost + distTo[u]));
		    }
		}
	    }
	}
    }


    
    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();
	int E = s1.nextInt();
	marked = new boolean[V+1];
	distTo = new long[V+1];
	adj = (ArrayList<Edge>[]) new ArrayList[V+1];
	for(int i=1;i<=V;i++)adj[i] = new ArrayList<>();
	Arrays.fill(distTo,-1);
	int start = s1.nextInt();
	long K[] = s1.nextLongArrayOneBased(V);
	while(E-->0)
	{
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    long cost = s1.nextLong();
	    adj[u].add(new Edge(v, cost));
	    adj[v].add(new Edge(u, cost));
	}
	dijkstra(start);
	long total[] = new long[V+1];
	
	long kSum = 0;
	long kdSum = 0;
	for(int i=1;i<=V;i++)
	{
	    if(distTo[i] != -1)
	    {
		kSum  += K[i];
		kdSum += (distTo[i] * K[i]);
	    }
	}
	for(int i=1;i<=V;i++)
	{
	    if(distTo[i] == -1)
		total[i] = 0;
	    else
	    {
		/*     //Slow approach
		for(int j=1;j<=V;j++)
		    if(j != i && distTo[j] != -1)
			total[i] += ((distTo[i] + distTo[j])*K[j]);
		*/
		total[i] = (distTo[i] * (kSum-K[i])) + (kdSum-(distTo[i]*K[i]));
	    }
	}
	
	for(int i=1;i<=V;i++)
	    out.print(total[i] + " ");
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