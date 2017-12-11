import java.util.*;
import java.io.*;
public class uva_12160  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int INF = (int) 1e8;
    static final int MAX = (int) 1e4;
    
    private static void solve() {
        
        int L = nextInt(), U = nextInt(), R = nextInt();
        
        for(int caseNum = 1; R > 0; L = nextInt(), U = nextInt(), R = nextInt() , caseNum++) {
            
            int options[] = nextIntArray(R);
            int dist[] = new int[MAX];
            boolean marked[] = new boolean[MAX];
            
            Arrays.fill(dist, INF);
            ArrayDeque<Integer> queue = new ArrayDeque<>();
            queue.add(L);
            marked[L] = true;
            dist[L] = 0;
            
            while(!queue.isEmpty()) {
                int curr = queue.remove();
                for(int opt : options) {
                    int next = (curr + opt) % MAX;
                    if(!marked[next]) {
                        marked[next] = true;
                        dist[next] = dist[curr] + 1;
                        queue.add(next);
                    }
                }
            }
            
            println("Case " + caseNum + ": " + (dist[U] == INF ? "Permanently Locked" : dist[U]));
        
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