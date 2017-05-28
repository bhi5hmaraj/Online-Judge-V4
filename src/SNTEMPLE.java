import java.util.*;
import java.io.*;
class SNTEMPLE {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            int arr[] = nextIntArray(N);
            int left[] = new int[N];
            int right[] = new int[N];
            long total = 0;
            for(int a : arr) total += a;
            Arrays.fill(left, 1);
            Arrays.fill(right, 1);
            
            {
                int last = 0;
                for(int i = 1; i <= N && arr[i - 1] >= i; i++) {
                    last++;
                }
                left[last - 1] = last;
                for(int i = 1; i < N; i++) {
                    int ptr = i + last - 1;
                    last--;
                    while(ptr < N && arr[ptr] >= last + 1) {
                        ptr++;
                        last++;
                    }
                    left[i + last - 1] = Math.max(left[i + last - 1] , last);
                }
            }
            
            
            {
                int last = 0;
                for(int i = 1; i <= N && arr[N - i] >= i; i++) {
                    last++;
                }
                right[N - last] = last;
                for(int i = N - 2; i >= 0; i--) {
                    int ptr = i - last + 1;
                    last--;
                    while(ptr >= 0 && arr[ptr] >= last + 1) {
                        ptr--;
                        last++;
                    }
                    right[i - last + 1] = Math.max(right[i - last + 1] , last);
                }
            }
            
            int prev = left[N - 1];
            for(int i = N - 2; i >= 0; i--) {
                prev--;
                left[i] = Math.max(left[i] , prev);
                prev = Math.max(prev , left[i]);
            }
            
            prev = right[0];
            for(int i = 1; i < N; i++) {
                prev--;
                right[i] = Math.max(right[i] , prev);
                prev = Math.max(prev , right[i]);
            }
            
            /*
            System.out.println("left  "  + Arrays.toString(left));
            System.out.println("right " + Arrays.toString(right));
            */
            
            int maxTop = 0;
            for(int i = 0; i < N; i++)
                maxTop = Math.max(maxTop , Math.min(left[i] , right[i]));
            
            println(total - 1L * maxTop * maxTop);
            
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