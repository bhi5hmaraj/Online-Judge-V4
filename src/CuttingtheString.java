import java.util.*;
import java.io.*;
public class CuttingtheString {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int p1 = 100151 , m1 = (int) 1e9 + 7;  // Twin Primes
    static final int p2 = p1 + 2 , m2 = m1 + 2;
    static final int MAX = (int) 6e3 + 10;
    static final int pow1[] = new int[MAX + 1];
    static final int pow2[] = new int[MAX + 1];
    static int modPow(long a , long b , long mod) {
        long pow = 1;
        while(b > 0) {
            if((b & 1L) == 1)
                pow = ((pow * a) % mod);

            a = ((a * a) % (mod));
            b >>= 1;
        }
        return (int) pow;
    }
    static int modInverse(long n , long mod)  {return modPow(n, mod - 2 , mod);} // Fermat's little theorem
    static {
        pow1[0] = pow2[0] = 1;
        for(int i = 1; i <= MAX; i++) {
            pow1[i] = (int) ((1L * pow1[i - 1] * p1) % (long)(m1));
            pow2[i] = (int) ((1L * pow2[i - 1] * p2) % (long)(m2));
        }
    }
    
    static int hash1[] , hash2[];
    static int[] subHash(int l , int r) {
        return new int[] {
                (int) ((1L * modInverse(pow1[l - 1], m1) * ((hash1[r] - hash1[l - 1] + m1) % m1)) % (long)(m1)),
                (int) ((1L * modInverse(pow2[l - 1], m2) * ((hash2[r] - hash2[l - 1] + m2) % m2)) % (long)(m2))
        };
    }
    
    
    static int[] concat(int[] h1 , int[] h2 , int len) {
        return new int[] {
                (h1[0] + (int) ((1L * len * h2[0]) % (long)(m1))) % m1,
                (h1[1] + (int) ((1L * len * h2[1]) % (long)(m2))) % m2
        };
    }
    
    private static void solve() {
        
        
        char str[] = nextLine().toCharArray();
        int N = str.length;
        hash1 = new int[N + 1];
        hash2 = new int[N + 1];
        
        hash1[1] = hash2[1] = str[0];
        for(int i = 2; i <= N; i++) {
            hash1[i] = (hash1[i - 1] + ((int) ((1L * pow1[i - 1] * str[i - 1]) % (long)(m1)))) % m1;
            hash2[i] = (hash2[i - 1] + ((int) ((1L * pow2[i - 1] * str[i - 1]) % (long)(m2)))) % m2;
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