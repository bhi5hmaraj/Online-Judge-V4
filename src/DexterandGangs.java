import java.util.*;
import java.io.*;
public class DexterandGangs  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
    static int DP[][]; // One based vertices
    static int level[];
    static int parent[];
    static int[] compress(int arr[]) {
        HashMap<Integer , Integer> map = new HashMap<>();
        Arrays.stream(arr).forEach(a -> map.putIfAbsent(a, map.size()));
        int inv[] = new int[map.size()];
        for(int i = 0; i < arr.length; i++) {
            inv[map.get(arr[i])] = arr[i];
            arr[i] = map.get(arr[i]);
        }
        return inv;
    }
    static int log(int N){
        return 31 - Integer.numberOfLeadingZeros(N);
    }
    static void binaryLift() {
        
        for(int i=1;i<=V;i++)
            DP[0][i] = parent[i];
        
        for (int i = 1; i < DP.length; i++) 
            for (int j = 1; j <= V; j++) 
                DP[i][j] = DP[i - 1][DP[i - 1][j]];

    }

    static int LCA(int u , int v){
        if(level[v] < level[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level[v] - level[u];
        while(diff > 0){        // Bring v to the same level as u
            int log = log(diff);
            v = DP[log][v];
            diff -= (1 << log);
        }
        if(u == v)
            return u;
        for(int i = log(level[u]); i >= 0; i--) {    
            if(DP[i][u] != DP[i][v]) { 
                u = DP[i][u];
                v = DP[i][v];
            }
        }
        return DP[0][u];
    }
    
    static void dfs(int u , int par , int lev) {
        level[u] = lev;
        parent[u] = par;
        for(int v : adj[u])
            if(v != par)
                dfs(v, u, lev + 1);
    }
    
    static ArrayList<Integer>[] adj;
    static int V;
    private static void solve() {   // May the (Brute) force be with you
        // https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
        V = nextInt();
        int Q = nextInt();
        int G[] = nextIntArrayOneBased(V);
        int inv[] = compress(G);
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        int E = V - 1;
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        DP = new int[log(V) + 1][V + 1];
        level = new int[V + 1];
        parent = new int[V + 1];
        dfs(1, 0, 0);
        binaryLift();
        
        while(Q-->0) {
            int u = nextInt();
            int v = nextInt();
            int lca = LCA(u, v);
            int x = lca;
            int cnt = 1;
            int n = 1;
            // println(u + " " + v );
            for(int i = u; i != lca; i = parent[i]) {
                // print(i + " => ");
                cnt += G[i] == G[x] ? 1 : -1;
                if(cnt < 0) {
                    cnt = 0;
                    x = i;
                }
                n++;
            }
            for(int i = v; i != lca; i = parent[i]) {
                // print(i + " => ");
                cnt += G[i] == G[x] ? 1 : -1;
                if(cnt < 0) {
                    cnt = 0;
                    x = i;
                }
                n++;
            }
            // println(lca);
            int freq = G[x] == G[lca] ? 1 : 0;
            for(int i = u; i != lca; i = parent[i]) 
                freq += G[i] == G[x] ? 1 : 0;
            for(int i = v; i != lca; i = parent[i])
                freq += G[i] == G[x] ? 1 : 0;
            
            println(freq > n / 2 ? "D " + inv[G[x]] : "S");
        }
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
   /* 
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new DexterandGangs().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run() throws IOException { 
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