import java.util.*;
import java.io.*;
public class poj_2127 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class FenwickTreeRMQ {
        /*
         * Stores max(1 ... i)
         */
        int tree[];
        int len;

        FenwickTreeRMQ(int len) {
            this.len = len;
            tree = new int[len + 10];
        }

        void update(int idx, int val) {
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] = Math.max(tree[idx] , val);
        }

        int query(int idx) {
            int max = 0;
            for (; idx > 0; idx -= (idx & -idx))
                max = Math.max(max , tree[max]);

            return max;
        }
    }


    static class SegmentTree  { 
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
        }
        void update(int node,int idx,int val,int nl,int nr) {
            if(nl == nr)
                tree[node] = Math.max(tree[idx] , val);
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
        int query(int L , int R){
            if(L > R) return 0;
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
    }

    
    private static void solve() {
        
        int n = nextInt();
        int A[] = nextIntArray(n);  // small
        int m = nextInt();
        int B[] = nextIntArray(m);  // big
        
        if(m < n) {
            int tA[] = A;
            int tI = n;
            A = B;
            n = m;
            B = tA;
            m = tI;
        }
        
        TreeSet<Integer> set = new TreeSet<>();
        for(int a : A) set.add(a);
        for(int b : B) set.add(b);
        
        HashMap<Integer , Integer> compress = new HashMap<>();
        for(int num : set) compress.put(num, compress.size() + 1);
        
        for(int i = 0; i < n; i++) A[i] = compress.get(A[i]);
        for(int i = 0; i < m; i++) B[i] = compress.get(B[i]);
        
        SegmentTree segTree = new SegmentTree(compress.size());
        int DP[][] = new int[n][m];
        
        
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