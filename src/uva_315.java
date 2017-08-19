import java.util.*;
import java.io.*;
public class uva_315 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static int level[];
    static int minLevel[];
    static boolean marked[];
    
    static int dfs(int u , int lev) {
        marked[u] = true;
        level[u]  = lev;
        int cnt = 0;
        int subTree = 0;
        boolean articulation = false;
        for(int v : adj[u]) {
            if(!marked[v]) {  // tree edge
                cnt += dfs(v, lev + 1);
                articulation |= minLevel[v] >= level[u];
                minLevel[u] = Math.min(minLevel[u] , minLevel[v]);
                subTree++;
            }
            else // back edge
                minLevel[u] = Math.min(minLevel[u] , level[v]);
        }
        if(lev == 0) // root node
            return cnt + (subTree <= 1 ? 0 : 1);
        else 
            return cnt + (articulation ? 1 : 0);
    }
    
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int V;
        while((V = nextInt()) > 0) {
            
            adj = new ArrayList[V + 1];
            for(int i = 1; i <= V; i++)
                adj[i] = new ArrayList<>();
            
            String line;
            while(!(line = nextLine()).equals("0")) {
                int to[] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                for(int i = 1; i < to.length; i++) {
                    adj[to[0]].add(to[i]);
                    adj[to[i]].add(to[0]);
                }
            }
            
            marked = new boolean[V + 1];
            level  = new int[V + 1];
            minLevel = new int[V + 1];
            Arrays.fill(minLevel, Integer.MAX_VALUE);
            int totalArticulation = 0;
            for(int i = 1; i <= V; i++)
                if(!marked[i])
                    totalArticulation += dfs(i, 0);
            
            println(totalArticulation);
        }
        
        
        
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