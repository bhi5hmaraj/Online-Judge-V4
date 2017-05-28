import java.util.*;
import java.io.*;
class CONSESNK_SLOW {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N; 
    static long L;
    static long arr[];
    static long A , maxX , minCost;
    
    static long f(long X) {
        long cost = 0;
        for(int i = 0; i < N; i++)
            cost += Math.abs(arr[order[i]] - (X + (L * i)) );
        return cost;
    }
    static int order[];
    static boolean used[];
    static void findOpt(int idx) {
        if(idx == N) {
            for(long X = A; X <= maxX; X++)
                minCost = Math.min(minCost , f(X));
        }
        else {
            for(int i = 0; i < N; i++) {
                if(!used[i]) {
                    used[i] = true;
                    order[idx] = i;
                    findOpt(idx + 1);
                    used[i] = false;
                }
            }
        }
    }
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            N = nextInt();
            L = nextLong();
            A = nextLong();
            long B = nextLong();
            
            arr = nextLongArray(N);
            Arrays.sort(arr);
            maxX = B - (L * N) + 1;
            order = new int[N];
            used = new boolean[N];
            for(int i = 0; i < N; i++)
                order[i] = i;
            minCost = Long.MAX_VALUE;
            findOpt(0);
            println(minCost);
            
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