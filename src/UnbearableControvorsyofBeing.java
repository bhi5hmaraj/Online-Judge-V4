import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
public class UnbearableControvorsyofBeing {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class MyBitSet {
        long bits[];
        int cardinality;
        int size;
        MyBitSet(int MAX) {
            size = MAX;
            bits = new long[((MAX - 1) / 64) + 1];
            cardinality = 0;
        }

        void set(int n, boolean f) {
            int index = n / 64;
            if (f) {
                if((bits[index] & (1L << (n % 64))) == 0)
                    cardinality++;
                bits[index] |= (1L << (n % 64));
            }
            else
                bits[index] ^= (bits[index] & (1L << (n % 64))) != 0 ? (1L << (n % 64)) : 0;
        }

        void set(int n) {
            set(n, true);
        }
        
        int cardinality() {
            return cardinality;
        }
        
        int intersectSize(MyBitSet other) {
            int sum = 0;
            for(int i = 0; i < bits.length; i++)
                sum += Long.bitCount(bits[i] & other.bits[i]);
            return sum;
        }
        
        boolean get(int n) {
            return ((bits[n / 64]) & (1L << (n % 64))) != 0;
        }
        
    }

    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        MyBitSet bitSet[] = new MyBitSet[V + 1];
        ArrayList<Integer> adj[] = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
            bitSet[i] = new MyBitSet(V + 1);
            adj[i] = new ArrayList<>();
        }
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            bitSet[u].set(v);
        }
        
        int cache[][] = new int[V + 1][V + 1];
        for(int i = 1; i <= V; i++)
            for(int j = 1; j <= V; j++)
                if(bitSet[i].cardinality > 0 && bitSet[j].cardinality > 0)
                    cache[i][j] = bitSet[i].intersectSize(bitSet[j]);
        
        int cnt = 0;
        for(int i = 1; i <= V; i++) 
            for(int j = 0; j < adj[i].size(); j++) {
                int jj = adj[i].get(j);
                for(int k = j + 1; k < adj[i].size(); k++) {
                    int kk = adj[i].get(k);
                    cnt += cache[jj][kk] - (bitSet[jj].get(i) && bitSet[kk].get(i) ? 1 : 0);
                }
            }
        
        println(cnt);

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