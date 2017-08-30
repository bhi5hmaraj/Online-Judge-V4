import java.util.*;
import java.io.*;
public class uva_12546 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int mod = 1000000007;
    static final int MAX_POW = 50;
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int K = nextInt();
            int primeFact[] = new int[K];
            int pow[] = new int[K];
            for(int i = 0; i < K; i++) {
                primeFact[i] = nextInt();
                pow[i] = nextInt();
            }
            
            int cachePow[][] = new int[K][MAX_POW + 1];
            for(int i = 0; i < K; i++) {
                cachePow[i][0] = 1;
                int p = primeFact[i];
                for(int j = 1; j <= MAX_POW; j++) {
                    cachePow[i][j] = (cachePow[i][j - 1] + p) % mod;
                    p = (int) ((1L * p * primeFact[i]) % mod);
                }
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