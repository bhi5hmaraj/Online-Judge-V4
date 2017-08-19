import java.util.*;
import java.io.*;
public class DexterandGangs_Persistent {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class SegTreeNode {
        int cnt;
        SegTreeNode left , right;
        public SegTreeNode(int cnt) {
            this.cnt = cnt;
        }
        
        @Override
        public String toString() {
            return String.format("[cnt = %d]", cnt);
        }
    }
    
    static SegTreeNode persistent[];
    static SegTreeNode initSegTree(int L , int R) {
        if(L == R)
            return new SegTreeNode(0);
        else {
            SegTreeNode newNode = new SegTreeNode(0);
            int mid = (L + R) >> 1;
            newNode.left = initSegTree(L, mid);
            newNode.right = initSegTree(mid + 1, R);
            return newNode;
        }
    }
    static SegTreeNode initSegTree(SegTreeNode prev , int L , int R , int val) {
        if(L == R) 
            return new SegTreeNode(1 + prev.cnt);
        else {
            int mid = (L + R) >> 1;
            SegTreeNode l = prev.left;
            SegTreeNode r = prev.right;
            if(val <= mid)
                l = initSegTree(prev.left , L , mid , val);
            else
                r = initSegTree(prev.right, mid + 1, R, val);
            SegTreeNode newNode = new SegTreeNode(l.cnt + r.cnt);
            newNode.left = l;
            newNode.right = r;
            return newNode;
        }
    }
    
    static int[] compress(int arr[] , int one) {
        int temp[] = Arrays.copyOf(arr, arr.length);
        Arrays.sort(temp);
        int sz = 0;
        for(int i = one; i < temp.length; ) {
            int curr = temp[i];
            sz++;
            for(; i < temp.length && temp[i] == curr; i++)
                ;
        }
        int inv[] = new int[sz];
        sz = 0;
        for(int i = one; i < temp.length; ) {
            int curr = temp[i];
            inv[sz++] = curr;
            for(; i < temp.length && temp[i] == curr; i++)
                ;
        }
        for(int i = one; i < arr.length; i++)
            arr[i] = Arrays.binarySearch(inv, arr[i]);
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

    /* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
    static int DP[][]; // One based vertices
    static int level[];
    static int parent[];
    static int V;
    static ArrayList<Integer>[] adj;
    static int G[];
    static int M;
    static void dfs(int u , int par , int lev) {
        level[u] = lev;
        parent[u] = par;
        persistent[u] = initSegTree(persistent[par], 0, M - 1, G[u]);
        for(int v : adj[u])
            if(v != par)
                dfs(v, u, lev + 1);
    }
    @SuppressWarnings("unchecked")
    private static void solve() {

        V = nextInt();
        int Q = nextInt();
        G = nextIntArrayOneBased(V);
        int inv[] = compress(G , 1);
        /*
        System.out.println(Arrays.toString(G));
        System.out.println(Arrays.toString(inv));
        */
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
        M = inv.length;
        persistent = new SegTreeNode[V + 1];
        persistent[0] = initSegTree(0, M - 1);
        dfs(1, 0, 0);
        binaryLift();
        
        while(Q-->0) {
            int p = nextInt();
            int q = nextInt();
            int lca = LCA(p, q);
            int len = level[p] + level[q] - 2 * level[lca] + 1;
            int medianCnt = len / 2 + 1;
            int l = 0 , r = M - 1;
            SegTreeNode pNode = persistent[p];
            SegTreeNode qNode = persistent[q];
            SegTreeNode lcaNode = persistent[lca];
            while(pNode.left != null) {
                int currCnt = pNode.left.cnt + qNode.left.cnt - 2 * lcaNode.left.cnt + (G[lca] >= l && G[lca] <= ((l + r) >> 1) ? 1 : 0);
                // println("l " + l +  " r " + r + " cnt " + currCnt);
                if(currCnt >= medianCnt) {
                    r = (l + r) >> 1;
                    pNode = pNode.left;
                    qNode = qNode.left;
                    lcaNode = lcaNode.left;
                } else {
                    l = ((l + r) >> 1) + 1;
                    medianCnt -= currCnt;
                    pNode = pNode.right;
                    qNode = qNode.right;
                    lcaNode = lcaNode.right;
                }
            }
            // println("final l " + l +  " r " + r);
            if(pNode.cnt + qNode.cnt - 2 * lcaNode.cnt + (G[lca] >= l && G[lca] <= r ? 1 : 0) >= (len / 2 + 1)) 
                println("D " + inv[l]);
            else 
                println("S");
            
        }
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new DexterandGangs_Persistent().run();
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