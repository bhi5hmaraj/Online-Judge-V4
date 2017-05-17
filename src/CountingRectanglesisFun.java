import java.util.*;
import java.io.*;
public class CountingRectanglesisFun {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    private static void solve() {
        
        
        int N = nextInt();
        int M = nextInt();
        int Q = nextInt();
        
        char arr[][] = new char[N][];
        for(int i = 0; i < N; i++)
            arr[i] = nextLine().toCharArray();
        
        int DP[][][][] = new int[N][M][N][M];
        boolean conn[][][][] = new boolean[N][M][N][M];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == '0') {
                    conn[i][j][i][j] = true;
                
                    // Base case 
                    for(int ii = i + 1; ii < N; ii++) 
                        conn[i][j][ii][j] = (arr[ii][j] == '0' && conn[i][j][ii - 1][j]);
                        
                    for(int jj = j + 1; jj < M; jj++)
                        conn[i][j][i][jj] = (arr[i][jj] == '0' && conn[i][j][i][jj - 1]);
                    
                    for(int ii = i + 1; ii < N; ii++)
                        for(int jj = j + 1; jj < M; jj++)
                            conn[i][j][ii][jj] = (arr[ii][jj] == '0' && conn[i][j][ii][jj - 1]  && conn[i][j][ii - 1][jj]);
                    
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(arr[i][j] == '0') {
                    DP[i][j][i][j] = 1;
                    for(int ii = i + 1; ii < N; ii++)
                        DP[i][j][ii][j] = DP[i][j][ii - 1][j] + (conn[i][j][ii][j] ? 1 : 0);
                    for(int jj = j + 1; jj < M; jj++)
                        DP[i][j][i][jj] = DP[i][j][i][jj - 1] + (conn[i][j][i][jj] ? 1 : 0);
                    
                    for(int ii = i + 1; ii < N; ii++)
                        for(int jj = j + 1; jj < M; jj++) {
                            if(conn[i][j][ii][jj])
                                DP[i][j][ii][jj] = (ii - i + 1) * (jj - j + 1);
                            else
                                DP[i][j][ii][jj] = DP[i][j][ii][jj - 1] + DP[i][j][ii - 1][jj] - DP[i][j][ii - 1][jj - 1];
                        }
                }
            }
        }
        /*
        System.out.println();
        for(boolean a[] : conn[0][1])
            System.out.println(Arrays.toString(a));
        for(int a[] : DP[0][1])
            System.out.println(Arrays.toString(a));
        */
        
        int prefix[][][][] = new int[N][M][][];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                prefix[i][j] = new int[i + 1][j + 1];
                if(arr[i][j] == '0')
                    prefix[]
            }
        }
        
        while(Q-->0) {
            int a = nextInt() - 1;
            int b = nextInt() - 1;
            int c = nextInt() - 1;
            int d = nextInt() - 1;
            int cnt = 0;
            for(int i = a; i <= c; i++)
                for(int j = b; j <= d; j++)
                    if(arr[i][j] == '0') {
//                        System.out.printf("\nDP[%d][%d][%d][%d] = %d" , i , j , c , d , DP[i][j][c][d]);
                        cnt += DP[i][j][c][d];
                    }
//            System.out.println();
            println(cnt);
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