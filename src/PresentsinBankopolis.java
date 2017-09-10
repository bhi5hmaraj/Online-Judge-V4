import java.util.*;
import java.io.*;
public class PresentsinBankopolis {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<int[]>[] adj;
    static final int INF = (int) 1e7;
    static int memo[][][][];
    // dir - 0 (we are at left)
    static int rec(int L , int R , int k , int dir) {
        int mem = memo[L][R][k][dir];
        if(k > R - L - 1)
            return INF;
        else if(k <= 0)
            return 0;
        else if(mem != -1)
            return mem;
        else {
            int min = INF;
            for(int edge[] : adj[dir == 0 ? L : R])
                if(edge[0] > L && edge[0] < R)
                    min = Math.min(min , edge[1] + rec(dir == 0 ? L : edge[0], 
                                                       dir == 0 ? edge[0] : R, k - 1, 1 - dir));
            return memo[L][R][k][dir] = min;
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        int k = nextInt();
        int m = nextInt();
        
        if(k == 1) {
            println(0);
            return;
        }
        
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();
        
        while(m-->0) 
            adj[nextInt()].add(new int[]{nextInt() , nextInt()});
        
        memo = new int[n + 1][n + 1][k + 1][2];
        for(int a[][][] : memo)
            for(int b[][] : a)
                for(int c[] : b)
                    Arrays.fill(c, -1);
        
        int minCost = INF;
        for(int i = 1; i <= n; i++)
            for(int edge[] : adj[i]) {
                if(i < edge[0]) 
                    minCost = Math.min(minCost , edge[1] + rec(i, edge[0], k - 2, 1));
                else if(i > edge[0])
                    minCost = Math.min(minCost , edge[1] + rec(edge[0], i, k - 2, 0));
            }
        
        println(minCost == INF ? -1 : minCost);
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