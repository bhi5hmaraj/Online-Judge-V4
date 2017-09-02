import java.util.*;
import java.io.*;
public class HappinessofaKingdom {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static boolean marked[];
    static long initHapp;
    static int id[];
    static int level[];
    static int degree[];
    static ArrayList<Integer> trees;
    static long globalMax = 0;
    static int backU , backV;
    static int remU , remV;
    static int dfs(int u , int par , int lev , int grp) {
        marked[u] = true;
        level[u] = lev;
        int size = 1;
        id[u] = grp;
        degree[u] = adj[u].size();
        for(int v : adj[u])
            if(v != par) {
                if(marked[v]) { // back edge
                    if(level[v] < level[u] && backU == -1 && backV == -1) {
                        backU = u;
                        backV = v;
                    }
                }
                else {
                    size += dfs(v, u , lev + 1,  grp);
                    degree[u] += degree[v];
                }
            }
        return size;
    }
    
    static int findOpt(int u , int par) {
        marked[u] = true;
        int subtree = 1;
        for(int v : adj[u])
            if(v != par) {
                int sz = findOpt(v, u);
                long hap = initHapp - 2L * sz * (trees.get(id[u]) - sz);
                if(hap > globalMax) {
                    globalMax = hap;
                    remU = u;
                    remV = v;
                }
                subtree += sz;
            }
        return subtree;
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int V = nextInt();
        int m = nextInt();
        
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(m-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        marked = new boolean[V + 1];
        trees = new ArrayList<>();
        id    = new int[V + 1];
        initHapp = 0;
        level = new int[V + 1];
        degree = new int[V + 1];
        int comp = 0;
        int extra = 0;
        backU = backV = -1;
        for(int i = 1; i <= V; i++)
            if(!marked[i]) {
                int size = dfs(i, 0, 0 , comp++);
                trees.add(size);
                initHapp += 1L * size * (size - 1);
                extra += (degree[i] / 2) - (size - 1);
            }

        if(extra >= 2)
            println(initHapp);
        else if(extra == 1){
            adj[backU].remove(adj[backU].indexOf(backV));
            adj[backV].remove(adj[backV].indexOf(backU));
            marked = new boolean[V + 1];
            globalMax = 0;
            for(int i = 1; i <= V; i++)
                if(!marked[i])
                    findOpt(i, 0);
            println(globalMax);
        }
        else {
            marked = new boolean[V + 1];
            globalMax = 0;
            for(int i = 1; i <= V; i++)
                if(!marked[i])
                    findOpt(i, 0);
            
            adj[remU].remove(adj[remU].indexOf(remV));
            adj[remV].remove(adj[remV].indexOf(remU));

            marked = new boolean[V + 1];
            trees = new ArrayList<>();
            id    = new int[V + 1];
            initHapp = 0;
            level = new int[V + 1];
            degree = new int[V + 1];
            comp = 0;
            backU = backV = -1;
            for(int i = 1; i <= V; i++)
                if(!marked[i]) {
                    int size = dfs(i, 0, 0 , comp++);
                    trees.add(size);
                    initHapp += 1L * size * (size - 1);
                }
            
            marked = new boolean[V + 1];
            globalMax = 0;
            for(int i = 1; i <= V; i++)
                if(!marked[i])
                    findOpt(i, 0);
            
            println(globalMax);
        }
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new HappinessofaKingdom().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        try {
            solve();
            reader.close();
        }
        catch (Throwable e) {
            throw new RuntimeException();
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