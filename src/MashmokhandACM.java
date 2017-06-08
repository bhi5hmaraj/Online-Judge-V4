import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class MashmokhandACM {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MOD = (int) 1e9 + 7;
    static int memo[][];
    static int n;
    
    static int rec(int idx , int remain) {
        if(remain == 0)
            return 1;
        else if(memo[idx][remain] != -1)
            return memo[idx][remain];
        else {
            int ways = 0;
            for(int mult = idx; mult <= n; mult += idx)
                ways = (ways + rec(mult, remain - 1)) % MOD;
            return memo[idx][remain] = ways;
        }
    }
    
    private static void solve() {
        
        n = nextInt();
        int k = nextInt();
        memo = new int[n + 1][k + 1];
        for(int temp[] : memo) Arrays.fill(temp, -1);
        
        int totalWays = 0;
        for(int i = 1; i <= n; i++)
            totalWays = (totalWays + rec(i, k - 1)) % MOD;
        
        println(totalWays);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        long st = System.nanoTime();
        solve();
        // System.out.println("Time : " + TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - st) + " ms");
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