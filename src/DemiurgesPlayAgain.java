import java.util.*;
import java.io.*;
public class DemiurgesPlayAgain {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static int leaves[];
    static boolean isLeaf[];
    static int dfs(int u , int par) {
        int child = 0;
        for(int v : adj[u])
            if(v != par) {
                child++;
                leaves[u] += dfs(v, u);
            }
        isLeaf[u] = child == 0;
        leaves[u] = isLeaf[u] ? 1 : leaves[u];
        return leaves[u];
    }
    
    // turn T - maximize , F - minimize
    
    static int inc(int u , int par , boolean turn) {
        if(isLeaf[u])
            return 1;
        if(turn) {
            // we can fill each of the subtree with the leaves(v) largest elements 
            int max = 0;
            for(int v : adj[u])
                if(v != par)
                    max = Math.max(max , leaves[u] - leaves[v] + inc(v, u, !turn));
            return max;
        } else {
            // We need to maximize the minimum , so we need to couple smaller numbers with larger ones
            // so that the minimum is maximized . we get sum(inc(v) - 1) + 1    --- (1)
            // We can construct this in the following way
            // for v1 -- 1,2...inc[v1] - 1, l[u]-(l[v1]-inc[v1]+1)..l[u]        --- (2)
            // If we expand (2) for all subtree vertices we can get relation (1)
            // as l[u] = sum(l[vi])
            int sum = 1;
            for(int v : adj[u])
                if(v != par)
                    sum += inc(v, u, !turn) - 1;
            return sum;
        }
    }
    
    static int dec(int u , int par , boolean turn) {
        if(isLeaf[u])
            return 1;
        if(turn) {
            // we need to minimize the maximum , we can use a similar strategy as above
            // for each subtree we will fill 1...dec[v] , (l[u] - (l[v]-dec[v])) + 1 ... l[u]
            int sum = leaves[u];
            for(int v : adj[u])
                if(v != par)
                    sum -= (leaves[v] - dec(v, u, !turn));
            return sum;
        } else {
            int min = Integer.MAX_VALUE;
            for(int v : adj[u])
                if(v != par)
                    min = Math.min(min , dec(v, u, !turn));
            return min;
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int n = nextInt();
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();
        
        int E = n - 1;
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        isLeaf = new boolean[n + 1];
        leaves = new int[n + 1];
        dfs(1, 0);

        println(inc(1, 0, true) + " " + dec(1, 0, true));
        
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