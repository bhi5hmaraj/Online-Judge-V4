import java.util.*;
import java.io.*;
public class TreasureIsland {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int R , C;
    static char grid[][];
    static boolean marked[][];
    
    static boolean isValid(int i , int j) {
        return i >= 0 && i < R && j >= 0 && j < C;
    }
    
    static int dx[] = {-1 , 0 , 1 , 0};
    static int dy[] = {0 , 1 , 0 , -1};
    
    static void floodFill(int x , int y , char arr[][]) {
        marked[x][y] = true;
        for(int k = 0; k < 4; k++) {
            int nx = x + dx[k];
            int ny = y + dy[k];
            if(isValid(nx, ny) && arr[nx][ny] == '.' && !marked[nx][ny])
                floodFill(nx, ny , arr);
        }
    }
    
    static boolean isPossible(char gg[][]) {
        marked = new boolean[R][C];

        outer:
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(grid[i][j] == '.') {
                    floodFill(i, j, gg);
                    break outer;
                }
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(grid[i][j] == '.' && !marked[i][j])
                    return false;
        
        return true;
    }
    
    private static void solve() {
        
        
        R = nextInt();
        C = nextInt();
        
        grid = new char[R][];
        for(int i = 0; i < R; i++) {
//            nextLine();
            grid[i] = nextLine().toCharArray();
        }
        char gg[][] = new char[R][];
        for(int i = 0; i < R; i++)
            gg[i] = Arrays.copyOf(grid[i], C);
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                gg[i][j] = gg[i][j] == '?' ? '.' : gg[i][j];
        
        if(!isPossible(gg)) {
            println("Impossible");
            return;
        }
        
        boolean inIsland[][] = new boolean[R][];
        
        for(int i = 0; i < R; i++)
            inIsland[i] = Arrays.copyOf(marked[i], C);
        
        for(int i = 0; i < R; i++)
            for(int j = 0; j < C; j++)
                if(grid[i][j] == '?' && inIsland[i][j]) {
                    gg[i][j] = '#';
                    if(isPossible(gg)) {
                        println("Ambiguous");
                        return;
                    }
                    gg[i][j] = '.';
                }
        
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++)
                print(grid[i][j] == '?' ? (inIsland[i][j] ? '.' : '#') : grid[i][j]);
            println('\n');
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