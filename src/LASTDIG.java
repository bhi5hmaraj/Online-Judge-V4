import java.util.*;
import java.io.*;
class LASTDIG {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int num[];
    static final int MAX_SUM = 150; // actually lesser
    static int memo[][][] = new int[9][MAX_SUM][2];
    static int cnt(int idx , int sum , int isPrefix) {
        if(sum < 0)
            return 0;
        else if(idx == num.length)
            return sum == 0 ? 1 : 0;
        else if(memo[idx][sum][isPrefix] != -1)
            return memo[idx][sum][isPrefix];
        else {
            int collect = 0;
            for(int dig = 0; dig <= (isPrefix == 1 ? num[idx] : 9); dig++)
                collect += cnt(idx + 1, sum - (dig * (dig % 2 == 0 ? 2 : 1)), isPrefix & (dig == num[idx] ? 1 : 0));
            return memo[idx][sum][isPrefix] = collect;
        }
    }
    
    static long cnt(int N) {
        if(N < 0)
            return 0;
        num = String.valueOf(N).chars().map(Character::getNumericValue).toArray();
        for(int a[][] : memo)
            for(int b[] : a)
                Arrays.fill(b, -1);
        long sum = 0;
        for(int i = 1; i < MAX_SUM; i++)
            sum += 1L * cnt(0, i, 1) * (i % 10);
        return sum;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            long cntA = cnt(nextInt() - 1);
            long cntB = cnt(nextInt());
            println(cntB - cntA);
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