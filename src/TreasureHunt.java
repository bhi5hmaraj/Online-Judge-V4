import java.util.*;
import java.io.*;
public class TreasureHunt {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int INF = (int) 1e5 , MINF = -INF;
    static boolean isValid(int x , int y) {
        return x >= MINF && x <= INF && y >= MINF && y <= INF;
    }
    static int x2 , y2; 
    static boolean reachable(int x , int y) {
        return x + y == x2 + y2 || x - y == x2 - y2;
    }
    private static void solve() {

        int x1 = nextInt();
        int y1 = nextInt();
        x2 = nextInt();
        y2 = nextInt();
        
        int x = nextInt();
        int y = nextInt();
        
        int tx = x1 , ty = y1;
        while(isValid(tx, ty)) {
            if(reachable(tx, ty)) {
                println("YES");
                return;
            }
            tx += x;
            ty += y;
        }
        
        tx = x1; ty = y1;
        while(isValid(tx, ty)) {
            if(reachable(tx, ty)) {
                println("YES");
                return;
            }
            tx += x;
            ty -= y;
        }
        

        tx = x1; ty = y1;
        while(isValid(tx, ty)) {
            if(reachable(tx, ty)) {
                println("YES");
                return;
            }
            tx -= x;
            ty += y;
        }
        

        tx = x1; ty = y1;
        while(isValid(tx, ty)) {
            if(reachable(tx, ty)) {
                println("YES");
                return;
            }
            tx -= x;
            ty -= y;
        }
        
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