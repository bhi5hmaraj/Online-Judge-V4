import java.util.*;
import java.io.*;
public class LA_5110 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int bigPrime[];
    static int N = (int) 1e6;
    
    static {
        bigPrime = new int[N + 1];
        preCalBigPrimeSieve();
    }
    
    private static void preCalBigPrimeSieve() {
        bigPrime[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= N; j += i)
                    bigPrime[j] = i;
            }
        }
    }

    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int n = nextInt();
            int maxPow = 0;
            while(n > 1) {
                int cnt = 0;
                int p = bigPrime[n];
                while(n % p == 0) {
                    cnt++;
                    n /= p;
                }
                maxPow = Math.max(maxPow , cnt);
            }
            println(maxPow);
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