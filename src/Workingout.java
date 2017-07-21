import java.util.*;
import java.io.*;
public class Workingout {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        
        int a[][] = new int[n][];
        for(int i = 0; i < n; i++)
            a[i] = nextIntArray(m);
        
        int toA[][] = new int[n][m];
        int toB[][] = new int[n][m];
        int fromA[][] = new int[n][m];
        int fromB[][] = new int[n][m];
        
        toA[0][0] = a[0][0];
        toB[n - 1][0] = a[n - 1][0];
        fromA[n - 1][m - 1] = a[n - 1][m - 1];
        fromB[0][m - 1] = a[0][m - 1];
        
        for(int i = 1; i < n; i++) {
            toA[i][0] = toA[i - 1][0] + a[i][0];
            toB[n - i - 1][0] = toB[n - i][0] + a[n - i][0];
            fromA[n - i - 1][m - 1] = fromA[n - i][m - 1] + a[n - i - 1][m - 1];
            fromB[i][m - 1] = fromB[i - 1][m - 1] + a[i][m - 1];
        }
        for(int j = 1; j < m; j++) {
            toA[0][j] = toA[0][j - 1] + a[0][j];
            toB[n - 1][j] = toB[n - 1][j - 1] + a[n - 1][j];
            fromA[n - 1][n - j - 1] = fromA[n - 1][n - j] + a[n - 1][n - j - 1];
            fromB[0][n - j - 1] = fromB[0][n - j] + a[0][n - j - 1];
        }
        for(int i = 1; i < n; i++) {
            for(int j = 1; j < m; j++) {
                toA[i][j] = a[i][j] + Math.max(toA[i][j - 1] , toA[i - 1][j]);
                toB[n - i - 1][j] = a[n - i - 1][j] + Math.max(toB[n - i][j] , toB[n - i - 1][j - 1]);
                fromA[n - i - 1][m - j - 1] = a[n - i - 1][m - j - 1] + Math.max(fromA[n - i][m - j - 1] , fromA[n - i -1][m - j]);
                fromB[i][m - j - 1] = a[i][m - j - 1] + Math.max(fromB[i - 1][m - j - 1] , fromB[i][m - j]);
            }
        }
        
        int maxGain = 0;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                
            }
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