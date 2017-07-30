import java.util.*;
import java.io.*;
public class MaximumValue {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class SparseTable1D {
        // < O(Nlog(N)) , O(1) > 0 based indexing
        int sparse[][];
        int len;

        static int log(int N) {
            return 31 - Integer.numberOfLeadingZeros(N);
        }

        SparseTable1D(int arr[]) {
            len = arr.length;
            int k = log(len) + 1;
            sparse = new int[k][len];
            for (int i = 0; i < len; i++)
                sparse[0][i] = arr[i];

            for (int i = 1; i < k; i++)
                for (int j = 0; j + (1 << i) <= len; j++)
                    sparse[i][j] = Math.max(sparse[i - 1][j], sparse[i - 1][j + (1 << (i - 1))]);

        }

        int getMax(int L, int R) {
            int sz = R - L + 1;
            int k = log(sz);
            int v1 = sparse[k][L];
            int v2 = sparse[k][L + (sz - (1 << k))];
            return Math.max(v1, v2);
        }

    }

    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int tree[];
        int len;
        int size;
        SegmentTree(int arr[]) { // arr should be a 1 based array
            len = arr.length - 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            build(arr, 1, 1, len);
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

                tree[node] = Math.max(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 1, len);
        }
        int getMax(int L , int R){
            return query(1, L, R, 1, len);
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
                return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R)
                tree[node] = arr[L];
            else
            {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = Math.max(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }

    private static void solve() {
        
        final int MAX = (int) 1e6;
        int arr[] = new int[MAX + 1];
        Arrays.fill(arr, -1);
        
        int N = nextInt();
        while(N-->0) {
            int a = nextInt();
            arr[a] = a;
        }
        
        SparseTable1D sparseTable = new SparseTable1D(arr); // 312 ms
        // SegmentTree segTree = new SegmentTree(arr); // 404 ms
        int max = 0;
        for(int i = 1; i <= MAX; i++)
            if(arr[i] > 0) {
                int localMax = 0;
                for(int j = 2 * i; j <= MAX && localMax != i - 1; j += i) 
                    localMax = Math.max(localMax , sparseTable.getMax(j - i, j - 1) % i);
                if(MAX % i != 0)
                    localMax = Math.max(localMax , sparseTable.getMax(MAX - (MAX % i) + 1, MAX) % i);
  
                max = Math.max(max , localMax);
            }
        
        println(max);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        // reader = new BufferedReader(new FileReader("/home/bhishmaraj/Documents/CODE/max_value.txt"));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        // long st = System.nanoTime();
        solve();
        // println("Time : " + (System.nanoTime() - st) / 1e9);
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