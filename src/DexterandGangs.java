import java.util.*;
import java.io.*;
public class DexterandGangs  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int freq[];
/*    static SegmentTree segTreeFreq;
    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
        }
        void update(int node,int idx,int nl,int nr) {
            if(nl == nr && nl == idx)
                tree[node] = idx;
            else {
                int mid = (nl + nr) >> 1;
                if(idx <= mid)
                    update(2*node, idx ,nl , mid);
                else
                    update((2*node) + 1, idx , mid + 1, nr);
                
                tree[node] = freq[tree[2*node]] > freq[tree[2 * node + 1]] ? tree[2 * node] : tree[2 * node + 1];
            }
        }
        void update(int idx , int val){
            freq[idx] += val;
            update(1, idx, 0, len - 1);
        }
        int query(){
            return tree[1];
        }
        int query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R)
                return tree[node];
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else {
                int l = query(2*node, L, mid , nl , mid); 
                int r = query((2*node)+1, mid+1, R , mid+1,nr);
                return freq[l] > freq[r] ? l : r;
            }
        }
    }
    
    */
    /* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
    static int DP[][]; // One based vertices
    static int level[];
    static int parent[];
    static int clock , eulerTour[] , start[] , end[];
    static int[] compress(int arr[]) {
        HashMap<Integer , Integer> map = new HashMap<>();
        for(int i = 1; i < arr.length; i++)
            map.putIfAbsent(arr[i], map.size());
        // System.out.println(map);
        int inv[] = new int[map.size()];
        for(int i = 1; i < arr.length; i++) {
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
        eulerTour[clock] = u;
        start[u] = clock++;
        level[u] = lev;
        parent[u] = par;
        for(int v : adj[u])
            if(v != par)
                dfs(v, u, lev + 1);
        eulerTour[clock] = u;
        end[u] = clock++;
    }

    static class Query {
        int L , R , LCA , id;

        public Query(int l, int r, int lCA , int id) {
            L = l;
            R = r;
            LCA = lCA;
            this.id = id;
        }
        @Override
        public String toString() {
            return String.format("[L = %d R = %d LCA = %d id = %d]", L , R , LCA , id);
        }
    }
    
    static class MoComparator implements Comparator<Query> {
        @Override
        public int compare(Query o1, Query o2) {
            if(blockCache[o1.L] != blockCache[o2.L])
                return blockCache[o1.L] - blockCache[o2.L];
            else 
                return blockCache[o1.R] - blockCache[o2.R];
        }
    }
    
    static void visit(int idx) {
        segTreeFreq.update(G[idx], marked[idx] ? -1 : 1);
        marked[idx] = !marked[idx];
    }
    
    static boolean marked[];
    static int blockCache[];
    static int G[];
    static ArrayList<Integer>[] adj;
    static int V;
    // Inspired By DISTNUM3
    private static void solve() {  
        
        V = nextInt();
        int Q = nextInt();
        G = nextIntArrayOneBased(V);
        int inv[] = compress(G);
        // System.out.println(Arrays.toString(G));
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
        eulerTour = new int[2 * V];
        start = new int[(V + 1)];
        end = new int[(V + 1)];
        marked = new boolean[V + 1];
        freq = new int[inv.length];
        segTreeFreq = new SegmentTree(inv.length);
        clock = 0;
        dfs(1, 0, 0);
        binaryLift();
        /*
        System.out.println("start " + Arrays.toString(start));
        System.out.println("end " + Arrays.toString(end));
        System.out.println("eulerTour " + Arrays.toString(eulerTour));
        */
        int ans[] = new int[Q];
        Query queries[] = new Query[Q];
        for(int i = 0; i < Q; i++) {
            int u = nextInt();
            int v = nextInt();
            Query q;
            if(end[u] < start[v])   // Cousin Nodes
                q = new Query(end[u], start[v], LCA(u, v), i);
            else if(start[u] > end[v])
                q = new Query(end[v], start[u], LCA(u, v), i);
            else            // Ancestors
                q = new Query(Math.min(start[u],start[v]), Math.max(start[u],start[v]), -1, i);
            queries[i] = q;
        }
        
        int BLOCK_SIZE = (int) Math.sqrt(2 * V) + 1;
        blockCache = new int[2 * (V + 1)];
        for(int i = 0; i < blockCache.length; i++)
            blockCache[i] = i / BLOCK_SIZE;
        
        Arrays.sort(queries, new MoComparator());
        // println(Arrays.toString(queries));
        int moLeft = -1 , moRight = -1;
        for(Query q : queries) {
            while(moLeft < q.L - 1) {
                moLeft++;
                visit(eulerTour[moLeft]);
            }
            while(moLeft >= q.L) {
                visit(eulerTour[moLeft]);
                moLeft--;
            }
            while(moRight < q.R) {
                moRight++;
                visit(eulerTour[moRight]);
            }
            while(moRight > q.R) {
                visit(eulerTour[moRight]);
                moRight--;
            }

            if(q.LCA != -1) visit(q.LCA);
            
            int x = segTreeFreq.query();
            int lca = q.LCA == -1 ? LCA(eulerTour[q.L], eulerTour[q.R]) : q.LCA;
            int n = level[eulerTour[q.L]] + level[eulerTour[q.R]] - 2 * level[lca] + 1;
            ans[q.id] = freq[x] > n / 2 ? inv[x] : -1;
            
            if(q.LCA != -1) visit(q.LCA);
        }
        
        for(int a : ans)
            println(a < 0 ? "S" : ("D " + a));
    }
 /*   
    static void brute() {
         // May the (Brute) force be with you
        // https://en.wikipedia.org/wiki/Boyer%E2%80%93Moore_majority_vote_algorithm
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
    */
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