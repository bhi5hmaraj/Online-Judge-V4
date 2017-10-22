import java.util.*;
import java.io.*;
public class HugeNumbers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int modPow(long a , long b , long mod) {
        long pow = 1;
        while(b > 0) {
            if((b & 1L) == 1)
                pow = ((pow * a) % mod);

            a = ((a * a) % (mod));
            b >>= 1;
        }
        return (int) pow;
    }
    
    private static void solve() {
        
        
        int T = nextInt();
        for(int tc = 1; tc <= T; tc++) {
            int A = nextInt();
            int N = nextInt();
            int P = nextInt();
            while(N-->0) 
                A = modPow(A, N + 1, P);
            println("Case #" + tc + ": " + A);
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