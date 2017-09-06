import java.util.*;
import java.io.*;
public class uva_601 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N;
    static char grid[][];
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    static boolean isValid(int i , int j) {
        return i >= 0 && i < N && j >= 0 && j < N;
    }
    
    static void dfs(int i , int j , char ch , boolean marked[][]) {
        marked[i][j] = true;
        for(int k = 0; k < 4; k++) {
            int ni = i + x[k];
            int nj = j + y[k];
            if(isValid(ni, nj) && !marked[ni][nj] && grid[ni][nj] == ch)
                dfs(ni, nj, ch, marked);
        }
    }
    
    private static void solve() {
        
        
        while((N = nextInt()) != 0) {
            nextLine(); // burn
            grid = new char[N][];
            for(int i = 0; i < N; i++)
                grid[i] = nextLine().toCharArray();
            nextLine(); // burn
            boolean whiteMark[][] = new boolean[N][N];
            for(int i = 0; i < N; i++)
                if(grid[i][0] == 'W' && !whiteMark[i][0])
                    dfs(i, 0, 'W', whiteMark);
            boolean blackMark[][] = new boolean[N][N];
            for(int i = 0; i < N; i++)
                if(grid[0][i] == 'B' && !blackMark[0][i])
                    dfs(0, i, 'B', blackMark);
            
            boolean whiteWin = false;
            boolean whiteWinnext = false;
            for(int i = 0; i < N; i++) {
                whiteWin |= whiteMark[i][N - 1];
                if(N > 1)
                    whiteWinnext |= (whiteMark[i][N - 2] && grid[i][N - 1] == 'U');
            }
            boolean blackWin = false;
            boolean blackWinNext = false;
            for(int i = 0; i < N; i++) {
                blackWin |= blackMark[N - 1][i];
                if(N > 1)
                    blackWinNext |= blackMark[N - 2][i] && grid[N - 1][i] == 'U';
            }
            
            if(whiteWin)          println("White has a winning path.");
            else if(blackWin)     println("Black has a winning path.");
            else if(whiteWinnext) println("White can win in one move.");
            else if(blackWinNext) println("Black can win in one move.");
            else                  println("There is no winning path.");
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