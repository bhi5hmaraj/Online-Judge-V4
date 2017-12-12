import java.util.*;
import java.io.*;
public class RobotsatWarehouse {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static char grid[][];
    static int R , C;
    static int dx[] = {-1 , 0 , 1 , 0};
    static int dy[] = {0 , 1 , 0 , -1};
    static boolean marked[][];
    static int degree[][];
    static boolean isValid(int i , int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }
    
    static void dfs(int x , int y) {
        marked[x][y] = true;
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            degree[x][y] += isValid(nx, ny) && grid[nx][ny] != '#' ? 1 : 0;
            if(isValid(nx, ny) && grid[nx][ny] != '#' && !marked[nx][ny])
                dfs(nx, ny);
        }
    }
    
    static boolean hasCycle(int x , int y) {
        marked = new boolean[R][C];
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        marked[x][y] = true;
        queue.add(new int[]{x , y , -1 , -1}); // curr , par
        
        while(!queue.isEmpty()) {
            int p[] = queue.remove();
            for(int k = 0; k < 4; k++) {
                int nx = p[0] + dx[k];
                int ny = p[1] + dy[k];
                if(!isValid(nx, ny) || grid[nx][ny] == '#' || (nx == p[2] && ny == p[3]))
                    continue;
                if(marked[nx][ny])
                    return true;
                else {
                    marked[nx][ny] = true;
                    queue.add(new int[]{nx , ny , p[0] , p[1]});
                }
            }
        }
        
        return false;
    }
    
    private static void solve() {
        
        R = nextInt();
        C = nextInt();
        grid = new char[R][];
        marked = new boolean[R][C];
        degree = new int[R][C];
        
        int sx = -1 , sy = -1;
        int ex = -1 , ey = -1;
        for(int i = 0; i < R; i++)
            grid[i] = nextLine().toCharArray();
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++) {
                if(grid[i][j] == '1') {
                    sx = i;
                    sy = j;
                } else if(grid[i][j] == '2') {
                    ex = i;
                    ey = j;
                }
            }
        
        dfs(sx, sy);
        if(!marked[ex][ey]) { 
            println("NO");
            return;
        }
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(marked[i][j] && degree[i][j] > 2) {
                    println("YES");
                    return;
                }
        
        println(hasCycle(sx, sy) ? "YES" : "NO");
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