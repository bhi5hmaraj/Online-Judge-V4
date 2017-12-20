import java.util.*;
import java.io.*;
public class poj_3259 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static final int INF = (int) 1e9;
    
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            int E = nextInt();
            int W = nextInt();
            int dist[][] = new int[N + 1][N + 1];
            for(int d[] : dist) Arrays.fill(d, INF);
            while(E-->0) {
                int from = nextInt();
                int to = nextInt();
                int c = nextInt();
                dist[from][to] = Math.min(dist[from][to] , c);
                dist[to][from] = Math.min(dist[to][from] , c);
            }
            
            while(W-->0) {
                int from = nextInt();
                int to = nextInt();
                int c = nextInt();
                dist[from][to] = Math.min(dist[from][to] , -c);
            }
            
            int aux[][] = new int[N + 1][N + 1];
            boolean flag = false;
            // Floyd Warshal
            outer:
            for(int k = 1; k <= N; k++) {
                for(int i = 1; i <= N; i++)
                    for(int j = 1; j <= N; j++) {
                        aux[i][j] = dist[i][j];
                        if(dist[i][k] < INF && dist[k][j] < INF)
                            aux[i][j] = Math.min(aux[i][j] , dist[i][k] + dist[k][j]);
                        
                        if(aux[i][i] < 0) {
                            flag = true;
                            break outer;
                        }
                    }
                for(int i = 1; i <= N; i++)
                    System.arraycopy(aux[i], 0, dist[i], 0, N + 1);
            }
            
            
            println(flag ? "YES" : "NO");
        }
        
        
        
    }
    

    static class Edge { // Take this Edge class to the outer class
        int v;
        long cost;
        Edge(int vv, long wt) {
            v = vv;
            cost = wt;
        }
    }
    
    private static void solve2() {
            
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            int E = nextInt();
            int W = nextInt();
    
        }
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new FileReader("/home/bhishmaraj/Downloads/wormhole_files/wormhole.8.in"));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        long start = System.nanoTime();
        solve();
        System.out.println("Time " + (System.nanoTime() - start) / 1e9);
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