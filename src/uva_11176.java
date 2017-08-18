import java.util.*;
import java.io.*;
public class uva_11176 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static double memo[];
    static double p;
    static double rec(int idx , int streak) {
        if(idx < 0)
            return 0;
        else if(idx == 0)
            return 1;
        else if(memo[idx] != -1)
            return memo[idx];
        else {
            double prob = 0;
            double curr = 1 - p;
            for(int i = 0; i <= streak; i++) {
                prob += curr * rec(idx - i - 1, streak);
                curr *= p;
            }
            if(idx <= streak)
                prob += Math.pow(p, idx);
            return memo[idx] = prob;
        }
    }
    
    
    private static void solve() {
        
        int n;
        // double t = 0;
        memo = new double[501];
        while((n = nextInt()) != 0) {
            p = nextDouble();
            // long st = System.nanoTime();
            double expect = 0;
            Arrays.fill(memo, -1);
            double prev = rec(n, 0);
            for(int i = 1; i <= n; i++) { 
                Arrays.fill(memo, -1);
                double curr = rec(n, i);
                expect += 1.0 * i * (curr - prev);
                prev = curr;
            }
            println(String.format("%.10f", expect));
            // t += (System.nanoTime() - st) / 1e9;
        }
        
        // println("Time : " + t);
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