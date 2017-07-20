import java.util.*;
import java.util.function.BiFunction;
import java.io.*;
public class KalilaandDimnaintheLoggingIndustry {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * Convex Hull Trick
     * http://pclub.in/tutorial/algos/2016/08/22/dpconvex.html
     * wcipeg.com/wiki/Convex_hull_trick
     * 
     */
    private static void solve() {
        
        
        int n = nextInt();
        long A[] = nextLongArray(n);
        long B[] = nextLongArray(n);

        long DP[] = new long[n];
        ArrayList<Double> soln = new ArrayList<>();
        ArrayList<Integer> line = new ArrayList<>();
        BiFunction<Integer , Integer , Double> x = (i , j) -> ((double) DP[j] - DP[i]) / ((double) B[i] - B[j]);
        line.add(0);
        
        for(int i = 1; i < n; i++) {
            int lo = 0, hi = soln.size() - 1;
            int floor = -1;
            
            while(lo <= hi) {
                int mid = (lo + hi) >> 1;
                if(A[i] >= soln.get(mid)) {
                    floor = mid;
                    lo = mid + 1;
                }
                else
                    hi = mid - 1;
            }
            
            int p = line.get(floor + 1);
            DP[i] = DP[p] + B[p] * A[i];
            
            /*
             * Each entry is added one and removed once so amortized O(N) overall
             */
            while(line.size() >= 2 && x.apply(i, line.get(line.size() - 2)) < soln.get(soln.size() - 1)) {
                soln.remove(soln.size() - 1);
                line.remove(line.size() - 1);
            }
            
            soln.add(x.apply(soln.isEmpty() ? 0 : line.get(line.size() - 1), i));
            line.add(i);
        }
        
        println(DP[n - 1]); // O(N log N) overall
        
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