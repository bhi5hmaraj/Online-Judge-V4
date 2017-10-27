import java.util.*;
import java.io.*;
public class Strip {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int INF = (int) 1e6;
    
    static class SegmentTreeDiff  { 
        int treeMin[];
        int treeMax[];
        int len;
        int size;
        SegmentTreeDiff (int arr[]) { 
            len = arr.length;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            treeMax = new int[size];
            treeMin = new int[size];
            build(arr, 1, 0, len - 1);
        }
        int query(int L , int R){
            return queryMax(1, L, R, 0, len - 1) - queryMin(1, L, R, 0, len - 1);
        }
        int queryMin(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R)
                return treeMin[node];
            else if(R <= mid)
                return queryMin(2 * node, L, R, nl, mid);
            else if(L > mid)
                return queryMin((2*node)+1, L, R, mid + 1 , nr);
            else
                return Math.min(queryMin(2*node, L, mid , nl , mid) ,  queryMin((2*node)+1, mid+1, R , mid+1,nr));
        }
        int queryMax(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R)
                return treeMax[node];
            else if(R <= mid)
                return queryMax(2 * node, L, R, nl, mid);
            else if(L > mid)
                return queryMax((2*node)+1, L, R, mid + 1 , nr);
            else
                return Math.max(queryMax(2*node, L, mid , nl , mid) ,  queryMax((2*node)+1, mid+1, R , mid+1,nr));
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R) 
                treeMin[node] = treeMax[node] = arr[L];
            else
            {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                treeMin[node] = Math.min(treeMin[2*node] , treeMin[(2 * node) + 1]);
                treeMax[node] = Math.max(treeMax[2*node] , treeMax[(2 * node) + 1]);
            }
        }
    }
    
    static class SegmentTree  { 
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { 
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            Arrays.fill(tree, INF);
        }
        void update(int node,int idx,int val,int nl,int nr) {
            if(nl == nr && nl == idx)
                tree[node] = val;
            else {
                int mid = (nl + nr) >> 1;
                if(idx <= mid)
                    update(2*node, idx , val ,nl , mid);
                else
                    update((2*node) + 1, idx ,val , mid + 1, nr);

                tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 0, len - 1);
        }
        int query(int L , int R){
            return query(1, L, R, 0, len - 1);
        }
        int query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R)
                return tree[node];
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else
                return Math.min(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
    }
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int s = nextInt();
        int l = nextInt();
        int arr[] = nextIntArray(n);
        
        if(l > n) {
            println(-1); 
            return;
        }
        
        SegmentTree segTreeDP = new SegmentTree(n);
        SegmentTreeDiff segTreeDiff = new SegmentTreeDiff(arr);
        
        if(segTreeDiff.query(0, l - 1) <= s) 
            segTreeDP.update(l - 1, 1); 
        
        for(int i = l; i < n; i++) {
            int lo = 0 , hi = i - l + 1;
            int pos = -1;
            while(lo <= hi) {
                int mid = (lo + hi) >> 1;
                if(segTreeDiff.query(mid, i) > s) {
                    pos = mid;
                    lo = mid + 1;
                }
                else 
                    hi = mid - 1;
            }
            if(pos == -1)
                segTreeDP.update(i, 1);
            else if(i - pos >= l) 
                segTreeDP.update(i, Math.min(segTreeDP.query(pos, i - l) + 1 , INF));
        }
        
        int ans = segTreeDP.query(n - 1, n - 1);
        println(ans == INF ? -1 : ans);
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