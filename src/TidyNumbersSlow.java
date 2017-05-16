import java.util.*;
import java.io.*;
public class TidyNumbersSlow {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean isTidy(long N) {
        long prev = 10;
        while(N > 0) {
            if(N % 10 > prev)
                return false;
            prev = N % 10;
            N /= 10;
        }
        return true;
    }
    
    private static void solve() {

        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            long N = nextLong();
            while(!isTidy(N))
                N--;
            println("Case #" + tc + ": " + N);
        }
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    /*
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    */
    static String outputFile = "output_tidy_correct.txt";
    public static void main(String []args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        writer = 
                new PrintWriter(outputFile); 
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