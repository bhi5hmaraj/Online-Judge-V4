import java.util.*;
import java.io.*;
class ThreeTypes
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
    static class Edge implements Comparable<Edge>
    {
	int u,v,type;
	Edge(int u,int v,int type)
	{
	    this.u = u;
	    this.v = v;
	    this.type = type;
	}
	@Override
	public int compareTo(Edge o) {
	    return o.type - this.type;
	}
    }
    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();
	int E = s1.nextInt();
	DSU men   = new DSU(V);
	DSU women = new DSU(V);
	Edge arr[] = new Edge[E];
	for(int e = 0;e<E;e++)
	{
	    int u = s1.nextInt();
	    int v = s1.nextInt();
	    int type = s1.nextInt();
	    arr[e] = new Edge(u, v, type);
	    switch(type)
	    {
	    case 1:
		men.union(u, v);
		break;
	    case 2:
		women.union(u, v);
		break;
	    case 3:
		men.union(u, v);
		women.union(u, v);
		break;
	    }
	}
	if(men.components() != 1 || women.components() != 1)
	{
	    out.print(-1);
	    return;
	}
	Arrays.sort(arr);
	men   = new DSU(V);
	women = new DSU(V);
	int cnt = 0;
	for(Edge e:arr)
	{
	    if(e.type == 3)
	    {
		if(men.connected(e.u, e.v) && women.connected(e.u, e.v))
		    cnt++;
		else
		{
		    men.union(e.u, e.v);
		    women.union(e.u, e.v);
		}
	    }
	    else if(e.type == 1)
	    {
		if(men.connected(e.u, e.v))
		    cnt++;
		else
		    men.union(e.u, e.v);
	    }
	    else
	    {
		if(women.connected(e.u, e.v))
		    cnt++;
		else
		    women.union(e.u, e.v);
	    }
	}
	
	out.print(cnt);
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