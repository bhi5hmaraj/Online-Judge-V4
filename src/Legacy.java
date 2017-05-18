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

    
    static ArrayList<Edge> adj[];
    static int sz = 0;
    static int[][] child;
    static int map[];
    static final int[] EMPTY = {-1 , -1};
    static boolean marked[];
    static long distTo[];
    
    static int initGraph(int l , int r) {
        if(l == r) {
            int node = sz;
            adj[sz++] = (new ArrayList<>());
//            adj.get(node).add(new Edge(adj.size(), 0));
//            adj.add(new ArrayList<>());
//            child.add(EMPTY);
            map[l] = node;
//             System.out.printf("L = %d R = %d node = %d\n", l , r , node);
            return node;
        } else {
            int m = (l + r) / 2;
            int left = initGraph(l, m);
            int right = initGraph(m + 1, r);
            int top = sz;
//             System.out.printf("L = %d R = %d node = %d\n", l , r , top);
            adj[sz] = (new ArrayList<>());
            adj[sz].add(new Edge(left, 0));
            adj[sz].add(new Edge(right, 0));
            child[sz++] = (new int[]{left , right});
            int bottom = sz;
            adj[sz++] = (new ArrayList<>());
            if(l != m)
                adj[(left + 1)].add(new Edge(bottom, 0));
            else
                adj[(left)].add(new Edge(bottom, 0));
            
            if(m + 1 != r)
                adj[(right + 1)].add(new Edge(bottom, 0));
            else
                adj[(right)].add(new Edge(bottom, 0));
            
            return top;
        }
    }
    
    static void modifyGraph(int node , int nl , int nr , int planet ,int l , int r  , long cost , boolean top) {
        if(nl == l && nr == r) {
            if(top) 
                adj[(map[planet])].add(new Edge(node, cost));
            else
                adj[(node + (l == r ? 0 : 1))].add(new Edge(map[planet], cost));
        } else {
            int m = (nl + nr) / 2;
            if(r <= m)
                modifyGraph(child[(node)][0], nl, m , planet , l, r, cost, top);
            else if(l > m)
                modifyGraph(child[(node)][1], m + 1, nr, planet,  l, r, cost, top);
            else {
                int c[] = child[(node)];
                modifyGraph(c[0], nl, m, planet, l, m,cost, top);
                modifyGraph(c[1], m + 1, nr, planet, m + 1, r, cost, top);
            }
        }
    }
    
    private static void dijkstra(int start) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(start, 0));
        while (!pq.isEmpty()) {
            Edge min = pq.remove();
            int u = min.v;
            if(!marked[u]){
                marked[u] = true;
                distTo[u] = min.cost;
                for (Edge e : adj[u])
                    if (!marked[e.v])
                        pq.add(new Edge(e.v, e.cost + distTo[u]));
            }
        }
    }

    static void trim() {
        for (int node = 0; node < sz; node++) {
            ArrayList<Edge> arl = new ArrayList<>();
            HashMap<Integer, Edge> edges = new HashMap<>();
            for (Edge ee : adj[(node)]) {
                if (ee.v != node) {
                    Edge already = edges.get(ee.v);
                    if (already == null || ee.cost < already.cost) 
                        edges.put(ee.v, ee);
                }
            }
            for(Map.Entry<Integer, Edge> entry : edges.entrySet())
                arl.add(entry.getValue());
            adj[node] = arl;
        }
    }

    
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        int start = nextInt();
        adj = new ArrayList[8 * N];
        child = new int[8 * N][];
        map = new int[N + 1];
        int root = initGraph(1, N);
        /*
         System.out.println("After init");
         adj.stream().forEach(System.out::println);
        
         System.out.println("child");
         child.stream().forEach(a -> System.out.println(Arrays.toString(a)));
         
         System.out.println("map");
         Arrays.stream(map, 1, N + 1).forEach(System.out::println);
        */ 
        marked = new boolean[sz];
        distTo = new long[sz];
        
        Arrays.fill(distTo, -1);
        while(Q-->0) {
            int type = nextInt();
            if(1 == type)
                adj[(map[nextInt()])].add(new Edge(map[nextInt()], nextLong()));
            else
                modifyGraph(root, 1, N, nextInt(), nextInt(), nextInt(), nextLong(), type == 2);
        }
        if(start == 49273) 
            System.out.println("here");
//        System.out.println("V = " + adj.size() + " E = " + countE());
        
//         System.out.println("After edit");
//        trim();
//        adj.stream().forEach(System.out::println);
        dijkstra(map[start]);
        
//        Arrays.stream(map, 1, N + 1).forEach(i -> print(distTo[i] + " "));
        for(int i = 1; i <= N; i++)
            print(distTo[map[i]] + " ");
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