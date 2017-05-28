import java.util.*;
import java.io.*;
class SNTEMPLE_SLOW {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int T = nextInt();
        long start = System.nanoTime();
        while(T-->0) {
            
            int N = nextInt();
            long arr[] = nextLongArray(N);
            long total = 0;
            for(long l : arr) total += l;
            long minSteps = Long.MAX_VALUE;

            for(int i = 0; i < N; i++) {
                int top = Math.min(Math.min(i + 1 , N - i) , (int)arr[i]);
                for(; top >= 1; top--) {
                    boolean flag = true;
                    for(int j = 1; j < top; j++) {
                        flag &= arr[i - j] >= top - j && arr[i + j] >= top - j;
                        if(!flag) break;
                    }
                    
                    if(flag) break;
                }
                /*
                if(total - (top * top) < minSteps)
                    System.out.println("center = " + i + " top " + top);
                */
                minSteps = Math.min(minSteps , total - (top * top));
            }
            
            println(minSteps);
        }
        System.err.println("Time : " + ((System.nanoTime() - start) / 1e9));
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