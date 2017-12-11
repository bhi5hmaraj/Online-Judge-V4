import java.util.*;
import java.io.*;
public class PieRules {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int slice[];
    static int memo[][];
    static int rec(int idx , int turn) {    // 0 - Bob , 1 - Alice    
        if(idx == slice.length)
            return 0;
        else if(memo[idx][turn] != -1)
            return memo[idx][turn];
        else {
            if (turn == 0)
                return memo[idx][turn] = Math.max(rec(idx + 1, 0) , slice[idx] + rec(idx + 1, 1));
            else 
                return memo[idx][turn] = Math.min(slice[idx] + rec(idx + 1, 1), rec(idx + 1, 0));
        }
    }
    
    private static void solve() {
        
        
        int N = nextInt();
        slice = nextIntArray(N);
        
        memo = new int[N][2];
        for(int t[] : memo) Arrays.fill(t, -1);
        
        int bobScore = rec(0, 0);
        int aliceScore = Arrays.stream(slice).sum() - bobScore;
        
        println(aliceScore + " " + bobScore);
        
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