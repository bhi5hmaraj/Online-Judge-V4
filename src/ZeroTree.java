import java.util.*;
import java.io.*;

public class ZeroTree {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
	static ArrayList<Integer>[] adj;
	static long v[];
	static long delta[][];	// 0 - to increment, 1 - to decrement
	// delta represents how many times does the current node gets inc/dec
	
	static void dfs(int u, int par) {
		long posMax = 0;
		long negMax = 0;
		for(int child : adj[u])
			if(child != par) {
				dfs(child, u);
				negMax = Math.max(negMax, delta[child][0]);
				posMax = Math.max(posMax, delta[child][1]);
			}
						// +        -
		long leftOver = (negMax - posMax) - v[u];
		if(leftOver > 0)		// Need to decrement
			posMax += leftOver;
		else
			negMax += -leftOver;

		delta[u][0] = negMax;
		delta[u][1] = posMax;
	}
	
    @SuppressWarnings("unchecked")
	private static void solve() {
        
    	int V = nextInt();
    	int E = V - 1;
    	
    	adj = new ArrayList[V + 1];
    	delta = new long[V + 1][2];
    	
    	for(int i = 1; i <= V; i++)
    		adj[i] = new ArrayList<>();
    	
    	
    	while(E-->0) {
    		int u = nextInt();
    		int v = nextInt();
    		adj[u].add(v);
    		adj[v].add(u);
    	}
    	
    	v = nextLongArrayOneBased(V);
    	
    	dfs(1, 0);
    	println(delta[1][0] + delta[1][1]);
    	
    	
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    
    static BufferedReader reader;
    static PrintWriter    writer;
    static StringTokenizer st;
    
    static String next()
    {while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}            
    st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
    static String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}             
    static int    nextInt()   {return Integer.parseInt(next());}
    static long   nextLong()  {return Long.parseLong(next());}     
    static double nextDouble(){return Double.parseDouble(next());}
    static char   nextChar()  {return next().charAt(0);}
    static int[]  nextIntArray(int n)         {int[] a= new int[n];   int i=0;while(i<n){a[i++]=nextInt();}  return a;}
    static long[] nextLongArray(int n)        {long[]a= new long[n];  int i=0;while(i<n){a[i++]=nextLong();} return a;}    
    static int[]  nextIntArrayOneBased(int n) {int[] a= new int[n+1]; int i=1;while(i<=n){a[i++]=nextInt();} return a;}            
    static long[] nextLongArrayOneBased(int n){long[]a= new long[n+1];int i=1;while(i<=n){a[i++]=nextLong();}return a;}            
    static void   print(Object o)  { writer.print(o);  }
    static void   println(Object o){ writer.println(o);}
    
    /************************ TEMPLATE ENDS HERE ************************/
    
}