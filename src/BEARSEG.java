/************************         ௳            ************************/
import java.util.*;
import java.io.*;
class BEARSEG {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long sub(long a , long b , long mod) {
        return (a - b + mod) % mod;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            long mod = nextLong();
            long arr[] = nextLongArray(N);
            TreeSet<Long> prefixSums = new TreeSet<>();
            long currSum = 0;
            prefixSums.add(currSum);
            long max = 0;
            
            for(long num : arr) {
                currSum = (currSum + num) % mod;
                long smallest = prefixSums.first();
                max = Math.max(max,sub(currSum, smallest, mod));
                Long higher = prefixSums.higher(currSum);
                if(higher != null)
                    max = Math.max(max,sub(currSum, higher, mod));
                prefixSums.add(currSum);
            }
            
            long cnt = 0;
            currSum = 0;
            HashMap<Long , Long> freq = new HashMap<>();
            freq.put(currSum, 1L);
            for(long num : arr) {
                currSum = (currSum + num) % mod;
                cnt += freq.getOrDefault(sub(currSum, max, mod), 0L);
                freq.put(currSum, freq.getOrDefault(currSum, 0L) + 1);
            }
            
            println(max + " " + cnt);
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