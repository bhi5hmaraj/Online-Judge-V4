import java.util.*;
import java.io.*;
class MATDYS {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long pos(long idx , long len) {
        if(len == 0)
            return idx;
        long accum = 0;
        if(Long.remainderUnsigned(idx, 2) == 1)
            accum =  len;
        idx = Long.divideUnsigned(idx, 2);
        len = Long.divideUnsigned(len, 2);
        return accum + pos(idx, len);
    }
    
    static final long max = Long.parseUnsignedLong("9223372036854775808");
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            long K = Long.parseUnsignedLong(next());
            long off = 1L << (N - 1); 
            if(N == 64)
                off = max;
            println(Long.toUnsignedString(pos(K, off)));
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