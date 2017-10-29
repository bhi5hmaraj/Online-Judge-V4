import java.util.*;
import java.io.*;
public class GameGraph {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int cnt[][];
    static int nonzero[];
    static int perm[][] = {
            {0,1,2,3},
            {0,1,3,2},
            {0,2,1,3},
            {0,2,3,1},
            {0,3,1,2},
            {0,3,2,1}
    };
    static int cost[][] = {
            {0,0,0,0},
            {0,0,1,2},
            {0,2,0,1},
            {0,1,2,0}
    };
    
    static int memo[][];
    
    static int rec(int lev , int prev) {
        if(lev >= cnt.length)
            return 0;
        else if(memo[lev][prev] != -1) 
            return memo[lev][prev];
        else {
            int min = (int) 1e6; 
            int initPrev = prev;
            println("init " + initPrev);
            for(int p[] : perm) {
                int c = 0;
                int sz = nonzero[lev];
                println(Arrays.toString(p));
                for(int i = 1; i <= 3 && sz > 0; i++) {
                    c += cost[prev][p[i]];
                    prev = p[i];
                    sz -= cnt[lev][p[i]] > 0 ? 1 : 0;
                }
                println("prev " + prev);
                min = Math.min(min , c + rec(lev + 1, prev));
            }
            
            return memo[lev][initPrev] = min;
        }
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int comp[] = nextIntArray(n);
        ArrayList<Integer> adj[] = new ArrayList[n];
        for(int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();
        
        int inDeg[] = new int[n];
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            inDeg[i] = nextInt();
            if(inDeg[i] == 0)
                queue.add(i);
            for(int j = 0; j < inDeg[i]; j++)
                adj[nextInt() - 1].add(i);
        }
        
        int level[] = new int[n];
        while(!queue.isEmpty()) {
            int curr = queue.remove();
            for(int v : adj[curr]) {
                inDeg[v]--;
                if(inDeg[v] == 0) {
                    level[v] = level[curr] + 1;
                    queue.add(v);
                }
            }
        }
        
        System.out.println(Arrays.toString(level));
        cnt = new int[Arrays.stream(level).max().getAsInt() + 1][4];
        nonzero = new int[cnt.length];
        for(int i = 0; i < n; i++)
            cnt[level[i]][comp[i]]++;
        
        for(int i = 0; i < cnt.length; i++) {
            println(Arrays.toString(cnt[i]));
            for(int j = 0; j < 4; j++)
                nonzero[i] += cnt[i][j] > 0 ? 1 : 0;
        }
        memo = new int[cnt.length][4];
        for(int t[] : memo)
            Arrays.fill(t, -1);
        println(Math.min(Math.min(rec(0, 1) , rec(0, 2)) , rec(0, 3)) + n);
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