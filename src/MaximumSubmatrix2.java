import java.util.*;
import java.io.*;
public class MaximumSubmatrix2 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N = nextInt();
        int M = nextInt();
        
        char grid[][] = new char[N][];
        for(int i = 0; i < N; i++)
            grid[i] = nextLine().toCharArray();
        
        int DP[][] = new int[M][N]; // DP[i][j] = stores the largest block of 1 to the left of (j , i) 
        
        for(int i = 0; i < N; i++) {
            DP[0][i] = grid[i][0] == '1' ? 1 : 0;
            for(int j = 1; j < M; j++) 
                DP[j][i] = grid[i][j] == '1' ? DP[j - 1][i] + 1 : 0;
        }
        
        for(int i = 0; i < M; i++)
            Arrays.sort(DP[i]);
        
        int maxArea = 0;
        for(int i = 0; i < M; i++)
            for(int j = 0; j < N; j++)
                maxArea = Math.max(maxArea , (N - j + 1) * DP[i][j]);
        
        println(maxArea);
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