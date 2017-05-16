import java.util.*;
import java.io.*;
public class MinimumPenaltyPath
{


    /************************ SOLUTION STARTS HERE ***********************/

    static class Edge implements Comparable<Edge>
    {
	int v,cost;
	Edge(int v,int cost){
	    this.v = v;
	    this.cost = cost;
	}
	@Override
	public int compareTo(Edge o) {
	    return Integer.compare(cost, o.cost);
	}
	@Override
	public String toString() {
	    return "v = "+v+" c = "+cost;
	}

    }

    private static int[] calcDistance(ArrayList<Edge>[] adj , int start , int V){

	PriorityQueue<Edge> pq = new PriorityQueue<>();
	pq.add(new Edge(start, 0));
	boolean marked[] = new boolean[V + 1];
	int distTo[] = new int [V+1];
	Arrays.fill(distTo, -1);
	while(!pq.isEmpty()){
	    Edge curr = pq.remove();
	    if(!marked[curr.v]){
		marked[curr.v] = true;
		distTo[curr.v] = curr.cost;
		for(Edge adja : adj[curr.v]){
		    if(!marked[adja.v])
			pq.add(new Edge(adja.v, (distTo[curr.v] | adja.cost)));
		}
	    }
	}

	return distTo;
    }

    private static int bellmanFord(ArrayList<Edge>[] adj,int start,int end,int V){

	int distTo[] = new int [V+1];
	Arrays.fill(distTo, Integer.MAX_VALUE);
	distTo[start] = 0;
	for(int v = 1;v<=V;v++){

	    for(int i=1;i<=V;i++)
		for(Edge e : adj[i])
		    distTo[e.v] = Math.min(distTo[e.v],distTo[i] | e.cost);
	}

	return distTo[end] == Integer.MAX_VALUE ? -1 : distTo[end];
    }

    private static int bfs(ArrayList<Edge>[] adj , int start, int end , int V){

	ArrayDeque<Edge> queue = new ArrayDeque<>();
	boolean dist[][] = new boolean[V+1][1025];	
	queue.add(new Edge(start, 0));
	while(!queue.isEmpty()){
	    Edge curr = queue.remove();	    
	    dist[curr.v][curr.cost] = true;
	    for(Edge e : adj[curr.v]){
		if(!dist[e.v][e.cost | curr.cost]){
		    dist[e.v][e.cost | curr.cost] = true;  //Important
		    queue.add(new Edge(e.v, e.cost | curr.cost));	
		}
	    }
	}
	for(int i=0;i<1025;i++)
	    if(dist[end][i])
		return i;

	return -1;
    }

    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();
	int E = s1.nextInt();
	@SuppressWarnings("unchecked")
	ArrayList<Edge>[] adj = (ArrayList<Edge>[])new ArrayList[V+1];
	for(int i=1;i<=V;i++)adj[i] = new ArrayList<>();

	while(E-->0){
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    int cost = s1.nextInt();
	    adj[u].add(new Edge(v, cost));
	    adj[v].add(new Edge(u, cost));
	}
	out.print(bfs(adj, s1.nextInt(), s1.nextInt(), V));
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