import java.util.*;
import java.io.*;
public class RaceAgainstTime {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int h = (nextInt() * 5) % 60;
        int m = nextInt();
        int s = nextInt();
        int t1 = (nextInt() * 5) % 60;
        int t2 = (nextInt() * 5) % 60;
        
//        List<Integer> times = Arrays.asList(t2 , h , m , s);
        List<Integer> times = Arrays.asList(h , m , s);
        int aclock = -1;
        int clock  = -1;
        /*
        for(int i = 1; i < 60; i++)
            if((aclock = times.indexOf((t1 - i + 60) % 60)) >= 0)
                break;
        for(int i = 1; i < 60; i++)
            if((clock = times.indexOf((t1 + i) % 60)) >= 0)
                break;
        */
        boolean b1 = false , b2 = false;
        for(int i = 1; i < 60 && (t1 - i + 60) % 60 != t2; i++)
            if(times.indexOf((t1 - i + 60) % 60) >= 0) {
                b1 = true;
                break;
            }
                
        for(int i = 1; i < 60 && (t1 + i) % 60 != t2; i++)
            if(times.indexOf((t1 + i) % 60 ) >= 0) {
                b2 = true;
                break;
            }
            
//        println(clock == 0 || aclock == 0 ? "YES" : "NO");
        println(b1 && b2 ? "NO" : "YES");
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