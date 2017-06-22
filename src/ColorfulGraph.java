import java.util.*;
import java.io.*;
public class ColorfulGraph {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        final int MAX_COLOR = (int) 1e5;
        
        int V = nextInt();
        int E = nextInt();
        int color[] = nextIntArrayOneBased(V);
        ArrayList<Integer>[] adj = new ArrayList[V + 1];
        ArrayList<Integer>[] colorGroup = new ArrayList[MAX_COLOR + 1];
        for(int i = 1; i <= V; i++) 
            adj[i] = new ArrayList<>();
        for(int i = 1; i <= MAX_COLOR; i++)
            colorGroup[i] = new ArrayList<>();
        
        
        for(int i = 1; i <= V; i++)
            colorGroup[color[i]].add(i);
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        int maxCardinality = -1 , maxColor = 0;
        for(int col = 1; col <= MAX_COLOR; col++) {
            HashSet<Integer> set = new HashSet<>();
            for(int v : colorGroup[col])
                for(int u : adj[v])
                    if(color[u] != col)
                        set.add(color[u]);
            
            if(maxCardinality < set.size() && colorGroup[col].size() > 0) {
                maxCardinality = set.size();
                maxColor = col;
            }
        }
        
        println(maxColor);
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