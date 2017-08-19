import java.util.*;
import java.io.*;
class KSPHERES {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final long mod = (long) 1e9 + 7;
    
    private static void solve() {
        
        int N = nextInt();
        int M = nextInt();
        int C = nextInt();
        
        int U[] = nextIntArray(N);
        int L[] = nextIntArray(M);
        
        long radA[] = new long[C + 1];
        long radB[] = new long[C + 1];
        
        for(int a : U) radA[a]++;
        for(int a : L) radB[a]++;
        
        long DP[][] = new long[C + 1][C + 1];   // (seq , radius)
        for(int i = 1; i <= C; i++)
            DP[0][i] = (radA[i] * radB[i]) % mod;
        
        for(int seq = 1; seq <= C; seq++) {
            for(int rad = seq + 1; rad <= C; rad++) {
                
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