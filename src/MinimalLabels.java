import java.util.*;
import java.io.*;
public class MinimalLabels {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int n = nextInt();
        int m = nextInt();
        int inDegree[] = new int[n + 1];
        ArrayList<Integer>[] adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++)
            adj[i] = new ArrayList<>();
        
        while(m-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            inDegree[v]++;
        }
        int grpSize = 0;
        for(int i = 1; i <= n; i++)
            if(inDegree[i] == 0)
                grpSize++;
        PriorityQueue<Integer>[] grp = new PriorityQueue[grpSize];
        boolean marked[] = new boolean[n + 1];
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 1; i <= n; i++)
            if(inDegree[i] == 0)
                pq.add(i);
        println(pq);
        int perm[] = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            int curr = pq.remove();
            println(pq);
            perm[curr] = i;
            for(int v : adj[curr]) {
                inDegree[v]--;
                if(inDegree[v] == 0)
                    pq.add(v);
            }
        }
        
        for(int i = 1; i <= n; i++)
            print(perm[i] + " ");
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