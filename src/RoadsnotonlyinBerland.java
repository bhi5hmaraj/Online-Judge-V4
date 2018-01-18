import java.util.*;
import java.io.*;
public class RoadsnotonlyinBerland {
    
    
    
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
            while(p != parent[p]) p = parent[p];
            return p;
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
    
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = V - 1;
        
        DSU unionFind = new DSU(V);
        ArrayList<int[]> toRemove = new ArrayList<>();
        
        HashSet<Integer> cycleComps = new HashSet<>();
        TreeSet<Integer> treeComps = new TreeSet<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            if(unionFind.connected(u, v)) 
                toRemove.add(new int[]{u, v});
            unionFind.union(u, v);
        }
        
        int unwantedCnt[] = new int[V + 1];
        for(int p[] : toRemove) {
            int root = unionFind.root(p[0]);
            unwantedCnt[root]++;
            cycleComps.add(root);
        }
        
        for(int i = 1; i <= V; i++) {
            int root = unionFind.root(i);
            if(!cycleComps.contains(root))
                treeComps.add(root);
        }
        
        
//        println(treeComps);
//        println(cycleComps);
        
        int base = 1;
        println(toRemove.size());
        for(int p[] : toRemove)
            if(unionFind.root(p[0]) == base) {
                println(p[0] + " " + p[1] + " " + p[0] + " " + treeComps.pollFirst());
                unwantedCnt[base]--;
            }
        
        for(int p[] : toRemove) {
            int root = unionFind.root(p[0]);
            if(root == base) continue;
            int other = unwantedCnt[root] > 1 ? treeComps.pollFirst() : base;
            unwantedCnt[root]--;
            println(p[0] + " " + p[1] + " " + p[0] + " " + other);
//            println(p[0] + "--" + p[1] + "[color=blue]");
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