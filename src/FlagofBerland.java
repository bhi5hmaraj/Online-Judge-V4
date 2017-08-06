import java.util.*;
import java.io.*;
public class FlagofBerland {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    static int n , m ;
    static char grid[][];
    static int[] find(char ch) {
        int sx = -1, sy = -1 , ex = -1 , ey = -1;
        o1:
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == ch) {
                    sx = i;
                    sy = j;
                    break o1;
                }
            }
        o2:
        for(int i = n - 1; i >= 0; i--)
            for(int j = m - 1; j >= 0; j--) {
                if(grid[i][j] == ch) {
                    ex = i;
                    ey = j;
                    break o2;
                }
            }
            
            if(sx >= 0 && sy >= 0 && ex >= 0 && ey >= 0) {
                
            }
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        char grid[][] = new char[n][];
        for(int i = 0; i < n; i++)
            grid[i] = nextLine().toCharArray();
        
        
        
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