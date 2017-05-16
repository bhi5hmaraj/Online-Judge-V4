import java.util.*;
import java.io.*;
public class SiruseriTrafficLights
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
    }

    private static boolean marked[];
    private static int distTo[];    
    private static ArrayList<Edge>[] adj;
    private static int period[];

    private static void dijkstra(int start, int dest)
    {
	PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
	pq.add(new Edge(start, 0));
	while(true)
	{
	    Edge curr = pq.remove();
	    if(!marked[curr.v])
	    {
		marked[curr.v] = true;
		distTo[curr.v] = curr.cost;
		if(curr.v == dest)
		    return;
		for(Edge adjacent:adj[curr.v])
		{
		    if(!marked[adjacent.v])
		    {
			int dist = distTo[curr.v] + adjacent.cost;
			int quotient = dist / period[adjacent.v];
			if(adjacent.v == dest)
			{
			    pq.add(new Edge(adjacent.v, dist));
			}
			else
			{
			    if(dist % period[adjacent.v] != 0)
				pq.add(new Edge(adjacent.v, (quotient + 1) * period[adjacent.v]));
			    else
				pq.add(new Edge(adjacent.v, dist));
			}
		    }
		}
	    }
	}
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int start = s1.nextInt();
	int dest = s1.nextInt();
	int V = s1.nextInt();
	int E = s1.nextInt();
	adj = (ArrayList<Edge>[])new ArrayList[V+1];
	for(int i=1;i<=V;i++)adj[i] = new ArrayList<Edge>();
	marked = new boolean[V+1];
	distTo = new int[V+1];
	period = s1.nextIntArrayOneBased(V);
	while(E-->0)
	{
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    int cost  = s1.nextInt();
	    adj[u].add(new Edge(v, cost));
	    adj[v].add(new Edge(u, cost));
	}
	dijkstra(start, dest);
	out.println(distTo[dest]);
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