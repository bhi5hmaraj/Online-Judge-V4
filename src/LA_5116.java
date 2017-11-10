import java.util.*;
import java.io.*;
public class LA_5116 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int P;

    static class MyBitSet {
        long bits[];
        int cardinality;
        int size;
        MyBitSet(int MAX) {
            size = MAX;
            bits = new long[((MAX - 1) / 64) + 1];
            cardinality = 0;
        }

        void set(int n) {
            int index = n / 64;
            if((bits[index] & (1L << (n % 64))) == 0)
                cardinality++;
            bits[index] |= (1L << (n % 64));
        }

        
        int cardinality() {
            return cardinality;
        }
    }
    
    static int primes[] = {2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67};
    static MyBitSet bitset;

    static void rec(int idx , int n , int val) {
        if(idx < primes.length && n >= 0) {
            bitset.set(val);
            int curr = 1;
            for(int i = 0; n - primes[idx] * i >= 0; i++) {
                rec(idx + 1, n - primes[idx] * i, (int) ((1L * val * curr) % (long) P));
                curr = (int) ((1L * curr * primes[idx]) % (long) P);
            }
        }
    }
    
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            P = nextInt();
            bitset = new MyBitSet(P);
            rec(0, N, 1);
            bitset.set(N % P);
            println(bitset.cardinality());
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