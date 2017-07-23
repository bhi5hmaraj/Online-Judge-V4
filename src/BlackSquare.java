import java.util.*;
import java.io.*;
public class BlackSquare {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int prefixSum[][];
    private static int sumOfRegion(int x1,int y1,int x2,int y2) {
        int entire = prefixSum[x2][y2];
        int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
        int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
        int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;      
        return entire - N - W + NW;
    }
    
    
    private static void solve() {
        
        int n = nextInt();
        int m = nextInt();
        int arr[][] = new int[n][];
        for(int i = 0; i < n; i++)
            arr[i] = nextLine().chars().map(ch -> ch == 'B' ? 1 : 0).toArray();
        
        for(int i=0;i<n;i++)
            prefixSum[i][0] = arr[i][0];

        for(int i=0;i<n;i++)
            for(int j=1;j<m;j++)
                prefixSum[i][j] = prefixSum[i][j-1] + arr[i][j];

        for(int i=1;i<n;i++)
            for(int j=0;j<m;j++)
                prefixSum[i][j] += prefixSum[i-1][j];   
        
        int min = Integer.MAX_VALUE;
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