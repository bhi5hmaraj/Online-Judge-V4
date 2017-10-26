import java.util.*;
import java.io.*;
public class TheBakery {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int arr[];
    static int dp[][];
    static int n;
    
    static int cost(int i , int j) {
        return (int) Arrays.stream(arr , i , j + 1).distinct().count();
    }
    
    static void divideAndConquer(int lev , int L , int R , int optL , int optR) {
        if(L <= R) {
            int mid = (L + R ) >> 1;
            MyBitSet set = new MyBitSet(n + 1);
            int opt = -1;
            for(int m = Math.min(optR , mid) ; m >= optL; m--) {
                set.set(arr[m]);
                int relax = set.cardinality() + dp[lev - 1][m - 1];
                if(relax > dp[lev][mid]) {
                    dp[lev][mid] = relax;
                    opt = m;
                }
            }
        }
    }
    
    static class MyBitSet {
        long bits[];
        int cardinality;
        int size;
        MyBitSet(int MAX) {
            size = MAX;
            bits = new long[((MAX - 1) / 64) + 1];
            cardinality = 0;
        }

        void set(int n, boolean f) {
            int index = n / 64;
            if (f) {
                if((bits[index] & (1L << (n % 64))) == 0)
                    cardinality++;
                bits[index] |= (1L << (n % 64));
            }
            else
                bits[index] ^= (bits[index] & (1L << (n % 64))) != 0 ? (1L << (n % 64)) : 0;
        }

        void set(int n) {
            set(n, true);
        }
        
        int cardinality() {
            return cardinality;
        }
                
        boolean get(int n) {
            return ((bits[n / 64]) & (1L << (n % 64))) != 0;
        }
        
    }

    
    private static void solve() {
        
        
        n = nextInt();
        int k = nextInt();
        arr = nextIntArray(n);
        dp = new int[k][n];
        for(int i = 0; i < n; i++)
            dp[0][i] = cost(0, i);
        for(int i = 1; i < k; i++) 
            for(int j = i; j < n; j++) 
                for(int m = i; m <= j; m++)
                    dp[i][j] = Math.max(dp[i][j] , cost(m , j) + dp[i - 1][m - 1]);
            
        
        println(dp[k - 1][n - 1]);
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