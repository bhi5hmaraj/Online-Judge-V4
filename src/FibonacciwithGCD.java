import java.util.*;
import java.io.*;
public class FibonacciwithGCD {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }

    static class SegmentTree  { 
        int tree[];
        int len;
        int size;
        SegmentTree(int arr[]) { 
            len = arr.length - 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            build(arr, 1, 1, len);
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
                return gcd(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R)
                tree[node] = arr[L];
            else
            {
                int mid = L + ((R-L)/2);
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = gcd(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }
    
    static final long mod = (long) 1e9 + 7;
    
    static class Matrix {

        long e00, e01, e10, e11;

        Matrix(long a, long b, long c, long d) {
            e00 = a;
            e01 = b;
            e10 = c;
            e11 = d;
        }
        Matrix() {}
        void setUnit() {
            e00 = e11 = 1;
            e01 = e10 = 0;
        }
        boolean isUnit() {
            return e00 == 1 && e11 == 1 && e01 == 0 && e10 == 0;
        }
        
        void multiply(Matrix t) {
            long a = (((e00 * t.e00) % mod) + ((e01 * t.e10) % mod)) % mod;
            long b = (((e00 * t.e01) % mod) + ((e01 * t.e11) % mod)) % mod;
            long c = (((e10 * t.e00) % mod) + ((e11 * t.e10) % mod)) % mod;
            long d = (((e10 * t.e01) % mod) + ((e11 * t.e11) % mod)) % mod;
            e00 = a;
            e01 = b;
            e10 = c;
            e11 = d;
        }
        public String toString() {
            return e00 + " " + e01 + "\n" + e10 + " " + e11 + "\n";
        }
        public static Matrix pow(Matrix mat , int b) {
            Matrix ans = new Matrix();
            ans.setUnit();
            Matrix m   = new Matrix(mat.e00, mat.e01, mat.e10, mat.e11);
            while(b > 0) {
                if((b & 1) == 1)
                    ans.multiply(m);
                m.multiply(m);
                b >>= 1;
            }
            return ans;
        }

    }
    
    static long fib(int n) {
        if(n <= 2)
            return 1;
        else {
            Matrix f = Matrix.pow(new Matrix(1, 1, 1, 0), n - 2);
            Matrix base = new Matrix(1, 0, 1, 0);
            f.multiply(base);
            return f.e00;
        }
    }
    
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        int arr[] = nextIntArrayOneBased(N);
        SegmentTree segTree = new SegmentTree(arr);
        
        while(Q-->0)
            println(fib(segTree.query(nextInt(), nextInt())));
        
        
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