import java.util.*;
import java.io.*;
public class WeirdChess {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int n;
    static boolean valid(int i , int j) {
        return i >= 0 && i < n && j >= 0 && j < n;
    }
    
    private static void solve() {
        
        n = nextInt();
        char grid[][] = new char[n][];
        for(int i = 0; i < n; i++)
            grid[i] = nextLine().toCharArray();
        
        ArrayList<int[]> possible = new ArrayList<>();
        
        for(int dx = -n; dx < n; dx++) {
            for(int dy = -n; dy < n; dy++) {
                boolean used = false;
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < n; j++)
                        if(grid[i][j] == 'o')
                            used |= (valid(i + dx, j + dy) && grid[i + dx][j + dy] == 'x');
                
                for(int i = 0; i < n; i++)
                    for(int j = 0; j < n; j++)
                        if(grid[i][j] == 'o') {
                            int nx = i + dx;
                            int ny = j + dy;
                            used &= !valid(nx, ny) || (grid[nx][ny] == 'x' || grid[nx][ny] == 'o');
                        }
                
                if(used)
                    possible.add(new int[]{dx , dy});
            }
        }
        
        char check[][] = new char[n][n];
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(grid[i][j] == 'o')
                    for(int m[] : possible)
                        if(valid(i + m[0], j + m[1]))
                            check[i + m[0]][j + m[1]] = '#';
        
        //possible.stream().forEach(a -> println(Arrays.toString(a)));
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