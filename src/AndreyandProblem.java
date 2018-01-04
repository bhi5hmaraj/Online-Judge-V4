import java.util.*;
import java.io.*;
public class AndreyandProblem  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static double rec(int idx , double f1 , double f2) {
        if(idx >= n)
            return f1;
        
        return Math.max(rec(idx + 1, f1, f2) , rec(idx + 1, f1 * (1 - prob[idx]) + f2 * prob[idx], f2 * (1 - prob[idx])));
        
    }
    static double prob[];
    static int n;
    
    private static void solve() {
        
        n = nextInt();
        prob = new double[n];
        for(int i = 0; i < n; i++)
            prob[i] = nextDouble();
        
        Arrays.sort(prob);
        double max = 0 , f1 = 0 , f2 = 1;
        boolean flag = false;
        double last = -1;
        for(int i = n - 1; i >= 0; i--) {
            double ff1 = f1 * (1 - prob[i]) + f2 * prob[i];
            double ff2 = f2 * (1 - prob[i]);
            f1 = ff1;
            f2 = ff2;
            max = Math.max(max , f1);
            if(flag && f1 > last)
                throw new RuntimeException();
            if(!flag && f1 < last)
                flag = true;
            
            last = f1;
        }
        
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