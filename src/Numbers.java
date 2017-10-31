import java.util.*;
import java.io.*;
public class Numbers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int a[];
    private static int MAX = 500; // (Change it to max N limit (be careful with the index)                   
    private static long fact[] = new long[MAX + 5];
    private static long invFact[] = new long[MAX + 5];
    static {
        fact[1] = 1;
        fact[0] = 1;
        for (int i = 2; i <= MAX; i++)
            fact[i] = MM.mul(i, fact[i - 1]);
        for(int i=0;i<=MAX;i++)
            invFact[i] = MM.modInverse(fact[i]);
    }

    private static long nCr(int n, int r) { // Precompute inv Factorials (Dont compute every time) 
        return MM.mul(fact[n], MM.mul(invFact[r], invFact[n - r]));
    }
    
    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) (1e9) + 7; // Default
        static long sub(long a, long b) {return (a - b  + mod) % mod;}
        static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
        static long add(long a, long b) {return (a + b) % mod;}
        static long div(long a, long b) {return mul(a, modInverse(b));}
        static long modInverse(long n)  {return modPow(n, mod - 2);} // Fermat's little theorem
        static long modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return pow;
        }
    }
    
    static long rec(int n , int digit) {
        if(digit > 9)
            return n == 0 ? 1 : 0;
        else if((digit != 0 && n < a[digit]) || (digit == 0 && a[digit] > n - 1))
            return 0;
        else {
            long ways = 0;
            for(int k = a[digit] , end = n - (digit == 0 ? 1 : 0); k <= end; k++)
                ways = MM.add(ways, MM.mul(nCr(end, k), rec(n - k, digit + 1)));
            
            println("digit " + digit + " n " + n + " ways " + ways);
            return ways;
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        a = nextIntArray(10);
        long total = 0;
        for(int i = Arrays.stream(a).sum(); i <= n; i++)
            total = MM.add(total, rec(i, 0));
        
        println(total);
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