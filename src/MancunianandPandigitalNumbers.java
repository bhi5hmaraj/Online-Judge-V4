import java.util.*;
import java.io.*;
public class MancunianandPandigitalNumbers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean isAlmightyPandigital(int N) {
        int len = 0;
        int freq[] = new int[10];
        while(N > 0) {
            freq[N % 10]++;
            N /= 10;
            len++;
        }
        boolean flag = freq[0] == 0;
        for(int i=1;i<=len;i++)
            flag &= freq[i] == 1;
        
        return flag;
    }
    
    private static void solve() {
        int MAX = (int) 1e6;
        int prefixSum[] = new int[MAX + 1];
        for(int i=1;i<=MAX;i++) 
            prefixSum[i] = prefixSum[i - 1] + (isAlmightyPandigital(i) ? 1 : 0);

        int Q = nextInt();
        while(Q-->0)
            println(-(prefixSum[nextInt() - 1] - prefixSum[nextInt()]));
        
        
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