import java.util.*;
import java.io.*;
public class RoundSubset {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    /*
     3 2
     200 6250 16
     */
    
    static int pair[][];    // 0 - 2^ , 1 - 5^
    static final int INF = (int) 1e7;
    static int memo[][][];
    static int[] rec(int idx , int remain) {
        if(remain == 0)
            return new int[]{0 , 0};
        else if(idx < 0)
            return new int[]{-INF , -INF};
        else if(memo[idx][remain] != null)
            return memo[idx][remain];
        else {
            int op1[] = Arrays.copyOf(rec(idx - 1, remain) , 2);
            int op2[] = Arrays.copyOf(rec(idx - 1, remain - 1), 2);
            op2[0] += pair[idx][0];
            op2[1] += pair[idx][1];
            int m1 = Math.min(op1[0] , op1[1]);
            int m2 = Math.min(op2[0] , op2[1]);
            m1 = m1 < 0 ? -INF : m1;
            m2 = m2 < 0 ? -INF : m2;
            return memo[idx][remain] = m1 > m2 ? op1 : op2;
        }
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int k = nextInt();
        long arr[] = nextLongArray(n);
        pair = new int[n][2];
        for(int i = 0; i < n; i++) {
            while(arr[i] % 2 == 0) {
                pair[i][0]++;
                arr[i] /= 2;
            }
            while(arr[i] % 5 == 0) {
                pair[i][1]++;
                arr[i] /= 5;
            }
        }
        
        memo = new int[n][k + 1][];
        int ans[] = rec(n - 1, k);
        println(Math.min(ans[0] , ans[1]));
        
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