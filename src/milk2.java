/*
ID: bs3861
LANG: JAVA
TASK: milk2
*/
import java.util.*;
import java.io.*;
public class milk2 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int N = nextInt();
        int arr[][] = new int[2*N][2];    // start = -1 , end = 1
        for(int i=0;i<N;i++){
            arr[2*i][0] = nextInt();
            arr[2*i][1] = -1;
            arr[2*i + 1][0] = nextInt();
            arr[2*i + 1][1] = 1;
        }
            
        Arrays.sort(arr, (a1 , a2) -> a1[0] != a2[0] ? a1[0] - a2[0] : a1[1] - a2[1]);
        /*
        for(int a[] : arr)
            System.out.print(Arrays.toString(a) + " ");
        */
        int lastStart = -1 , lastEnd = -1;
        int maxUsed = 0 , maxUnused = 0;
        int curr = 0;
        for(int pair[] : arr) {
            if(curr == 0) {
                lastStart = pair[0];
                if(lastEnd != -1)
                    maxUnused = Math.max(maxUnused,lastStart - lastEnd);
            }
            curr += -1 * pair[1];
            if(curr == 0) {
                lastEnd = pair[0];
                maxUsed = Math.max(maxUsed,lastEnd - lastStart);
            }
        }
        
        println(maxUsed + " " + maxUnused);
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    static String file = "milk2";
    static final boolean OJ = false;
    
    public static void main(String[] args) throws IOException {
        if(!OJ) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        }
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file + ".in")));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")), false);
        }
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