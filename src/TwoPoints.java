import java.util.*;
import java.io.*;
public class TwoPoints {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static double x1 , x2 , y1 , y2 , vx1 , vx2 ,vy1 , vy2;
    
    static double distAtTime(double t) {
        double vec1[] = new double[]{x1 + vx1 * t , y1 + vy1 * t};
        double vec2[] = new double[]{x2 + vx2 * t , y2 + vy2 * t};
        
        return Math.sqrt(Math.pow(vec1[0] - vec2[0], 2) + Math.pow(vec1[1] - vec2[1], 2));
    }
    
    private static void solve() {
        
        x1 = nextDouble();
        y1 = nextDouble();
        x2 = nextDouble();
        y2 = nextDouble();
        
        vx1 = nextDouble();
        vy1 = nextDouble();
        vx2 = nextDouble();
        vy2 = nextDouble();
        
        if(vx1 == vx2 && vy1 == vy2)
            println(distAtTime(0));
        else {
            double nume = -((x2 - x1) * (vx2 - vx1) + (y2 - y1) * (vy2 - vy1));
            double deno = Math.pow(vx2 - vx1, 2) + Math.pow(vy2 - vy1, 2);
            double t = nume / deno;
            if(t > 0)
                println(distAtTime(nume / deno));
            else
                println(distAtTime(0));
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