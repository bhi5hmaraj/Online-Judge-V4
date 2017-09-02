import java.util.*;
import java.io.*;
public class LA_5111 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int digits[];
    static int total;
    static Boolean memo[][][];
    static boolean rec(int idx , int k , int sum) {
        //System.out.println("idx " + idx + " k " + k + " sum " + sum);
        if(k < 0)
            return false;
        if(idx < 0)
            return k == 0 && sum == 0; 
        else if(memo[idx][k][sum] != null)
            return memo[idx][k][sum];
        else 
            return memo[idx][k][sum] = rec(idx - 1, k - 1, (sum - digits[idx] + 11) % 11) | rec(idx - 1, k, sum);
    }
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            
            int d[] = nextIntArray(9);
            int len = 0;
            total = 0;
            for(int a : d) len += a;
            digits = new int[len];
            int ptr = 0;
            for(int i = 0; i < d.length; i++) {
                total += (i + 1) * d[i];
                while(d[i]-->0)
                    digits[ptr++] = i + 1;
            }
            
            memo = new Boolean[digits.length][digits.length + 1][11];
            int min = Integer.MAX_VALUE;
            for(int i = 1; i <= digits.length; i++)
                if(rec(digits.length - 1, i, (total * 6) % 11)) {
                    int need = Math.max(0 , Math.max(i , len - i) - Math.min(i , len - i) - 1);
                    min = Math.min(min , need + len);
                }
            
            println(min == Integer.MAX_VALUE ? -1 : min);
        }
        
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
/*    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        reader.close();
        writer.close();
    }
    */
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new LA_5111().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        try {
            reader.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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