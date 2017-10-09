import java.util.*;
import java.io.*;
public class FacelessArya {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static int MAX = 1000000;
    static int bigPrime[] = new int[MAX + 1];
    
    // instead of the loPrimesieve you could use bigprimeSieve which has the same performance and its a lot more intutive
    
    static {
        bigPrime[1] = 1;
        for (int i = 2; i <= MAX; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= MAX; j += i)
                    bigPrime[j] = i;
            }
        }
    }
    private static void solve() {
        
        int next[] = new int[MAX + 1];
        int prev[] = new int[MAX + 1];
        
        int T = nextInt();
        while(T-->0) {
            Arrays.fill(next, 0);
            Arrays.fill(prev, 0);
            int N = nextInt();
            int M = nextInt();
            int arr[][] = new int[N][];
            for(int i = 0; i < N; i++)
                arr[i] = nextIntArray(M);
            
            if(N == 1) {
                println(Arrays.stream(arr[0]).max().getAsInt());
                continue;
            }
            
            for(int a : arr[0]) {
                int cost = a;
                while(a > 1) {
                    int p = bigPrime[a];
                    prev[p] = Math.max(prev[p] , cost);
                    while(a % p == 0)
                        a /= p;
                }
            }
            
            for(int i = 1; i < N; i++) {
                for(int a : arr[i]) {
                    int cost = a;
                    int optPrev = 0;
                    int temp = a;
                    while(temp > 1) {
                        int p = bigPrime[temp];
                        optPrev = Math.max(optPrev , prev[p]);
                        while(temp % p == 0)
                            temp /= p;
                    }
                    
                    if(optPrev > 0) {
                        while(a > 1) {
                            int p = bigPrime[a];
                            next[p] = Math.max(next[p] , optPrev + cost);
                            while(a % p == 0)
                                a /= p;
                        }
                    }
                }
                
                for(int a : arr[i - 1]) {
                    while(a > 1) {
                        int p = bigPrime[a];
                        prev[p] = 0;
                        while(a % p == 0)
                            a /= p;
                    }
                }
                
                int swap[] = prev;
                prev = next;
                next = swap;
                
            }
            
            println(Arrays.stream(prev).max().getAsInt());
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