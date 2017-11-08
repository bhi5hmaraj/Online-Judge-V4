import java.util.*;
import java.io.*;
public class Scarilyinteresting  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int A[][] = new int[n][2]; 
        int sumA = 0 , sumB = 0;
        for(int i = 0; i < n; i++) {
            A[i][0] = nextInt();
            A[i][1] = i + 1;
            sumA += A[i][0];
        }
        int B[][] = new int[n][2]; 
        for(int i = 0; i < n; i++) {
            B[i][0] = nextInt();
            B[i][1] = i + 1;
            sumB += B[i][0];
        }
        
        int[][] win = A , lose = B;
        if(sumB > sumA) {
            int temp[][] = win;
            win = lose;
            lose = temp;
        }
        Arrays.sort(win , (p1 , p2) -> Integer.compare(p1[0], p2[0]));
        Arrays.sort(lose , (p1 , p2) -> Integer.compare(p2[0], p1[0]));
        
        for(int i = 0; i < n; i++) 
            println(A[i][1] + " " + B[i][1]);
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