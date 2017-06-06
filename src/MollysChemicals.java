import java.util.*;
import java.io.*;
public class MollysChemicals {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        long MAX = (long) 1e14;
        ArrayList<Long> possiblePow = new ArrayList<>();
        
        int N = nextInt();
        long K = nextLong();
        long arr[] = nextLongArray(N);
        
        possiblePow.add(1L);
        long pow = K;
        
        while(Math.abs(pow) <= MAX) {
            if(pow == 1) break;
            possiblePow.add(pow);
            pow *= K;
        }
        
        HashMap<Long , Integer> freq = new HashMap<>();
        freq.put(0L, 1);
        long cnt = 0;
        long prefixSum = 0;
        
        for(long a : arr) {
            prefixSum += a;
            
            for(long KPow : possiblePow)
                cnt += freq.getOrDefault(prefixSum - KPow, 0);
            
            freq.put(prefixSum, freq.getOrDefault(prefixSum, 0) + 1);
        }
        
        println(cnt);
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