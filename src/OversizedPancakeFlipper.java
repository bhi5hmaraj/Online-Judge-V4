import java.util.*;
import java.io.*;
public class OversizedPancakeFlipper {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
          
            String str = next();
            int K = nextInt();
            int N = str.length();
            BitSet start = new BitSet();
            for(int i=0 , len = str.length();i < len;i++)
                start.set(i, str.charAt(i) == '+');
            
            HashMap<BitSet , Integer> dist = new HashMap<>();
            ArrayDeque<BitSet> queue = new ArrayDeque<>();
            dist.put(start, 0);
            queue.add(start);
            
            int minMove = -1;
            while(!queue.isEmpty()) {
                // println(queue);
                BitSet curr = queue.remove();
                if(curr.cardinality() == N) {
                    minMove = dist.get(curr);
                    break;
                }
                for(int i=0;i + K <= N;i++) {
                    BitSet adj = ((BitSet)curr.clone());
                    adj.flip(i, i + K);
                    if(!dist.containsKey(adj)) {
                        dist.put(adj, dist.get(curr) + 1);
                        queue.add(adj);
                    }
                }
            }
            
            println("Case #" + tc + ": " + (minMove == -1 ? "IMPOSSIBLE" : minMove));
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
    /*
    static String outputFile = "output_overSizedPancakes.txt";
    public static void main(String []args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        writer = 
                new PrintWriter(outputFile); 
        solve();
        reader.close();
        writer.close();
    }  
    */
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