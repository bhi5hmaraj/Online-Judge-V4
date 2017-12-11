import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class uva_10990  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX = (int) 2e6;
    
    private static void solve() {
        
        int totient[] = IntStream.range(0, MAX + 1).toArray();
        for(int i = 2; i <= MAX; i++)
            if(totient[i] == i) {   // i is a prime
                for(int j = i; j <= MAX; j += i)
                    totient[j] = totient[j] - totient[j] / i;
            }
        
        int depthPhi[] = Arrays.stream(totient)
                         .map(phi -> {
                             if(phi <= 1) return 0;
                             int cnt = 1;
                             while(phi != 1) {
                                 phi = totient[phi];
                                 cnt++;
                             }
                             return cnt;
                         }).toArray();
        
        depthPhi[1] = 0;
        depthPhi[2] = 1;
        
        
        for(int i = 1; i <= MAX; i++)
            depthPhi[i] += depthPhi[i - 1];
        
        int Q = nextInt();
        while(Q-->0)
            println(-(depthPhi[nextInt() - 1] - depthPhi[nextInt()]));
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