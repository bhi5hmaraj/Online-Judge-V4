import java.util.*;
import java.io.*;
public class CommonDivisors {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int p[] = {100151 , 100151 + 2};
    static final int m[] = {(int) 1e9 + 7 , (int) 1e9 + 7 + 2};
    static final int MAX = (int) 1e5;
    static final int pow[][] = new int[MAX + 1][2];
    static int sub(int a, int b , int mod) {return (a - b + mod) % mod;}
    static int mul(int a, int b , int mod) {return (int) ((1L * a * b) % (1L * mod));}
    static int add(int a, int b , int mod) {return (a + b) % mod;}
    static int div(int a, int b , int mod) {return (int) ((1L * a * modPow(b, mod - 2, mod)) % (1L * mod));}
    static int modPow(long a , long b , long mod) {
        long pow = 1;
        while(b > 0) {
            if((b & 1L) == 1)
                pow = ((pow * a) % mod);

            a = ((a * a) % (mod));
            b >>= 1;
        }
        return (int) (pow % mod);
    }

    static {
        pow[0][0] = pow[0][1] = 1;
        for(int i = 1; i <= MAX; i++) {
            pow[i][0] = mul(pow[i - 1][0], p[0], m[0]);
            pow[i][1] = mul(pow[i - 1][1], p[1], m[1]);
        }
    }
    
    static int[][] hash(String str) {
        int n = str.length();
        int h[][] = new int[n][2];
        h[0][0] = h[0][1] = str.charAt(0);
        for(int i = 1; i < n; i++) 
            for(int j = 0; j < 2; j++)
                h[i][j] = add(h[i - 1][j], mul(str.charAt(i), pow[i][j], m[j]), m[j]);
        
        return h;
    }
    
    static int[] repeat(int h[] , int period , int len) {
        int hh[] = new int[2];
        for(int i = 0; i < 2; i++) {
            int num = sub(1 , pow[len][i] , m[i]);
            int den = sub(1 , pow[period][i], m[i]);
            hh[i] = div(mul(h[i], num, m[i]), den, m[i]);
        }
        return hh;
    }
    
    static void pa(Object... o) {
        println(Arrays.deepToString(o));
    }
    
    static boolean equals(int a[] , int b[]) {
        return a[0] == b[0] && a[1] == b[1];
    }
    
    private static void solve() {
        
        
        String str1 = nextLine();
        String str2 = nextLine();
        
        int n = str1.length();
        int m = str2.length();
        
        int h1[][] = hash(str1);
        int h2[][] = hash(str2);
        
        int commonPeriod = 0;
        
        for(int divCand = 1; divCand * divCand <= n; divCand++) {
            if(n % divCand == 0) {
                if(m % divCand == 0 && equals(repeat(h1[divCand - 1], divCand, n), h1[n - 1])
                                    && equals(repeat(h1[divCand - 1], divCand, m), h2[m - 1]))
                    commonPeriod++;
                
                int other = n / divCand;
                
                if(other != divCand && m % other == 0 && 
                   equals(repeat(h1[other - 1], other, n), h1[n - 1]) &&
                   equals(repeat(h1[other - 1], other, m), h2[m - 1]))
                    commonPeriod++;
            }
        }
        
        println(commonPeriod);
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