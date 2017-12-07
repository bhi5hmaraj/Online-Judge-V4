import java.util.*;
import java.io.*;
public class TreeRequests  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static ArrayList<int[]>[] queries;
    static HashMap<Integer , Integer>[] map;
    
    static char alph[];
    static int ans[];
    static int height[];
    
    static int preprocess(int u) {
        
        for(int v : adj[u])
            height[u] = Math.max(height[u] , preprocess(v));
        
        return ++height[u];

    }
    
    static void dfs(int u) {
        int maxPos = adj[u].size() > 0 ? adj[u].get(0) : -1;
        for(int i = 0; i < adj[u].size(); i++) {
            int v = adj[u].get(i);
            dfs(v);
            maxPos = map[v].size() > map[maxPos].size() ? v : maxPos;
        }
        
        
    }
    
    private static void solve() {
        
        
        int V = nextInt();
        int Q = nextInt();
        
        queries = new ArrayList[V];
        adj = new ArrayList[V];
        
        for(int i = 0; i < V; i++) {
            adj[i] = new ArrayList<>();
            queries[i] = new ArrayList<>();
        }
        
        for(int i = 1; i < V; i++)
            adj[nextInt()].add(i);
        
        alph = nextLine().toCharArray();
        ans = new int[Q];
        height = new int[V];
        while(Q-->0)
            queries[nextInt() - 1].add(new int[]{nextInt() , Q});   // height , qNo
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