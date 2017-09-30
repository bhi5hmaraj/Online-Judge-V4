import java.util.*;
import java.io.*;
class CHEFTRAF {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge {
        int v , cost;
        Edge(int v , int c) {
            this.v = v;
            cost = c;
        }
    }
    
    static int DP1[][]; // One based vertices
    static int level1[];
    static int parent1[];
    static int DP2[][];
    static int level2[] , parent2[];
    static int V;
    
    static int log(int N){
        return 31 - Integer.numberOfLeadingZeros(N);
    }
    static void binaryLift() {
        
        for(int i=1;i<=V;i++) {
            DP1[0][i] = parent1[i];
            DP2[0][i] = parent2[i];
        }
        for (int i = 1; i < DP1.length; i++) 
            for (int j = 1; j <= V; j++) {
                DP1[i][j] = DP1[i - 1][DP1[i - 1][j]];
                DP2[i][j] = DP2[i - 1][DP2[i - 1][j]];
            }
    }

    static int LCA1(int u , int v){
        if(level1[v] < level1[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level1[v] - level1[u];
        while(diff > 0){        // Bring v to the same level as u
            int log = log(diff);
            v = DP1[log][v];
            diff -= (1 << log);
        }
        if(u == v)
            return u;
        for(int i = log(level1[u]); i >= 0; i--) {    
            if(DP1[i][u] != DP1[i][v]) { 
                u = DP1[i][u];
                v = DP1[i][v];
            }
        }
        return DP1[0][u];
    }

    static int LCA2(int u , int v){
        if(level2[v] < level2[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level2[v] - level2[u];
        while(diff > 0){        // Bring v to the same level as u
            int log = log(diff);
            v = DP2[log][v];
            diff -= (1 << log);
        }
        if(u == v)
            return u;
        for(int i = log(level2[u]); i >= 0; i--) {    
            if(DP2[i][u] != DP2[i][v]) { 
                u = DP2[i][u];
                v = DP2[i][v];
            }
        }
        return DP2[0][u];
    }
    
    static int prefixSum1[] , prefixSum2[];
    static long allPairSum;
    static ArrayList<Edge>[] adj1 , adj2;
    static int dfs1(int u , int par , int lev , int sum) {
        level1[u] = lev;
        parent1[u] = par;
        prefixSum1[u] = sum;
        int subTree = 1;
        for(Edge ed : adj1[u])
            if(ed.v != par) {
                int size = dfs1(ed.v, u, lev + 1, sum + ed.cost);
                allPairSum += 1L * size * (V - size) * ed.cost;
                subTree += size;
            }
        return subTree;
    }
    
    static void dfs2(int u , int par , int lev , int sum) {
        level2[u] = lev;
        parent2[u] = par;
        prefixSum2[u] = sum;
        for(Edge ed : adj2[u])
            if(ed.v != par) 
                dfs2(ed.v, u, lev + 1, sum + ed.cost);
    }
    
    
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            V = nextInt();
            int ed1[][] = new int[V - 1][3];
            int ed2[][] = new int[V - 1][3];
            
            adj1 = new ArrayList[V + 1];
            adj2 = new ArrayList[V + 1];
            
            for(int i = 1; i <= V; i++) {
                adj1[i] = new ArrayList<>();
                adj2[i] = new ArrayList<>();
            }
            
            for(int i = 0; i < V - 1; i++) {
                ed1[i] = nextIntArray(3);
                adj1[ed1[i][0]].add(new Edge(ed1[i][1], ed1[i][2]));
                adj1[ed1[i][1]].add(new Edge(ed1[i][0], ed1[i][2]));
            }
            for(int i = 0; i < V - 1; i++) {
                ed2[i] = nextIntArray(3);
                adj2[ed2[i][0]].add(new Edge(ed2[i][1], ed2[i][2]));
                adj2[ed2[i][1]].add(new Edge(ed2[i][0], ed2[i][2]));
            }
            
            boolean fast = true;
            for(int i = 0; i < V - 1; i++)
                for(int j = 0; j < 3; j++)
                    fast &= ed1[i][j] == ed2[i][j];
            
            if(fast) {
                level1 = new int[V + 1];
                level2 = new int[V + 1];
                parent1 = new int[V + 1];
                parent2 = new int[V + 1];
                prefixSum1 = new int[V + 1];
                prefixSum2 = new int[V + 1];
                allPairSum = 0;
                dfs1(1, 0, 0, 0);
                dfs2(1, 0, 0, 0);
                println(allPairSum);
            }
            else {
                /*
                DP1 = new int[log(V) + 1][V + 1];
                DP2 = new int[log(V) + 1][V + 1];
                binaryLift();
                long sum = 0;
                for(int i = 1; i <= V; i++)
                    for(int j = i + 1; j <= V; j++) {
                        int cost1 = prefixSum1[i] + prefixSum1[j] - 2 * prefixSum1[LCA1(i, j)];
                        int cost2 = prefixSum2[i] + prefixSum2[j] - 2 * prefixSum2[LCA2(i, j)];
                        sum += Math.min(cost1 , cost2);
                    }
                
                println(sum);
                */
                long sum = 0;
                for(int i = 1; i <= V; i++) {
                    int dist1[] = new int[V + 1];
                    int dist2[] = new int[V + 1];
                    ArrayDeque<Integer> queue = new ArrayDeque<>();
                    queue.add(i);
                    boolean marked[] = new boolean[V + 1];
                    marked[i] = true;
                    while(!queue.isEmpty()) {
                        int curr = queue.remove();
                        for(Edge ed : adj1[curr])
                            if(!marked[ed.v]) {
                                marked[ed.v] = true;
                                dist1[ed.v] = dist1[curr] + ed.cost;
                                queue.add(ed.v);
                            }
                    }
                    Arrays.fill(marked, false);
                    marked[i] = true;
                    queue.add(i);
                    while(!queue.isEmpty()) {
                        int curr = queue.remove();
                        for(Edge ed : adj2[curr])
                            if(!marked[ed.v]) {
                                marked[ed.v] = true;
                                dist2[ed.v] = dist2[curr] + ed.cost;
                                queue.add(ed.v);
                            }
                    }
                    
                    for(int j = i + 1; j <= V; j++)
                        sum += Math.min(dist1[j] , dist2[j]);
                    
                }
                
                println(sum);
            }
            
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    /* Increase stack size in java

     */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new CHEFTRAF().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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