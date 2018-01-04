import java.util.*;
import java.util.function.BiPredicate;
import java.io.*;
public class BiridianForest  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    
    private static void solve() {
        
        
        int r = nextInt();
        int c = nextInt();
        
        char grid[][] = new char[r][];
        for(int i = 0; i < r; i++)
            grid[i] = nextLine().toCharArray();
        
        int dist[][] = new int[r][c];
        for(int d[] : dist) Arrays.fill(d, Integer.MAX_VALUE);
        
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(grid[i][j] == 'E') {
                    queue.add(new int[]{i , j});
                    dist[i][j] = 0;
                }
        
        BiPredicate<Integer , Integer> isValid = (xx , yy) -> xx >= 0 && xx < r && yy >= 0 && yy < c;
        
        while(!queue.isEmpty()) {
            int curr[] = queue.remove();
            for(int k = 0; k < 4; k++) {
                int nx = curr[0] + x[k];
                int ny = curr[1] + y[k];
                if(isValid.test(nx, ny) && grid[nx][ny] != 'T' && dist[nx][ny] == Integer.MAX_VALUE) {
                    dist[nx][ny] = dist[curr[0]][curr[1]] + 1;
                    queue.add(new int[]{nx , ny});
                }
            }
        }
        
        int thresh = 0;
        
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(grid[i][j] == 'S')
                    thresh = dist[i][j];
        
        int cnt = 0;
        for(int i = 0; i < r; i++)
            for(int j = 0; j < c; j++)
                if(Character.isDigit(grid[i][j]) && dist[i][j] <= thresh)
                    cnt += grid[i][j] - '0';
        
        println(cnt);
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