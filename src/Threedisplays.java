import java.util.*;
import java.io.*;
public class Threedisplays {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[] compressPreserveOrder(int arr[]) {
        
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(arr).forEach(set::add);
        int ordered[] = set.stream().mapToInt(Integer::new).toArray();
        int compressed[] = new int[arr.length];
        for(int i = 0; i < arr.length; i++)
            compressed[i] = Arrays.binarySearch(ordered, arr[i]);
        
        return compressed;
    }

    static final int INF = (int) 1e9;
    
    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
            Arrays.fill(tree, INF);
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

                tree[node] = Math.min(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 0, len - 1);
        }
        int query(int L , int R){
            if(L > R) return INF;
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
    }

    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int cost[] = nextIntArray(n);

        long start = System.nanoTime();
        
        int s[] = compressPreserveOrder(arr);
        int segTreeLen = Arrays.stream(s).max().getAsInt() + 1;
        SegmentTree segTree = new SegmentTree(segTreeLen);
        int minCostCache[] = new int[segTreeLen];

        int minCost = INF;

        for(int i = 0; i < n - 2; i++) {
            
            Arrays.fill(minCostCache, INF);
            Arrays.fill(segTree.tree, INF);
            
            for(int j = i + 1; j < n; j++) {

                int ret = INF;
                if(s[j] > s[i] && (ret = segTree.query(s[i] + 1, s[j] - 1)) < INF) 
                    minCost = Math.min(minCost , cost[i] + ret + cost[j]);
                
                if(cost[j] < minCostCache[s[j]]) {
                    minCostCache[s[j]] = cost[j];
                    segTree.update(s[j], cost[j]);
                }
            }
        }

        System.out.println("Time : " + (System.nanoTime() - start) / 1e6);
        
        println(minCost == INF ? -1 : minCost); 
        
    }
    
    private static void solve2() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int cost[] = nextIntArray(n);
        
        int bestToRight[] = new int[n];
        for(int i = n - 1; i >= 0; i--) {
            bestToRight[i] = INF;
            for(int j = i + 1; j < n; j++)
                bestToRight[i] = arr[j] > arr[i] ? Math.min(bestToRight[i] , cost[j]) : bestToRight[i];
        }
        
        int minCost = INF;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if(bestToRight[j] < INF && arr[i] < arr[j])
                    minCost = Math.min(minCost , cost[i] + cost[j] + bestToRight[j]);
        
        println(minCost == INF ? -1 : minCost); 
        
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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