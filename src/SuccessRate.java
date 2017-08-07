import java.util.*;
import java.io.*;
public class SuccessRate {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    // greater than equal to a / b
    static long grtEq(long a , long b) {
        long ans = (a / b) + (Long.signum(a) * Long.signum(b) > 0 && Math.abs(a) % Math.abs(b) != 0 ? 1 : 0);
        // println("grt a  " + a + " b " + b + " ans " + ans);
        return ans;
    }
    // less than or equal to a / b
    static long lessEq(long a , long b) {
        long ans = (a / b) + (Long.signum(a) * Long.signum(b) < 0 && a % b != 0 ? -1 : 0);
        // println("less a  " + a + " b " + b + " ans " + ans);
        return ans;
    }
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            long x = nextLong();
            long y = nextLong();
            long p = nextLong();
            long q = nextLong();
            if(p == 0)
                println(x == 0 ? 0 : -1);
            else if(p == q)
                println(x >= y ? x - y : -1);
            else {
                long opti = Math.max(grtEq(x - y, p - q) , Math.max(grtEq(x, p) , grtEq(y, q)));
                println(opti * q - y);
            }
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