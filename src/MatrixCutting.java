import java.util.*;
import java.io.*;
public class MatrixCutting {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int arr[];
    static int memo[][];
    static int findOpt(int l , int r) {
        if(memo[l][r] != -1)
            return memo[l][r];
        else if(l == r)
            return 0;
        else {
            int min = Arrays.stream(arr, l, r + 1).min().getAsInt();
            int max = 0;
            for(int i = l + 1; i <= r; i++)
                max = Math.max(max , min + findOpt(l, i - 1) + findOpt(i, r));
            return memo[l][r] = max;
        }
    }
    
    private static void solve() {
        
        
        int T = nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int N = nextInt();
            int M = nextInt();
            arr = nextIntArray(M);
            memo = new int[M][M];
            for(int t[] : memo)
                Arrays.fill(t, -1);
            println("Case #" + tc + ": " + findOpt(0, M - 1));
        }
        
        
        
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
//        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        writer = new PrintWriter("out.txt");
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