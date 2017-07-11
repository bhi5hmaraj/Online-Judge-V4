import java.util.*;
import java.io.*;
public class StanfordMWIS  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int req[] = { 1, 2, 3, 4, 17, 117, 517, 997};
        char ans[] = "00000000".toCharArray();
        
        int n = nextInt();
        long arr[] = nextLongArrayOneBased(n);
        long DP[] = new long[n + 1];
        DP[1] = arr[1];
        for(int i = 2; i <= n; i++)
            DP[i] = Math.max(DP[i - 1] , arr[i] + DP[i - 2]);
        
        int curr = n;
        while(curr > 1) {
            if(DP[curr] == DP[curr - 2] + arr[curr]) {
                if(Arrays.binarySearch(req, curr) >= 0)
                    ans[Arrays.binarySearch(req, curr)] = '1';
//                System.out.println(curr);
                curr -= 2;
            }
            else
                curr--;
        }
        
        if(curr == 1)
            ans[0] = '1';
        
        println(new String(ans));
//        System.out.println("sum " + DP[n]);
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