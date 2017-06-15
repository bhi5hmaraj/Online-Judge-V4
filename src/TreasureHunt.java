import java.util.*;
import java.io.*;
public class TreasureHunt {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {

        int x1 = nextInt();
        int y1 = nextInt();
        int x2 = nextInt();
        int y2 = nextInt();
        int x = nextInt();
        int y = nextInt();
        
        long t1 = 1L * x * (y1 - y2);
        long t2 = 1L * y * (x1 + x2);
        if((t1 + t2) % (2L * y) == 0) {
            long ix = (t1 + t2) / (2L * y);
            long t3 = 1L * x * y1;
            long t4 = 1L * y * (x1 - ix);
            if((t3 + t4) % (long)x == 0) {
                long iy = (t3 + t4) / (long)x;
                long a = x;
                long b = y;
                // System.out.println("ix = " + ix + " iy = " + iy);
                if((ix - x1) % a == 0 && (y1 - iy) % b == 0 && (ix - x1) / a == (y1 - iy) / b)
                    println("YES");
                else
                    println("NO");
            }
            else
                println("NO");
        }
        else
            println("NO");
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