import java.util.*;
import java.io.*;
public class ChessMatch  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }
    
    static int a[] , b[];
    
    static boolean check(int[] aa , int bb[] , int start1 , int start2 , int len) {
        for(int i = 0; i < len; i++)
            if(aa[start1 + i] < bb[start2 + i])
                return false;
        return true;
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int a[] = nextIntArray(n);
        int b[] = nextIntArray(n);
        
        shuffleArray(a);
        shuffleArray(b);
        
        Arrays.sort(a);
        Arrays.sort(b);
        
        int thresh = n / 2 + 1;
        int aWin = check(a, b, n - thresh, 0, thresh) ? 1 : 0; 
        int bWin = check(b, a, n - thresh, 0, thresh) ? 1 : 0;
        
        println(new String[]{"None" , "Second" , "First" , "Both"}[(aWin << 1) | bWin]);
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