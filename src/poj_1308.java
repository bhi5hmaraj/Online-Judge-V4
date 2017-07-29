import java.util.*;
import java.io.*;
public class poj_1308 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        int from , to;
        int tc = 1;
        while((from = nextInt()) >= 0 && (to = nextInt()) >= 0) {
            HashMap<Integer , Integer> inDegree = new HashMap<Integer , Integer>();
            while(true) {
                if(from == 0 && to == 0)
                    break;
                if(!inDegree.containsKey(from))
                    inDegree.put(from, 0);
                if(!inDegree.containsKey(to))
                    inDegree.put(to, 0);
                inDegree.put(to, inDegree.get(to) + 1);
                
                from = nextInt();
                to = nextInt();
            }
            println(inDegree);
            
            boolean flag = true;
            int root = 0;
            for(Map.Entry<Integer, Integer> e : inDegree.entrySet()) {
                root += e.getValue() == 0 ? 1 : 0;
                flag &= e.getValue() <= 1;
            }
            flag &= root == 1;
            println("Case " + (tc++) + " is " + (flag ? "" : "not") + " a tree.");
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