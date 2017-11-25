import java.util.*;
import java.io.*;
public class VitalyandCycle  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    
    private static ArrayList<int[]> isBipartite(int V) {

        boolean marked[] = new boolean[V+1];
        boolean color[]  = new boolean[V+1];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayList<int[]> comp = new ArrayList<>();  // stores the size of the 2 sets in a bipartite comp
        
        for(int i = 1; i <= V; i++) {
            if(marked[i])
                continue;
            
            marked[i] = true;
            queue.add(i);
            int size[] = new int[2];
            while (!queue.isEmpty()) {
                int u = queue.remove();
                size[color[u] ? 1 : 0]++;
                for (int v : adj[u]) {
                    if (marked[v]) {
                        if (color[u] == color[v])
                            return null;
                    } else {
                        marked[v] = true;
                        color[v] = !color[u];
                        queue.add(v);
                    }
    
                }
            }
            
            comp.add(size);
        }
        return comp;
    }

    static long nc2(long n) {
        return (n * (n - 1)) / 2;
    }

    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        adj = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new ArrayList<>();
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }
        
        ArrayList<int[]> comp = isBipartite(V);
        if(comp  == null)
            println("0 1");
        else if(comp.stream().anyMatch(size -> size[0] + size[1] > 2))
               println("1 " + comp.stream().map(size -> nc2(size[0]) + nc2(size[1])).reduce(0L, Long::sum));
        else if(comp.stream().anyMatch(size -> size[0] + size[1] == 2)) {
            long size2 = comp.stream().filter(size -> size[0] + size[1] == 2).count();
            long size1 = comp.stream().filter(size -> size[0] + size[1] == 1).count();
            
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