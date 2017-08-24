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
    
    static HashMap<Integer , Integer> primeFactorize(int n) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        for(int p : primes) {
            int cnt = 0;
            if(n == 1)
                break;
            while(n % p == 0) {
                cnt++;
                n /= p;
            }
            if(cnt > 0)
                freq.put(p, cnt);
        }
        
        if(n > 1)
            freq.put(n, 1);
        
        return freq;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int A = nextInt();
            int B = nextInt();
            HashMap<Integer, Integer> a = primeFactorize(A);
            HashMap<Integer, Integer> b = primeFactorize(B);
            HashMap<Integer, Integer> merge = new HashMap<>();
            a.forEach((k , v) -> merge.put(k, v + b.getOrDefault(k, 0)));
            b.forEach((k , v) -> {
                if (!merge.containsKey(k)) 
                    merge.put(k, v);   
            });
            
            println(a);
            println(b);
            println(merge);
            
            
            boolean flag = true;
            for(Map.Entry<Integer, Integer> e : merge.entrySet())
                flag &= e.getValue() % 3 == 0;
            
            long min = Math.min(A , B);
            long max = Math.max(A , B);
            flag &= max <= min * min;
            
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