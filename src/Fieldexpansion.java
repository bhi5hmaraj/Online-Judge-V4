import java.util.*;
import java.io.*;
public class Fieldexpansion {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int a = nextInt();
        int b = nextInt();
        int h = nextInt();
        int w = nextInt();
        
        int n = nextInt();
        long arr[] = nextLongArray(n);
        Arrays.sort(arr);
        for(int i = 0; i < n >> 1; i++) {
            long temp = arr[i];
            arr[i] = arr[n - i - 1];
            arr[n - i - 1] = temp;
        }
        System.out.println(Arrays.toString(arr));
        int MAX_POSSIBLE = 34;
        int MAX_NUM = (int) 1e5;
        
        int DP[][] = new int[MAX_POSSIBLE + 1][MAX_NUM + 1];
        Arrays.fill(DP[0], w);
        for(int i = 0; i < Math.min(MAX_POSSIBLE , n); i++) {
            for(int j = 1; j <= MAX_NUM; j++) {
                
                DP[i + 1][(int) Math.min(MAX_NUM , arr[i] * j)] 
                        = Math.max(DP[i + 1][(int) Math.min(MAX_NUM , arr[i] * j)] , DP[i][j]); // Apply on height
                DP[i + 1][j] = Math.max(DP[i + 1][j] , (int) Math.min(MAX_NUM , arr[i] * DP[i][j]));   // Apply on width
            }
        }
        
        for(int i = 0; i <= Math.min(n , MAX_POSSIBLE); i++) {
            for(int j = 1; j <= 12; j++) {
                System.out.printf("%3d ", DP[i][j]);
            }
            System.out.println();
        }   
        
        for(int i = 0; i <= MAX_POSSIBLE; i++)
            for(int j = 1; j <= MAX_NUM; j++) {
                if((a <= j && b <= DP[i][j]) || (a <= DP[i][j] && b <= j)) {
                    println(i);
                    return;
                }
            }
        
        println(-1);
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