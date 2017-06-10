import java.util.*;
import java.io.*;
class CLONEME {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int p1 = 100151 , m1 = (int) 1e9 + 7;
    static final int p2 = p1 + 2 , m2 = m1 + 2;
    static final int MAX = (int) 1e5;
    static final int pow1[] = new int[MAX + 1];
    static final int pow2[] = new int[MAX + 1];
    
    static {
        pow1[0] = pow2[0] = 1;
        for(int i = 1; i <= MAX; i++) {
            pow1[i] = (int) ((1L * pow1[i - 1] * p1) % (long)(m1));
            pow2[i] = (int) ((1L * pow2[i - 1] * p2) % (long)(m2));
        }
    }
    
    static class SegTreeNode {
        int freq , hash1 , hash2;
        SegTreeNode left , right;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            
            
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