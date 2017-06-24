import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class howmanyzeros {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static long numOfZeros[];
    static final int MAX_DIGIT = 10;
    static long pow10[];
    
    static long countZeros(long n) {
        
        if(n < 0)
            return 0;
        
        char str[] = String.valueOf(n).toCharArray();
        long cnt = 1;
        int zerosCnt = 0;
        
        for(int i = 1 , len = str.length; i < len - 1; i++)
            cnt += 9L * numOfZeros[len - i - 1];
        
        cnt += 1L * (str[0] - '0' - 1) * numOfZeros[str.length - 1];
        System.out.println("cnt0 " + cnt);
        for(int i = 1 , len = str.length; i < len; i++) {
            int dig = str[i] - '0';
            if(i < len - 1 && dig > 0) {
                cnt += 1L * dig * numOfZeros[len - i - 1];
                cnt += 1L * (zerosCnt + 1) * dig * pow10[len - i - 1];
            }
            else
                zerosCnt++;
        }
        
        return cnt + zerosCnt;
    }
    
    private static void solve() {
        
        numOfZeros = new long[MAX_DIGIT + 1];
        numOfZeros[1] = 1;
        pow10 = new long[MAX_DIGIT + 1];
        pow10[0] = 1;
        long pow9 = 9;
        for(int i = 2; i <= MAX_DIGIT; i++ , pow9 *= 9)
            numOfZeros[i] = 10 * numOfZeros[i - 1] + pow9;
        for(int i = 1; i <= MAX_DIGIT; i++)
            pow10[i] = pow10[i - 1] * 10;
        /*
        long L , R;
        while((L = nextLong()) != -1 && (R = nextLong()) != -1) {
            
        }
        */
        int x = nextInt();
        System.out.println(countZeros(x));
        
        int[] cnt = new int[1];
        IntStream.rangeClosed(0, x).
            forEach(i -> String.valueOf(i).chars()
                            .forEach(ch -> cnt[0] += ch == '0' ? 1 : 0));
        
        System.out.println("brute " + cnt[0]);
        
    }
    
    static char num[];
    static long memo[][][];
    static long count(int idx , int any , int numStarted) {
        if(idx == num.length)
            return 0;
        else if(memo[idx][any][numStarted] != -1)
            return memo[idx][any][numStarted];
        else if(any > 0)
            return memo[idx][any][numStarted] = 1 + 10L * count(idx + 1, any, numStarted);
        else {
            long cnt = 0;
            int d = num[idx] - '0';
            if(d == 0)
                cnt = numStarted + count(idx + 1, any, numStarted);
            else {
                cnt = numStarted + count(idx + 1, 1, numStarted); // for 0 
                cnt += 1L * (d - 1) * count(idx + 1, 1, 1);       // < d
                cnt += count(idx + 1, 0, 1);                      // = d
            }
            
            return memo[idx][any][numStarted] = cnt;
        }
    }
    
    private static void solve2() {
        
        
        
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