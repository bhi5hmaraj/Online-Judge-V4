import java.util.*;
import java.io.*;
public class Multiplierss {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int mod = (int) (1e9) + 7; // Default
    static class MMInt {       // MM (Modular Math) class 
        static int sub(int a, int b , int mod) {return (a - b  + mod) % mod;}
        static int mul(int a, int b , int mod) {return (int) ((1L * a * b ) % mod);}
        static int add(int a, int b , int mod) {return (a + b) % mod;}
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
    }
    
    private static void solve() {
        
        int m = nextInt();
        int primes[] = nextIntArray(m);
        
        int MAX = (int) 2e5;
        int freq[] = new int[MAX + 1];
        for(int p : primes) 
            freq[p]++;
        
        TreeSet<Integer> set = new TreeSet<>(); 
        Arrays.stream(primes).forEach(set::add);
        int pSorted[] = new int[set.size()];
        int ptr = 0;
        for(int p : set)
            pSorted[ptr++] = p;
        
        int left = 1;
        int right[] = new int[set.size()];
        right[set.size() - 1] = 1;
        for(int i = pSorted.length - 2; i >= 0; i--)
            right[i] = MMInt.mul(right[i + 1], freq[pSorted[i + 1]] + 1, mod - 1);
        
        int ans = 1;
        for(int i = 0; i < pSorted.length; i++) {
            int p = pSorted[i];
            // println(p + " == " + freq[p]);
            int pow = p;
            int ways = MMInt.mul(left, right[i], mod - 1);
            for(int j = 0; j < freq[p]; j++) {
                // println("pow " + pow);
                ans = MMInt.mul(ans, MMInt.modPow(pow, ways , mod) , mod);
                pow = MMInt.mul(pow, p , mod);
            }
            left = MMInt.mul(left, freq[p] + 1 , mod - 1);
        }
        
        println(ans);
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