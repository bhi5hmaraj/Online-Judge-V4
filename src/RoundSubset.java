import java.util.*;
import java.io.*;
public class RoundSubset {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int INF = (int) 1e6;
    private static void solve() {
        
        int n = nextInt();
        int k = nextInt();
        long arr[] = nextLongArray(n);
        int[][] pow = new int[n][2];
        int sum = 0;
        for(int i = 0; i < n; i++) {
            while(arr[i] % 2 == 0) {
                pow[i][0]++;
                arr[i] /= 2;
            }
            while(arr[i] % 5 == 0) {
                pow[i][1]++;
                sum++;
                arr[i] /= 5;
            }
        }
        
        int DP[][] = new int[k + 1][sum + 1];
        for(int a[] : DP)
            Arrays.fill(a, -INF);
        DP[0][0] = 0;
        
        for(int i = 0; i < n; i++) {    // index
            int t[][] = new int[k + 1][sum + 1];
            for(int a[] : t)
                Arrays.fill(a, -INF);
            t[0][0] = 0;
            
            for(int j = 1; j <= Math.min(k , i + 1); j++) { // remain
                for(int pow5 = 0; pow5 <= sum; pow5++) {   // sum of power of 5
                    t[j][pow5] = DP[j][pow5];
                    if(pow5 - pow[i][1] >= 0)
                        t[j][pow5] = Math.max(t[j][pow5] , pow[i][0] + DP[j - 1][pow5 - pow[i][1]]);
                    t[j][pow5] = t[j][pow5] < 0 ? -INF : t[j][pow5];
                }
            }
            DP = t;
        }
        
        int max = 0;
        for(int i = 0; i <= sum; i++)
            max = Math.max(max , Math.min(i , DP[k][i]));
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