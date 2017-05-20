import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.io.*;
public class Legacy {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * Severe flaw in my old implementation of dijkstra's 
     * From the current vertex I was adding all the unvisited vertex to the priority queue 
     * instead of only the ones which needs to be relaxed hence my priority queue was holding 
     * a lot of unnecessary edges which should not have been there . 
     * 
     * Till now I was using this implementation for most of the problems and it was running fine . This shows how 
     * weak their test cases were . I would like to thank the problem author for making me realize my mistake.
     */
    
    static class Edge implements Comparable<Edge> {
        int v;
        long cost;

        Edge(int v, long cost) {
            this.v = v;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return cost != o.cost ? Long.compare(cost, o.cost) : v - o.v;
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
    static int initGraph(int l , int r) {
        if(l == r) {
            int node = adj.size();
            adj.add(new ArrayList<>());
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
            if(l != m)
                adj.get(left + 1).add(new Edge(bottom, 0));
            else
                adj.get(left).add(new Edge(bottom, 0));
            
            if(m + 1 != r)
                adj.get(right + 1).add(new Edge(bottom, 0));
            else
                adj.get(right).add(new Edge(bottom, 0));
            
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
                adj.get(node + (l == r ? 0 : 1)).add(new Edge(map[planet], cost));
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
   
    static long[] dijkstraVertex(int start , int V) {
        long distTo[] = new long[V];
        Arrays.fill(distTo, Long.MAX_VALUE);
        TreeSet<Integer> set = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(distTo[o1] != distTo[o2])
                    return Long.compare(distTo[o1], distTo[o2]);
                else
                    return o1 - o2;
            }
        });
        distTo[start] = 0;
        for(int i = 0; i < V; i++) set.add(i);
        while(!set.isEmpty()) {
            int u = set.pollFirst();
            if(distTo[u] != Long.MAX_VALUE) {
                for(Edge to : adj.get(u)) {
                    if(distTo[to.v] > distTo[u] + to.cost) {
                        set.remove(to.v);
                        distTo[to.v] = distTo[u] + to.cost;
                        set.add(to.v);
                    }
                }
            }
            else 
                break;
        }
        
        return distTo;
    }
    
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        int start = nextInt();
        adj = new ArrayList<>();
        child = new ArrayList<>();
        map = new int[N + 1];
        int root = initGraph(1, N);
        while(Q-->0) {
            int type = nextInt();
            if(1 == type)
                adj.get(map[nextInt()]).add(new Edge(map[nextInt()], nextLong()));
            else
                modifyGraph(root, 1, N, nextInt(), nextInt(), nextInt(), nextLong(), type == 2);
        }
        
        long distTo[] = dijkstraVertex(map[start] , adj.size());
        
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