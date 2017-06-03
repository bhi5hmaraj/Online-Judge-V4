import java.util.*;
import java.io.*;
class MEXDIV {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * Range Mex : https://stackoverflow.com/a/41635805
     */
    
    static class CustomSegmentTree { 
        int tree[];
        int len;
        int size;
        CustomSegmentTree(int len) { // arr should be a 1 based array
            this.len = len + 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(this.len - 1) + 1);  // ceil(log(len)) + 1
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

                tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 0, len);
        }
        int query(int value){
            return query(1, value, 0, len);
        }
        int query(int node , int value, int nl, int nr) {
            int mid = (nl + nr) / 2;
            if(nl == nr)
                return nl;
            else if(tree[2 * node] < value)
                return query(2 * node, value, nl, mid);
            else    
                return query((2*node)+1, value, mid + 1 , nr);
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R)
                tree[node] = arr[L];
            else
            {
                int mid = L + ((R-L)/2);
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = Math.min(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }
    
    
    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) (1e9) + 7; // Default
        static long sub(long a, long b) {return (a - b  + mod) % mod;}
        static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
        static long add(long a, long b) {return (a + b) % mod;}
        static long div(long a, long b) {return mul(a, modInverse(b));}
        static long modInverse(long n)  {return modPow(n, mod - 2);} // Fermat's little theorem
        static long modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return pow;
        }
    }

    
    private static void solve() {
        
        int N = nextInt();
        int K = nextInt();
        
        int arr[] = nextIntArray(N);
        for(int i = 0; i < N; i++)
            arr[i] = Math.min(arr[i] , N);
        
        CustomSegmentTree tree = new CustomSegmentTree(N);
        //r+1 - l
        long DP[] = new long[N];
        long prefixDP[] = new long[N + 1];
        prefixDP[1] = DP[0] = arr[0] == 0 && K == 0 ? 0 : 1;
        tree.update(arr[0], 0);
        
        for(int i = 1; i < N; i++) {
            tree.update(arr[i], i);
            int lo = 0 , hi = i - 1;
            int last = -1;
            while(lo <= hi) {
                int mid = (lo + hi) / 2;
                int mex = tree.query(mid);
                if(mex <= K) {
                    hi = mid - 1;
                    last = mid;
                } else
                    lo = mid + 1;
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