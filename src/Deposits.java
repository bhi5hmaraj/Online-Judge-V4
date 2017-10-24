import java.util.*;
import java.io.*;
public class Deposits {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int MAX = (int) 1e6;
        int freqA[] = new int[MAX + 1];
        int freqB[] = new int[MAX + 1];
        long cnt = 0;
        for(int num : nextIntArray(nextInt()))
            freqA[num]++;
        for(int num : nextIntArray(nextInt()))
            freqB[num]++;
        
        for(int b = 1; b <= MAX; b++)
            for(int a = b; a <= MAX; a += b)
                cnt += 1L * freqB[b] * freqA[a];
        
        println(cnt);
    }
    
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
//        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        reader = new BufferedReader(new FileReader("deposits.in"));
        writer = new PrintWriter("deposits.out");
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