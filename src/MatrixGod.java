import java.util.*;
import java.io.*;
public class MatrixGod {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int n = nextInt();
        int A[][] = new int[n][];
        int B[][] = new int[n][];
        int C[][] = new int[n][];
        for(int i = 0; i < n; i++)
            A[i] = nextIntArray(n);
        for(int i = 0; i < n; i++)
            B[i] = nextIntArray(n);
        for(int i = 0; i < n; i++)
            C[i] = nextIntArray(n);
        
        int mod = (int) 1e9 + 7;
        
        boolean marked[][] = new boolean[n][n];
        int runs = (int) 5e4;
        Random rand = new Random();
        boolean correct = true;
        int visited = n * n;
        while(runs-->0 && visited > 0) {
            int r = rand.nextInt(n);
            int c = rand.nextInt(n);
            while(marked[r][c]) {
                r = rand.nextInt(n);
                c = rand.nextInt(n);
            }
            marked[r][c] = true;
            visited--;
            long sum = 0;
            for(int i = 0; i < n; i++) {
                long temp = (sum + 1L * A[r][i] * B[i][c]);
                if(temp >= mod)
                    temp %= mod;
                sum = temp;
            }
            
            correct &= C[r][c] == sum;
        }
        
        println(correct ? "YES" : "NO");
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