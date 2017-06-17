import java.util.*;
import java.io.*;
public class TillICollapse {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
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
    static int arr[] , last[];
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
    static int selection(SegTreeNode root , int k , int l , int r) {
        int sel = -1;
        while(l <= r && root != null) {
            int m = (l + r) >> 1;
            if(k >= (root.left == null ? 0 : root.left.size)) {
                k -= (root.left == null ? 0 : root.left.size);
                l = m + 1;
                sel = m;
                root = root.right;
            }
            else {
                r = m;
                root = root.left;
            }
        }
        return sel;
    }
    
    static void print(SegTreeNode root , int l , int r) {
        System.out.println("l " + l + " r " + r + " sz " + root.size + " " + (l == r ? "*" : ""));
        if(l < r) {
            int m = (l + r) >> 1;
            print(root.left, l, m);
            print(root.right , m + 1 , r);
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        arr = Arrays.copyOf(nextIntArray(n), n + 1);    // small trick so that selection works properly
        n++;                                            // having a distinct number at the last
        last = new int[n + 1];
        int next[] = new int[n];
        Arrays.fill(last, n);
        for(int i = n - 1; i >= 0; i--) {
            next[i] = last[arr[i]];
            last[arr[i]] = i;
        }
        
        persistent = new SegTreeNode[n];
        persistent[0] = initSegTree(0, n - 1);
        for(int i = 1; i < n; i++) {
            persistent[i] = initSegTree(persistent[i - 1], 0, n - 1, i - 1, 0);
            if(next[i - 1] < n)
                persistent[i] = initSegTree(persistent[i], 0, n - 1, next[i - 1], 1);
        }
        
        for(int k = 1; k < n; k++) {
            int cnt = 0;
            int ptr = 0;
            while(ptr < n - 1) {
                cnt++;
                ptr = selection(persistent[ptr], k, 0, n - 1);
            }
            print(cnt + " ");
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