import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class MisterBandPRShifts {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[] brute(int arr[]) {
        int deviation[] = new int[arr.length];
        for(int k = 0; k < arr.length; k++)
            for(int i = 0; i < arr.length; i++)
                deviation[k] += Math.abs(i - arr[(i - k + arr.length) % arr.length]);
        return deviation;
    }
    
    static void rotate(int arr[] , int k) {
        int temp[] = new int[arr.length];
        System.arraycopy(arr, arr.length - k, temp, 0, k);
        System.arraycopy(arr, 0, temp, k, arr.length - k);
        pa(temp);
    }
    
    static void pa(int arr[]) {
        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.toString(IntStream.range(0, arr.length).toArray()));
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = Arrays.stream(nextIntArray(n)).map(a -> a - 1).toArray();
        int edgeCase[] = new int[n];    // arr[i] = 0 and n - 1
        
        for(int i = 0; i < n; i++) {
            if(arr[i] == 0) {
                for(int j = 0; j < n; j++)
                    edgeCase[j] += (i + j) % n;
            } else if(arr[i] == n - 1) {
                for(int j = 0; j < n; j++)
                    edgeCase[j] += Math.abs(n - 1 - ((i + j) % n));
            }
        }
        
        long deviation[] = new long[n];
        int inflection[] = new int[n];
        
        int inc = 0 , dec = 0;
        for(int i = 0; i < n; i++) {
            if(arr[i] != 0 && arr[i] != n - 1) {
                if(i - arr[i] >= 0)
                    inc++;
                else
                    dec++;
                deviation[0] += Math.abs(i - arr[i]);
                inflection[(arr[i] - i + n) % n]++;
            }
        }
        /*
        System.out.println("inflection");
        pa(inflection);
        pa(arr);
        */
        for(int i = 1; i < n; i++) {
            int diff = 0;
            if(arr[n - i] != 0 && arr[n - i] != n - 1) {
                inc--;
                diff = -(n - 1 - arr[n - i]) + arr[n - i];
            }

            // System.out.println("inc " + inc + " dec " + dec + " deviation i - 1 " + deviation[i - 1] + " diff " + diff);
            // rotate(arr, i);
            
            deviation[i] = deviation[i - 1] + diff + inc - dec;
            
            dec -= inflection[i];
            inc += inflection[i];
            
            if(arr[n - i] != 0 && arr[n - i] != n - 1) 
                dec++;
        }
        
        int id = 0;
        long minDeviation = Long.MAX_VALUE;
        /*
        System.out.println(Arrays.toString(deviation));
        System.out.println(Arrays.toString(edgeCase));
        System.out.println();
        System.out.println(Arrays.toString(brute(arr)));
        */
        for(int i = 0; i < n; i++)
            if(deviation[i] + edgeCase[i] < minDeviation) {
                minDeviation = deviation[i] + edgeCase[i];
                id = i;
            }
        
        println(minDeviation + " " + id);
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