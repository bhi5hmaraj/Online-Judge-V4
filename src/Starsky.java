import java.util.*;
import java.io.*;
public class Starsky {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    private static int sumOfRegion(int prefixSum[][],int x1,int y1,int x2,int y2) {
        int entire = prefixSum[x2][y2];
        int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
        int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
        int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;      
        return entire - N - W + NW;
    }
    
    static int[][] initPrefixSum(int arr[][]) {
        int prefixSum[][] = new int[MAX][MAX];
        for(int i=0;i<MAX;i++)
            prefixSum[i][0] = arr[i][0];

        for(int i=0;i<MAX;i++)
            for(int j=1;j<MAX;j++)
                prefixSum[i][j] = prefixSum[i][j-1] + arr[i][j];

        for(int i=1;i<MAX;i++)
            for(int j=0;j<MAX;j++)
                prefixSum[i][j] += prefixSum[i-1][j];   
        return prefixSum;
    }
    
    static final int MAX = 100;
    static final int MAX_C = 11;
    private static void solve() {
        
        int n = nextInt();
        int q = nextInt();
        int c = nextInt() + 1;
        int bright[][][] = new int[MAX_C][MAX][MAX];
        int cnt[][][] = new int[MAX][MAX][MAX_C];
        while(n-->0) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int s = nextInt();
            bright[0][x][y] += s;
            cnt[x][y][s]++;
        }
        
        for(int i = 1; i < MAX_C; i++) {
            
        }
        
        while(q-->0) {
            int x1 = nextInt() - 1;
            int y1 = nextInt() - 1;
            int x2 = nextInt() - 1;
            int y2 = nextInt() - 1;
            long t = nextInt();
            long ans = (sumOfRegion(prefixBright, x1, y1, x2, y2) + (1L * sumOfRegion(prefixCNT, x1, y1, x2, y2) * t);
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