import java.util.*;
import java.io.*;
public class CowProgram {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int CYCLE = -1;
    
    static boolean marked[];
    static boolean inStack[];
    
    static int arr[];
    static long memo[];
    static int n;
    static long dfs(int x) {
        if(marked[x] && !inStack[x]) {
            // System.out.println("cached " + x + " " + memo[x]);
            return memo[x];
        }
        else if((marked[x] && inStack[x])) { // cycle detected 
            // System.out.println("cycle");
            return CYCLE;
        }
        else {
            marked[x] = inStack[x] = true;
            int y = 0;
            int nx = x + arr[x];
            long ny = y + arr[x];
            long ans = 0;
            if(nx >= 1 && nx <= n) {
                ny += arr[nx];
                nx -= arr[nx];
                if(nx >= 1 && nx <= n) {
                    long ret = dfs(nx);
                    ans = ret == CYCLE ? CYCLE : ny + ret;
                }
                else
                    ans = ny;
            }
            else
                ans = ny;
            
            inStack[x] = false;
            // System.out.println(x + " ans " + ans);
            return memo[x] = ans;
        }
    }
    
    private static void solve() {
        
        n = nextInt();
        arr = new int[n + 1];
        System.arraycopy(nextIntArray(n - 1), 0, arr, 2, n - 1);
        memo = new long[n + 1];
        marked = new boolean[n + 1];
        inStack = new boolean[n + 1];
        
        for(int i = 1; i <= n - 1; i++) {
            marked[1] = false;
            arr[1] = i;
            println(dfs(1));
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