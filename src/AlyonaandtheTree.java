import java.util.*;
import java.io.*;
public class AlyonaandtheTree
{


	/************************ SOLUTION STARTS HERE ***********************/

	static class Edge {
		int v;
		long cost;

		Edge(int v, long cost) {
			this.v = v;
			this.cost = cost;
		}
	}

	static ArrayList<Edge>[] adj;
	static int size[];
	static boolean marked[];
	static long distTo[];
	static long a[];
	static int size(int u)
	{
		marked[u] = true;
		size[u] = 1;
		for(Edge v:adj[u])
			if(!marked[v.v])
				size[u] += size(v.v);
		marked[u] = false;
		return size[u];
	}

	static void distance(int u,long dist)
	{
		marked[u] = true;
		distTo[u] = dist;
		for(Edge e:adj[u])
			if(!marked[e.v])
				distance(e.v, dist + e.cost);

		marked[u] = false;
	}

	static int toRemove(int u,long dist)
	{
		marked[u] = true;
		if(distTo[u] - dist > a[u])
			return size[u];
		else
		{
			int cnt = 0;
			for(Edge e:adj[u])
				if(!marked[e.v])
					cnt += toRemove(e.v, Math.min(dist,distTo[u]));

			return cnt;
		}
	}

	@SuppressWarnings("unchecked")
	private static void solve(FastScanner s1, PrintWriter out){

		int V = s1.nextInt();
		int E = V - 1;
		a      = s1.nextLongArrayOneBased(V);
		adj    = (ArrayList<Edge>[]) new ArrayList[V+1];
		marked = new boolean[V+1];
		distTo = new long[V+1];
		size   = new int[V+1];
		for(int i=1;i<=V;i++)
			adj[i] = new ArrayList<>();

		for(int i=1;i<=E;i++)
		{
			int v = s1.nextInt();
			long cost = s1.nextLong();
			adj[i+1].add(new Edge(v, cost));
			adj[v].add(new Edge(i+1, cost));
		}

		size(1);
		distance(1, 0);

		out.print(toRemove(1, 0));
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