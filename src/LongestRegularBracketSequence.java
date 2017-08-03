import java.util.*;
import java.util.stream.IntStream;
import java.io.*;
public class LongestRegularBracketSequence {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        char str[] = nextLine().toCharArray();
        new String(str).chars().forEach(ch -> print(String.format("%3s ", (char) ch)));
        int n = str.length;
        print('\n');
        IntStream.range(0, n).forEach(i -> print(String.format("%3d ", i)));
        print('\n');
        ArrayDeque<Integer> opening = new ArrayDeque<>();
        int DP[] = new int[n];
        
        for(int i = 0; i < n; i++) {
            if(str[i] == '(')
                opening.push(i);
            else if(!opening.isEmpty()) {
                int pos = opening.pop();
                DP[i] = (i - pos + 1) + (i > 0 ? DP[i - 1] : 0);
            }
            println(i + " " + opening);
        }
        new String(str).chars().forEach(ch -> print(String.format("%3s ", (char) ch)));
        print('\n');
        IntStream.range(0, n).forEach(i -> print(String.format("%3d ", i)));
        print('\n');
        
        println(Arrays.toString(DP));
        int max = 0 , cnt = 1;
        for(int dp : DP) {
            if(dp == max)
                cnt++;
            else if(dp > max) {
                max = dp;
                cnt = 1;
            }
        }
        
        println(max + " " + cnt);    
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