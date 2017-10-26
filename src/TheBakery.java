import java.util.*;
import java.io.*;
public class TheBakery {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int arr[];
    static int dp[][];
    static int n;
    static HashMap<Query , Integer> memo;
    static class Query {
        int l , r;
        Query(int l , int r) {
            this.l = l;
            this.r = r;
        }
        @Override
        public int hashCode() {
            return Integer.hashCode(l) * 31 + Integer.hashCode(r);
        }
        @Override
        public boolean equals(Object obj) {
            Query that = (Query) obj;
            return l == that.l && r == that.r;
        }
    }
    static int cost(int L , int R) {
        Query q = new Query(L, R);
        Integer ret = memo.get(q);
        if(ret != null)
            return ret;
        else {
            int c = query(persistent[R], L, R, 0, n - 1);
            memo.put(q, c);
            return c;
        }
    }
    static int query(SegTreeNode node , int L , int R, int nl, int nr) {
        int mid = (nl + nr) >> 1;
        if(nl == L && nr == R)
            return node.size;
        else if(R <= mid)
            return query(node.left, L, R, nl, mid);
        else if(L > mid)
            return query(node.right, L, R, mid + 1 , nr);
        else
            return query(node.left, L, mid , nl , mid) + query(node.right, mid+1, R , mid+1,nr);
    }
    static void divideAndConquer(int lev , int L , int R , int optL , int optR) {
        if(L <= R) {
            int mid = (L + R) >> 1;
            int opt = -1;
            for(int m = Math.min(optR , mid) ; m >= optL; m--) {
                int relax = cost(m, mid) + dp[lev - 1][m - 1];
                if(relax > dp[lev][mid]) {
                    dp[lev][mid] = relax;
                    opt = m;
                }
            }
            // println("lev = " + lev + " mid = " + mid + " opt " + opt + " L " + L + " R "+ R + " optL " + optL + " optR " + optR);
            divideAndConquer(lev, L, mid - 1, optL, opt);
            divideAndConquer(lev, mid + 1, R, opt, optR);
        }
    }

    static class SegTreeNode {
        int size;
        SegTreeNode left , right;
        public SegTreeNode(int size) {
            this.size = size;
        }
        
        @Override
        public String toString() {
            return String.format("[sz = %d]", size);
        }
    }
    
    static SegTreeNode persistent[];
    static int last[];
    static SegTreeNode initSegTree(int L , int R) {
        if(L == R)
            return new SegTreeNode(last[arr[L]] == L ? 1 : 0);
        else {
            SegTreeNode newNode = new SegTreeNode(0);
            int mid = (L + R) >> 1;
            newNode.left = initSegTree(L, mid);
            newNode.right = initSegTree(mid + 1, R);
            newNode.size = newNode.left.size + newNode.right.size;
            return newNode;
        }
    }
    static SegTreeNode initSegTree(SegTreeNode prev , int L , int R , int index , int val) {
        if(L == R) 
            return new SegTreeNode(val);
        else {
            int mid = (L + R) >> 1;
            SegTreeNode l = prev.left;
            SegTreeNode r = prev.right;
            if(index <= mid)
                l = initSegTree(prev.left , L , mid , index , val);
            else
                r = initSegTree(prev.right, mid + 1, R, index , val);
            SegTreeNode newNode = new SegTreeNode(l.size + r.size);
            newNode.left = l;
            newNode.right = r;
            return newNode;
        }
    }
    private static void solve() {
        
        n = nextInt();
        int k = nextInt();
        arr = nextIntArray(n);
        dp = new int[k][n];
        last = new int[n + 1];
        memo = new HashMap<>();
        int next[] = new int[n];
        Arrays.fill(last, -1);
        for(int i = 0; i < n; i++) {
            next[i] = last[arr[i]];
            last[arr[i]] = i;
        }
        
        persistent = new SegTreeNode[n];
        persistent[n - 1] = initSegTree(0, n - 1);
        for(int i = n - 2; i >= 0; i--) {
            persistent[i] = initSegTree(persistent[i + 1], 0, n - 1, i + 1, 0);
            if(next[i + 1] >= 0)
                persistent[i] = initSegTree(persistent[i], 0, n - 1, next[i + 1], 1);
        }
        
        for(int i = 0; i < n; i++) 
            dp[0][i] = cost(0 , i);
        
        
        for(int i = 1; i < k; i++) 
            divideAndConquer(i, i, n - 1, i, n - 1);
        
        println(dp[k - 1][n - 1]);
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