import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class HighLoad {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int k = nextInt();
        
        int edges[][] = new int[n - 1][2];
        int sz = 0;
        
        if(k >= n - k) {    // leaves are greater 
            // Create a star graph of n - k nodes
            for(int i = 2; i <= n - k; i++ , sz++) {
                edges[sz][0] = 1;
                edges[sz][1] = i;
            }
            // Make the current leaves as internal 
            for(int i = n - k + 1; i <= 2 * (n - k) - 1; i++ ,sz++) {
                edges[sz][0] = i - (n - k - 1);
                edges[sz][1] = i;
            }
            // Add remaining leaves to the center
            for(int i = 2 * (n - k); i <= n; i++ , sz++) {
                edges[sz][0] = i;
                edges[sz][1] = 1;
            }
            
            println(Math.min(n - k + 1 , 4));
        }
        else {            // internal nodes are greater 
            int last[] = new int[k];
            IntStream.range(1, k + 1).forEach(i -> last[i - 1] = i);
            for(int i = k + 1; i <= n - 1; i++ , sz++) {
                edges[sz][0] = i;
                edges[sz][1] = last[(i - 1) % k];
                last[(i - 1) % k] = i;
            }
            for(int i : last) {
                edges[sz][0] = n;
                edges[sz][1] = i;
                sz++;
            }
            int internal = n - k - 1;
            println(2 * (internal / k) + Math.min(internal % k, 2) + 2);
        }
        
        Arrays.stream(edges).forEach(e -> println(e[0] + " " + e[1]));
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