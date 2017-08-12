import java.util.*;
import java.io.*;
public class StronglyConnectedCity2 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int V;
    static boolean adjMat[][];
    static boolean marked[];
    
    static int dfs(int u) {
        marked[u] = true;
        int cnt = 1;
        for(int i = 1; i <= V; i++)
            if(i != u && !marked[i] && adjMat[u][i])
                cnt += dfs(i);
        return cnt;
    }
    
    private static void solve() {
        
        V = nextInt();
        int E = nextInt();
        int edges[][] = new int[E][2];
        for(int i = 0; i < E; i++) 
            edges[i] = nextIntArray(2);
        
        boolean maxMat[][] = null;
        int maxCnt = 0;
        for(int mask = 0; mask < (1 << E); mask++) {
            adjMat = new boolean[V + 1][V + 1];
            for(int i = 0; i < E; i++) {
                if((mask & (1 << i)) != 0) 
                    adjMat[edges[i][1]][edges[i][0]] = true;
                else
                    adjMat[edges[i][0]][edges[i][1]] = true;
            }
            int cnt = 0;
            for(int i = 1; i <= V; i++) {
                marked = new boolean[V + 1];
                cnt += dfs(i);
            }
            
            if(cnt > maxCnt) {
                maxCnt = cnt;
                maxMat = adjMat;
            }
        }
        
        println(maxCnt);
         
        for(int i = 1; i <= V; i++)
            for(int j = 1; j <= V; j++)
                if(maxMat[i][j])
                    println(i + " " + j); 
        
    }
    
    static ArrayList<Integer>[] adj;
    static int prev[];
    static int level[];
    static int globalMax;
    
    static class Pair {
        int vertex , backVertex;
        Pair(int v , int d) {
            vertex = v;
            backVertex = d;
        }
        @Override
        public String toString() {
            return "v = " + vertex + " bv " + backVertex;
        }
    }
    
    static void rec(int u , int par , int lev) {
        prev[u] = par;
        level[u] = lev;
        marked[u] = true;
        for(int v : adj[u])
            if(!marked[v])
                rec(v, u, lev + 1);
    }
    
    static int joiner = -1;
    static Pair p1 = null , p2 = null;
    
    static void relax(int u , Pair pp1 , Pair pp2) {
        int len = level[pp1.vertex] + level[pp2.vertex] - 2 * level[u] + 
                  Math.abs(level[pp1.backVertex] - level[pp2.backVertex]) + 2;
        if(len > globalMax) {
            globalMax = len;
            joiner = u;
            p1 = pp1;
            p2 = pp2;
        }
    }
    
    static ArrayList<Pair> findLargestCycle(int u , int par) {
        marked[u] = true;
        ArrayList<Pair> collect = new ArrayList<>();
        ArrayList<Pair> curr = new ArrayList<>();
        for(int v : adj[u]) {
            if(!marked[v]) {
                ArrayList<Pair> child = findLargestCycle(v , u);
                System.out.println("u " + u + "v " + child);
                child.stream().forEach(pp1 -> collect.forEach(pp2 -> {
                    if(level[pp1.backVertex] < level[u] && level[pp2.backVertex] < level[u])
                        relax(u, pp1, pp2);   
                }));
                collect.addAll(child);
            }
            else if(v != par && level[v] < level[u]) {
                Pair p = new Pair(u, v);
                curr.add(p);
                if(level[u] - level[v] + 1 > globalMax) {
                    globalMax = level[u] - level[v] + 1;
                    joiner = -1;
                    p1 = p;
                    p2 = null;
                }
            }
        }
        collect.addAll(curr);
        System.out.println(u);
        System.out.println(collect);
        return collect;
    }
    
    static ArrayList<Integer> enumerateMaxCycle() {
        ArrayList<Integer> ans = new ArrayList<>();
        if(p1 == null)
            ans = null;
        else if(p2 == null) {
            for(int i = p1.vertex; i != p1.backVertex; i = prev[i])
                ans.add(i);
            ans.add(p1.backVertex);
        }
        else {
            System.out.println("join " + joiner + " p1 " + p1 + " p2 " + p2);
            for(int i = p1.vertex; i != joiner; i = prev[i])
                ans.add(i);
            ans.add(joiner);
            for(int i = p2.vertex; i != joiner; i = prev[i])
                ans.add(i);
            if(level[p2.backVertex] < level[p1.backVertex]) {
                Pair temp = p1;
                p1 = p2;
                p2 = temp;
            }
            for(int i = p2.backVertex; i != p1.backVertex; i = prev[i])
                ans.add(i);
            ans.add(p1.backVertex);
        }
        return ans;
    }
    
    private static void solve2() {
        
        V = nextInt();
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
        marked = new boolean[V + 1];
        prev = new int[V + 1];
        level = new int[V + 1];
        rec(1, 0, 0);
        marked = new boolean[V + 1];
        globalMax = 0;
        findLargestCycle(1, 0);
        System.out.println(enumerateMaxCycle());
        
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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