import java.util.*;
import java.io.*;
public class StronglyConnectedCity2 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int V;
    static boolean adjMat[][];
    static boolean marked[];
    
    static int dfs(int u) {
        marked[u] = true;
        int cnt = 1;
        for(int i = 1; i <= V; i++)
            if(i != u && !marked[i] && adjMat[u][i])
                cnt += dfs(i);
        return cnt;
    }
    
    private static void solve() {
        
        V = nextInt();
        int E = nextInt();
        int edges[][] = new int[E][2];
        for(int i = 0; i < E; i++) 
            edges[i] = nextIntArray(2);
        
        boolean maxMat[][] = null;
        int maxCnt = 0;
        for(int mask = 0; mask < (1 << E); mask++) {
            adjMat = new boolean[V + 1][V + 1];
            for(int i = 0; i < E; i++) {
                if((mask & (1 << i)) != 0) 
                    adjMat[edges[i][1]][edges[i][0]] = true;
                else
                    adjMat[edges[i][0]][edges[i][1]] = true;
            }
            int cnt = 0;
            for(int i = 1; i <= V; i++) {
                marked = new boolean[V + 1];
                cnt += dfs(i);
            }
            
            if(cnt > maxCnt) {
                maxCnt = cnt;
                maxMat = adjMat;
            }
        }
        
        println(maxCnt);
         
        for(int i = 1; i <= V; i++)
            for(int j = 1; j <= V; j++)
                if(maxMat[i][j])
                    println(i + " " + j); 
        
    }
    
    static ArrayList<Integer>[] adj;
    static int prev[];
    static int level[];
    static int globalMax;
    
    static class Pair {
        int vertex , backVertex;
        Pair(int v , int d) {
            vertex = v;
            backVertex = d;
        }
    }
    
    static void rec(int u , int par , int lev) {
        prev[u] = par;
        level[u] = lev;
        marked[u] = true;
        for(int v : adj[u])
            if(v != par)
                rec(v, u, lev + 1);
        marked[u] = false;
    }
    
    static ArrayList<Pair>[] child;
    static int joiner;
    static Pair p1 , p2;
    
    static void findLargestCycle(int u , )
    
    private static void solve2() {
        
        V = nextInt();
        int E = nextInt();
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        marked = new boolean[V + 1];
        prev = new int[V + 1];
        globalMax = 0;
        
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