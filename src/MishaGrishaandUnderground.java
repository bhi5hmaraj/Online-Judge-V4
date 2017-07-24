import java.util.*;
import java.io.*;
public class MishaGrishaandUnderground {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer> adj[];
    /* LCA <NlogN , logN> dependency : level , log , V , DP = new int[log(V) + 1][V + 1];, parent (for the first level of DP) */
    static int DP[][]; // One based vertices
    static int level[];
    static int parent[];
    static int V;
    static int log(int N){
        return 31 - Integer.numberOfLeadingZeros(N);
    }
    static void binaryLift() {
        
        for(int i=1;i<=V;i++)
            DP[0][i] = parent[i];
        
        for (int i = 1; i < DP.length; i++) 
            for (int j = 1; j <= V; j++) 
                DP[i][j] = DP[i - 1][DP[i - 1][j]];

    }

    static int LCA(int u , int v){

        if(level[v] < level[u]){
            int temp = u;
            u = v;
            v = temp;
        }
        int diff = level[v] - level[u];
        while(diff > 0){        // Bring v to the same level as u
            int log = log(diff);
            v = DP[log][v];
            diff -= (1 << log);
        }
        while(u != v){
            int i = log(level[u]);
            for(;i > 0 && DP[i][u] == DP[i][v];)
                i--;

            u = DP[i][u];
            v = DP[i][v];
        }

        return u;
    }
    
    static void dfs(int u , int lev) {
        level[u] = lev;
        for(int v : adj[u])
            dfs(v, lev + 1);
    }
    
    static int countInter(int s1 , int s2 , int dest) {
        int lca1 = LCA(s1, dest);
        int lca2 = LCA(s2, dest);
        int lca = LCA(s1, s2);
        int cnt = 0;
        
        if(lca1 == lca2) 
            cnt += Math.abs(level[lca] - level[lca1]);
        cnt++;  // reached lca
        cnt += Math.abs(Math.max(level[lca1] , level[lca2]) - level[dest]);    
        // println(String.format("s1 = %d s2 = %d dest = %d lca1 = %d lca2 = %d lca = %d cnt = %d", s1,s2,dest,lca1,lca2,lca,cnt));
        return cnt;
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        V = nextInt();
        int q = nextInt();
        
        level = new int[V + 1];
        parent = new int[V + 1];
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        DP = new int[log(V) + 1][V + 1];
        
        for(int i = 2; i <= V; i++) {
            int v = i;
            int u = nextInt();
            parent[i] = u;
            adj[u].add(v);
        }
        
        binaryLift();
        dfs(1, 1);
        //println(Arrays.toString(level));
        while(q-->0) {
            int opt[] = nextIntArray(3);
            int max = 0;
            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 3; j++)
                    for(int k = 0; k < 3; k++)
                        if(!(i == j || j == k || i == k))
                            max = Math.max(max , countInter(opt[i], opt[j], opt[k]));
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