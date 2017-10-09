import java.util.*;
import java.io.*;
public class ApproximatePaperPlanes {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long distSq(long pt1[] , long pt2[]) {
        long dsq = 0;
        for(int i = 0; i < 3; i++)
            dsq += (pt1[i] - pt2[i]) * (pt1[i] - pt2[i]);
        
        return dsq;
    }
    
    private static void solve() {
        
        
        int t = nextInt();
        
        long p1[] = nextLongArray(3);
        long v1[] = nextLongArray(3);
        long p2[] = nextLongArray(3);
        long v2[] = nextLongArray(3);
        
        long minDistSq = Long.MAX_VALUE;
        while(t-->=0) {
            minDistSq = Math.min(minDistSq , distSq(p1, p2));
            for(int i = 0; i < 3; i++) {
                p1[i] += v1[i];
                p2[i] += v2[i];
            }
        }
        
        println(String.format("%.9f", Math.sqrt(minDistSq)));
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