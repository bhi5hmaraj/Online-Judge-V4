import java.util.*;
import java.io.*;
public class lyaMuromet {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int tree[];
        int len;
        int size;
        SegmentTree(int arr[]) { // arr should be a 1 based array
            len = arr.length;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            build(arr, 1, 0, len - 1);
        }
        int query(int L , int R){
            if(L > R) 
                return 0;
            return query(1, L, R, 0, len - 1);
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
            else {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = Math.max(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }

    
    private static void solve() {
        
        int n = nextInt();
        int k = nextInt();
        int arr[] = nextIntArray(n);
        if(2 * k >= n)
            println(Arrays.stream(arr).sum());
        else {
            int sum[] = new int[n - k + 1];
            for(int i = 0; i < k; i++)
                sum[0] += arr[i];
            
            for(int i = k; i < n; i++) 
                sum[i - k + 1] = sum[i - k] + arr[i] - arr[i - k];
            
            int max = 0;
            SegmentTree segTree = new SegmentTree(sum);
            for(int i = 0; i < sum.length; i++) 
                max = Math.max(max , sum[i] + Math.max(segTree.query(0, i - k) , segTree.query(i + k, n - k)));
            
            println(max);
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