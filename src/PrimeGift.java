import java.util.*;
import java.io.*;
public class PrimeGift {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long primes[];
    static final long MAX = (long) 1e18;
    static int ptr;
    static void brute(int idx ,int len,  long curr, long collect[]) {
        if(idx == len) { 
            collect[ptr++] = curr;
            return;
        }
        brute(idx + 1, len , curr , collect);
        while(curr <= MAX / primes[idx]) {
            curr *= primes[idx];
            brute(idx + 1, len, curr, collect);
        }
    }
    
    // Lets see how far you can go
    
    private static void solve() {
        
        int n = nextInt();
        int p[] = nextIntArray(n);
        primes = new long[n];
        for(int i = 0; i < n / 2; i++) {
            primes[i] = p[2 * i];
            primes[n / 2 + i] = p[2 * i + 1];
        }
        if((n & 1) == 1)
            primes[n - 1] = p[n - 1];
        
        // System.out.println(Arrays.toString(primes));
        
        int k = nextInt();
        // long start = System.nanoTime();
       
        long large[] = new long[(int) 1.2e6];
        Arrays.fill(large, Long.MAX_VALUE);
        ptr = 0;
        brute(0, n / 2, 1, large);
        int szL = ptr;
        long small[] = new long[(int) 1e6];
        Arrays.fill(small, Long.MAX_VALUE);
        ptr = 0;
        brute(n / 2, n, 1, small);
        int szS = ptr;
        
        Arrays.sort(large);
        Arrays.sort(small);
        
        // println("large " + large.size());
        // println("small " + small.size());
        
        long lo = 1 , hi = MAX;
        long find = -1;
        while(lo <= hi) {
            long mid = (lo + hi) >> 1;
            long lessCnt = 0;
            for(int i = 0; i < szS; i++) {
                long other = mid / small[i];
                int bs = Arrays.binarySearch(large, 0, szL, other);
                lessCnt += Math.abs(bs + 1);
                if(Math.abs(bs + 1) == 0)
                    break;
            }
            
            if(lessCnt >= k) {
                hi = mid - 1;
                find = mid;
            }
            else
                lo = mid + 1;
        }
        
        println(find);
        // System.out.println("Time " + (System.nanoTime() - start) / 1e9);
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