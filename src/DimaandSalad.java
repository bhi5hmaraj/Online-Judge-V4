import java.util.*;
import java.io.*;
public class DimaandSalad  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int k;
    static int a[] , b[];
    static final int INF = (int) 1e8;
    static final int OFF = (int) 1e5;
    static int memo[][];
    static int findMax(int idx , int sum) {
        if(idx < 0)
            return sum == OFF ? 0 : -INF;
        else if(memo[idx][sum] != -1)
            return memo[idx][sum];
        else {
            int max = -INF;
            max = Math.max(findMax(idx - 1, sum) , a[idx] + findMax(idx - 1, sum + a[idx] - k * b[idx]));
            max = max < 0 ? -INF : max;
            return memo[idx][sum] = max;
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        k = nextInt();
        a = nextIntArray(n);
        b = nextIntArray(n);
        
        memo = new int[n][2 * OFF];
        for(int t[] : memo) Arrays.fill(t, -1);
        
        int max = -1;
        for(int i = n - 1; i >= 0; i--) 
            max = Math.max(max , a[i] + findMax(i - 1, OFF + a[i] - k * b[i]));
        
        println(max);
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