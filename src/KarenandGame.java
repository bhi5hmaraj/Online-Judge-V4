import java.util.*;
import java.io.*;
public class KarenandGame {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * https://www.codechef.com/problems/MTRNSFRM
     */
    
    private static void solve() {
        
        
        int N = nextInt();
        int M = nextInt();
        int C[][] = new int[N][];
        for(int i = 0; i < N; i++)
            C[i] = nextIntArray(M);
        
        boolean flag = true;
        for(int i=0;i<N;i++)
            for(int j=0;j<M;j++)
                flag &= C[i][j] + C[0][0] - C[i][0] - C[0][j] == 0;
        
        if(!flag)
            println(-1);
        else {
            
            int pts[] = new int[N + M];
            for(int i=0;i<N;i++)
                pts[i] = C[i][0] - C[0][0];
            for(int i=0;i<M;i++)
                pts[N + i] = -C[0][i];
            
            Arrays.sort(pts);
            int x = pts[pts.length / 2];
            int minCost = 0;
            for(int pt : pts)
                minCost += Math.abs(x - pt);
            
            println(minCost);
            for(int i = 0; i < N; i++) {
                int ROW = Math.abs(C[i][0] - C[0][0] - x);
                while(ROW-->0)
                    println("row " + (i + 1));
            }
            
            for(int j = 0; j < M; j++) {
                int COL = Math.abs(-C[0][j] - x);
                while(COL-->0)
                    println("col " + (j + 1));
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