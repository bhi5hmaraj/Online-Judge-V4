import java.util.*;
import java.io.*;
public class MagicFive {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    static class MMInt {       // MM (Modular Math) class 
        static final int mod = (int) (1e9) + 7; // Default
        static int sub(int a, int b) {return (a - b  + mod) % mod;}
        static int mul(int a, int b) {return (int) ((1L * a * b ) % mod);}
        static int add(int a, int b) {return (a + b) % mod;}
        static int div(int a, int b) {return mul(a, modInverse(b));}
        static int modInverse(int n) {return modPow(n, mod - 2);} // Fermat's little theorem
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
    private static void solve() {
        
        char str[] = nextLine().toCharArray();
        int k = nextInt();
        int ans = 0;
        int cache = MMInt.div(MMInt.sub(MMInt.modPow(2, 1L * str.length * k), 1), 
                    MMInt.sub(MMInt.modPow(2, str.length), 1)); 
        for(int i = 0; i < str.length; i++) 
            if(str[i] == '0' || str[i] == '5') 
                ans = MMInt.add(ans, MMInt.mul(MMInt.modPow(2, i), cache));

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