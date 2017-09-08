import java.util.*;
import java.io.*;
public class Civilization {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class DSU {
        private int parent[];
        private int size[];
        private int cnt;
        private int diameter[];
        
        DSU(int length) {
            this.cnt = length;
            parent = new int[length + 10];
            size   = new int[length + 10];
            diameter = new int[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        int root(int p) {
             while(p != parent[p]) p = parent[p];
             return p;
        }

        int sizeOf(int p) {
            return size[root(p)];
        }

        boolean connected(int u, int v) {
            return root(u) == root(v);
        }

        int components() {
            return cnt;
        }

        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                int bigD = Math.max(diameter[rootU] , diameter[rootV]);
                int smallD = Math.min(diameter[rootU] , diameter[rootV]);
                int newDiam = (bigD + 1) / 2 + (Math.max(bigD / 2 , 1 + ((smallD + 1) / 2)));
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                    diameter[rootV] = newDiam;
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                    diameter[rootU] = newDiam;
                }
            }
        }
    }

    
    static boolean globalMark[];
    static ArrayList<Integer>[] adj;
    
    static int[] getLongest(int u , int par , int lev) {  // (vertex , dist) tuple 
        globalMark[u] = true;
        int curr[] = new int[]{u , lev};
        for(int v : adj[u])
            if(v != par) {
                int sub[] = getLongest(v, u, lev + 1);
                if(sub[1] > curr[1]) {
                    curr[0] = sub[0];
                    curr[1] = sub[1];
                }
            }
        return curr;
    }
    
    
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        int Q = nextInt();
        
        adj = new ArrayList[V + 1];
        DSU unionFind = new DSU(V);
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            unionFind.union(u, v);
            adj[u].add(v);
            adj[v].add(u);
        }
        
        globalMark = new boolean[V + 1];
        for(int i = 1; i <= V; i++)
            if(!globalMark[i]) 
                unionFind.diameter[unionFind.root(i)] = getLongest(getLongest(i, 0, 0)[0], 0, 0)[1];
        
        while(Q-->0) {
            if(nextInt() == 1) 
                println(unionFind.diameter[unionFind.root(nextInt())]);
            else
                unionFind.union(nextInt(), nextInt());
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