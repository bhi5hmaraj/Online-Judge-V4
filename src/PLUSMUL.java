import java.util.*;
import java.io.*;
class PLUSMUL {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
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

    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            long arr[] = nextLongArray(N);
            
            long F[] = new long[N];
            long M[] = new long[N];
            long A[] = new long[N];
            long suffixConv[] = new long[N];
            long prefixF[] = new long[N];
            A[0] = prefixF[0] = F[0] = suffixConv[0] =  arr[0];
            if(N > 1) {
                A[1] = MM.add(arr[0], arr[1]);
                M[1] = MM.mul(arr[0], arr[1]);
                F[1] = MM.add(A[1], M[1]);
                prefixF[1] = F[1] + prefixF[0];
                suffixConv[1] = MM.mul(arr[0], arr[1]);
            }
            long pow2 = 2;
            long pow22 = 1;
            for(int i = 2; i < N; i++) {
                A[i] = MM.add(F[i - 1], MM.mul(pow2, arr[i]));
                suffixConv[i] = MM.add(MM.mul(pow22, MM.mul(arr[i], arr[i - 1])), MM.mul(arr[i], suffixConv[i - 1]));
                M[i] = MM.add(prefixF[i - 2], suffixConv[i]);
                F[i] = MM.add(A[i], M[i]);
                prefixF[i] = MM.add(F[i], prefixF[i - 1]);
                pow2 = MM.mul(pow2, 2); // last
                pow22 = MM.mul(pow22, 2);
            }
            
            println(F[N - 1]);
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