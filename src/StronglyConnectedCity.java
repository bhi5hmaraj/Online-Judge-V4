import java.util.*;
import java.io.*;
public class StronglyConnectedCity  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer>[] adj;
    static int n , m;
    static boolean isValid(int i , int j) {
        return i >= 0 && i < n && j >= 0 && j < m; 
    }

    static class SCC {

        /*
         * Kosaraju-Sharir Algorithm
         * 
         * Identify sinks (reverse post order in inverse graph)
         * Start normal dfs from the above order , the resulting components form SCC
         * 
         * If you want to use 1 based indexing set onBased flag to true
         * 
         */

        private ArrayList<Integer>[] invGraph;
        private ArrayDeque<Integer> stack;
        private Iterable<Integer>[] adj;
        private int group[];
        private boolean marked[];
        private int numOfComponents;
        private int st , V;


        @SuppressWarnings("unchecked")
        SCC(Iterable<Integer>[] adj , boolean oneBased) {
            st = oneBased ? 1 : 0;
            V = adj.length - st; 
            group = new int[V + st];
            this.adj = adj;
            invGraph = new ArrayList[V + st];
            for(int i=st;i<V + st;i++)
                invGraph[i] = new ArrayList<>();
            for(int i=st;i<V + st;i++)
                for(int j : adj[i])
                    invGraph[j].add(i);

            marked = new boolean[V + st];
            stack = new ArrayDeque<>();
            for(int i=st;i<V + st;i++)
                if(!marked[i])
                    reversePostOrder(i);

            marked = new boolean[V + st];
            int grp = 0;
            for(int i : stack)
                if(!marked[i])
                    dfs(i, grp++);

            numOfComponents = grp;
            stack = null;
        }

        private void reversePostOrder(int u) {
            marked[u] = true;
            for(int v : invGraph[u])
                if(!marked[v])
                    reversePostOrder(v);
            stack.push(u);
        }

        private void dfs(int u , int grp) {
            marked[u] = true;
            group[u] = grp;
            for(int v : adj[u])
                if(!marked[v])
                    dfs(v, grp);
        }
        public int[] getSCC() {
            return group;
        }
        public int numberOfComponents() {
            return numOfComponents;
        }

        @SuppressWarnings("unchecked")
        public HashSet<Integer>[] getDAG() {
            HashSet<Integer>[] dag = new HashSet[numOfComponents];
            for(int i=0;i<numOfComponents;i++)
                dag[i] = new HashSet<>();
            for(int i=st;i<V + st;i++)
                for(int v : adj[i])
                    if(group[i] != group[v])
                        dag[group[i]].add(group[v]);

            return dag;
        }
    }

    private static void solve() {
        
        
        n = nextInt();
        m = nextInt();
        char row[] = nextLine().toCharArray();
        char col[] = nextLine().toCharArray();

        adj = new ArrayList[n * m];
        for(int i = 0; i < n * m; i++)
            adj[i] = new ArrayList<>();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++){
                int nx = i , ny = j;
                ny += row[i] == '>' ? 1 : -1;
                nx += col[i] == 'v' ? 1 : -1;
                if(isValid(i, ny))
                    adj[i * m + j].add(i * m + ny);
                if(isValid(nx, j))
                    adj[i * m + j].add(nx * m + j);
            }
        
        SCC scc = new SCC(adj, false);
        println(scc.numberOfComponents() == 1 ? "YES" : "NO");
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