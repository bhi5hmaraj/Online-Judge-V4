import java.util.*;
import java.io.*;
public class ImbalancedArray {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

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
        
        int MAX = (int) 1e6;
        int N = nextInt();
        int arr[] = nextIntArray(N);
        SegmentTree segTree = new SegmentTree(MAX);
        
        long DPmin[] = new long[N];
        long DPmax[] = new long[N];
        long imbalance = 0;
        
        for(int i = 0; i < N; i++) {
            int floorIdx = segTree.query(1, arr[i] - 1);
            int ceilIdx = segTree.query(arr[i] + 1, MAX);
            DPmax[i] = (1L * arr[i] * (i - ceilIdx)) + (ceilIdx >= 0 ? DPmax[ceilIdx] : 0);
            DPmin[i] = (1L * arr[i] * (i - floorIdx)) + (floorIdx >= 0 ? DPmin[floorIdx] : 0);
            imbalance += DPmax[i] - DPmin[i];
            segTree.update(arr[i], i);
        }
        
        println(imbalance);
        
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