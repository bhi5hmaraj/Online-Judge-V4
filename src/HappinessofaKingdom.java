import java.util.*;
import java.io.*;
public class HappinessofaKingdom {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static ArrayList<int[]> bridges;
    static int level[];
    static int minLevel[];
    static boolean marked[];

    static void dfs(int u , int par , int lev) {
        marked[u] = true;
        level[u]  = lev;
        for(int v : adj[u])
            if(v != par) {
                if(marked[v])
                    minLevel[u] = Math.min(minLevel[u] , level[v]);
                else {
                    dfs(v, u, lev + 1);
                    if(minLevel[v] > level[u] && bridges.size() < 2)
                        bridges.add(new int[]{Math.min(u , v) , Math.max(u , v)});
                    minLevel[u] = Math.min(minLevel[u] , minLevel[v]);
                }
            }
    }
    
    
    private static void solve() {
        
        
        int V = nextInt();
        int m = nextInt();
        
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(m-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        marked = new boolean[V];
        level  = new int[V];
        minLevel = new int[V];
        Arrays.fill(minLevel, Integer.MAX_VALUE);
        bridges = new ArrayList<>();
        
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