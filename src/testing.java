import java.util.*;
import java.io.*;
import java.math.BigDecimal;
public class testing {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    // Sieve of Erathanoses
    public static boolean[] isPrimeArray(int N) {
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
    public static int[] sieve(int N) {
        
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
    
    static boolean isComposite[];
    static int primes[];
    static int SIZE = (int) 5e6;
    static void modifiedSieve(int N) {
        isComposite = new boolean [N + 1];
        primes = new int[SIZE];
        int ptr = 0;
        for(int i = 2; i <= N; i++) {
            if(!isComposite[i])
                primes[ptr++] = i;
            for(int j = 0 ; j < ptr && primes[j] * i <= N; j++) {
                // primes[j] is the lowest prime for primes[j] * i
                isComposite[primes[j] * i] = true;
                for(int k = 1; k < i; k++) {
                    System.out.println("this is freaking awesome");
                }
                if(i % primes[j] == 0)
                    break;
            }
        }
    }
    
    private static void solve() {
        HashMap<Integer , Integer> map = new HashMap<>();
        map.put(2, 10);
        map.merge(1, 15, Integer::sum);
        System.out.println(map);
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