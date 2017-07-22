import java.util.*;
import java.io.*;
public class uva_12047 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge implements Comparable<Edge> {
        int v , cost;
        Edge(int a , int b) {
            v = a;
            cost = b;
        }
        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    static final int INF = (int) 1e9 + 1;
    
    static int[] dijkstra(ArrayList<Edge>[] adj , int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int V = adj.length - 1;
        int distTo[] = new int[V + 1];
        Arrays.fill(distTo, INF);

        pq.add(new Edge(start, 0));
        distTo[start] = 0;
        
        while (!pq.isEmpty()) {
            Edge min = pq.remove();
            int u = min.v;
            if (distTo[u] < min.cost)
                continue;

            for (Edge e : adj[u])
                if (distTo[e.v] > distTo[u] + e.cost) {
                    distTo[e.v] = distTo[u] + e.cost;
                    pq.add(new Edge(e.v, distTo[e.v]));
                }
        }
        
        return distTo;
    }

    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int V = nextInt();
            int E = nextInt();
            int start = nextInt();
            int dest = nextInt();
            int P = nextInt();
            
            ArrayList<Edge>[] adj = new ArrayList[V + 1];
            ArrayList<Edge>[] adjInv = new ArrayList[V + 1];
            int edg[][] = new int[E][3];
            
            for(int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
                adjInv[i] = new ArrayList<>();
            }
            
            while(E-->0) {
                edg[E][0] = nextInt();
                edg[E][1] = nextInt();
                edg[E][2] = nextInt();
                adj[edg[E][0]].add(new Edge(edg[E][1], edg[E][2]));
                adjInv[edg[E][1]].add(new Edge(edg[E][0], edg[E][2]));
            }
            
            int to[] = dijkstra(adj, start);
            int from[] = dijkstra(adjInv, dest);
            
            int maxToll = -1;
            for(int edge[] : edg) {
                long pathCost = to[edge[0]] + edge[2] + from[edge[1]];
                if(pathCost <= P)
                    maxToll = Math.max(maxToll , edge[2]);
            }
            
            println(maxToll);
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