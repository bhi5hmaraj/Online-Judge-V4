import java.util.*;
import java.io.*;
public class TheDoorProblem {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
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

    static int comp(int x , int N) {
        return x <= N ? x + N : x - N;
    }
    
    private static void solve() {
        
        
        int N = nextInt();  // Doors
        int M = nextInt();  // Switch
        
        int status[] = nextIntArrayOneBased(N); // 0 - locked
        
        int controller[][] = new int[N + 1][2];
        for(int i = 1; i <= M; i++) {
            int x = nextInt();
            while(x-->0) {
                int d = nextInt();
                if(controller[d][0] == 0)
                    controller[d][0] = i;
                else
                    controller[d][1] = i;
            }
        }
        
        int V = 2 * M;
        ArrayList<Integer>[] SATGraph = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++)
            SATGraph[i] = new ArrayList<>();
        
        for(int i = 1; i <= N; i++) {
//            println(Arrays.toString(controller[i]));
            int A = status[i] == 1 ? comp(controller[i][0], M) : controller[i][0];
            int B = controller[i][1];
            SATGraph[comp(A, M)].add(B);
            SATGraph[comp(B, M)].add(A);
            SATGraph[A].add(comp(B, M));
            SATGraph[B].add(comp(A, M));
        }
        
        SCC scc = new SCC(SATGraph, true);
        int component[] = scc.getSCC();
        
        boolean sat = true;
        for(int i = 1; i <= M; i++)
            sat &= component[i] != component[comp(i, M)];
        
        println(sat ? "YES" : "NO");
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