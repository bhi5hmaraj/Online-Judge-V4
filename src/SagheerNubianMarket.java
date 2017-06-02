import java.util.*;
import java.io.*;
public class SagheerNubianMarket {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class Pair  {
        int idx;
        long cost;
        Pair(int id , long c) {
            idx = id;
            cost = c;
        }
    }
    
    private static void solve() {
        
        
        int N = nextInt();
        long S = nextLong();
        
        Pair arr[] = new Pair[N];
        for(int i = 0; i < N; i++)
            arr[i] = new Pair(i, nextLong());
        
        
        int lo = 0 , hi = N - 1;
        int opt = 0;
        long cost = 0;
        
        while(lo <= hi) {
            int mid = (lo + hi) / 2;
            long sum = 0;
            
            Arrays.sort(arr, new Comparator<Pair>() {
                @Override
                public int compare(Pair o1, Pair o2) {
                    long left = o1.cost - o2.cost;
                    long right = 1L * (mid + 1) * (o2.idx - o1.idx);
                    return Long.compare(left, right);
                }
            });
            
            
            for(int i = 0; i <= mid; i++)
                sum += (arr[i].cost + (1L * (arr[i].idx + 1) * (mid + 1)));
            
            if(sum <= S) {
                opt = mid + 1;
                cost = sum;
                lo = mid + 1;
            }
            else
                hi = mid - 1;
        }
        
        println(opt + " " + cost);
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