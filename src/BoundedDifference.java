import java.util.*;
import java.io.*;
public class BoundedDifference {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static void swap(int i , int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    
    static boolean check(int i , int j ) {
        swap(i, j);
        boolean b = check(i) && check(j);
        swap(i, j);
        return b;
    }
    static boolean check(int i) {
        boolean b = true;
        b &= !(i > 0) || Math.abs(arr[i] - arr[i - 1]) <= k;
        b &= !(i < n - 1) || Math.abs(arr[i] - arr[i + 1]) <= k;
        return b;
    }
    
    static int arr[] , k , n;
    static int prefix[];
    static boolean full(int i , int j) {
        if(i > j) return true;
        
        return (prefix[j + 1] - prefix[i]) == (j - i + 1);
    }
    private static void solve() {
        
        n = nextInt();
        k = nextInt();
        arr = nextIntArray(n);
        prefix = new int[n];
        for(int i = 0; i < n - 1; i++)
            prefix[i + 1] = prefix[i] + (Math.abs(arr[i] - arr[i + 1]) <= k ? 1 : 0);
        
        
        for(int i = 0; i < n - 1; i++) {
            if(Math.abs(arr[i] - arr[i + 1]) > k) {
                int p = i + 1;
                for(int j = 0; j < n; j++)
                    if(j != p) {
                        int min = Math.min(j , p);
                        int max = Math.max(j , p);
                        if(check(min , max) && full(min + 1, max - 2) && full(max + 2, n - 2)) {
                            println((min + 1) + " " + (max + 1));
                            return;
                        }
                    }
                println(-1);
                return;
            }
        }
        
        println(0);
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