import java.util.*;
import java.io.*;
public class Palindromiccharacteristics  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    static final long p = 100151;
    static final int MAX = (int) 5e3 + 10;
    static final long pow[] = new long[MAX + 1];
    // implicit mod (2^63)
    static {
        pow[0] = 1;
        for(int i = 1; i <= MAX; i++) 
            pow[i] = pow[i - 1] * p;
    }
    
    static long hash[];
    
    static long subHash(int l , int r) {
        l++;
        r++;
        return hash[r] - hash[l - 1] * pow[r - l + 1];
    }
    

    private static void solve() {
        
        char str[] = nextLine().toCharArray();
//         long st = System.nanoTime();
        int n = str.length;
        int log = 32 - Integer.numberOfLeadingZeros(n);
        
        boolean DP[][] = new boolean[n][n];
        boolean isPalin[][] = new boolean[n][n];
        
        for(int i = 0; i < n; i++)
            isPalin[i][i] = true;
        for(int i = 0; i < n - 1; i++)
            isPalin[i][i + 1] = str[i] == str[i + 1];
        for(int len = 3; len <= n; len++)
            for(int i = 0; i + len - 1 < n; i++)
                isPalin[i][i + len - 1] = isPalin[i + 1][i + len -2] && str[i] == str[i + len - 1];
        
        hash = new long[n + 1];
        hash[1] = str[0];
        for(int i = 2; i <= n; i++) 
            hash[i] = hash[i - 1] * p + str[i - 1];
        
        int cnt[] = new int[n];
        for(int len = 1; len <= n; len++)
            for(int i = 0; i + len - 1 < n; i++) {
                cnt[0] += isPalin[i][i + len - 1] ? 1 : 0;
                DP[i][i + len - 1] = isPalin[i][i + len - 1];
            }
        
        for(int k = 1; k < log; k++) {
            boolean t[][] = new boolean[n][n];
            for(int len = 2; len <= n; len++)
                for(int i = 0; i + len - 1 < n; i++) {
                    int L = i;
                    int R = i + len - 1;
                    long lH = subHash(L, L + (len / 2) - 1 );
                    long rH = subHash(R - (len / 2) + 1, R );
                    if(lH == rH && DP[L][L + (len / 2) - 1] && DP[R - (len / 2) + 1][R]) {
                        t[L][R] = true;
                        cnt[k]++;
                    }
                }
                    
            DP = t;
        }
        
        for(int a : cnt)
            print(a + " ");

//        println("\nTime : " + (System.nanoTime() - st) / 1e9);
         

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