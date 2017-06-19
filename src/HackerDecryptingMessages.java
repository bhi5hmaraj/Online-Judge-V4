import java.util.*;
import java.io.*;
public class HackerDecryptingMessages {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX = (int) 1e6;
    static int bigPrime[];
    
    private static void preCalBigPrimeSieve()  {
        bigPrime = new int[MAX + 1];
        bigPrime[1] = 1;
        for (int i = 2; i <= MAX; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= MAX; j += i)
                    bigPrime[j] = i;
            }
        }
    }
    
    static class MyBitSet {
        long bits[];
        int cardinality;
        int size;
        MyBitSet(int MAX) {
            size = MAX;
            bits = new long[((MAX - 1) / 64) + 1];
            cardinality = 0;
        }

        void set(int n, boolean f) {
            int index = n / 64;
            if (f) {
                if((bits[index] & (1L << (n % 64))) == 0)
                    cardinality++;
                bits[index] |= (1L << (n % 64));
            }
            else
                bits[index] ^= (1L << (n % 64));
        }

        void set(int n) {
            set(n, true);
        }
        
        int cardinality() {
            return cardinality;
        }
        
        boolean get(int n) {
            return ((bits[n / 64]) & (1L << (n % 64))) != 0;
        }
        
        void print() {
            System.out.println("bitset card " + cardinality + " size " + size);
            for(long l : bits)
                System.out.println(Long.toBinaryString(l));
        }
    }

    
    private static void solve() {
        
        preCalBigPrimeSieve();
        int N = nextInt();
        int Q = nextInt();
        int arr[] = nextIntArray(N);
        
        MyBitSet powers = new MyBitSet(MAX);
        powers.set(1);
        for(int a : arr) 
            if(!powers.get(a)){
                int pow = a;
                powers.set(pow);
                while(pow <= MAX / a) {
                    pow *= a;
                    powers.set(pow);
                }
            }
        
        int pows[] = new int[powers.cardinality];
        for(int i = )
        
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