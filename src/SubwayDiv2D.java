import java.util.*;
import java.io.*;
public class SubwayDiv2D {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static ArrayDeque<Integer> queue;
    static boolean bfsMarked[];
    static boolean marked[];
    static int prev[];
    
    static boolean findCycle(int u , int par) {
        marked[u] = true;
        prev[u] = par;
        for(int v : adj[u]) {
            if(marked[v] && v != par) {
                int curr = u;
                while(curr != v) {
                    bfsMarked[curr] = true;
                    queue.add(curr);
                    curr = prev[curr];
                }
                bfsMarked[curr] = true;
                queue.add(curr);
                return true;
            }
            else if(v != par && findCycle(v, u))
                return true;
        }
        
        return false;
    }
    
    
    private static void solve() {
        
        
        int V = nextInt();
        int E = V;
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        marked = new boolean[V + 1];
        bfsMarked = new boolean[V + 1];
        queue = new ArrayDeque<>();
        prev = new int[V + 1];
        int dist[] = new int[V + 1];
        findCycle(1 , 0);
        while(!queue.isEmpty()) {
            int u = queue.remove();
            for(int v : adj[u])
                if(!bfsMarked[v]) {
                    bfsMarked[v] = true;
                    dist[v] = dist[u] + 1;
                    queue.add(v);
                }
        }
        
        for(int i = 1; i <= V; i++)
            print(dist[i] + " ");
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