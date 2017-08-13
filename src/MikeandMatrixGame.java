import java.util.*;
import java.io.*;
public class MikeandMatrixGame {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX = 1234;
    static double logFact[];
    static {
        logFact = new double[MAX];
        for(int i = 2; i < MAX; i++)
            logFact[i] = Math.log(i) + logFact[i - 1];
    }
    
    private static void solve() {
        
        
        int N = nextInt();
        int K = nextInt();
        int X = nextInt();
        
        int arr[][] = new int[N][];
        int trans[][] = new int[N][N];
        
        for(int i = 0; i < N; i++)
            arr[i] = nextIntArray(N);
        
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                trans[j][i] = arr[i][j];
        
        for(int i = 0; i < N; i++)
            Arrays.sort(trans[i]);
        
        double totalExpectation = 0;
        for(int i = 0; i < N; i++) {
            double colExpectation = 0;
            for(int j = 1; j <= N - K + 1; j++) {
                double logProb = Math.log(K) + logFact[N - K] + logFact[N - j] - logFact[N - K - (j - 1)] - logFact[N];
                double prob = Math.exp(logProb);
                colExpectation += prob * (trans[i][N - j] + X); 
            }
            totalExpectation += colExpectation;
        }
        
        println(String.format("%.10f", totalExpectation));
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