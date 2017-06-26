import java.util.*;
import java.io.*;
public class NHSPCTaskG {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX_LEN = 20;
    static class SegmentTreeLazyPropagation {
        int tree[];
        boolean lazy[];
        int len;
        int size;
        SegmentTreeLazyPropagation(int arr[]) {
            len = arr.length - 1;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            lazy = new boolean[size];
            build(arr, 1, 1, len);
        }
        
        int reverse(int n) {
            int rev = 0;
            for(int i = 0; i <= MAX_LEN; i++)
                if((n & (1 << i)) != 0)
                    rev |= 1 << (MAX_LEN - i);
            return rev;
        }
        void propagate(int node) {
            if (lazy[node]) {
                if (2 * node < size && 2 * node + 1 < size) {
                    lazy[2 * node] ^= true;
                    lazy[2 * node + 1] ^= true;
                }
                tree[node] = reverse(tree[node]);
                lazy[node] = false;
            }
        }
        int query(int L , int R) {
            return query(1, L, R, 1, len);
        }
        void update(int L , int R) {
            update(1, L, R, 1, len);
        }
        
        int query(int L , int R , int parity) {
            while(L < R) {
                int mid = (L + R) >> 1;
                if((query(L, mid) & (1 << parity)) != 0)
                    R = mid;
                else
                    L = mid + 1;
            }
            return L;
        }
        
        int query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            propagate(node);
            if(nl == L && nr == R)
                return tree[node];
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else
                return query(2*node, L, mid , nl , mid) | query((2*node)+1, mid+1, R , mid+1,nr);
        }
        int update(int node , int L , int R , int nl , int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R) {
                lazy[node] ^= true;
                propagate(node);
                return tree[node];
            }
            else {
                propagate(node);
                propagate(2 * node);
                propagate(2 * node + 1);
                int l = tree[2 * node];
                int r = tree[2 * node + 1];
                if(R <= mid)
                    l = update(2 * node, L, R, nl, mid);
                else if(L > mid)
                    r = update(2 * node + 1, L, R, mid + 1, nr);
                else {
                    l = update(2 * node, L, mid, nl, mid);
                    r = update(2 * node + 1, mid + 1, R, mid + 1, nr);
                }
                return tree[node] = l | r;
            }
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R) 
                tree[node] = 1 << Integer.bitCount(arr[L]);
            else {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = tree[2 * node] | tree[2 * node + 1];
            }
        }      
    }
    

    
    private static void solve() {
        
        
        for(int tc = 1 , T = nextInt(); tc <= T; tc++) {
            int n = nextInt();
            int m = nextInt();
            SegmentTreeLazyPropagation segTree = new SegmentTreeLazyPropagation(nextIntArrayOneBased(n));
            println("Case " + tc + ":");
            while(m-->0) {
                switch(nextInt()) {
                case 1:
                    int L = nextInt();
                    int R = nextInt();
                    int parity = Integer.bitCount(nextInt());
                    int mask = segTree.query(L, R);
                    if((mask & (1 << parity)) != 0)
                        println(segTree.query(L, R, parity));
                    else {
                        int ceil = parity;
                        int floor = parity;
                        for(; ceil <= MAX_LEN && (mask & (1 << ceil)) == 0; ceil++)
                            ;
                        for(; floor >= 0 && (mask & (1 << floor)) == 0; floor--)
                            ;
                        
                        if(ceil > MAX_LEN)
                            println(segTree.query(L, R, floor));
                        else if(floor < 0)
                            println(segTree.query(L, R , ceil));
                        else {
                            if(parity - floor < ceil - parity)
                                println(segTree.query(L, R, floor));
                            else if(parity - floor > ceil - parity)
                                println(segTree.query(L, R , ceil));
                            else
                                println(Math.min(segTree.query(L, R , ceil) , segTree.query(L, R , floor)));
                        }
                    }
                    break;
                case 2:
                    segTree.update(nextInt(), nextInt());
                    break;
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