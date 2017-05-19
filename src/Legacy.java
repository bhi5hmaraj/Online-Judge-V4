import java.util.*;
import java.io.*;
public class Legacy {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge implements Comparable<Edge> {
        int v;
        long cost;

        Edge(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
        @Override
        public String toString() {
            return String.format("[to = %d wt = %d]", v , cost);
        }
    }

    
    static ArrayList<ArrayList<Edge>> adj;
    static ArrayList<int[]> child;
    static int map[];
    static final int[] EMPTY = {-1 , -1};
    static boolean marked[];
    static long distTo[];
    
    static int initGraph(int l , int r) {
        if(l == r) {
            int node = adj.size();
            adj.add(new ArrayList<>());
            adj.get(node).add(new Edge(adj.size(), 0));
            adj.add(new ArrayList<>());
            child.add(EMPTY);
            child.add(EMPTY);
            map[l] = node;
            return node;
        } else {
            int m = (l + r) / 2;
            int left = initGraph(l, m);
            int right = initGraph(m + 1, r);
            int top = adj.size();
            adj.add(new ArrayList<>(Arrays.asList(new Edge(left, 0) , new Edge(right, 0))));
            int bottom = adj.size();
            adj.add(new ArrayList<>());
            adj.get(left + 1).add(new Edge(bottom, 0));
            adj.get(right + 1).add(new Edge(bottom, 0));
            child.add(new int[]{left , right});
            child.add(EMPTY);
            return top;
        }
    }
    
    static void modifyGraph(int node , int nl , int nr , int planet ,int l , int r  , long cost , boolean top) {
        if(nl == l && nr == r) {
            if(top) 
                adj.get(map[planet]).add(new Edge(node, cost));
            else
                adj.get(node + 1).add(new Edge(map[planet], cost));
        } else {
            int m = (nl + nr) / 2;
            if(r <= m)
                modifyGraph(child.get(node)[0], nl, m , planet , l, r, cost, top);
            else if(l > m)
                modifyGraph(child.get(node)[1], m + 1, nr, planet,  l, r, cost, top);
            else {
                int c[] = child.get(node);
                modifyGraph(c[0], nl, m, planet, l, m,cost, top);
                modifyGraph(c[1], m + 1, nr, planet, m + 1, r, cost, top);
            }
        }
    }
    
    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        distTo[start] = 0;
        while (!pq.isEmpty()) {
            Edge min = pq.remove();
            int u = min.v;
            if(distTo[u] < min.cost)
                continue;
            
            for(Edge e : adj.get(u))
                if(distTo[e.v] > distTo[u] + e.cost) {
                    distTo[e.v] = distTo[u] + e.cost;
                    pq.add(new Edge(e.v, distTo[e.v]));
                }
        }
    }

    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        int start = nextInt();
        adj = new ArrayList<>();
        child = new ArrayList<>();
        map = new int[N + 1];
        int root = initGraph(1, N);
        marked = new boolean[adj.size()];
        distTo = new long[adj.size()];
        Arrays.fill(distTo, Long.MAX_VALUE);
        while(Q-->0) {
            int type = nextInt();
            if(1 == type)
                adj.get(map[nextInt()]).add(new Edge(map[nextInt()], nextLong()));
            else
                modifyGraph(root, 1, N, nextInt(), nextInt(), nextInt(), nextLong(), type == 2);
        }
        dijkstra(map[start]);
        for(int i = 1; i <= N; i++)
            print((distTo[map[i]] == Long.MAX_VALUE ? -1 : distTo[map[i]]) + " ");
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