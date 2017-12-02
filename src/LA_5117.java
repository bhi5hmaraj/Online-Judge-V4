import java.util.*;
import java.io.*;
public class LA_5117  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class MM {       // MM (Modular Math) class 
        static final long mod = (long) (1e9) + 7; // Default
        static long sub(long a, long b) {return (a - b  + mod) % mod;}
        static long mul(long a, long b) {return ((a % mod) * (b % mod)) % mod;}
        static long add(long a, long b) {return (a + b) % mod;}
        static long div(long a, long b) {return mul(a, modInverse(b));}
        static long modInverse(long n)  {return modPow(n, mod - 2);} // Fermat's little theorem
        static long modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return pow;
        }
    }


    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            int K = nextInt();
            int to[] = nextIntArray(N);
            
            long DP[] = new long[N + 1];
            DP[2] = K * (K - 1);
            for(int i = 3; i <= N; i++)
                DP[i] = MM.add(MM.mul(K - 2, DP[i - 1]), MM.mul(K - 1, DP[i - 2]));
            
            int marked[] = new int[N];
            int timeIn[] = new int[N];
            
            long ways = 1;
            int comp = 0;
            for(int i = 0; i < N; i++)
                if(marked[i] == 0) {
                    comp++;
                    int time = 0;
                    int curr = i;
                    boolean cycle = false;
                    while(true) {
                        if(marked[curr] == 0) {
                            marked[curr] = comp;
                            timeIn[curr] = ++time;
                            curr = to[curr];
                        } else {
                            cycle = marked[curr] == comp;
                            break;
                        }
                    }
                    
                    if(cycle) {
                        int len = time - timeIn[curr] + 1;
                        time = timeIn[curr] - 1;
                        ways = MM.mul(ways, DP[len]);
                    }
                    
                    ways = MM.mul(ways, MM.modPow(K - 1, time));
                }
            
            println(ways);
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