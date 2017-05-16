/*
ID: bs3861
LANG: JAVA
TASK: dualpal
 */
import java.util.*;
import java.io.*;
public class dualpal {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX_BASE = 10;
    
    static boolean isDualPal(int N) {
        
        int cnt = 0;
        for(int base = 2;base <= MAX_BASE;base++) {
            String numInBase = Integer.toString(N, base);
            if(new StringBuilder(numInBase).reverse().toString().equals(numInBase))
                cnt++;
            
            if(cnt == 2)
                return true;
        }
        
        return false;
    }
    
    private static void solve() {
        
        int toFind = nextInt();
        int curr = nextInt() + 1;
        while(toFind-->0) {
            while(!isDualPal(curr))
                curr++;
            println(curr++);
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    static String file = "dualpal";
    static final boolean OJ = false;
    public static void main(String[] args) throws IOException {
        if(!OJ) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        }
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file + ".in")));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")), false);
        }
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