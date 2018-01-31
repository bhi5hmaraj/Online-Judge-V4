import java.util.*;
import java.io.*;

public class Substring {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int adj[][];
    private static boolean visitedGlobal[];
    private static boolean visitedTemp[];

    private static boolean dfs(int u){

        visitedGlobal[u] = true;
        visitedTemp[u] = true;
        for(int v:adj[u]){

            if(visitedTemp[v])
                return true;

            if(!visitedGlobal[v]){
                if(dfs(v))
                    return true;
            }

        }
        visitedTemp[u] = false;
        return false;

    }

    private static boolean isCyclic(int V){

        for(int i=1;i<=V;i++){

            if(!visitedGlobal[i]){      
                if(dfs(i))
                    return true;
            }

        }

        return false;

    }
    
    static int memo[];
    static char str[];
    
    static int topDown(int u, char alph) {
        memo[u] = 0;
        for(int v : adj[u])
            memo[u] = Math.max(memo[u] , memo[v] == -1 ? topDown(v, alph) : memo[v]);
        
        memo[u] += str[u - 1] == alph ? 1 : 0;
        return memo[u];
    }
    
    // Courtesy : UWI ( adjacency list using Jagged Arrays )
    static int[][] packD(int n, int[] from, int[] to , int isOneBased) {   
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) 
            g[from[i]][--p[from[i]]] = to[i];

        return g;
    }
    
    private static void solve(){

        int V = nextInt();
        int E = nextInt();
        
        str = nextLine().toCharArray();
        
        visitedGlobal = new boolean[V + 1];
        visitedTemp = new boolean[V + 1];
        
        int from[] = new int[E];
        int to[] = new int[E];
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            from[E] = v;
            to[E] = u;
        }
        
        adj = packD(V, from, to, 1);
        
        if(isCyclic(V)) {
            println(-1);
            return;
        }
        
        // Now a DAG
        memo = new int[V + 1];
        int max = 0;
        for(char ch = 'a'; ch <= 'z'; ch++) {
            Arrays.fill(memo, -1);
            for(int i = 1; i <= V; i++) {
                if(memo[i] == -1)
                    topDown(i, ch);
                max = Math.max(max , memo[i]);
            }
        }
        
        println(max);
        
    }
        
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    /* Increase stack size in java

     */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new Substring().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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