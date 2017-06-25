import java.util.*;
import java.io.*;
public class OkabeandBananaTrees {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long sum(long n , long offset) {
        long s = (n * (n + 1)) / 2;
        s += n * offset;
//        System.out.println("n " + n + " off " +offset + " s "+ s);
        return s;
    }
    
    static long m , b;
    static long f(long x) {
        double ff = (-1.0 * x) / m  + b;
        return (long) Math.floor(ff);
    }
    static long inv(long y) {
        return m * (b - y);
    }
    static long area(long x , long y) {
        long a = ((x + 1) * (y + 1) * (x + y)) / 2L;
//        System.out.println("x " + x + " y " + y + " a " + a);
        return a;
    }
    
    private static void solve() {
        
        
        m = nextLong();
        b = nextLong();
        
        long max = 0;
        for(long x = 0; x <= m * b; x++) {
            long y = f(x);
//            System.out.println("x " + x + " y " + y); 
            max = Math.max(max , sum(y + 1, x - 1));    // parallel to y axis
            max = Math.max(max , area(x, y));
            max = Math.max(max , x + y);                // point
        }
        
        for(long y = 0; y <= b; y++) {
            long x = inv(y);
//            System.out.println("x " + x + " y " + y);
            max = Math.max(max , sum(x + 1, y - 1));
            max = Math.max(max , area(y, x));
            max = Math.max(max , x + y);
        }
        
        println(max);
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