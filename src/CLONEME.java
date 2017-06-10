import java.util.*;
import java.io.*;
class CLONEME {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int p1 = 100151 , m1 = (int) 1e9 + 7;
    static final int p2 = p1 + 2 , m2 = m1 + 2;
    static final int MAX = (int) 1e5;
    static final int pow1[] = new int[MAX + 1];
    static final int pow2[] = new int[MAX + 1];
    
    static {
        pow1[0] = pow2[0] = 1;
        for(int i = 1; i <= MAX; i++) {
            pow1[i] = (int) ((1L * pow1[i - 1] * p1) % (long)(m1));
            pow2[i] = (int) ((1L * pow2[i - 1] * p2) % (long)(m2));
        }
    }
    
    static class SegTreeNode {
        int size , hash1 , hash2;
        SegTreeNode left , right;
        public SegTreeNode(int size , int hash1 , int hash2) {
            this.size = size;
            this.hash1 = hash1;
            this.hash2 = hash2;
        }
        
        @Override
        public String toString() {
            return String.format("[sz = %d , h1 = %d , h2 = %d", size , hash1 , hash2);
        }
    }
    
    static SegTreeNode persistent[];
    static SegTreeNode initSegTree(int L , int R) {
        if(L == R)
            return new SegTreeNode(0, 0, 0);
        else {
            SegTreeNode newNode = new SegTreeNode(0, 0, 0);
            int mid = (L + R) >> 1;
            newNode.left = initSegTree(L, mid);
            newNode.right = initSegTree(mid + 1, R);
            return newNode;
        }
    }
    static SegTreeNode initSegTree(SegTreeNode prev , int L , int R , int val) {
        if(L == R) 
            return new SegTreeNode(1, pow1[val], pow2[val]);
        else {
            int mid = (L + R) >> 1;
            SegTreeNode l = prev.left;
            SegTreeNode r = prev.right;
            if(val <= mid)
                l = initSegTree(prev , L , mid , val);
            else
                r = initSegTree(prev, mid + 1, R, val);
            SegTreeNode newNode = new SegTreeNode(l.size + r.size, (l.hash1 + r.hash1) % m1, (l.hash2 + r.hash2) % m2);
            newNode.left = l;
            newNode.right = r;
            return newNode;
        }
    }
    
    static boolean equals(SegTreeNode... n) {
        
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            int Q = nextInt();
            int arr[] = nextIntArrayOneBased(N);
            persistent[0] = initSegTree(1, N);
            for(int i = 1; i <= N; i++)
                persistent[i] = initSegTree(persistent[i - 1], 1, N, arr[i]);
            
            while(Q-->0) {
                SegTreeNode L1 = persistent[nextInt()];
                SegTreeNode R1 = persistent[nextInt()];
                SegTreeNode L2 = persistent[nextInt()];
                SegTreeNode R2 = persistent[nextInt()];
                
                if((R1.hash1 - L1.hash1 + m1) % m1 == (R2.hash1 - L2.hash1 + m1) % m1 &&
                   (R1.hash2 - L1.hash2 + m2) % m2 == (R2.hash2 - L2.hash2 + m2) % m2)
                    println("YES");
                else {
                    
                }
            }
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