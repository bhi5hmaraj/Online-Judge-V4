import java.util.*;
import java.io.*;
class PRMQ {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int MAX = (int) 1e6;

    private static int[] preCalBigPrimeSieve()  {
        int bigPrime[] = new int[MAX + 1];
        bigPrime[1] = 1;
        for (int i = 2; i <= MAX; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= MAX; j += i)
                    bigPrime[j] = i;
            }
        }
        
        return bigPrime;
    }

    static class FenwickTree { 
        /**************** DONT USE BIT IF YOUR INDEX STARTS FROM ZERO (causes infinite loop) ******************/
        int tree[];
        int len;
        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }
        void update(int idx , int val) {
            if(idx == 0) throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for(;idx <= len;idx += (idx & -idx))
                tree[idx] += val;
        }
        int query(int idx) {
            int sum = 0;
            for(;idx > 0;idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }
        int query(int L , int R) {
            return query(R) - query(L - 1);
        }
    }

    static class Query {
        int X , Y , qID;
        boolean isLeft;
        Query(int x , int y, boolean left , int qid) {
            X = x;
            Y = y;
            isLeft = left;
            qID = qid;
        }
    }
    
    private static void solve() {
        int bigPrime[] = preCalBigPrimeSieve();
        
        int T = nextInt();

        while(T-->0) {
            
            int N = nextInt();
            int arr[] = nextIntArrayOneBased(N);
            int Q = nextInt();
            int ans[] = new int[Q];
            
            ArrayList<Query>[] offline = new ArrayList[N + 1];
            for(int i = 1; i <= N; i++)
                offline[i] = new ArrayList<>();
            
            for(int i = 0; i < Q; i++) {
                int L = nextInt();
                int R = nextInt();
                int X = nextInt();
                int Y = nextInt();
                offline[L].add(new Query(X, Y, true, i));
                offline[R].add(new Query(X, Y, false, i));
            }
            
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