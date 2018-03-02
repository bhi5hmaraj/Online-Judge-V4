import java.util.*;
import java.io.*;
class MINVOTE {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int getCeil(long sorted[] , int lo, int hi, long key) {
        int ceil = hi;
        while(lo <= hi) {
            int mid = (lo + hi) >> 1;
            if(key > sorted[mid])
                lo = mid + 1;
            else {
                ceil = mid;
                hi = mid - 1;
            }
        }
        
        return ceil;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            long arr[] = nextLongArrayOneBased(N);
            
            long prefix[] = new long[N + 1];
            long suffix[] = new long[N + 1];

            for(int i = 1; i <= N; i++) {
                prefix[i] = arr[i] + prefix[i - 1];
                suffix[i] = arr[N - i + 1] + suffix[i - 1];
            }
            
            int cnt[] = new int[N + 2];
            for(int i = 1; i <= N; i++) {
                int left = getCeil(prefix, 1, i, prefix[i] - 2L * arr[i]);
                int right = N - getCeil(suffix, 1, N - i + 1, suffix[N - i + 1] - 2L * arr[i]) + 1;
                // System.out.println("i " + i + " left " + left + " right " + right);
                cnt[left]++;
                cnt[i]--;
                cnt[i + 1]++;
                cnt[right + 1]--;
            }
            
            for(int i = 1; i <= N; i++) {
                cnt[i] += cnt[i - 1];
                print(cnt[i] + " ");
            }
            
            print('\n');
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