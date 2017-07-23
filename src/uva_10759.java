import java.util.*;
import java.io.*;
public class uva_10759 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
    
    private static void solve() {
        int n , m;
        final int MAX_DICE = 24;
        final int MAX_SUM = 150;
        long ways[][] = new long[MAX_DICE][MAX_SUM];
        for(int i = 1; i <= 6; i++)
            ways[0][i] = 1;
        
        for(int i = 1; i < MAX_DICE; i++) 
            for(int j = 1; j < MAX_SUM; j++)
                for(int k = 1; k <= Math.min(6 , j); k++)
                    ways[i][j] += ways[i - 1][j - k];
        
        
        while((n = nextInt()) != 0) {
            m = nextInt();
            if(m <= n)
                println(1);
            else if(m > 6 * n)
                println(0);
            else {
                long numer = 0;
                long denom = 1;
                for(int i = m; i <= 6 * n; i++)
                    numer += ways[n - 1][i];
                
                while(n-->0) denom *= 6;
                long gcd = gcd(numer, denom);
                numer /= gcd;
                denom /= gcd;
                println(numer + "/" + denom);
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