import java.util.*;
import java.io.*;
public class BathroomStalls {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Pair implements Comparable<Pair> {
        int index , L , R;
        int max , min;
        Pair(int index , int L , int R) {
            this.index = index;
            this.L = L;
            this.R = R;
            max = Math.max(L,R);
            min = Math.min(L,R);
        }
        
        @Override
        public int compareTo(Pair o) {
            if(min != o.min)
                return o.min - min;
            else if(max != o.max)
                return o.max - max;
            else
                return index - o.index;
        }
        @Override
        public String toString() {
            return String.format("[idx = %d L = %d R = %d]", index , L , R);
        }
    }
    
    static Pair getPair(int leftOcc , int rightOcc) {
        int mid = (leftOcc + rightOcc) / 2;
        return new Pair(mid, mid - leftOcc - 1, rightOcc - mid - 1);
    }
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            int N = nextInt();
            int K = nextInt();
            PriorityQueue<Pair> pq = new PriorityQueue<>();
            pq.add(getPair(0, N + 1));
            K--;
            // long start = System.nanoTime();
            while(K-->0) {
                // println(pq);
                Pair curr = pq.remove();
                pq.add(getPair(curr.index - curr.L - 1, curr.index));
                pq.add(getPair(curr.index, curr.index + curr.R + 1));
            }
            // println(pq);
            // long stop = System.nanoTime();
            // System.err.println("Time = " + (stop - start) / 1e9);
            
            println("Case #" + tc + ": " + pq.peek().max + " " + pq.peek().min);
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
/*    
    static String outputFile = "output_bathroomStalls.txt";
    public static void main(String []args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        writer = 
                new PrintWriter(outputFile); 
        solve();
        reader.close();
        writer.close();
    }     */
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