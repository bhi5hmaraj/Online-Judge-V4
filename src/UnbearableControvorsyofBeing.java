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
                
        boolean get(int n) {
            return ((bits[n / 64]) & (1L << (n % 64))) != 0;
        }
        
    }

    
    private static void solve() {
        
        
        int V = nextInt();
        int E = nextInt();
        MyBitSet adj[] = new MyBitSet[V + 1];
        for(int i = 1; i <= V; i++)
            adj[i] = new MyBitSet(V + 1);
        
        while(E-->0)
            adj[nextInt()].set(nextInt());
        
        
        
        
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