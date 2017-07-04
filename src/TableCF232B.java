import java.util.*;
import java.io.*;
public class TableCF232B {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int mod = (int) (1e9) + 7; // Default
    static class MM {       // MM (Modular Math) class 
        static int sub(int a, int b) {return (a - b  + mod) % mod;}
        static int mul(int a, int b) {return (int) ((1L * a * b ) % mod);}
        static int add(int a, int b) {return (a + b) % mod;}
        static int div(int a, int b) {return mul(a, modInverse(b));}
        static int modInverse(int n)  {return modPow(n, mod - 2);} // Fermat's little theorem
        static int modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return (int) pow;
        }
    }
    
    static final int MAX = 128;                    
    static int fact[] = new int[MAX];
    static int invFact[] = new int[MAX];
    static {
        fact[0] = fact[1] = 1;
        for (int i = 2; i < MAX; i++)
            fact[i] = MM.mul(i, fact[i - 1]);
        for (int i = 0; i < MAX; i++)
            invFact[i] = MM.modInverse(fact[i]);
    }

    static int nCr(int n, int r) { // Precompute inv Factorials (Dont compute every time) 
        return MM.mul(fact[n], MM.mul(invFact[r], invFact[n - r]));
    }
    
    static long m;
    static int n;
    static int memo[][];
    static int rec(int idx , int k) {
        if(idx == n)
            return k == 0 ? 1 : 0;
        else if(memo[idx][k] != -1)
            return memo[idx][k];
        else {
            long pow = (m / n) + (idx < m % n ? 1 : 0);
            int sum = 0;
            for(int i = 0; i <= Math.min(k , n); i++) 
                sum = MM.add(sum, MM.mul(rec(idx + 1, k - i), MM.modPow(nCr(n, i), pow)));
            
            return memo[idx][k] = sum;
        }
    }
    
    private static void solve() {
        
        n = nextInt();
        m = nextLong();
        int k = nextInt();
        
        memo = new int[n][k + 1];
        for(int temp[] : memo) Arrays.fill(temp, -1);
        
        long st = System.nanoTime();
        println(rec(0, k));
        System.out.println("Time : " + (System.nanoTime() - st) / 1e9);
    }

    private static void solve2() {
        
        int n = nextInt();
        long m = nextLong();
        int k = nextInt();
        long quo = m / n;
        int rem = (int) (m % n);
        int cache[][] = new int[Math.min(n , k) + 1][2];
        
        long st = System.nanoTime();
        
        for(int i = 0; i <= Math.min(n , k); i++) {
            int comb = nCr(n, i);
            cache[i][0] = MM.modPow(comb , quo);
            cache[i][1] = MM.mul(cache[i][0], comb);
        }
        int DP[] = new int[k + 1];
        DP[0] = 1;   // base case
        for(int i = 1; i <= Math.min(n , k); i++) 
            DP[i] = cache[i][0];
        
        for(int i = n - 2; i >= 0; i--) {
            int temp[] = new int[k + 1];
            int flag  = (i < rem ? 1 : 0);
            for(int kk = 0; kk <= k; kk++)
                for(int j = 0; j <= Math.min(n , kk); j++)
                    temp[kk] = MM.add(temp[kk], MM.mul(cache[j][flag], DP[kk - j]));
            DP = temp;
        }
        
        println(DP[k]);
        
        System.out.println("Time : " + (System.nanoTime() - st) / 1e9);
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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