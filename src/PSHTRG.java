import java.util.*;
import java.io.*;
class PSHTRG  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int THRESHOLD = 43;
    
    /*
     * I didnt expect the unoptimized to pass !!
     */
    
    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        PriorityQueue<Integer> tree[];
        int len;
        int size;
        SegmentTree(int arr[]) { // arr should be a 1 based array
            len = arr.length;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new PriorityQueue[size];
            build(arr, 1, 0, len - 1);
        }
        
        PriorityQueue<Integer> merge(PriorityQueue<Integer> pq1, PriorityQueue<Integer> pq2) {
            PriorityQueue<Integer> pq3 = new PriorityQueue<>(pq1);
            for(int val : pq2) {
                pq3.add(val);
                if(pq3.size() > THRESHOLD)
                    pq3.remove();
            }
            
            return pq3;
        }
        
        void update(int node,int idx,int val,int nl,int nr) {
            if(nl == nr && nl == idx) {
                tree[node].remove();
                tree[node].add(val);
            }
            else {
                int mid = (nl + nr) >> 1;
                if(idx <= mid)
                    update(2*node, idx , val ,nl , mid);
                else
                    update((2*node) + 1, idx ,val , mid + 1, nr);

                tree[node] = merge(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 0, len - 1);
        }
        PriorityQueue<Integer> query(int L , int R){
            return query(1, L, R, 0, len - 1);
        }
        PriorityQueue<Integer> query(int node , int L , int R, int nl, int nr) {
            int mid = (nl + nr) >> 1;
            if(nl == L && nr == R)
                return new PriorityQueue<>(tree[node]);
            else if(R <= mid)
                return query(2 * node, L, R, nl, mid);
            else if(L > mid)
                return query((2*node)+1, L, R, mid + 1 , nr);
            else
                return merge(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
        void build(int arr[],int node,int L,int R) {
            if(L == R) {
                tree[node] = new PriorityQueue<>(1);
                tree[node].add(arr[L]);
            }
            else {
                int mid = (L + R) >> 1;
                build(arr, 2 * node, L, mid);
                build(arr, (2 * node) + 1, mid + 1, R);
                tree[node] = merge(tree[2*node] , tree[(2 * node) + 1]);
            }
        }
    }
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        
        int arr[] = nextIntArray(N);
        SegmentTree segTree = new SegmentTree(arr);
        
        while(Q-->0) {
            
            if(nextInt() == 1) 
                segTree.update(nextInt() - 1, nextInt());
            else {
                int L = nextInt() - 1;
                int R = nextInt() - 1;
                PriorityQueue<Integer> pq = segTree.query(L, R);
                int newArr[] = new int[pq.size()];
                for(int i = 0; i < newArr.length; i++)
                    newArr[i] = pq.remove();
                
                long perimeter = 0;
                int ptr = newArr.length - 1;
                for(; ptr >= 2 && newArr[ptr - 1] + newArr[ptr - 2] <= newArr[ptr]; ptr--)
                    ;
                perimeter = ptr >= 2 ? 0L + newArr[ptr] + newArr[ptr - 1] + newArr[ptr - 2] : 0;
                println(perimeter);
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