import java.util.*;
import java.io.*;
class KGP14C {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[][] adj;
    static boolean marked[];
    static boolean color[];
    
    static boolean isBipartite(int start) {

        ArrayDeque<Integer> queue = new ArrayDeque<>();
        marked[start] = true;
        queue.add(start);

        while(!queue.isEmpty()) {
            int u = queue.remove();
            for(int v:adj[u]) {
                if(marked[v]){
                    if(color[u] == color[v])
                        return false;
                }
                else{
                    marked[v] = true;
                    color[v] = !color[u];
                    queue.add(v);
                }
            }
        }
        return true;

    }

    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1; tc <= T; tc++) {
            
            int V = nextInt();
            adj = new int[V + 1][];
            
            for(int i = 1; i <= V; i++)
                adj[i] = nextIntArray(nextInt());
            
            marked = new boolean[V + 1];
            color = new boolean[V + 1];
            
            boolean notPossible = false;
            
            for(int i = 1; i <= V; i++)
                if(!marked[i] && !isBipartite(i)) {
                    notPossible = true;
                    break;
                }
            
            if(notPossible)
                println("Case " + tc + ": 0 0 0");
            else {
                int cnt = 0;
                for(int i = 1; i <= V; i++)
                    cnt += color[i] ? 1 : 0;
                
                int first = Math.min(cnt , V - cnt);
                int sec = Math.max(cnt , V - cnt);
                println("Case " + tc + ": 1 " + first + " " + sec);
            }
            
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