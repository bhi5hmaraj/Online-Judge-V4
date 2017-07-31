import java.util.*;
import java.io.*;
public class Palindromiccharacteristics  {
    
    
    
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
    static int cache1[] , cache2[];
    static int hold[][];
    static void subHash(int l , int r , int idx) {
        l++;
        r++;
        hold[idx][0] = (int) ((1L * cache1[l - 1] * ((hash1[r] - hash1[l - 1] + m1) % m1)) % (long)(m1));
        hold[idx][1] = (int) ((1L * cache2[l - 1] * ((hash2[r] - hash2[l - 1] + m2) % m2)) % (long)(m2));
    }
    
    
    private static void solve() {
        
        char str[] = nextLine().toCharArray();
        long st = System.nanoTime();
        int n = str.length;
        int log = 32 - Integer.numberOfLeadingZeros(n);
        
        boolean DP[][] = new boolean[n][n];
        boolean isPalin[][] = new boolean[n][n];
        
        for(int i = 0; i < n; i++)
            isPalin[i][i] = true;
        for(int i = 0; i < n - 1; i++)
            isPalin[i][i + 1] = str[i] == str[i + 1];
        for(int len = 3; len <= n; len++)
            for(int i = 0; i + len - 1 < n; i++)
                isPalin[i][i + len - 1] = isPalin[i + 1][i + len -2] && str[i] == str[i + len - 1];
        hold = new int[2][2];
        hash1 = new int[n + 1];
        hash2 = new int[n + 1];
        cache1 = new int[n + 1];
        cache2 = new int[n + 1];
        hash1[1] = hash2[1] = str[0];

        for(int i = 2; i <= n; i++) {
            hash1[i] = (hash1[i - 1] + ((int) ((1L * pow1[i - 1] * str[i - 1]) % (long)(m1)))) % m1;
            hash2[i] = (hash2[i - 1] + ((int) ((1L * pow2[i - 1] * str[i - 1]) % (long)(m2)))) % m2;
        }
        
        for(int i = 0; i < n; i++) {
            cache1[i] = modInverse(pow1[i], m1);
            cache2[i] = modInverse(pow2[i], m2);
        }
        
        int cnt[] = new int[n];
        for(int len = 1; len <= n; len++)
            for(int i = 0; i + len - 1 < n; i++) {
                cnt[0] += isPalin[i][i + len - 1] ? 1 : 0;
                DP[i][i + len - 1] = isPalin[i][i + len - 1];
            }
        for(int k = 1; k < log; k++) {
            boolean t[][] = new boolean[n][n];
            for(int len = 2; len <= n; len++)
                for(int i = 0; i + len - 1 < n; i++) {
                    int L = i;
                    int R = i + len - 1;
                    subHash(L, L + (len / 2) - 1 , 0);
                    subHash(R - (len / 2) + 1, R , 1);
                    if(hold[0][0] == hold[1][0] && hold[0][1] == hold[1][1] && DP[L][L + (len / 2) - 1] && 
                                                           DP[R - (len / 2) + 1][R]) {
                        t[L][R] = true;
                        cnt[k]++;
                    }
                }
                    
            DP = t;
        }
        
        for(int a : cnt)
            print(a + " ");

        println("\nTime : " + (System.nanoTime() - st) / 1e9);

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