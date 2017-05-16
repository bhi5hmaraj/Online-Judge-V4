import java.util.*;
import java.io.*;
public class Berzerk {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N;
    static int options[][];
    static int memo[][][];
    static boolean marked[][][];
    static String debug[][][];
    static final int WIN = 3 , LOOSE = 1 , LOOP = 2 , BLACK_HOLE = 0;
    static int rec(int idx , int player , int winner , int dep) {
        //debug[idx][player][winner] = String.format("%d idx = %d player = %d winner = %d",dep, idx , player , winner);
        int ans;
        if(idx == BLACK_HOLE)
            ans = player != winner ? WIN : LOOSE;
        else if(memo[idx][player][winner] != -1)
            ans = memo[idx][player][winner];
        else if(marked[idx][player][winner])
            ans = -1;
        else {
            marked[idx][player][winner] = true;
            int min = 4;
            int max = 0;
            boolean isLoop = true;
            for(int opt : options[player]) {
                int ret = rec((idx + opt) % N, 1 - player , winner , dep + 1);
                isLoop &= ret < 0;
                if(ret > 0) {
                    min = Math.min(min, ret);
                    max = Math.max(max, ret);
                }
            }
            ans = memo[idx][player][winner] = isLoop ? LOOP : player == winner ? max: min;
        }
//        debug[idx][player][winner] += " ret = " + ans;
//        println(debug[idx][player][winner]);
        return ans;
    }
    private static void solve() {
        
        N = nextInt();
        options = new int[2][];
        options[0] = nextIntArray(nextInt());
        options[1] = nextIntArray(nextInt());
        memo = new int[N][2][2];
        marked = new boolean[N][2][2];
        debug = new String[N][2][2];
        for(int a[][] : memo)
            for(int b[] : a)
                Arrays.fill(b, -1);
        
        for(int i=0;i<2;i++) {
            for(int j=1;j<N;j++)
                print(rec(j, i, i , 0) + " ");
            print('\n');
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