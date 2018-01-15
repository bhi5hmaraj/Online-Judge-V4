import java.util.*;
import java.util.function.UnaryOperator;
import java.io.*;
public class Cycles {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        UnaryOperator<Integer> choose3 = ct -> (ct * (ct - 1) * (ct - 2)) / 6;
        int k = nextInt();
        
        TreeMap<Integer, Integer> map = new TreeMap<>();
        
        for(int n = 3; choose3.apply(n) <= (int) 1e5; n++)
            map.put(choose3.apply(n), n);
        
        ArrayList<Integer> req = new ArrayList<>();
        int n = 0;
        while(k > 0) {
            Map.Entry<Integer, Integer> floor = map.floorEntry(k);
            k -= floor.getKey();
            req.add(floor.getValue());
            n += floor.getValue();
        }
        
        char graph[][] = new char[n][n];
        for(char ch[] : graph) Arrays.fill(ch, '0');
        
        int offset = 0;
        for(int comp : req) {
            for(int i = offset; i < offset + comp; i++)
                for(int j = offset; j < offset + comp; j++)
                    graph[i][j] = i == j ? '0' : '1';
            
            offset += comp;
        }
        
//        println(req);
        println(n);
        Arrays.stream(graph).forEach(v -> println(new String(v)));
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