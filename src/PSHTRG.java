import java.util.*;
import java.io.*;
class PSHTRG  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int N = nextInt();
        int Q = nextInt();
        
        if(!(N <= 100 && Q <= 100)) {
            throw new RuntimeException("work in progress...");
        }
        
        int arr[] = nextIntArray(N);
        while(Q-->0) {
            
            if(nextInt() == 1)
                arr[nextInt()] = nextInt();
            else {
                int L = nextInt() - 1;
                int R = nextInt() - 1;
                int newArr[] = Arrays.copyOfRange(arr, L, R + 1);
                Arrays.sort(newArr);
                long perimeter = 0;
                int ptr = newArr.length - 1;
                for(; ptr >= 2 && newArr[ptr - 1] + newArr[ptr - 2] < newArr[ptr]; ptr--)
                    ;
                
                perimeter = ptr >= 2 ? 0L + newArr[ptr] + newArr[ptr - 1] + newArr[ptr - 2] : 0;
                println(perimeter);
            }
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