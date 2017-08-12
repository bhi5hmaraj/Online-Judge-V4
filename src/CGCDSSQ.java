import java.util.*;
import java.io.*;
public class CGCDSSQ {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        HashMap<Integer , Long> freq = new HashMap<>();
        HashMap<Integer , Integer> gcdFreq = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            HashMap<Integer , Integer> temp = new HashMap<>();
            final int wtf = arr[i];
            gcdFreq.forEach((k , v) -> temp.put(gcd(wtf, k), temp.getOrDefault(gcd(wtf, k), 0) + v));
            temp.put(arr[i], temp.getOrDefault(arr[i], 0) + 1);
            temp.forEach((k , v) -> freq.put(k, freq.getOrDefault(k, 0L) + v));
            gcdFreq = temp;
            // println(temp);
        }
        
        int Q = nextInt();
        while(Q-->0)
            println(freq.getOrDefault(nextInt(), 0L));
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