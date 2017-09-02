import java.util.*;
import java.io.*;
public class LA_5112 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int mod = (int) 1e9 + 7;
    static int R;
    static int aux[][];
    static int base[][];
    static int unit[][];
    
    static void setUnit(int a[][]) {
        for(int i = 0 ; i < R; i++)
            a[i][i] = 1;
    }
    static void multiplyAndSet(int a[][] , int b[][]) {
        for(int i = 0; i < R; i++)
            for(int j = 0; j < R; j++) {
                aux[i][j] = 0;
                for(int k = 0; k < R; k++)
                    aux[i][j] = (aux[i][j] + (int) ((1L * a[i][k] * b[k][j]) % mod)) % mod;
            }
        for(int i = 0; i < R; i++)
            for(int j = 0; j < R; j++)
                a[i][j] = aux[i][j];
    }
    public static int[][] pow(int[][] mat , int b) {
        int ans[][] = new int[R][R];
        setUnit(ans);
        int m[][] = new int[R][];
        for(int i = 0; i < R; i++)
            m[i] = Arrays.copyOf(mat[i], R);
        
        while(b > 0) {
            if((b & 1) == 1)
                multiplyAndSet(ans, m);
            multiplyAndSet(m, m);
            b >>= 1;
        }
        return ans;
    }
    static int[][] add(int a[][] , int b[][]) {
        int c[][] = new int[R][R];
        for(int i = 0; i < R; i++)
            for(int j = 0; j < R; j++)
                c[i][j] = (a[i][j] + b[i][j]) % mod;
        
        return c;
    }
    
    static int[][] gpSum(int n) {
        if (n <= 1)
            return base;
        else if((n & 1) != 0) 
            return add(pow(base, n - 1), gpSum(n - 1));
        else {
            int temp[][] = add(unit, pow(base, n / 2));
            multiplyAndSet(temp, gpSum(n / 2));
            return temp;
        }
    }
    
    static void print(int a[][]) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < R; j++)
                print(String.format("%3d ", a[i][j]));
            print('\n');
        }
    }
    
    private static void solve() {
        
        int T = nextInt();
        
        while(T-->0) {
            int N = nextInt();
            R = nextInt();
            aux = new int[R][R];
            unit = new int[R][R];
            for(int i = 0; i < R; i++)
                unit[i][i] = 1;
            
            int K = nextInt();
            int seed[] = nextIntArray(R);
            base = new int[R][R];
            base[0] = nextIntArray(R);
            for(int i = 1; i < R; i++)
                base[i][i - 1] = 1;
            
            base = pow(base, K);
            int sum[][] = gpSum(N);
            int ans = 0;
            for(int i = 0; i < R; i++)
                ans = (ans + (int) ((1L * sum[0][i] * seed[R - i - 1]) % mod)) % mod;
            
            println(ans);
            for(int i = 1; i <= N; i++)
                print(pow(base, i));
        }
        
        
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