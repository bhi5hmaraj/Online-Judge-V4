import java.util.*;
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
        MyBitSet bitSetOut[] = new MyBitSet[V + 1];
        MyBitSet bitSetIn[] = new MyBitSet[V + 1];
        int bitSetLen = (V / 64) + 1;
        ArrayList<Integer> adj[] = new ArrayList[V + 1];
        for(int i = 1; i <= V; i++) {
            bitSetOut[i] = new MyBitSet(V + 1);
            bitSetIn[i] = new MyBitSet(V + 1);
            adj[i] = new ArrayList<>();
        }
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            bitSetOut[u].set(v);
            bitSetIn[v].set(u);
        }
        
        int cnt = 0;
        for(int i = 1; i <= V; i++) {
            for(int j = i + 1; j <= V; j++) {
                if(bitSetIn[i].cardinality > 0 && bitSetIn[j].cardinality > 0 && bitSetOut[i].cardinality > 0 && bitSetOut[j].cardinality > 0) {
                    cnt += bitSetIn[i].intersectSize(bitSetIn[j]) * bitSetOut[i].intersectSize(bitSetOut[j]);
                    for(int k = 0; k < bitSetLen; k++)
                        cnt -= Long.bitCount(bitSetIn[i].bits[k] & bitSetIn[j].bits[k] & bitSetOut[i].bits[k] & bitSetOut[j].bits[k]);
                }
            }
        }
        println(cnt);

    }
    static int[][] packU(int n, int[] from, int[] to , int isOneBased) {    // Courtesy : UWI ( adjacency list using Jagged Arrays )
        int[][] g = new int[n + isOneBased][];
        int[] p = new int[n + isOneBased];
        for (int f : from)
            p[f]++;
        for (int t : to)
            p[t]++;
        for (int i = 0 + isOneBased; i < n + isOneBased; i++)
            g[i] = new int[p[i]];
        for (int i = 0; i < from.length; i++) {
            g[from[i]][--p[from[i]]] = to[i];
            g[to[i]][--p[to[i]]] = from[i];
        }
        return g;
    }
    
    private static void solve2() {
        
        int V = nextInt();
        int E = nextInt();
        boolean conn[][] = new boolean[V + 1][V + 1];
        
        while(E-->0) {
            int u = nextInt();
            int v = nextInt();
            adj[u].add(v);
            conn[u][v] = true;
        }
        
        int cnt = 0;
        for(int i = 1; i <= V; i++) 
            for(int j = 1; j <= V; j++) 
                if(i != j && adj[i].size() > 0) {
                    int canTake = 0;
                    for(int k : adj[i])
                        canTake += conn[k][j] ? 1 : 0;
                    cnt += (canTake * (canTake - 1)) / 2;
                }
         
        println(cnt);
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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