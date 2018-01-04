import java.util.*;
import java.io.*;
public class LittleElephantandArray  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int BLOCK_SIZE;
    static int freq[];
    static int arr[];
    static boolean marked[];
    static int currentAns;
    static int n;
    
    static class Query implements Comparable<Query> {
        int L , R , index;
        Query(int l , int r , int idx) {
            L = l;
            R = r;
            index = idx;
        }
        @Override
        public int compareTo(Query o) {
            if(L / BLOCK_SIZE != o.L / BLOCK_SIZE)
                return L / BLOCK_SIZE - o.L / BLOCK_SIZE;
            return R - o.R;
        }
    }
    
    private static void solve() {
        
        n = nextInt();
        int q = nextInt();
        arr = nextIntArray(n);
        
        BLOCK_SIZE = (int) Math.sqrt(n);
        int ans[] = new int[q];

        Query queries[] = new Query[q];
        for(int i = 0; i < q; i++)
            queries[i] = new Query(nextInt(), nextInt(), i);
            
        Arrays.sort(queries);
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