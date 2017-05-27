import java.util.*;
import java.io.*;
public class StanfordPrims {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    static class Edge implements Comparable<Edge> {
        int v , cost;
        Edge(int vv , int cc) {
            v = vv;
            cost = cc;
        }
        @Override
        public int compareTo(Edge o) {
            return cost - o.cost;
        }
    }
    
    static ArrayList<Edge>[] adj;
    
    static int primsMST(int V) {
        boolean marked[] = new boolean[V + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0));
        int sum = 0;
        while(!pq.isEmpty()) {
            Edge cutEdge = pq.remove();
            if(!marked[cutEdge.v]) {
                marked[cutEdge.v] = true;
                sum += cutEdge.cost;
                for(Edge e : adj[cutEdge.v])
                    if(!marked[e.v])
                        pq.add(e);
            }
        }
        
        return sum;
    }
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            int cost = nextInt();
            adj[u].add(new Edge(v, cost));
            adj[v].add(new Edge(u, cost));
        }
        
        println(primsMST(V));
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new FileReader("Stanford_C3_W1_edges.txt"));
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