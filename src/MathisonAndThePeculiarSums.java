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
                    even[node] = (even[node] + inv[idx]) % mod;
                else
                    odd[node] = (odd[node] + inv[idx]) % mod;
            }
            else {
                if(size[node] % 2 == 1)
                    even[node] = (even[node] - inv[idx] + mod) % mod;
                else 
                    odd[node] = (odd[node] - inv[idx] + mod) % mod;
            }
            size[node] += val;
            for(node >>= 1; node > 0; node >>= 1) {
                size[node] = size[2 * node] + size[2 * node + 1];
                if(size[2 * node] % 2 == 0) {
                    even[node] = (even[2 * node] + even[2 * node + 1]) % mod;
                    odd[node] = (odd[2 * node] + odd[2 * node + 1]) % mod;
                }
                else {
                    even[node] = (even[2 * node] + odd[2 * node + 1]) % mod;
                    odd[node] = (odd[2 * node] + even[2 * node + 1]) % mod;
                }
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
    
    private static void solve() throws IOException {
        FasterScanner scan = new FasterScanner();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        int N = scan.nextInt();
        wt = new int[N + 1];
        for(int i = 1; i <= N; i++)
            wt[i] = scan.nextInt();
        compress();
        int E = N - 1;
        int from[] = new int[E];
        int to[] = new int[E];
        while(E-->0) {
            from[E] = scan.nextInt();
            to[E] = scan.nextInt();
        }
        adj = packU(N, from, to, 1);
        int M = inv.length;
        segTree = new SegmentTree(M);
        ans = new int[N + 1];
        dfs(1, 0);
        for(int i = 1; i <= N; i++)
            out.println(ans[i]);
        out.close();
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    /* 
     * Increase stack size in java
     */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                try {
                    new MathisonAndThePeculiarSums().run();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run() throws IOException{ 
        solve();
    }
    

    static class FasterScanner {
        private byte[] buf = new byte[1024];
        private int tmp_curChar;
        private int tmp_numChars;

        public int read() {
            if (tmp_numChars == -1)
                throw new InputMismatchException();
            if (tmp_curChar >= tmp_numChars) {
                tmp_curChar = 0;
                try {
                    tmp_numChars = System.in.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (tmp_numChars <= 0)
                    return -1;
            }
            return buf[tmp_curChar++];
        }

        public int nextInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        private boolean isSpaceChar(int c) {
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        private boolean isEndOfLine(int c) {
            return c == '\n' || c == '\r' || c == -1;
        }
    }

    /************************ TEMPLATE ENDS HERE ************************/
    
}