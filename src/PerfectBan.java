import java.util.*;
import java.io.*;
public class PerfectBan {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class SparseTable2D  // <O (MNlog(M)log(N)) , O(1) >  
    {       
        /*
         * Dont use Math.log its (very * 2 ^ 64) slow for a lot of queries (10^6), 
         * instead precompute the log values . or use log(n) = (31 - Integer.numberOfLeadingZeros(n))
         * Costed me several days
         * 
         * In a sparse table you break the query rectangle into four smaller rects each with 
         * dimensions log(height) X log(width)
         * 
         * So the query time is O(1) , but the build time is O(M * N * log(M) * log(N))
         * 
         *  ********|****|******
         *  ********|****|******
         *  ********|****|******
         *  --------|----|------
         *  ******************
         *  ******************
         */

        int sparse[][][][];
        static int log(int N) { return 31 - Integer.numberOfLeadingZeros(N); }
        int R,C;

        int getMax(int x1,int y1,int x2,int y2, boolean build){
            int x_sz = x2 - x1 + 1;
            int y_sz = y2 - y1 + 1;
            int k1 = (x_sz == 1) ? 0 : log(build ? (x_sz - 1) : x_sz);
            int k2 = (y_sz == 1) ? 0 : log(build ? (y_sz - 1) : y_sz);
            int NW = sparse[k1][k2][x1][y1]; // North-West
            int NE = sparse[k1][k2][x1][y_sz - (1 << k2) + y1]; // North-East
            int SW = sparse[k1][k2][x_sz - (1 << k1) + x1][y1]; // South-West
            int SE = sparse[k1][k2][x_sz - (1 << k1) + x1][y_sz - (1 << k2) + y1]; // South-East
            return (int) Math.max(Math.max(NW, NE), Math.max(SW, SE));
        }

        SparseTable2D(int arr[][],int R,int C) {
            this.R = R;
            this.C = C;
            int k1 = log(R) + 1;
            int k2 = log(C) + 1;
            sparse = new int[k1][k2][R][C];
            for (int i = 0; i < R; i++)
                for (int j = 0; j < C; j++)
                    sparse[0][0][i][j] = arr[i][j];

            for(int h=0;h<k1;h++)
                for(int v=0;v<k2;v++)
                    if(!(h == 0 && v == 0))
                        for(int i=0;i+(1<<h) <= R;i++)
                            for(int j=0;j+(1<<v) <= C;j++)
                                sparse[h][v][i][j] = getMax(i, j, i + (1<<h) - 1, j + (1<<v) - 1, true);

        }
    }
    
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        
        int arr[][] = new int[n + 2][m + 2];
        for(int i = 1; i <= n; i++)
            System.arraycopy(nextIntArray(m), 0, arr[i], 1, m);
        
        SparseTable2D sparseTable = new SparseTable2D(arr, n + 2, m + 2);
        int min = Integer.MAX_VALUE;
        int r = -1 , c = -1;
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= m; j++) {
                int cost = Math.min(min , Math.max(Math.max(sparseTable.getMax(0, 0, i - 1, j - 1, false) , 
                                                            sparseTable.getMax(0, j + 1, i - 1, m + 1, false)) , 
                                                   Math.max(sparseTable.getMax(i + 1, 0, n + 1, j - 1, false) , 
                                                            sparseTable.getMax(i + 1, j + 1, n + 1, m + 1, false))));
                if(cost < min) {
                    min = cost;
                    r = i;
                    c = j;
                }
            }
        
        println(r + " " + c);
        
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