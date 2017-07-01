import java.util.*;
import java.util.function.UnaryOperator;
import java.io.*;
public class Matrix {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int a = nextInt();
        int arr[] = nextLine().chars().map(Character::getNumericValue).toArray();
        int n = arr.length;
        long cnt = 0;
        int freq[] = new int[9 * n + 1];
        for(int i = 0; i < n; i++)
            for(int j = i , sum = 0; j < n; sum += arr[j++] , freq[sum]++)
                ;
        
        if(a > 0) {
            for(int i = 1; i * i <= a; i++)
                if(a % i == 0 && i < freq.length && a / i < freq.length) 
                    cnt += (i * i != a ? 2L : 1L) * freq[i] * freq[a / i];
        }
        else {
            UnaryOperator<Long> subArrayCnt = val -> (val * (val + 1)) / 2;
            cnt = 1L * freq[0] * (2L * subArrayCnt.apply((long) n) - freq[0]);
        }
        println(cnt);
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