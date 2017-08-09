import java.util.*;
import java.io.*;
public class TennisTournament {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N = nextInt();
        int K = nextInt();
        int M = nextInt();
        
        if((1 << M) > K)
            println(-1);
        else if(K == (1 << N)) {
            if(M == N)
                for(int i = 1; i <= 1 << N; i++)
                    print(i + " ");
            else
                println(-1);
        }
        else {
            int ans[] = new int[1 << N];
            boolean marked[] = new boolean[(1 << N) + 1];
            for(int i = (1 << M) - 1 , j = K; i >= 0; i-- , j--) {
                ans[i] = j;
                marked[j] = true;
            }
            int ptr = 1 << M;
            for(int j = 1 << N; j > K; j-- , ptr++) {
                ans[ptr] = j;
                marked[j] = true;
            }
            for(int j = 1; j <= (1 << N); j++)
                if(!marked[j])
                    ans[ptr++] = j;
            
            Arrays.stream(ans).forEach(a -> print(a + " "));
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