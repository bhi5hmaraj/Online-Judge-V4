import java.util.*;
import java.io.*;
public class NHSPCTaskH {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final long mod = 1000000007;
    static ArrayList<Edge>[] adj;
    static int V;
    static class Edge  {
        int v;
        long cost;

        Edge(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }
    }

    static int size[];
    static long DP[];
    static long subSum[];
    static long dfs(int u , int par) {
        size[u] = 1;
        long sum = 0;
        for(Edge e : adj[u])
            if(e.v != par) {
                sum = (sum + dfs(e.v, u)) % mod;
                size[u] += size[e.v];
                DP[u] = (DP[u] + DP[e.v]) % mod;
                DP[u] = (DP[u] + (e.cost * size[e.v]) % mod) % mod;
            }
        
        for(Edge e : adj[u])
            if(e.v != par) 
                sum = (sum + ((DP[e.v] * (size[u] - size[e.v])) % mod) + 
                        ((((e.cost * size[e.v]) % mod) * (size[u] - size[e.v])) % mod)) % mod;
            
        // System.out.println("u " + u + " sum " + sum);
        return subSum[u] = sum;
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        for(int tc = 1 , T = nextInt(); tc <= T; tc++) {
            V = nextInt();
            adj = new ArrayList[V + 1];
            for(int i = 1; i <= V; i++)
                adj[i] = new ArrayList<>();
            
            int E = V - 1;
            while(E-->0) {
                int u = nextInt();
                int v = nextInt();
                int cost = nextInt();
                adj[u].add(new Edge(v, cost));
                adj[v].add(new Edge(u, cost));
            }
            
            size = new int[V + 1];
            DP = new long[V + 1];
            subSum = new long[V + 1];
            dfs(1, 0);
            long s = 0;
            for(long l : subSum)
                s = (s + l) % mod;
            println("Case " + tc + ": " + s);
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