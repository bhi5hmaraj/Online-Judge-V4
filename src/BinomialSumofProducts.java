import java.util.*;
import java.io.*;
public class BinomialSumofProducts {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX = 1000;
    static long table[][];
    static final long mod = (long) (1e9) + 7; 
    static class MM {       // MM (Modular Math) class 
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
    static {
        table = new long[MAX + 1][MAX + 1];
        for(int i = 0; i <= MAX; i++)
            table[i][0] = table[i][i] = 1;
        
        for(int i = 1; i <= MAX; i++) {
            for(int j = 1; j < i; j++)
                table[i][j] = (table[i - 1][j - 1] + table[i - 1][j]) % mod;    // pascal triangle
            for(int j = i + 1; j <= MAX; j++)
                table[i][j] = i * j;
        }
        
        for(int i = 1; i <= MAX; i++) 
            for(int j = 1; j <= MAX; j++)
                table[i][j] = MM.mul(table[i][j], table[i][j - 1]);
        
    }
    
    static long get(int r , int L , int R) {
        if(r == 0)
            return L == 0 && R == 0 ? 1 : 0;
        else 
            return L == 0 ? table[r][R] : MM.div(table[r][R], table[r][L - 1]);
    }
    
    private static void solve() {
        
        
        int Q = nextInt();
        while(Q-->0) {
            long sum = 0;
            int a = nextInt();
            int b = nextInt();
            int c = nextInt();
            int d = nextInt();
            for(int i = a; i <= b; i++)
                sum = MM.add(sum, get(i, c, d));
            
            println(sum);
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