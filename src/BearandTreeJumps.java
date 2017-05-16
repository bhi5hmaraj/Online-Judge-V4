import java.util.*;
import java.io.*;
public class BearandTreeJumps {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static int size[];          // size of subtree
    static int chain[][];       // cnt of remainder of chain
    static long pairDist[][];   // number of pairs going through a particular node
    static long sumOfDist = 0;
    static int V , K;

    static void computeDists(int u , int par) {
        chain[u][0] = 1;    // the root node u
        size[u] = 1;
        for(int v : adj[u])
            if(v != par) {
                computeDists(v, u);
                size[u] += size[v];
                sumOfDist += 1L * size[v] * (V - size[v]);
                for(int i=0;i<K;i++)    // Incomming vertex
                    for(int j=0;j<K;j++)    // Already included vertices
                        pairDist[u][(i + 1 + j) % K] += 1L * chain[u][j] * chain[v][i];
                
                for(int i=0;i<K;i++)
                    chain[u][(i + 1) % K] += chain[v][i];
            }
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        V = nextInt();
        K = nextInt();
        int E = V - 1;
        adj = new ArrayList[V + 1];
        for(int i=1;i<=V;i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        size = new int[V + 1];
        chain = new int[V + 1][K];
        pairDist = new long[V + 1][K];
        
        computeDists(1, 0);

        for(int i=1;i<=V;i++)
            for(int rem=1;rem < K;rem++)
                sumOfDist += pairDist[i][rem] * (K - rem);  // Making it ceil(dist / K)
        
        sumOfDist /= K;
        println(sumOfDist);
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