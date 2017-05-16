import java.util.*;
import java.io.*;
public class WealthDisparity
{

    /************************ SOLUTION STARTS HERE ***********************/
    private static ArrayList<Integer>[] adj;
    private static long wealth[];
    private static long maxNegDiff = Long.MIN_VALUE;
    private static long maxPosDiff = Long.MIN_VALUE;
    private static void dfs(int u, long diff)
    {
	for(int v:adj[u])
	{
	    long curr_diff = wealth[u] - wealth[v];
	    long curr_sum = 0;
	    if(curr_diff <= 0)
		maxNegDiff = Math.max(maxNegDiff, curr_diff);

	    curr_sum = Math.max(0, diff + curr_diff);
	    maxPosDiff = Math.max(maxPosDiff, curr_sum);	    
	    dfs(v,curr_sum);
	}
    }

    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out){

	int V = s1.nextInt();
	adj = (ArrayList<Integer>[])new ArrayList[V+1];

	for(int i=1;i<=V;i++)adj[i] = new ArrayList<Integer>();

	wealth = s1.nextLongArrayOneBased(V);
	int root = -1;
	for(int u=1;u<=V;u++)
	{
	    int v = s1.nextInt();
	    if(v == -1)
		root = u;
	    else
		adj[v].add(u);
	}
	if(V == 1)
	    out.print(0);
	else
	{			
	    dfs(root, 0);		
	    //System.out.println("Answer "+((maxPosDiff == 0)?maxNegDiff:maxPosDiff));
	    out.print((maxPosDiff == 0)?maxNegDiff:maxPosDiff);
	}
    }

    /************************ SOLUTION ENDS HERE ************************/



    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	new Thread(null, new Runnable() {
	    public void run() {
		new WealthDisparity().run();
	    }
	}, "IncreaseStackPlzz", 1L << 25).start();

    }    
    public void run()
    {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve(in, out);
	in.close();
	out.close();
    }
    static class FastScanner{
	public BufferedReader reader;
	public StringTokenizer st;
	public FastScanner(InputStream stream){
	    reader = new BufferedReader(new InputStreamReader(stream));
	    st = null;
	}
	public FastScanner(String file){
	    try
	    {
		reader = new BufferedReader(new FileReader(file));
		st = null;
	    }
	    catch(FileNotFoundException e)
	    {
		e.printStackTrace();
	    }
	}
	public String next(){
	    while(st == null || !st.hasMoreTokens()){
		try{
		    String line = reader.readLine();
		    if(line == null) return null;
		    st = new StringTokenizer(line);
		}catch (Exception e){
		    throw (new RuntimeException());
		}
	    }
	    return st.nextToken();
	}
	public String nextLine(){
	    String str = null;
	    try {
		str = reader.readLine();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	    return str;
	}
	public int nextInt(){
	    return Integer.parseInt(next());
	}
	public long nextLong(){
	    return Long.parseLong(next());
	}
	public double nextDouble(){
	    return Double.parseDouble(next());
	}
	public char nextChar(){
	    return next().charAt(0);
	}
	int[] nextIntArray(int n) {
	    int[] arr = new int[n];
	    for (int i = 0; i < n; i++) {
		arr[i] = nextInt();
	    }
	    return arr;
	}

	long[] nextLongArray(int n) {
	    long[] arr = new long[n];
	    for (int i = 0; i < n; i++) {
		arr[i] = nextLong();
	    }
	    return arr;
	}
	int[] nextIntArrayOneBased(int n) {
	    int[] arr = new int[n+1];
	    for (int i = 1; i <= n; i++) {
		arr[i] = nextInt();
	    }
	    return arr;
	}

	long[] nextLongArrayOneBased(int n) {
	    long[] arr = new long[n+1];
	    for (int i = 1; i <= n; i++) {
		arr[i] = nextLong();
	    }
	    return arr;
	}
	public void close(){	
	    try{ reader.close(); } catch(IOException e){e.printStackTrace();}
	}
    }

    /************************ TEMPLATE ENDS HERE ************************/
}
