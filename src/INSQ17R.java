import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
class INSQ17R {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long base[] = {0 , 0};    // conv , sum of bits
    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) (1e9) + 7; // Default
        static long sub(long a, long b) {return (a - b  + mod) % mod;}
        static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
        static long add(long a, long b) {return ((a % mod) + (b % mod)) % mod;}
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
    static long[][] memo;
    static long[] rec(long N) {
        if(N == 1)
            return base;
        else {
            long sub[] = rec(N / 2);
            long n = N / 2;
            long nc2 = MM.div(MM.mul(n, MM.sub(n, 1)), 2);
            long conv = MM.add(MM.add(sub[0], nc2), MM.add(MM.mul(n, sub[1]),MM.mul(n, n)));
            long bitCnt = MM.add(n, MM.mul(2, sub[1]));
            return memo[Long.numberOfTrailingZeros(N)] = new long[]{MM.add(conv, sub[0]), bitCnt};
        }
    }
    
    static long sum(long N , long padding , int off) {
        // System.out.println("N " + N + " pad " + padding + " off " + off);
        if((N & (N + 1)) == 0) {
            int idx = Long.numberOfTrailingZeros(N + 1);
            long ret[] = memo[idx];
            long n = 1L << idx;
            long nc2 = MM.div(MM.mul(n, MM.sub(n, 1)), 2);
            long conv = MM.add(MM.add(ret[0], MM.mul(off, nc2)), 
                               MM.add(MM.mul(padding, ret[1]), MM.mul(MM.mul(padding, off), n)));
            return conv;
        }
        else {
            long near = Long.highestOneBit(N);
            long sub = sum(near - 1, padding, off);
            long remain = N - (near - 1) - 1;
            return MM.add(sub, sum(remain, padding + near, off + 1));
        }
    }
    
    private static void solve() {
        
        memo = new long[40][];
        memo[0] = base;
        rec(1L << 32);

        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            println(sum(N, 0, 0));
            // println("correct " + (IntStream.range(1, N + 1).mapToLong(i -> 1L * i * Integer.bitCount(i)).sum()) % MM.mod);
            
        }
        
        //println(Arrays.toString(rec(N)));
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