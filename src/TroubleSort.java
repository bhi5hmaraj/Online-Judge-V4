import java.util.*;
import java.io.*;
public class TroubleSort {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        for(int tc = 1, T = nextInt(); tc <= T; tc++) {
            int n = nextInt();
            int arr[] = nextIntArray(n);
            ArrayList<Integer> parity[] = new ArrayList[2];
            parity[0] = new ArrayList<>();
            parity[1] = new ArrayList<>();
            for(int i = 0; i < n; i++)
                parity[i % 2].add(arr[i]);
            
            Collections.sort(parity[0]);
            Collections.sort(parity[1]);
            
            for(int i = 0; i < n; i++)
                arr[i] = parity[i % 2].get(i / 2);
            
            int pos = 0;
            for(; pos < n - 1 && arr[pos] <= arr[pos + 1]; pos++)
                ;
            
            println("Case #" + tc + ": " + (pos == n - 1 ? "OK" : pos));
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