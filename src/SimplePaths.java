import java.util.*;
import java.io.*;
public class SimplePaths {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer> tree[];
    static ArrayList<Integer>[] adj;
    static boolean marked[];
    static int biConnected[];
    static int level[];
    static int minLevel[];
    static int biComp;
    static void dfs(int u , int par , int lev) {
        marked[u] = true;
        level[u]  = lev;
        int parallelEdge = 0;
        for(int v : adj[u]) {
            if(v != par) {
                if(marked[v])
                    minLevel[u] = Math.min(minLevel[u] , level[v]);
                else {
                    dfs(v, u, lev + 1);
                    minLevel[u] = Math.min(minLevel[u] , minLevel[v]);
                }
            }
            else 
                parallelEdge++;
        }
        
        if(parallelEdge > 1)
            minLevel[u] = Math.min(minLevel[u] , level[par]);
    }
    static void findBiconnected(int u) {
        marked[u] = true;
        for(int v : adj[u])
            if(!marked[v]) {
                if(minLevel[v] <= level[u]) // not a bridge
                    biConnected[v] = biConnected[u];
                else
                    biConnected[v] = ++biComp;
                findBiconnected(v);
            }
    }
    
    static int parent[];
    
    static void treeDfs(int u , int par , int lev) {
        System.out.println("u " + u);
        parent[u] = par;
        treeLevel[u] = lev;
        for(int v : tree[u])
            if(v != par)
                dfs(v, u , lev + 1);
        
    }
    static int DP[][]; // One based vertices
    static int treeLevel[];
    static int log(int N){
        return 31 - Integer.numberOfLeadingZeros(N);
    }

    static int LCA(int u , int v){
        if(treeLevel[v] < treeLevel[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = treeLevel[v] - treeLevel[u];
        while(diff > 0){        // Bring v to the same treeLevel as u
            int log = log(diff);
            v = DP[log][v];
            diff -= (1 << log);
        }
        if(u == v)
            return u;
        for(int i = log(treeLevel[u]); i >= 0; i--) {    
            if(DP[i][u] != DP[i][v]) { 
                u = DP[i][u];
                v = DP[i][v];
            }
        }
        return DP[0][u];
    }
    

    private static void solve() {
        
        int V = nextInt();
        int E = nextInt();
        int Q = nextInt();
        
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<Integer>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        marked = new boolean[V + 1];
        biConnected = new int[V + 1];
        level = new int[V + 1];
        minLevel = new int[V + 1];
        biComp = 0;
        Arrays.fill(minLevel, Integer.MAX_VALUE);
        dfs(1, 0, 0);
        marked = new boolean[V + 1];
        findBiconnected(1);
        biComp++;
        tree = new ArrayList[biComp];
        int treeCompSize[] = new int[biComp];
        parent = new int[biComp];
        treeLevel = new int[biComp];
        for(int i = 0; i < biComp; i++)
            tree[i] = new ArrayList<Integer>();
        for(int i = 1; i <= V; i++)
            for(int j : adj[i]) { 
                if(biConnected[i] != biConnected[j]) {
                    tree[biConnected[i]].add(biConnected[j]);
                    tree[biConnected[j]].add(biConnected[i]);
                }
                else
                    treeCompSize[biConnected[i]]++;
            }
        
        for(int i = 0; i < biComp; i++) 
            treeCompSize[i] = treeCompSize[i] / 2 + 1;
        
        treeDfs(0, 0, 0);
        DP = new int[log(biComp) + 1][biComp + 1];
        System.out.println(Arrays.toString(biConnected));
        System.out.println(Arrays.toString(treeCompSize));
        System.out.println(Arrays.toString(parent));
        
        for(int i=0;i<biComp;i++)
            DP[0][i] = parent[i];
        
        for (int i = 1; i < DP.length; i++) 
            for (int j = 0; j < biComp; j++) 
                DP[i][j] = DP[i - 1][DP[i - 1][j]];
        
        while(Q-->0) {
            int x = nextInt();
            int y = nextInt();
            if(x == y)
                throw new RuntimeException();
            
            x = biConnected[x];
            y = biConnected[y];
            int lca = LCA(x, y);
            boolean moreThanOne = treeCompSize[lca] > 1;
            for(int curr = x; curr != lca; curr = parent[curr])
                moreThanOne |= treeCompSize[curr] > 1;
            for(int curr = y; curr != lca; curr = parent[curr])
                moreThanOne |= treeCompSize[curr] > 1;
                
            println(moreThanOne ? "0" : "1");
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