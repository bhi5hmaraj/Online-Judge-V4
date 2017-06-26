import java.util.*;
import java.io.*;
public class NHSPCTaskG {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class MergeSortTree  { // Implemented to store min in a range , point update and range query
        int tree[][];
        boolean lazy[];
        int len;
        int size;
        final int INF = (int) 1e6;
        MergeSortTree(int arr[]) { // arr should be a 1 based array
            len = arr.length - 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size][];
            lazy = new boolean[size];
            build(arr, 1, 1, len);
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

                tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 1, len);
        }
        int query(int L , int R){
            return query(1, L, R, 1, len);
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
                return Math.min(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
        
        boolean propagate(int node) {
            boolean ret = lazy[node];
            if(2 * node < size && 2 * node + 1 < size) {
                lazy[2 * node] ^= true;
                lazy[2 * node + 1] ^= true;
            }
            lazy[node] = false;
            return ret;
        }
        
        int ceil(int node , int L , int R , int nl , int nr , int val) {
            boolean rev = propagate(node);
            int mid = (nl + nr) / 2;
            if(L == nl && R == nr) {
                int lo = 0 , hi = R - L;
                int ceil_ = INF;
                while(lo <= hi) {
                    int m = (lo + hi) >> 1;
                    if(tree[node][m] >= val) {
                        ceil_ = tree[node][m];
                        if(!rev)
                            hi = m - 1;
                        else
                            lo = m + 1;
                    }
                    else {
                        if(!rev)
                            lo = m + 1;
                        else
                            hi = m - 1;
                    }
                }
                return ceil_;
            }
            else if(R <= mid)
                return ceil(2 * node, L, R, nl, mid , val);
            else if(L > mid)
                return ceil((2*node)+1, L, R, mid + 1 , nr , val);
            else
                return Math.min(ceil(2 * node, L, R, nl, mid , val) ,  ceil((2*node)+1, L, R, mid + 1 , nr , val));
            
        }
        
        int floor(int node , int L , int R , int nl , int nr , int val) {
            boolean rev = propagate(node);
            int mid = (nl + nr) / 2;
            if(L == nl && R == nr) {
                int lo = 0 , hi = R - L;
                int floor_ = -INF;
                while(lo <= hi) {
                    int m = (lo + hi) >> 1;
                    if(tree[node][m] <= val) {
                        floor_ = tree[node][m];
                        if(!rev)
                            hi = m - 1;
                        else
                            lo = m + 1;
                    }
                    else {
                        if(!rev)
                            lo = m + 1;
                        else
                            hi = m - 1;
                    }
                }
                return ceil_;
            }
            else if(R <= mid)
                return ceil(2 * node, L, R, nl, mid , val);
            else if(L > mid)
                return ceil((2*node)+1, L, R, mid + 1 , nr , val);
            else
                return Math.min(ceil(2 * node, L, R, nl, mid , val) ,  ceil((2*node)+1, L, R, mid + 1 , nr , val));
            
        }
        
        
        void build(int arr[],int node,int L,int R) {
            if(L == R) {
                tree[node] = new int[1];
                tree[node][0] = Integer.bitCount(arr[L]);
            }
            else {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = merge(tree[2 * node] , tree[2 * node + 1]);
            }
        }
        
        private int[] merge(int A[] , int B[]) {
            int C[] = new int[A.length + B.length];
            int k = 0;
            for (int i = 0, j = 0; i < A.length || j < B.length;) {
                if (i < A.length && (j >= B.length || A[i] <= B[j]))
                    C[k++] = A[i++];
                else if (j < B.length && (i >= A.length || B[j] < A[i]))
                    C[k++] = B[j++];
            }
            
            return C;
        }
        
    }

    
    private static void solve() {
        
        
        
        
        
        
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