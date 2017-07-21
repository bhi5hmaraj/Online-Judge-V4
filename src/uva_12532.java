import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class uva_12532  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class FenwickTree {

        /****************
         * DONT USE BIT IF YOU UPDATE INDEX 0 (causes infinite loop)
         ******************/

        int tree[];
        int len;

        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }

        void update(int idx, int val) {
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] += val;
        }

        int query(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }

        int query(int L, int R) {
            return query(R) - query(L - 1);
        }
    }
    
    private static void solve() {
        
        String line;
        while((line = nextLine()) != null) {
            String in[] = line.split(" ");
            int N = Integer.parseInt(in[0]), Q = Integer.parseInt(in[1]);
            
            FenwickTree BIT[] = new FenwickTree[3]; // signum + 1
            for(int i = 0; i < 3; i++)
                BIT[i] = new FenwickTree(N);
            int arr[] = nextIntArrayOneBased(N);
            IntStream.range(1, N + 1).forEach(i -> BIT[Integer.signum(arr[i]) + 1].update(i, 1));
            
            while(Q-->0) {
                if('C' == nextChar()) {
                    int idx = nextInt();
                    int val = nextInt();
                    BIT[Integer.signum(arr[idx]) + 1].update(idx, -1);
                    BIT[Integer.signum(val) + 1].update(idx, 1);
                    arr[idx] = val;
                }
                else {
                    int L = nextInt();
                    int R = nextInt();
                    if(BIT[1].query(L, R) > 0)
                        print(0);
                    else if(BIT[0].query(L, R) % 2 == 1)
                        print("-");
                    else
                        print("+");
                }
            }
            print('\n');
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