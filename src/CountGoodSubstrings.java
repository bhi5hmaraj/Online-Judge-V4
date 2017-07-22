import java.util.*;
import java.io.*;
public class CountGoodSubstrings {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        int str[] = nextLine().chars().map(ch -> ch - 'a').toArray(); // a - 0 , b - 1
        long good[] = new long[2];   // even - 0 , odd - 1
        long cnt[][] = new long[2][2];  // (a , b) x (even , odd)
        long curr[] = new long[2];  // running counter
        int streak = 1;
        good[1] = 1;
        curr[0] = 1;
        
        for(int i = 1; i < str.length; i++) {
            if(str[i] != str[i - 1]) {
                streak = 0;
                cnt[str[i - 1]][0] += curr[0];
                cnt[str[i - 1]][1] += curr[1];
                curr[0] = curr[1] = 0;
            }
            streak++;
            curr[i % 2]++;
            good[0] += streak / 2;
            good[1] += streak - (streak / 2);
            good[0] += cnt[str[i]][1 - (i % 2)];
            good[1] += cnt[str[i]][i % 2];
        }
        
        println(good[0] + " " + good[1]);
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