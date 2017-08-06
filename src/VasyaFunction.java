import java.util.*;
import java.io.*;
public class VasyaFunction {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        long x = nextLong();
        long y = nextLong();
        
        ArrayList<Long> div = new ArrayList<>();
        for(long i = 1; i * i <= x; i++) {
            if(x % i == 0) {
                div.add(i);
                if(i * i != x)
                    div.add(x / i);
            }
        }
        
        Collections.sort(div);
        long gcd = 0;
        long ans = 0;
        int ptr;
        for(ptr = div.size() - 1; ptr >= 0; ptr--)
            if(y % div.get(ptr) == 0) {
                gcd = div.get(ptr);
                break;
            }
        
        while(y > 0) {
            long minRemainder = y;
            int pos = div.size() - 1;
            for(int i = ptr + 1; i < div.size(); i++) {
                long rem = y % div.get(i);
                if(rem % gcd == 0 && rem <= minRemainder) {
                    minRemainder = rem;
                    pos = i;
                }
            }
            
            y -= minRemainder;
            ptr = pos;
            ans += minRemainder / gcd;
            gcd = div.get(ptr);
        }
        
        println(ans);
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