import java.util.*;
import java.io.*;
public class TurnOffTheTV  {
    
    
    
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
                return Math.min(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R)
                tree[node] = arr[L];
            else
            {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = Math.min(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int inter[][] = new int[n][];
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < n; i++) {
            inter[i] = nextIntArray(2);
            set.add(inter[i][0]);
            set.add(inter[i][1]);
        }
        
        int unique[] = new int[set.size()];
        int ptr = 0;
        for(int a : set) unique[ptr++] = a;
        
        int overlap[] = new int[set.size() + 1];
        for(int i = 0; i < n; i++) {
            inter[i][0] = Arrays.binarySearch(unique, inter[i][0]);
            inter[i][1] = Arrays.binarySearch(unique, inter[i][1]);
            overlap[inter[i][0]]++;
            overlap[inter[i][1] + 1]--;
        }
        
        for(int i = 1; i < overlap.length; i++)
            overlap[i] += overlap[i - 1];
        
        
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