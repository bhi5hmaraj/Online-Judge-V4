import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class KefaandPark  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static boolean hasCat[];
    static boolean isLeaf[];
    static int countRestaurants(int u , int par , int TTL , int m) {
        // System.out.println("u " + u + " TTL " + TTL);
        if(TTL < 0)
            return 0;
        else if(isLeaf[u])
            return TTL == 0 && hasCat[u] ? 0 : 1;
        else {
            int cnt = 0;
            for(int v : adj[u])
                if(v != par)
                    cnt += countRestaurants(v, u, hasCat[u] ? TTL - 1 : m, m);
            
            return cnt;
        }
    }
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int V = nextInt();
        int m = nextInt();
        
        int E = V - 1;
        hasCat = new boolean[V + 1];
        isLeaf = new boolean[V + 1];
        adj  = new ArrayList[V + 1];
        IntStream.rangeClosed(1, V).forEach(i -> hasCat[i] = nextInt() == 1);
        
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        IntStream.rangeClosed(2, V).forEach(i -> isLeaf[i] = adj[i].size() == 1);
        println(countRestaurants(1, 0, m, m));
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