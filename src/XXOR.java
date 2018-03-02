import java.util.*;
import java.io.*;
class XXOR  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX_BITS = 31;
    
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        
        int bits[][] = new int[MAX_BITS][N + 1];
        int arr[] = nextIntArrayOneBased(N);
        
        for(int i = 0; i < MAX_BITS; i++) {
            for(int j = 1; j <= N; j++)
                bits[i][j] = (arr[j] & (1 << i)) == 0 ? 0 : 1;
            
            for(int j = 1; j <= N; j++)
                bits[i][j] += bits[i][j - 1];
        }
        
        while(Q-->0) {
            int L = nextInt();
            int R = nextInt();
            int X = 0;
            for(int i = MAX_BITS - 1; i >= 0; i--)
                X = (X << 1) | ((2 * (bits[i][R] - bits[i][L - 1]) < R - L + 1) ? 1 : 0);
            println(X);
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