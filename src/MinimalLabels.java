import java.util.*;
import java.io.*;
public class MinimalLabels {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        int n = nextInt();
        int m = nextInt();
        int outDegree[] = new int[n + 1];
        ArrayList<Integer>[] inv = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            inv[i] = new ArrayList<>();
        }
        while(m-->0) {
            int u = nextInt();
            int v = nextInt();
            inv[v].add(u);
            outDegree[u]++;
        }
        
        int perm[] = new int[n + 1];
        PriorityQueue<Integer> pq = new PriorityQueue<>((i1 , i2) -> i2 - i1);
        for(int i = 1; i <= n; i++)
            if(outDegree[i] == 0)
                pq.add(i);
        for(int label = n; label >= 1; label--) {
            int curr = pq.remove();
            perm[curr] = label;
            for(int v : inv[curr]) {
                outDegree[v]--;
                if(outDegree[v] == 0)
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