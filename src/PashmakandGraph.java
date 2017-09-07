import java.util.*;
import java.io.*;
public class PashmakandGraph {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        
        int edges[][] = new int[E][];
        for(int i = 0; i < E; i++)
            edges[i] = nextIntArray(3);
        
        Arrays.sort(edges , (e1 , e2) -> e1[2] - e2[2]);
        
        int DP[] = new int[V + 1];
        int aux[] = new int[V + 1];
        for(int i = 0; i < E; ) {
            int j;
            for(j = i; j < E && edges[j][2] == edges[i][2]; j++)
                aux[edges[j][1]] = Math.max(DP[edges[j][1]] , DP[edges[j][0]] + 1);
            for(j = i; j < E && edges[j][2] == edges[i][2]; j++)
                DP[edges[j][1]] = aux[edges[j][1]];
            
            i = j;
        }
        
        println(Arrays.stream(DP).max().getAsInt());
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