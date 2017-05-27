import java.util.*;
import java.io.*;
public class StanfordClusteringBig {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class DSU {
        private int parent[];
        private int size[];
        private int cnt;

        DSU(int length) {
            this.cnt = length;
            parent = new int[length + 10];
            size = new int[length + 10];
            Arrays.fill(size, 1);
            for (int i = 0; i < parent.length; i++)
                parent[i] = i;
        }

        int root(int p) {
            return (parent[p] == p) ? p : (parent[p] = root(parent[p]));
        }

        int sizeOf(int p) {
            return size[root(p)];
        }

        boolean connected(int u, int v) {
            return root(u) == root(v);
        }

        int components() {
            return cnt;
        }

        void union(int u, int v) {
            if (!connected(u, v)) {
                cnt--;
                int rootU = root(u);
                int rootV = root(v);
                if (size[rootU] < size[rootV]) {
                    parent[rootU] = rootV;
                    size[rootV] += size[rootU];
                } else {
                    parent[rootV] = rootU;
                    size[rootU] += size[rootV];
                }
            }
        }
    }

    /*
     * This implementation works only for spacing of 3
     */
    
    private static void solve() {
        
        int N = nextInt();
        int nBits = nextInt();
        HashMap<Integer , Integer> map = new HashMap<>();
        ArrayList<Integer> invMap = new ArrayList<>();
        
        long start = System.nanoTime();
        
        while(N-->0) {
            int bits = Integer.parseInt(String.join("", nextLine().split(" ")), 2);
            if(!map.containsKey(bits)) {
                map.put(bits, map.size());
                invMap.add(bits);
            }
        }
        
        int V = map.size();
        DSU dsu = new DSU(V);
        System.out.println("init size " + V);
        for(int i = 0; i < V; i++) {
            int u = invMap.get(i);
            for(int j = 0; j < nBits; j++) {
                int v = u ^ (1 << j); 
                if(map.containsKey(v))
                    dsu.union(i, map.get(v));
            }
        }
        
        for(int i = 0; i < V; i++) {
            int u = invMap.get(i);
            for(int j = 0; j < nBits; j++)
                for(int k = j + 1; k < nBits; k++) {
                    int v = u ^ (1 << j) ^ (1 << k);
                    if(map.containsKey(v))
                        dsu.union(i, map.get(v));
                }
        }
        
        println("answer = " + dsu.components());
        println("Time : " + ((System.nanoTime() - start) / 1e9) + "s");
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new FileReader("Stanford_C3_W2_clustering_big.txt"));   // Time : 4.5s
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