import java.util.*;
import java.io.*;
public class Enemyisweak {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class FenwickTree {

        /****************
         * DONT USE BIT IF YOU UPDATE INDEX 0 (causes infinite loop)
         ******************/

        int tree[];
        int len;

        FenwickTree(int len) {
            this.len = len;
            tree = new int[len + 10];
        }

        void update(int idx, int val) {
            if (idx == 0)
                throw new IndexOutOfBoundsException("BIT IS NOT ZERO INDEXED");
            for (; idx <= len; idx += (idx & -idx))
                tree[idx] += val;
        }

        int query(int idx) {
            int sum = 0;
            for (; idx > 0; idx -= (idx & -idx))
                sum += tree[idx];

            return sum;
        }

    }

    
    private static void solve() {
        
        int n = nextInt();
        TreeSet<Integer> set = new TreeSet<>();
        
        int arr[] = nextIntArray(n);
        Arrays.stream(arr).forEach(set::add);
        
        int m = set.size();
        
        HashMap<Integer , Integer> map = new HashMap<>();
        set.stream().forEach(num -> map.put(num, map.size() + 1));
        
        FenwickTree left = new FenwickTree(m);
        FenwickTree right = new FenwickTree(m);
        left.update(map.get(arr[0]), 1);
        
        for(int i = 1; i < n; i++)
            right.update(map.get(arr[i]), 1);
        
        long weakness = 0;
        for(int i = 1; i < n - 1; i++) {
            int x = map.get(arr[i]);
            right.update(x, -1);
            weakness += 1L * right.query(x - 1) * (left.query(m) - left.query(x));
            left.update(x, 1);
        }
        
        println(weakness);
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