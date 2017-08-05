import java.util.*;
import java.io.*;
public class GargariandPermutations {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int n = nextInt();
        int k = nextInt();
        int arr[][] = new int[k][n];
        for(int i = 0; i < k; i++)
            arr[i] = Arrays.stream(nextIntArray(n)).map(a -> a - 1).toArray();
        
        int inv[][] = new int[k][n];
        for(int i = 0; i < k; i++)
            for(int j = 0; j < n; j++)
                inv[i][arr[i][j]] = j;
        
        adj = new ArrayList[n];
        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                boolean flag = true;
                for(int x = 1; x < k; x++)
                    flag &= inv[x][arr[x][j]] > inv[x][arr[x][i]];
                if(flag)
                    adj[j].add(i);
            }
        }
        
        int DP[] = new int[n];
        DP[0] = 1;
        for(int i = 1; i < n; i++) {
            for(int v : adj[i])
                DP[i] = Math.max(DP[i] , DP[v]);
            DP[i]++;
        }
        
        println(Arrays.stream(DP).max().getAsInt());
            
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