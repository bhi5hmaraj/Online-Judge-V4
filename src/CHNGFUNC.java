import java.util.*;
import java.io.*;
class CHNGFUNC  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int A = nextInt();
        int B = nextInt();
        ArrayList<Long> sq = new ArrayList<>();
        for(long i = 1; i <= A; i++)
            sq.add(i * i);
        while(1L * (sq.size() + 1) * (sq.size() + 1) <= sq.get(A - 1) + B)
            sq.add(1L * (sq.size() + 1) * (sq.size() + 1));
        
        long cnt = 0;
        for(int i = 0; i < A; i++) {
            int lo = i + 1;
            int hi = sq.size() - 1;
            int floor = i;
            long key = sq.get(i) + B;
            while(lo <= hi) {
                int mid = (lo + hi) >> 1;
                if(sq.get(mid) <= key) {
                    lo = mid + 1;
                    floor = mid;
                }
                else
                    hi = mid - 1;
            }
            
            cnt += floor - i;
        }
        
        println(cnt);
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