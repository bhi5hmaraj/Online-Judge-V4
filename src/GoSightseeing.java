import java.util.*;
import java.io.*;
public class GoSightseeing {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
 
    static class Pair {
        long start , freq , dur;
        Pair(long a , long b , long c) {
            start = a;
            freq = b;
            dur = c;
        }
    }
    
    static long next(int time , int idx) {
        long hop = (time - arr[idx].start + arr[idx].freq - 1) / arr[idx].freq;
        hop = Math.max(hop , 0);
        return (arr[idx].start + hop * arr[idx].freq) + arr[idx].dur; 
    }
    
    static long rec(int idx , int visit) {
        if(time > Tf)
            return -INF;
        else if(idx == N - 1)
            return 0;
        else if(memo[idx][time] != -1)
            return memo[idx][time];
        else 
            return memo[idx][time] = Math.max(rec(idx + 1, next(time, idx)) , 1 + rec(idx + 1, next(time + Ts, idx)));
    }
    
    static Pair arr[];
    static int Ts , N , Tf;
    static final int INF = (int) 1e8;
    static int memo[][];

    private static void solve2() {

        for(int tc = 1 , T = nextInt(); tc <= T; tc++) {
            N = nextInt();
            Ts = nextInt();
            Tf = nextInt();
            
            arr = new Pair[N - 1];
            for(int i = 0; i < N - 1; i++)
                arr[i] = new Pair(nextInt(), nextInt(), nextInt());
            
            memo = new int[N][Tf + 1];
            for(int a[] : memo) Arrays.fill(a, -1);
            
            int ans = rec(0, 0);
            println("Case #" + tc + ": " + (ans < 0 ? "IMPOSSIBLE" : ans));
        }
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
//        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        writer = new PrintWriter("out.txt");
        st     = null;
        solve2();
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