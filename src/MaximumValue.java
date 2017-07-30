import java.util.*;
import java.io.*;
public class MaximumValue {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class SparseTable1D {
        // < O(Nlog(N)) , O(1) > 0 based indexing
        int sparse[][];
        int len;

        static int log(int N) {
            return 31 - Integer.numberOfLeadingZeros(N);
        }

        SparseTable1D(int arr[]) {
            len = arr.length;
            int k = log(len) + 1;
            sparse = new int[k][len];
            for (int i = 0; i < len; i++)
                sparse[0][i] = arr[i];

            for (int i = 1; i < k; i++)
                for (int j = 0; j + (1 << i) <= len; j++)
                    sparse[i][j] = Math.max(sparse[i - 1][j], sparse[i - 1][j + (1 << (i - 1))]);

        }

        int getMax(int L, int R) {
            int sz = R - L + 1;
            int k = log(sz);
            int v1 = sparse[k][L];
            int v2 = sparse[k][L + (sz - (1 << k))];
            return Math.max(v1, v2);
        }

    }

    private static void solve() {
        
        final int MAX = (int) 1e6;
        int arr[] = new int[MAX + 1];
        Arrays.fill(arr, -1);
        
        int N = nextInt();
        while(N-->0) {
            int a = nextInt();
            arr[a] = a;
        }
        
        SparseTable1D sparseTable = new SparseTable1D(arr);
        int max = 0;
        for(int i = 1; i <= MAX; i++)
            if(arr[i] > 0) {
                int localMax = 0;
                for(int j = 2 * i; j <= MAX && localMax != i - 1; j += i) 
                    localMax = Math.max(localMax , sparseTable.getMax(j - i, j - 1) % i);
                
                max = Math.max(max , localMax);
            }
        
        println(max);
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