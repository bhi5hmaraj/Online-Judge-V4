import java.util.*;
import java.io.*;
class SNAKEEAT {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int lowerBound(long arr[] , long key) {
        int lo = 0 , hi = arr.length - 1;
        int lb = -1;
        while(lo <= hi) {
            int mid = (lo + hi) >> 1;
            if(arr[mid] < key) {
                lb = mid;
                lo = mid + 1;
            }
            else
                hi = mid - 1;
        }
        
        return lb;
    }
    
    static boolean DEBUG = false;
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            int Q = nextInt();
            long L[] = nextLongArray(N);
            Arrays.sort(L);

            long prefixSum[] = new long[N + 1];
            for(int i = 1; i <= N; i++)
                prefixSum[i] = prefixSum[i - 1] + L[i - 1];
            
            while(Q-->0) {
                long K = nextLong();
                int lb = lowerBound(L, K);
                if(DEBUG) System.out.println("lb = " + lb);
                if(lb < 0)
                    println(N);
                else {
                    int last = lb + 1;
                    int lo = 0 , hi = lb;
                    while(lo <= hi) {
                        int mid = (lo + hi) >> 1;
                        long need = K - L[mid];
                        long canTake = mid - ((K * (lb - mid)) - (prefixSum[lb + 1] - prefixSum[mid + 1]));
                        if(DEBUG) System.out.printf("i = %d need[i] = %d canTake[i] = %d\n", mid , need , canTake);
                        if(canTake >= need) {
                            last = mid;
                            hi = mid - 1;
                        }
                        else 
                            lo = mid + 1;
                    }
                    if(DEBUG) System.out.println("last = " + last);
                    println((lb - last + 1) + N - 1 - lb);  // Can also take elements after lb
                }
            }
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