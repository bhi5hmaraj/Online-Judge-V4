import java.util.*;
import java.io.*;
public class MathisonAndThePeculiarSums {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int adj[][];
    static int wt[] , inv[];
    static final int mod = (int) 1e9 + 7;
    // Courtesy : UWI ( adjacency list using Jagged Arrays )
    static int[][] packU(int n, int[] from, int[] to , int isOneBased) {   
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }
    static void compress() {
        int temp[] = Arrays.copyOf(wt, wt.length);
        Arrays.sort(temp);
        int sz = 0;
        for(int i = 0; i < temp.length; ) {
            int curr = temp[i];
            sz++;
            for(; i < temp.length && temp[i] == curr; i++)
                ;
        }
        inv = new int[sz];
        sz = 0;
        for(int i = 0; i < temp.length; ) {
            int curr = temp[i];
            inv[sz++] = curr;
            for(; i < temp.length && temp[i] == curr; i++)
                ;
        }
        for(int i = 1; i < wt.length; i++)
            wt[i] = Arrays.binarySearch(inv, wt[i]);
    }
    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int size[];
        int odd[];
        int even[];
        int len;
        int treeSize;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            treeSize = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            size = new int[treeSize];
            odd  = new int[treeSize];
            even = new int[treeSize];
        }
        void update(int idx , int val){
            int node = (treeSize >> 1) + idx;
            if(val == 1) {
                if(size[node] % 2 == 0)
                    even[node] = (even[node] + inv[idx]);
                else
                    odd[node] = (odd[node] + inv[idx]);
            }
            else {
                if(size[node] % 2 == 1)
                    even[node] = (even[node] - inv[idx]);
                else 
                    odd[node] = (odd[node] - inv[idx]);
            }
            if(even[node] >= mod)
                even[node] -= mod;
            if(even[node] < 0)
                even[node] += mod;
            if(odd[node] >= mod)
                odd[node] -= mod;
            if(odd[node] < 0)
                odd[node] += mod;
            
            size[node] += val;
            for(node >>= 1; node > 0; node >>= 1) {
                size[node] = size[2 * node] + size[2 * node + 1];
                if(size[2 * node] % 2 == 0) {
                    even[node] = (even[2 * node] + even[2 * node + 1]);
                    odd[node] = (odd[2 * node] + odd[2 * node + 1]);
                }
                else {
                    even[node] = (even[2 * node] + odd[2 * node + 1]);
                    odd[node] = (odd[2 * node] + even[2 * node + 1]);
                }
                if(even[node] >= mod)
                    even[node] -= mod;
                if(even[node] < 0)
                    even[node] += mod;
                if(odd[node] >= mod)
                    odd[node] -= mod;
                if(odd[node] < 0)
                    odd[node] += mod;
            }
        }
        int query(){
            long evenSum = even[1];
            long oddSum  = odd[1];
            long prod    = (evenSum * oddSum) % (1L * mod);
            return (int) prod;
        }
    }
    
    static SegmentTree segTree;
    static int ans[];
    
    static void dfs(int u , int par) {
        segTree.update(wt[u], 1);
        ans[u] = segTree.query();
        for(int v : adj[u])
            if(v != par)
                dfs(v, u);
        segTree.update(wt[u], -1);
    }
    
    private static void solve() {
        int N = nextInt();
        wt  = nextIntArrayOneBased(N);
        compress();
        int E = N - 1;
        int from[] = new int[E];
        int to[] = new int[E];
        while(E-->0) {
            from[E] = nextInt();
            to[E] = nextInt();
        }
        adj = packU(N, from, to, 1);
        int M = inv.length;
        segTree = new SegmentTree(M);
        ans = new int[N + 1];
        dfs(1, 0);
        for(int i = 1; i <= N; i++)
            println(ans[i]);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    /* 
     * Increase stack size in java
     */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new MathisonAndThePeculiarSums().run();
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
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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