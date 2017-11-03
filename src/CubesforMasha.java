import java.util.*;
import java.io.*;
public class CubesforMasha {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean marked[];
    static boolean perm[];
    static int dice[][];
    static int n;
    static void rec(int idx , int num) {
        marked[num] = true;
//        println(num);
        if(idx < n) {
            for(int i = 0; i < n; i++) // dice i
                if(!perm[i]) {
                    perm[i] = true;
                    for(int face : dice[i])
                        rec(idx + 1, num * 10 + face);
                    perm[i] = false;
                }
        }
    }
    
    private static void solve() {
        
        n = nextInt();
        dice = new int[n][];
        for(int i = 0; i < n; i++)
            dice[i] = nextIntArray(6);
        
        marked = new boolean[1000];
        marked[0] = true;
        perm = new boolean[n];
        
        rec(0, 0);
        int ptr = 0;
        while(ptr < marked.length && marked[ptr])
            ptr++;
        
        println(ptr - 1);
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