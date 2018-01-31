import java.util.*;
import java.io.*;
public class SeatArrangements {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int find(String arr[], int k) {
        int cnt = 0;
        for(String seats : arr) 
            cnt += Arrays.stream(seats.split("\\*"))
                        .mapToInt(s -> Math.max(0, s.length() - k + 1))
                        .reduce(Integer::sum).orElse(0);
        
        return cnt;
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        
        int k = nextInt();
        
        String row[] = new String[n];
        for(int i = 0; i < n; i++)
            row[i] = nextLine();
        
        StringBuilder fast[] = new StringBuilder[m];
        for(int i = 0; i < m; i++)
            fast[i] = new StringBuilder();
        
        
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                fast[j].append(row[i].charAt(j));
        
        
        println(find(row, k) + (k == 1 ? 0 : 
                find(Arrays.stream(fast).map(StringBuilder::toString).toArray(String[]::new), k)));
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