import java.util.*;
import java.io.*;
public class PonyExpress {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long dist[];
    static long speed[];
    static long edges[];
    static long prefixSum[];
    static int N;
    static double INF = 1e18;
    
    static double memo[][];
    
    static double findOpt(int idx , int horse) {
        if(prefixSum[idx] - prefixSum[horse] > dist[horse])
            return INF;
        if(idx == N)
            return 0;
        else if(memo[idx][horse] != -1)
            return memo[idx][horse];
        else {
            
            double same = ((double)edges[idx] / (double)speed[horse]) + findOpt(idx + 1, horse);
            double change = ((double)edges[idx] / (double)speed[idx]) + findOpt(idx + 1, idx);
            
            return memo[idx][horse] = Math.min(same,change);
        }
    }
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            N = nextInt();
            int Q = nextInt();
            edges = new long[N + 1];
            speed = new long [N + 1];
            dist = new long[N + 1];
            prefixSum = new long[N + 1];
            memo = new double[N + 1][N + 1];
            for(double d[] : memo)
                Arrays.fill(d, -1);
            for(int i=1;i<=N;i++) {
                dist[i] = nextInt();
                speed[i] = nextInt();
            }
            for(int i=1;i<N;i++)
                edges[i] = nextIntArrayOneBased(N)[i + 1];
            nextLine();
            nextLine();
            for(int i = 2;i<=N;i++)
                prefixSum[i] = prefixSum[i - 1] + edges[i - 1];
            
            double minTime = ((double)edges[1] / (double)speed[1]) + findOpt(2, 1);
            println(String.format("Case #%d: %.8f", tc , minTime));
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
         //writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        writer = new PrintWriter("out.txt");
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