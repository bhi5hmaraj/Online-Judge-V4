import java.util.*;
import java.io.*;
public class Igorandhiswaywork {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int n , m;
    static char grid[][];
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    
    static class Edge implements Comparable<Edge> {
        int vx ; int vy;
        int cost;
        int dir;
        Edge(int vx , int vy , int cost , int dir) {
            this.vx = vx;
            this.vy = vy;
            this.dir = dir;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.cost, o.cost);
        }
    }
    static boolean isValid(int i , int j) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }
    
    static final int INF = (int) 1e6;
    
    private static boolean dijkstra(int start[] , int dest[]) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int dist[][][] = new int[n][m][4];
        for(int a[][] : dist)
            for(int b[] : a)
                Arrays.fill(b, INF);
        
        for(int k = 0; k < 4; k++) {
            int nx = start[0] + x[k];
            int ny = start[1] + y[k];
            if(isValid(nx, ny) && grid[nx][ny] != '*') {
                pq.add(new Edge(nx, ny, 0, k));
                dist[nx][ny][k] = 0;
            }
        }
        
        while (!pq.isEmpty()) {
            Edge min = pq.remove();
            if (dist[min.vx][min.vy][min.dir] < min.cost)
                continue;

            for(int k = 0; k < 4; k++) {
                int nx = min.vx + x[k];
                int ny = min.vy + y[k];
                int cost = k == min.dir ? 0 : 1; 
                if(isValid(nx, ny) && grid[nx][ny] != '*') {
                    if (dist[nx][ny][k]  > min.cost + cost) {
                        dist[nx][ny][k] = min.cost + cost;
                        pq.add(new Edge(nx , ny , min.cost + cost , k));
                    }
                }
            }
        }
        
        boolean flag = false;
        for(int i = 0; i < 4; i++)
            flag |= dist[dest[0]][dest[1]][i] <= 2;
        
        return flag;
        
    }

    private static void solve() {
        
        
        n = nextInt();
        m = nextInt();
        grid = new char[n][];
        for(int i = 0; i < n; i++)
            grid[i] = nextLine().toCharArray();
        
        int S[] = new int[2];
        int T[] = new int[2];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 'S') {
                    S[0] = i;
                    S[1] = j;
                }
                if(grid[i][j] == 'T') {
                    T[0] = i;
                    T[1] = j;
                }
            }
        
        println(dijkstra(S, T) ? "YES" : "NO");
        
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