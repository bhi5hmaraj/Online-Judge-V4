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
    
    static void visit(int idx) {
        if(arr[idx] <= n) { 
            if(freq[arr[idx]] == arr[idx])  // If its equal before change then we lose them
                currentAns--;
            freq[arr[idx]] += marked[idx] ? -1 : 1;
            if(freq[arr[idx]] == arr[idx])  // If its equal after change we gain them
                currentAns++;
        }
        marked[idx] = !marked[idx];
    }
    
    private static void solve() {
        
        n = nextInt();
        int Q = nextInt();
        arr = nextIntArray(n);
        
        BLOCK_SIZE = (int) Math.sqrt(n);
        int ans[] = new int[Q];

        Query queries[] = new Query[Q];
        for(int i = 0; i < Q; i++)
            queries[i] = new Query(nextInt() - 1, nextInt() - 1, i);
            
        Arrays.sort(queries);
        int moLeft = -1 , moRight = -1;
        
        freq = new int[n + 1];
        marked = new boolean[n];
        currentAns = 0;
        
        for(Query q : queries) {
            while(moLeft < q.L - 1) {
                moLeft++;
                visit(moLeft);
            }
            while(moLeft >= q.L) {
                visit(moLeft);
                moLeft--;
            }
            while(moRight < q.R) {
                moRight++;
                visit(moRight);
            }
            while(moRight > q.R) {
                visit(moRight);
                moRight--;
            }
            
            ans[q.index] = currentAns;
        }
        
        Arrays.stream(ans).forEach(writer::println);
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