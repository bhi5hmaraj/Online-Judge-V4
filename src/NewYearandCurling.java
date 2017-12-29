import java.util.*;
import java.io.*;
public class NewYearandCurling {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final double EPS = 1e-8;
    static int compare(double a , double b) {
        return a <= b - EPS ? -1 : a >= b + EPS ? 1 : 0;
    }
    
    
    private static void solve() {
        
        
        int n = nextInt();
        long r = nextLong();
        int x[] = nextIntArray(n);
        
        double y[] = new double[n];
        y[0] = r;

        for(int i = 1; i < n; i++) {
            int touchPt = -1;
            double y1 = 0;
            for(int j = 0; j < i; j++)
                if(!(x[j] + r < x[i] - r || x[j] - r > x[i] + r) && compare(y[j], y1) > 0) {
                    y1 = y[j];
                    touchPt = j;
                }
            
            if(touchPt < 0)
                y[i] = r;
            else
                y[i] = y1 + Math.sqrt(4.0 * r * r - Math.pow(x[i] - x[touchPt], 2));
                    
        }
            
        
        Arrays.stream(y).forEach(pt -> print(String.format("%.10f ", pt)));
        
        
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