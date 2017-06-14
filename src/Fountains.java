import java.util.*;
import java.io.*;
public class Fountains {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class SegmentTree  { // Implemented to store min in a range , point update and range query
        int tree[];
        int len;
        int size;
        SegmentTree(int len) { // arr should be a 1 based array
            this.len = len;
            size = 1 << (32 - Integer.numberOfLeadingZeros(len - 1) + 1);  // ceil(log(len)) + 1
            tree = new int[size];
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

                tree[node] = Math.max(tree[2*node],tree[(2 * node) + 1]);
            }
        }
        void update(int idx , int val){
            update(1, idx, val, 1, len);
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
                return Math.max(query(2*node, L, mid , nl , mid) ,  query((2*node)+1, mid+1, R , mid+1,nr));
        }
    }
    

    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int MAX_COST = (int) 1e5;
        int n = nextInt();
        int have[] = nextIntArray(2);   // 0 coin 1 diamond
        int max = 0;
        
        ArrayList<int[]>[] fountain = new ArrayList[2];
        SegmentTree[] costVsBeauty = new SegmentTree[2]; 
        for(int i = 0; i < 2; i++) {
            fountain[i] = new ArrayList<>();
            costVsBeauty[i] = new SegmentTree(MAX_COST);
        }
        TreeMap<Integer , Integer>[][] freq = new TreeMap[2][MAX_COST + 1];
        for(int type = 0; type < 2; type++)
            for(int i = 1; i <= MAX_COST; i++)
                freq[type][i] = new TreeMap<>();
        
        for(int i = 0; i < n; i++) {
            int b = nextInt();
            int p = nextInt();
            int type = nextChar() == 'C' ? 0 : 1;
            fountain[type].add(new int[]{b , p});
            costVsBeauty[type].update(p, Math.max(b , costVsBeauty[type].query(p, p)));
            freq[type][p].put(b, freq[type][p].getOrDefault(b, 0) + 1);
        }
        
        int maxBeautySeperate[] = new int[2];
        for(int type = 0; type < 2; type++) {
            for(int fount[] : fountain[type]) {
                if(fount[1] <= have[type])
                    maxBeautySeperate[type] = Math.max(maxBeautySeperate[type] , fount[0]);
                
                if(fount[1] < have[type]) {
                    TreeMap<Integer , Integer> temp = freq[type][fount[1]];
                    Map.Entry<Integer, Integer> cache = temp.lastEntry();
                    boolean flag = false;
                    if(cache.getKey().intValue() == fount[0] && cache.getValue().intValue() == 1) {
                        flag = true;
                        Integer floor = temp.lowerKey(fount[0]);
                        costVsBeauty[type].update(fount[1], floor == null ? 0 : floor);
                    }
                    int q = costVsBeauty[type].query(1, have[type] - fount[1]);
                    if(q > 0)
                        max = Math.max(max , fount[0] + q);
                    if(flag)
                        costVsBeauty[type].update(fount[1], fount[0]);
                }
            }
        }
        
        if(maxBeautySeperate[0] > 0 && maxBeautySeperate[1] > 0)
            max = Math.max(max , maxBeautySeperate[0] + maxBeautySeperate[1]);
        println(max);
        
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