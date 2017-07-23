import java.util.*;
import java.io.*;
class CHNGSUM {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * CF Edu round 23 http://codeforces.com/contest/817/problem/D
     * http://codeforces.com/contest/817/submission/27809774
     * 
     */
    static int[] compress(int arr[]) {
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(arr).forEach(set::add);
        HashMap<Integer , Integer> map = new HashMap<>();
        set.stream().forEach(a -> map.put(a, map.size() + 1));
        arr = Arrays.stream(arr).map(map::get).toArray();
        int inv[] = new int[map.size() + 1];
        map.forEach((k , v) -> inv[v] = k);
        return inv;
    }
    

    static class SegmentTree  { 
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { 
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            Arrays.fill(tree, -1);
        }
        void update(int node,int idx,int val,int nl,int nr) {
            if(nl == nr && nl == idx)
                tree[node] = val;
            else {
                int mid = (nl + nr) / 2;
                if(idx <= mid)
                    update(2*node, idx , val ,nl , mid);
                else
                    update((2*node) + 1, idx ,val , mid + 1, nr);

                tree[node] = Math.max(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 1, len);
        }
        int query(int L , int R){
            return L <= R ? query(1, L, R, 1, len) : -1;
        }
        int query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) / 2;
            if(nl == L && nr == R)
                return tree[node];
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else
                return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
    }

    
    private static void solve() {
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int inv[] = compress(arr);
        
        int MAX = (int) 1e6;
        long DPmin[] = new long[n];
        long DPmax[] = new long[n];
        long MOD = (long) 1e9 + 7;
        
        SegmentTree segTree = new SegmentTree(MAX);
        int rev[] = new int[n];
        for(int i = 0; i < n; i++)
            rev[i] = arr[n - i - 1];
        
        for(int i = 0; i < n; i++) {
            int floorIdx = segTree.query(1, rev[i] - 1);
            DPmin[i] = (((1L * inv[rev[i]] * (i - floorIdx)) % MOD) + (floorIdx >= 0 ? DPmin[floorIdx] : 0)) % MOD;
            segTree.update(rev[i], i);
        }
        
        segTree = new SegmentTree(MAX);
        for(int i = 0; i < n; i++) {
            int ceilIdx = segTree.query(arr[i] + 1, MAX);
            DPmax[i] = (((1L * inv[arr[i]] * (i - ceilIdx)) % MOD) + (ceilIdx >= 0 ? DPmax[ceilIdx] : 0)) % MOD;
            segTree.update(arr[i], i);
        }
        
        for(int i = 0; i < n / 2; i++) {
            long temp = DPmin[i];
            DPmin[i] = DPmin[n - i - 1];
            DPmin[n - i - 1] = temp;
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