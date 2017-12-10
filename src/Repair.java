import java.util.*;
import java.io.*;
public class Repair  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    // Check if rect 2 fits in rect 1
    static boolean canFit(int l1 , int b1 , int l2 , int b2) {
        return l2 <= l1 && b2 <= b1 || l2 <= b1 && b2 <= l1;
    }
    
    private static void solve() {
        
        
        int a = nextInt();
        int b = nextInt();
        
        int a1 = nextInt();
        int b1 = nextInt();
        int a2 = nextInt();
        int b2 = nextInt();
        
        
        // Try to fit horizontally
        if(a1 <= a && b1 <= b && (canFit(a - a1, b, a2, b2) || canFit(a1, b - b1, a2, b2))) {
            println("YES");
            return;
        }
        
        int temp = a1;
        a1 = b1;
        b1 = temp;
        // Try vertical 
        
        if(a1 <= a && b1 <= b && (canFit(a - a1, b, a2, b2) || canFit(a1, b - b1, a2, b2))) {
            println("YES");
            return;
        }
        
        temp = a1;
        a1 = a2;
        a2 = temp;
        
        temp = b1;
        b1 = b2;
        b2 = temp;
        
        // Try to fit horizontally
        if(a1 <= a && b1 <= b && (canFit(a - a1, b, a2, b2) || canFit(a1, b - b1, a2, b2))) {
            println("YES");
            return;
        }
        
        temp = a1;
        a1 = b1;
        b1 = temp;
        // Try vertical 
        
        if(a1 <= a && b1 <= b && (canFit(a - a1, b, a2, b2) || canFit(a1, b - b1, a2, b2))) {
            println("YES");
            return;
        }
        
        
        println("NO");
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