import java.util.*;
import java.io.*;
public class YetAnotherTaskwithQueens {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        int pt[][] = new int[m][];
        int row[][] = new int[n + 1][2];    // (min , max)
        int col[][] = new int[n + 1][2];
        int D1[][] = new int[n + 1][2];
        int D2[][] = new int[n + 1][2];
        
        for(int i = 1; i <= n; i++) {
            row[i][0] = col[i][0] = D1[i][0] = D2[i][0] = Integer.MAX_VALUE;
            row[i][1] = col[i][1] = D1[i][1] = D2[i][1] = Integer.MIN_VALUE;
        }
        
        
        for(int i = 0; i < m; i++) {
            pt[i] = nextIntArray(2);
            row[pt[i][0]][0] = Math.min(row[pt[i][0]][0] , pt[i][1]);
            row[pt[i][0]][1] = Math.max(row[pt[i][0]][1] , pt[i][1]);
            col[pt[i][1]][0] = Math.min(col[pt[i][1]][0] , pt[i][0]);
            col[pt[i][1]][1] = Math.max(col[pt[i][1]][0] , pt[i][0]);
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