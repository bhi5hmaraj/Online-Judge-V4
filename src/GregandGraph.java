import java.util.*;
import java.io.*;
public class GregandGraph {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int cost[][] = new int[n][];
        for(int i = 0; i < n; i++)
            cost[i] = nextIntArray(n);
        
        long sum = 0;
        int ord[] = Arrays.stream(nextIntArray(n)).map(Math::decrementExact).toArray();
        
        int DP[][] = new int[n][n];
        for(int a[] : DP) Arrays.fill(a, Integer.MAX_VALUE);
        
        ArrayDeque<Long> stack = new ArrayDeque<>();
        
        for(int i = n - 1; i >= 0; i--) {
            // i is new vertex
            DP[ord[i]][ord[i]] = 0;
            for(int j = i + 1; j < n; j++) {
                for(int k = i + 1; k < n; k++) {
                    DP[ord[j]][ord[i]] = Math.min(DP[ord[j]][ord[i]] , DP[ord[j]][ord[k]] + cost[ord[k]][ord[i]]);
                    DP[ord[i]][ord[j]] = Math.min(DP[ord[i]][ord[j]] , cost[ord[i]][ord[k]] + DP[ord[k]][ord[j]]);
                }
                sum += DP[ord[j]][ord[i]];
                sum += DP[ord[i]][ord[j]];
            }
            /*
            for(int a[] : DP)
                println(Arrays.toString(a));
            */
            
            for(int j = i + 1; j < n; j++) {
                for(int k = i + 1; k < n; k++) 
                    if(k != j && DP[ord[j]][ord[k]] > DP[ord[j]][ord[i]] + DP[ord[i]][ord[k]]) {
                        sum -= DP[ord[j]][ord[k]];
                        sum += DP[ord[j]][ord[i]] + DP[ord[i]][ord[k]];
                        DP[ord[j]][ord[k]] = DP[ord[j]][ord[i]] + DP[ord[i]][ord[k]];
                    }
            }
            
            /*
            for(int a[] : DP)
                println(Arrays.toString(a));
            
            println("=========================");
            */
            
            stack.push(sum);
        }
        
        stack.stream().forEach(a -> print(a + " "));
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