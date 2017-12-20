import java.util.*;
import java.io.*;
public class AlmostDifference {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static void compress(int arr[]) {
        TreeSet<Integer> set = new TreeSet<>();
        Arrays.stream(arr).forEach(set::add);
        inv= new int[set.size() + 1];
        int ptr = 1;
        for(int a : set) inv[ptr++] = a;
        
        for(int i = 0; i < arr.length; i++)
            arr[i] = Arrays.binarySearch(inv, arr[i]);
        
    }
    

    static class FenwickTree {

        /****************
         * DONT USE BIT IF YOU UPDATE INDEX 0 (causes infinite loop)
         ******************/

        long valTree[];
        int cntTree[];
        int len;

        FenwickTree(int len) {
            this.len = len;
            valTree = new long[len + 10];
            cntTree = new int[len + 10];
        }

        void update(int idx) {
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx)) {
                valTree[idx] += inv[idx];
                cntTree[idx] += 1;
            }
        }

        int queryCnt(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx)) 
                sum += cntTree[idx];

            return sum;
        }

        long queryVal(int idx) {
            long sum = 0;
            for (; idx > 0; idx -= (idx & -idx)) 
                sum += valTree[idx];

            return sum;
        }
        
    }

    static int inv[];
    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        compress(arr);
        
        FenwickTree BIT = new FenwickTree(inv.length);
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(inv));
        
        println(
        Arrays.stream(arr).mapToLong(y -> {
            long sumOfVal = BIT.queryVal(y - 2) + BIT.queryVal(inv.length) - BIT.queryVal(y + 1);
            int cntOfVal = BIT.queryCnt(y - 2) + BIT.queryCnt(inv.length) - BIT.queryCnt(y + 1);
            BIT.update(y);
            return 1L * inv[y] * cntOfVal - sumOfVal;
        }).reduce(Long::sum).
        getAsLong());
        
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