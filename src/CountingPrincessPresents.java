import java.util.*;
import java.io.*;
public class CountingPrincessPresents {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<int[]>[] adj;
    static final long mod = (long) 1e9 + 7;
    static boolean hasSpecial[];
    static long dfs(int u , int par) {
        long ways = 1;
        for(int v[] : adj[u])
            if(v[0] != par) {
                long subTreeWays = dfs(v[0], u);
                hasSpecial[u] |= hasSpecial[v[0]] | (v[1] == 1);
                 println("u " + u + " v " + v[0] + " sub " + subTreeWays);
                if(!(hasSpecial[v[0]] || (v[1] == 1)))
                    subTreeWays = (subTreeWays + 1) % mod;  // can remove
                ways = (ways * subTreeWays) % mod;
            }
        
        return ways;
    }
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            
            int V = nextInt();
            int E = V - 1;
            adj = new ArrayList[V + 1];
            for(int i = 1; i <= V; i++)
                adj[i] = new ArrayList<>();
            
            int root = 1;
            while(E-->0) {
                int u = nextInt();
                int v = nextInt();
                int type = nextInt();
                adj[u].add(new int[]{v , type});
                adj[v].add(new int[]{u , type});
                if(type == 1)
                    root = u;
            }
             println("root " + root);
            hasSpecial = new boolean[V + 1];
            println(dfs(2, 0));
            
        }
        
        
        
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