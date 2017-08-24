import java.util.*;
import java.io.*;
public class TheMeaninglessGame {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    // Sieve of Erathanoses
    public static boolean[] isPrimeArray(int N)  {
        boolean num[] = new boolean[N + 1];
        Arrays.fill(num, true);
        num[1] = num[0]=  false;
        for (int i = 2; i * i <= N; i++)
            if (num[i])  // i is prime
                for (int j = i * i; j <= N; j += i)
                    num[j] = false;
        
            
        return num;
    }
    // Sieve of Erathanoses dependency : isPrimeArray()
    public static int[] sieve(int N)  {
        
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
    
    static final int MAX = (int) Math.sqrt(1e9) + 10;
    static int primes[] = sieve(MAX);
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int A = nextInt();
            int B = nextInt();
            boolean flag = true;
            for(int p : primes) {
                
                if(!flag) break;
                
                if(A % p == 0 && B % p == 0) {
                    int v1 = 0 , v2 = 0;
                    while(A % p == 0) {
                        v1++;
                        A /= p;
                    }
                    while(B % p == 0) {
                        v2++;
                        B /= p;
                    }
                    flag &= (v1 + v2) % 3 == 0;
                    flag &= (2 * v1 - v2) % 3 == 0 && (2 * v2 - v1) % 3 == 0 &&
                            (2 * v1 - v2) / 3 >= 0 && (2 * v2 - v1) / 3 >= 0;
                }
            }
            
            flag &= A == B && A == 1;
            println(flag ? "Yes" : "No");
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