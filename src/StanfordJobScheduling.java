import java.util.*;
import java.io.*;
public class StanfordJobScheduling {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long arr[][];
    
    static class DifferenceComparator implements Comparator<long[]> {
        @Override
        public int compare(long[] o1, long[] o2) {
            long d1 = o1[0] - o1[1];
            long d2 = o2[0] - o2[1];
            if(d1 != d2)
                return Long.compare(d2, d1);
            else
                return Long.compare(o2[0], o1[0]);
        }
    }
    
    static class RatioComparator implements Comparator<long[]> {
        @Override
        public int compare(long[] o1, long[] o2) {
            return o2[0] * o1[1] > o2[1] * o1[0] ? 1 : -1;
        }
    }
    
    private static void solve() {
        
        
        int N = nextInt();
        arr = new long[N][];
        for(int i = 0; i < N; i++)
            arr[i] = nextLongArray(2);
        
        
        Arrays.sort(arr , new DifferenceComparator());
//        Arrays.sort(arr, new RatioComparator());
        long weightedSum = 0;
        long completionTime = 0;
        for(long pair[] : arr) {
            completionTime += pair[1];
            weightedSum += pair[0] * completionTime;
        }
        
        println(weightedSum);
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