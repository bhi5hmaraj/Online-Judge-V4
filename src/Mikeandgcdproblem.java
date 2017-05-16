import java.util.*;
import java.io.*;
public class Mikeandgcdproblem {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
    
    /*
     * The operation will change the 
     */
    private static void solve() {
        
        int N = nextInt();
        long arr[] = nextLongArray(N);
        long gcd = 0;
        for(long num : arr)
            gcd = gcd(gcd, num);
        
        if(gcd > 1) {
            println("YES");
            println(0);
            return;
        }
        
        int cnt = 0;
        int curr = 0;
        for(int i=0;i<N;i++) {
            if(arr[i] % 2 == 1)
                curr++;
            else {
                cnt += (curr / 2) + 2*(curr % 2);
                curr = 0;
            }
        }
        
        cnt += (curr / 2) + 2*(curr % 2);
        println("YES");
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