import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class howmanyzeros {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static long numOfZeros[];
    static final int MAX_DIGIT = 10;
    static long pow10[];
    
    static {
        numOfZeros = new long[MAX_DIGIT + 1];
        numOfZeros[1] = 1;
        long pow10 = 10;
        for(int i = 2; i <= MAX_DIGIT; i++ , pow10 *= 10)
            numOfZeros[i] = 10 * numOfZeros[i - 1] + pow10;
    }
    
    static long countZeros(long n) {
        
        if(n < 0)
            return 0;
        
        char str[] = String.valueOf(n).toCharArray();
        long cnt = 1;
        int zerosCnt = 0;
        
        for(int i = 1 , len = str.length; i < len - 1; i++)
            cnt += 9L * numOfZeros[len - i - 1];
        
        int i = 0;
        cnt += 1L * (str[i++] - '0' - 1) * numOfZeros[str.length - 1];
        // System.out.println("cnt0 " + cnt);
        for(int len = str.length; i < len - 1; i++) {
            int dig = str[i] - '0';
            if(dig > 0) {
                cnt += 1L * (dig) * numOfZeros[len - i - 1];    // fix 0 to d-1 in i
                cnt += 1L * (zerosCnt * (dig - 1))  * pow10[len - i - 1];   // for 1 to d - 1
                cnt += 1L * (zerosCnt + 1) * pow10[len - i - 1];    // for 0
            }
            else
                zerosCnt++;
            // System.out.println("i " + i + " zerocnt " + zerosCnt + " cnt " + cnt);
        }
        // System.out.println("before last " + cnt);
        return i < str.length ? cnt + (zerosCnt * (str[str.length - 1] - '0' + 1)) + 1 : cnt;
    }
    
    private static void solve() {
        pow10 = new long[MAX_DIGIT + 1];
        pow10[0] = 1;
        for(int i = 1; i <= MAX_DIGIT; i++)
            pow10[i] = pow10[i - 1] * 10;
        
        long L , R;
        while((L = nextLong()) != -1 && (R = nextLong()) != -1) 
            println(countZeros(R) - countZeros(L - 1));
        
        
        /*
        int x = nextInt();
        System.out.println(countZeros(x));
        
        int[] cnt = new int[1];
        IntStream.rangeClosed(0, x).
            forEach(i -> String.valueOf(i).chars()
                            .forEach(ch -> cnt[0] += ch == '0' ? 1 : 0));
        
        System.out.println("brute " + cnt[0]);
        */
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