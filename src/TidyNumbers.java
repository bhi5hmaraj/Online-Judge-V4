import java.util.*;
import java.io.*;
public class TidyNumbers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long N;
    static int lenOfN;
    static int digit[];
    static long memo[][][];
    static int steps;
    static long rec(int idx , int prevDigit , int borrow) {
        steps++;
        if(idx == 0) 
            return Math.min(digit[0] - borrow,prevDigit);
        else if(memo[idx][prevDigit][borrow] != -1)
            return memo[idx][prevDigit][borrow];
        else {
            long max = 0;
            for(int i=0;i<=prevDigit;i++) {
                long curr;
                if(i <= digit[idx] - borrow)
                    curr = (10L * rec(idx - 1, i, 0)) + i;
                else
                    curr = (10L * rec(idx - 1, i, 1)) + i;
                
                max = Math.max(max,curr);
            }
            
            // System.out.println("idx = " + idx + " prevDig = " + prevDigit + " max = " + max + " borrow = " + borrow);
            return memo[idx][prevDigit][borrow] = max;
        }
    }

    private static void solve() {
        
        int T = nextInt();
        
        for(int tc = 1;tc <= T;tc++) {
            steps = 0;
            lenOfN = 0;
            N = nextLong();
            for(long temp = N;temp > 0;temp /= 10) 
                lenOfN++;
            
            digit = new int[lenOfN];
            int ptr = 0;
            for(long temp = N;temp > 0;temp /= 10)
                digit[lenOfN - ptr++ - 1] = (int) (temp % 10);
            
            
            memo = new long[lenOfN][10][2];
            for(long a[][] : memo)
                for(long b[] : a)
                    Arrays.fill(b, -1);
            
            println("Case #" + tc + ": " + rec(lenOfN - 1, 9 , 0));
            // System.err.println("STEPS = " + steps);
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
 
/*    static String outputFile = "output_tidy_toCheck.txt";
    public static void main(String []args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt")));
        writer = 
                new PrintWriter(outputFile); 
        solve();
        reader.close();
        writer.close();
    }     */
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