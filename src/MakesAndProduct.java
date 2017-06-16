import java.util.*;
import java.io.*;
public class MakesAndProduct {
    
    
    
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

    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        shuffleArray(arr);
        Arrays.sort(arr);
        
        int min1 = arr[0] , min2 = arr[1] , min3 = arr[2];
        long cnt1 = 0 , cnt2 = 0 , cnt3 = 0;
        for(int a : arr) {
            if(a == min1)      cnt1++;
            else if(a == min2) cnt2++;
            else if(a == min3) cnt3++;
        }
        long cnt = 0;
        if(min1 == min3)    // All 3 are same
            cnt = (cnt1 * (cnt1 - 1) * (cnt1 - 2)) / 6;
        else if(min1 == min2)
            cnt = cnt3;
        else if(min2 == min3)
            cnt = (cnt2 * (cnt2 - 1)) / 2;
        else
            cnt = cnt3;
        
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