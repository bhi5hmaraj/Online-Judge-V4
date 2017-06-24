import java.util.*;
import java.io.*;
import java.math.BigInteger;
public class ConsecutivePrimeSum {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    public static boolean[] isPrimeArray(int N) // Sieve of Erathanoses
    {
        boolean num[] = new boolean[N + 1];
        Arrays.fill(num, true);
        num[1] = num[0]=  false;
        for (int i = 2; i * i <= N; i++)
            if (num[i])  // i is prime
                for (int j = i * i; j <= N; j += i)
                    num[j] = false;
        
            
        return num;
    }
    
    public static int[] sieve(int N) // Sieve of Erathanoses dependency : isPrimeArray()
    {
        
        boolean isPrime[] = isPrimeArray(N);
        int sz = 0;
        for(boolean b : isPrime)
            sz += b ? 1 : 0;
        int arr[] = new int[sz];
        int ptr = 0;
        for (int i = 2; i <= N; i++)
            if (isPrime[i])
                arr[ptr++] = i;
                
        return arr;
    }

    
    private static void solve() {
        
        long n = nextLong();
        final int MAX = (int) 7e5;
        int primes[] = sieve(MAX);
        // long s2 = System.nanoTime(); // Used for timing
        long sum = 5;
        int last;
        int cnt = 0;
        
        for(last = 2; last < primes.length && sum <= n; sum += primes[last] , last++) {
            
            
            if(BigInteger.valueOf(sum).isProbablePrime(10)) 
                cnt++;
            
            
            /*
             * sqrt approach
            boolean flag = true;
            for(long fac = 2; fac * fac <= sum; fac++)
                if(sum % fac == 0) {
                    flag = false;
                    break;
                }
            
            if(flag)
                cnt++;
            */
        }
        /*
        System.out.println("Time : " + (System.nanoTime() - s2) / 1e9);
        */
        print(cnt);
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