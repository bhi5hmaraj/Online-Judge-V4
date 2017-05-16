import java.util.*;
import java.io.*;
class KingdomOfMonkeys
{
    //DFS (Recursive)  = 5.29s (explicitly increase stack)
    //DFS (Iterative)  = 5.45s
    //BFS (ArrayDeque) = 6.06s
    //BFS (ArrayList)  = TLE  (Remove method is linear)
    //BFS (LinkedList) = 6.21s
    /************************ SOLUTION STARTS HERE ************************/

    private static ArrayList<Integer>[] adj;
    private static boolean marked[];
    private static int id[];


    private static void dfs(int u,int group)
    {
	marked[u] = true;
	id[u] = group;
	for(int v:adj[u])		
	    if(!marked[v])
		dfs(v,group);		
    }
    private static void bfs(int u,int group)
    {
	ArrayDeque<Integer> queue = new ArrayDeque<>();
	queue.add(u);
	while(!queue.isEmpty())
	{
	    u  = queue.removeFirst();
	    marked[u] = true;
	    id[u] = group;
	    for(int v:adj[u])
		if(!marked[v])
		    queue.add(v);
	}
    }
    private static void iterativeDfs(int u,int group)
    {
	ArrayDeque<Integer> stack = new ArrayDeque<>();
	stack.add(u);
	while(!stack.isEmpty())
	{
	    u = stack.removeLast();
	    marked[u] = true;
	    id[u] = group;
	    for(int v:adj[u])
		if(!marked[v])
		    stack.add(v);
	}
    }
    @SuppressWarnings("unchecked")
    private static void solve(FastScanner s1, PrintWriter out)/* This is the actual solution */{
	int t = s1.nextInt();
	while(t-->0)
	{
	    int V = s1.nextInt();
	    adj = (ArrayList<Integer>[]) new ArrayList[V+1];			
	    for(int i=1;i<=V;i++)
		adj[i] = new ArrayList<>();
	    marked = new boolean[V+1];
	    id = new int[V+1];
	    int E = s1.nextInt();
	    while(E-->0)
	    {
		int u = s1.nextInt();
		int v = s1.nextInt();
		adj[u].add(v);
		adj[v].add(u);
	    }
	    long collected[] = new long[V+1];
	    for(int i=1;i<=V;i++)collected[i] = s1.nextLong();
	    int group = 1;
	    for(int i=1;i<=V;i++)
		if(!marked[i])
		    iterativeDfs(i,group++);

	    long total[] = new long[group + 1];
	    for(int i=1;i<=V;i++)
		total[id[i]] += collected[i];
	    long max = Long.MIN_VALUE;
	    for(long n:total)
		max = Math.max(max, n);

	    out.println(max);
	}
    }


    /************************ SOLUTION ENDS HERE ************************/

    /************************ TEMPLATE STARTS HERE ************************/

    /*
		public static void main(String []args) throws IOException {				
			new Thread(null, new Runnable() {
	            public void run() {
	                new KingdomOfMonkeys().run();
	            }
	        }, "IncreaseStackPlzz", 1L << 25).start();

		}

		public void run()
		{
			FastScanner in  = new FastScanner(System.in);
			FastWriter  out = new FastWriter(System.out);
			solve(in, out);
			in.close();
			out.close();
		}
     */
    public static void main(String []args) throws IOException {		
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
	public void close(){	
	    try{ reader.close(); } catch(IOException e){e.printStackTrace();}
	}
    }
    static class FastWriter{
	BufferedWriter writer;
	public FastWriter(OutputStream stream){
	    writer = new BufferedWriter(new OutputStreamWriter(stream));
	}
	public void print(int i) {
	    print(Integer.toString(i));
	}
	public void println(int i) {
	    print(Integer.toString(i));
	    print('\n');
	}
	public void print(long i) {
	    print(Long.toString(i));
	}
	public void println(long i) {
	    print(Long.toString(i));
	    print('\n');
	}
	public void print(double i) {
	    print(Double.toString(i));
	}
	public void print(boolean i) {
	    print(Boolean.toString(i));
	}
	public void print(char i) {
	    try{writer.write(i);} catch(IOException e){e.printStackTrace();}
	}
	public void print(Object o){
	    print(o.toString());
	}
	public void println(Object o){
	    print(o.toString());
	    print('\n');
	}
	public void print(String s){
	    try{writer.write(s);} catch(IOException e){e.printStackTrace();}
	}
	public void println(String s){
	    try{writer.write(s);writer.write('\n');} catch(IOException e){e.printStackTrace();}
	}
	public void println(){
	    try{writer.write('\n');} catch(IOException e){e.printStackTrace();}
	}
	public void print(int arr[])
	{
	    for (int i = 0; i < arr.length; i++) {
		print(arr[i]);
		print(' ');
	    }
	}
	public void close(){
	    try{writer.close();} catch(IOException e){e.printStackTrace();}
	}
    }

    /************************ TEMPLATE ENDS HERE ************************/
}
