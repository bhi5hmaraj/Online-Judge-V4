import java.util.*;
import java.io.*;
public class DZYLovesModification {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        
        long row[] = new long[n];
        long col[] = new long[m];
        
        int k = nextInt();
        int p = nextInt();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++) {
                int a = nextInt();
                row[i] += a;
                col[j] += a;
            }
        
        long rowMoves[] = new long[k];
        PriorityQueue<Long> rowMax = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Long> colMax = new PriorityQueue<>(Collections.reverseOrder());
        
        for(long a : row) rowMax.add(a);
        for(long a : col) colMax.add(a);
        
        long currPleasure = 0;
        for(int t = 0; t < k; t++) {
            long top = rowMax.remove();
            rowMoves[t] = top;
            currPleasure += top;
            top -= m * p;
            rowMax.add(top);
        }
        
        long maxPleasure = currPleasure;
        int fix = 0;

        while(k-->0) {
            currPleasure -= rowMoves[k];    // Revert the effect of last row 
            long top = colMax.remove();
            currPleasure += top - k * p;    // The max col sum will be affected by the remaining k rows
            currPleasure += fix++ * p;  // Removing a row gives #fix ps
            maxPleasure = Math.max(maxPleasure , currPleasure);
            top -= n * p;
            colMax.add(top);
        }
        
        println(maxPleasure);
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