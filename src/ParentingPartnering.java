import java.util.*;
import java.io.*;
public class ParentingPartnering {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int TIME = 1440;
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            int N = nextInt();
            int M = nextInt();
            int A[][] = new int[N][];
            int B[][] = new int[M][];
            for(int i=0;i<N;i++)
                A[i] = nextIntArray(2);
            for(int i=0;i<M;i++)
                B[i] = nextIntArray(2);
            int ans = -1;
            if((N | M) == 1)
                ans = 2;
            else {
                if(M == 2)
                    A = B;
                if(A[0][0] > A[1][0]) {
                    int temp[] = A[0];
                    A[0] = A[1];
                    A[1] = temp;
                }
                if(A[1][1] - A[0][0] <= 720 || TIME - (A[1][0] - A[0][1]) <= 720)
                    ans = 2;
                else
                    ans = 4;
            }
            println("Case #" + tc + ": " + ans);
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        //writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
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