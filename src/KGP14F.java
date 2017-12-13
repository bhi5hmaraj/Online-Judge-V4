import java.util.*;
import java.io.*;
class KGP14F {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    // https://sites.google.com/site/indy256/algo/kuhn_matching
    public static int maxMatching(boolean[][] graph) {
        int n1 = graph.length;
        int n2 = n1 == 0 ? 0 : graph[0].length;
        int[] matching = new int[n2];
        Arrays.fill(matching, -1);
        int matches = 0;
        for (int u = 0; u < n1; u++)
            if (findPath(graph, u, matching, new boolean[n1]))
                ++matches;
        return matches;
    }

    static boolean findPath(boolean[][] graph, int u1, int[] matching, boolean[] vis) {
        vis[u1] = true;
        for (int v = 0; v < matching.length; ++v) {
            int u2 = matching[v];
            if (graph[u1][v] && (u2 == -1 || !vis[u2] && findPath(graph, u2, matching, vis))) {
                matching[v] = u1;
                return true;
            }
        }
        return false;
    }
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1; tc <= T; tc++) {
            
            int H = nextInt();
            int G = nextInt();
            int K = nextInt();
            int C = nextInt();
            
            int hosts[][] = new int[H][];
            int guests[][] = new int[G][];
            
            for(int i = 0; i < H; i++)
                hosts[i] = nextIntArray(2);
            
            for(int i = 0; i < G; i++)
                guests[i] = nextIntArray(2);
            
            boolean graph[][] = new boolean[H][G];
            for(int i = 0; i < H; i++)
                for(int j = 0; j < G; j++)
                    if(Math.abs(hosts[i][0] - guests[j][0]) + Math.abs(hosts[i][1] - guests[j][1]) + 
                       Math.abs(guests[j][0] - K) + Math.abs(guests[j][1] - K) <= C) 
                        graph[i][j] = true;
                    
            
            println("Case " + tc + ": " + maxMatching(graph));
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