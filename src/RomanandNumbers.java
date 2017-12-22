import java.util.*;
import java.io.*;
public class RomanandNumbers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int mod;
    static int digits[];
    static long memo[][];
    
    static long rec(int mask , int sum) {
        if(Integer.bitCount(mask) == digits.length) 
            return sum == 0 ? 1 : 0;
        else if(memo[mask][sum] != -1)
            return memo[mask][sum];
        else {
            long ways = 0;
            int used = 0;
            for(int i = 0; i < digits.length; i++)
                if((mask & (1 << i)) == 0) {
                    if(mask == 0 && digits[i] == 0) // first digit is 0
                        continue;
                    if((used & (1 << digits[i])) != 0) // digit already used
                        continue;
                    
                    used |= 1 << digits[i]; 
                    ways += rec(mask | (1 << i), (sum * 10 + digits[i]) % mod);
                }
            
            return memo[mask][sum] = ways;
        }
    }
    
    private static void solve() {
        
        long n = nextLong();
        mod = nextInt();
        digits = String.valueOf(n).chars().map(Character::getNumericValue).toArray();
        
        memo = new long[1 << digits.length][mod];
        for(long t[] : memo) Arrays.fill(t, -1);
        
        println(rec(0, 0));
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