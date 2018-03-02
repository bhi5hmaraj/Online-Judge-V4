import java.util.*;
import java.io.*;
public class CuttingFigure {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int R , C;
    static boolean isValid(int r , int c) {
        return r >= 0 && r < R && c >= 0 && c < C;
    }
    
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    
    static boolean marked[][];
    static char grid[][];
    
    static boolean isConnected() {
        boolean started = false;
        marked = new boolean[R][C];
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(grid[i][j] == '#') {
                    if(!started) {
                        dfs(i, j);
                        started = true;
                    }
                    else if(!marked[i][j])
                        return false;
                }
        
        return true;
    }
    
    static void dfs(int r, int c) {
        marked[r][c] = true;
        for(int k = 0; k < 4; k++)
            if(isValid(r + x[k], c + y[k]) && grid[r + x[k]][c + y[k]] == '#' && !marked[r + x[k]][c + y[k]])
                dfs(r + x[k], c + y[k]);
    }
    
    
    private static void solve() {
        
        R = nextInt();
        C = nextInt();
        grid = new char[R][];
        for(int i = 0; i < R; i++)
            grid[i] = nextLine().toCharArray();
        
        int total = 0;
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(grid[i][j] == '#') {
                    total++;
                    grid[i][j] = '.';
                    if(!isConnected()) {
                        println(1);
                        return;
                    }
                    grid[i][j] = '#';
                }
        
        println(total <= 2 ? -1 : 2);
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