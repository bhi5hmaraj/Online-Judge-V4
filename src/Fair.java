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
        
        PriorityQueue<Integer>[] pq = new PriorityQueue[V + 1];
        for(int i = 1; i <= V; i++)
            pq[i] = new PriorityQueue<>(Collections.reverseOrder());
        
        ArrayDeque<Integer>[] collectByColor = new ArrayDeque[k + 1];
        for(int i = 1; i <= k; i++)
            collectByColor[i] = new ArrayDeque<>();
        
        for(int i = 1; i <= V; i++)
            collectByColor[arr[i]].add(i);
        
        for(int color = 1; color <= k; color++) {
            
            // bfs for every color
            
            int dist[] = new int[V + 1];
            Arrays.fill(dist, V + 100);
            
            for(int u : collectByColor[color])
                dist[u] = 0;
            
            while(!collectByColor[color].isEmpty()) {
                int u = collectByColor[color].remove();
                for(int v : adj[u])
                    if(dist[v] > dist[u] + 1) {
                        collectByColor[color].add(v);
                        dist[v] = dist[u] + 1;
                        pq[v].add(dist[v]);
                        if(pq[v].size() > s)
                            pq[v].remove();
                    }
            }
        }
        
        for(int i = 1; i <= V; i++)
            print(pq[i].stream().mapToInt(Integer::new).sum() + " ");
        
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
