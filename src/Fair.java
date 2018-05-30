import java.util.*;
import java.io.*;
public class Fair {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    // Courtesy : UWI ( adjacency list using Jagged Arrays )
    static int[][] packU(int n, int[] from, int[] to , int isOneBased) {   
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }
    
    // Courtesy : Sharon
    static class Queue{
        int ar[];
        int st, en;
        public Queue(int n) {
            ar = new int[n];
            st = 0;
            en = 0;
        }
        public void add(int a) { ar[en++] = a; }
        public int poll() { return ar[st++]; }
        public boolean isEmpty() { return st==en;}
        public void reset() {
            st=0;
            en=0;
        }
    }
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        
        int k = nextInt();
        int s = nextInt() - 1;
        
        int arr[] = nextIntArrayOneBased(V);
        
        int f[] = new int[E];
        int t[] = new int[E];
        
        for(int i = 0; i < E; i++) {
            f[i] = nextInt();
            t[i] = nextInt();
        }
        
        int adj[][] = packU(V, f, t, 1);
        
        int costs[][] = new int[V + 1][k];
        
        
        for(int color = 1; color <= k; color++) {
            // bfs for every color
            
            int dist[] = new int[V + 1];
            Arrays.fill(dist, V + 100);
            Queue queue = new Queue(V);
            
            for(int i = 1; i <= V; i++) 
                if(color == arr[i]) {
                    dist[i] = 0;
                    queue.add(i);
                }
            
            
            while(!queue.isEmpty()) {
                int u = queue.poll();
                for(int v : adj[u])
                    if(dist[v] > dist[u] + 1) {
                        queue.add(v);
                        dist[v] = dist[u] + 1;
                        costs[v][color - 1] = dist[v];
                    }
            }
        }
        
        for(int i = 1; i <= V; i++) {
            Arrays.sort(costs[i]);
            int sum = 0;
            for(int j = 0; j <= s; j++) 
                sum += costs[i][j];
            print(sum + " ");
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
