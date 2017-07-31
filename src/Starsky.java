import java.util.*;
import java.io.*;
public class Starsky {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    private static long sumOfRegion(long prefixSum[][],int x1,int y1,int x2,int y2) {
        long entire = prefixSum[x2][y2];
        long W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
        long N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
        long NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;      
        return entire - N - W + NW;
    }
    
    static long[][] initPrefixSum(long arr[][]) {
        long prefixSum[][] = new long[MAX][MAX];
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
    
    static void prettyPrint(long a[][]) {
        int n = a.length;
        int m = a[0].length;
        long temp[][] = new long[n + 1][m + 1];
        temp[0][0] = -1;
        for(int i = 1; i <= n; i++) {
            temp[i][0] = i - 1;
            System.arraycopy(a[i - 1], 0, temp[i], 1, m);
        }
        for(int j = 1; j <= m; j++)
            temp[0][j] = j - 1;
        
        for(long t[] : temp) {
            for(long tt : t)
                print(String.format("%3d ", tt));
            print('\n');
        }
        print('\n');
    }
    
    static final int MAX = 100;
    private static void solve() {
        
        int n = nextInt();
        int q = nextInt();
        int MAX_C = nextInt() + 1;
        long[][][] bright = new long[MAX_C][MAX][MAX];
        long cnt[][][] = new long[MAX][MAX][MAX_C];
        while(n-->0) {
            int x = nextInt() - 1;
            int y = nextInt() - 1;
            int s = nextInt();
            bright[0][x][y] += s;
            cnt[x][y][s]++;
        }
        
        for(int i = 1; i < MAX_C; i++) 
            for(int j = 0; j < MAX; j++)
                for(int k = 0; k < MAX; k++)
                    for(int p = 0; p < MAX_C; p++)
                        bright[i][j][k] += cnt[j][k][p] * ((p + i) % MAX_C);
        /*
        for(int i = 0; i < MAX_C; i++)
            prettyPrint(bright[i]);
        */
        for(int i = 0; i < MAX_C; i++)
            bright[i] = initPrefixSum(bright[i]);
        /*
        for(int i = 0; i < MAX_C; i++)
            prettyPrint(bright[i]);
        */
        while(q-->0) {
            int t = nextInt();
            int x1 = nextInt() - 1;
            int y1 = nextInt() - 1;
            int x2 = nextInt() - 1;
            int y2 = nextInt() - 1;
            println(sumOfRegion(bright[t % MAX_C], x1, y1, x2, y2));
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