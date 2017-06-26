import java.util.*;
import java.io.*;
public class NHSPCTaskG {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class MergeSortTree  { 
        int tree[][];
        boolean lazy[];
        int len;
        int size;
        final int INF = (int) 1e6;
        final int MASK = 20;
        int parity[];
        
        static class Pair {
            int idx , p;
            Pair(int id , int pp) {
                idx = id;
                p = pp;
            }
            static Pair max(Pair a , Pair b) {
                if(a.p == b.p)
                    return a.idx < b.idx ? a : b;
                else if(a.p < b.p)
                    return b;
                else
                    return a;
            }
            static Pair min(Pair a , Pair b) {
                if(a.p == b.p) 
                    return a.idx < b.idx ? a : b;
                else if(a.p < b.p)
                    return a;
                else
                    return b;
            }
        }
        
        MergeSortTree(int arr[]) { // arr should be a 1 based array
            len = arr.length - 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size][];
            lazy = new boolean[size];
            parity = new int[len + 2];
            parity[0] = -INF;
            parity[len + 1] = INF;
            for(int i = 1; i <= len; i++)
                parity[i] = Integer.bitCount(arr[i]);
            build(arr, 1, 1, len);
        }
        
        void update(int node,int L , int R , int nl,int nr) {
            if(nl == L && nr == R)
                lazy[node] ^= true;
            else {
                int mid = (nl + nr) >> 1;
                propagate(node);
                if(R <= mid)
                    update(2 * node, L, R, nl, mid);
                else if(L > mid)
                    update((2*node) + 1, L, R, mid + 1 , nr);
                else {
                    update(2 * node, L, mid, nl, mid);
                    update(2 * node + 1, mid + 1, R, mid + 1, nr);
                }
            }
        }
        void update(int L , int R){
            update(1, L, R, 1, len);
        }
        boolean propagate(int node) {
            boolean ret = lazy[node];
            if(lazy[node] && 2 * node < size && 2 * node + 1 < size) {
                lazy[2 * node] ^= true;
                lazy[2 * node + 1] ^= true;
            }
            lazy[node] = false;
            return ret;
        }
        int ceil(int A[] , int key) {
            int lo = 0 , hi = A.length - 1;
            int pos = len + 1;
            while(lo <= hi) {
                int m = (lo + hi) >> 1;
                if(parity[A[m]] >= key) {
                    pos = A[m];
                    hi = m - 1;
                }
                else 
                    lo = m + 1;
            }
            return pos;
        }
        int floor(int A[] , int key) {
            int lo = 0 , hi = A.length - 1;
            int pos = 0;
            while(lo <= hi) {
                int m = (lo + hi) >> 1;
                if(parity[A[m]] <= key) {
                    pos = A[m];
                    if(parity[A[m]] < key)
                        lo = m + 1;
                    else
                        hi = m - 1;
                }
                else 
                    hi = m - 1;
            }
            return pos;
        }
        
        int ceil(int node , int L , int R , int nl , int nr , int val) {
            boolean rev = propagate(node);
            int mid = (nl + nr) / 2;
            if(L == nl && R == nr) 
                return rev ? MASK - floor(tree[node], MASK - val) : ceil(tree[node], val);
            else if(R <= mid)
                return ceil(2 * node, L, R, nl, mid , val);
            else if(L > mid)
                return ceil((2*node)+1, L, R, mid + 1 , nr , val);
            else
                return Math.min(ceil(2 * node, L, mid, nl, mid , val) ,
                                ceil((2*node)+1, mid + 1, R, mid + 1 , nr , val));
            
        }
        
        int floor(int node , int L , int R , int nl , int nr , int val) {
            boolean rev = propagate(node);
            int mid = (nl + nr) / 2;
            if(L == nl && R == nr) 
                return rev ? MASK - ceil(tree[node], MASK - val) : floor(tree[node], val);
            else if(R <= mid)
                return floor(2 * node, L, R, nl, mid , val);
            else if(L > mid)
                return floor((2*node)+1, L, R, mid + 1 , nr , val);
            else
                return Math.max(floor(2 * node, L, mid, nl, mid , val), 
                                floor((2*node)+1, mid + 1, R, mid + 1 , nr , val));
            
        }
        
        
        void build(int arr[],int node,int L,int R) {
            if(L == R) {
                tree[node] = new int[1];
                tree[node][0] = L;
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
                if (i < A.length && (j >= B.length || parity[A[i]] <= parity[B[j]]))
                    C[k++] = A[i++];
                else if (j < B.length && (i >= A.length || parity[B[j]] < parity[A[i]]))
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