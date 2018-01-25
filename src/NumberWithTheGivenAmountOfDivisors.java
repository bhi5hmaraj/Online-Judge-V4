import java.util.*;
import java.io.*;
public class NumberWithTheGivenAmountOfDivisors {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long primes[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 
            83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
            193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307
    };
    static Boolean memo[][][];
    static long ans;
    static boolean find(int idx, int divisorCnt, int prev) {
        if(divisorCnt == 1)
            return true;
        else if(memo[idx][divisorCnt][prev] != null)
            return memo[idx][divisorCnt][prev];
        
        long primePow = primes[idx];
        for(int pow = 1; pow <= prev; pow++, primePow *= primes[idx])
            if(divisorCnt % (pow + 1) == 0 && find(idx + 1, divisorCnt / (pow + 1), pow)) {
                ans *= primePow;
                return true;
            }
        
        return memo[idx][divisorCnt][prev] = false;
    }
    static final int MAX_POW = 63;
    
    // BruteForce solver
    static int bigPrime[];
    static final int N = (int) 1e6;
    private static void preCalBigPrimeSieve() {
        bigPrime = new int[N + 1];
        bigPrime[1] = 1;
        for (int i = 2; i <= N; i++) {
            if (bigPrime[i] == 0) {
                bigPrime[i] = i;
                for (int j = 2 * i; j <= N; j += i)
                    bigPrime[j] = i;
            }
        }
    }

    static int countDiv(int N)  {
        int ct, prime;
        int divCnt = 1;
        while (N != 1) {
            prime = bigPrime[N];
            ct = 0;
            while (N % prime == 0) {
                N /= prime;
                ct++;
            }
            divCnt *= (ct + 1);
        }
        return divCnt;
    }
    
    static final long MAX = (long) 1e18;
    static long min = Long.MAX_VALUE;
    static void findAll(int idx, int prev, int prd, long accum) {
        if(prd == 1) {
            min = Math.min(min , accum);
            return;
        }
        long primePow = 1;
        int pow = 2;
        while(pow <= prev && primePow <= MAX / primes[idx]) {
            primePow *= primes[idx];
            if(prd % pow == 0 && accum <= MAX / primePow)
                findAll(idx + 1, pow, prd / pow, accum * primePow);
            pow++;
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        /*        
        preCalBigPrimeSieve();       
        for(int i = 2; i <= N; i++)
            if(countDiv(i) == n) {
                println("correct " + i);
                break;
            }
        */
        findAll(0, MAX_POW, n, 1);
        println(min);
        
        
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