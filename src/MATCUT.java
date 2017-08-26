import java.util.*;
import java.io.*;
class MATCUT {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N = (int) 1e6;
    static int bigPrime[] = new int[N + 1];

    private static void preCalBigPrimeSieve() // instead of the loPrimesieve you
    // could use bigprimeSieve which
    // has the same performance and
    // its a lot more intutive
    {
        bigPrime[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= N; j += i)
                    bigPrime[j] = i;
            }
        }
    }

    static HashMap<Integer, Integer> primeFactorize(int N) // Dependency : A
    // sieve (loPrime[])
    // which contains the
    // lowest prime
    // divisor for each
    // number
    {
        HashMap<Integer, Integer> mp = new HashMap<>();
        int ct, prime;
        while (N != 1) {
            prime = bigPrime[N];
            ct = 0;
            while (N % prime == 0) {
                N /= prime;
                ct++;
            }
            mp.put(prime, ct);
        }
        return mp;
    }

    static ArrayList<Integer> adj[];
    static HashMap<Integer,Integer> pFact[];
    static HashMap<Integer , Integer> curr;
    static int globalMax;
    static boolean marked[];
    static void dfs(int u , int len) {
       marked[u] = true;
       pFact[u].forEach((k , v) -> curr.put(k, curr.getOrDefault(k, 0) + v));
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int V = nextInt();
            int E = V - 1;
            int arr[] = nextIntArrayOneBased(V);
            adj = new ArrayList[V + 1];
            pFact = new HashMap[V + 1];
            for(int i = 1; i <= V; i++) {
                adj[i] = new ArrayList<>();
                pFact[i] = primeFactorize(arr[i]);
            }
            while(E-->0) {
                int u = nextInt();
                int v = nextInt();
                adj[u].add(v);
                adj[v].add(u);
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