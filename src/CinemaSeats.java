import java.util.*;
import java.io.*;
public class CinemaSeats  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        char str[] = nextLine().toCharArray();
        int N = str.length;
        ArrayList<Integer> compress = new ArrayList<>();
        int one = 0;
        for(int i = 0; i < N;) {
            char ch = str[i];
            int cnt = 0;
            one += ch == '0' ? 1 : 0;
            while(i < N && str[i] == ch) {
                i++;
                cnt++;
            }
            compress.add(cnt);
        }
        
        int max = 0;
        // println(compress);
        if(compress.size() == 1)
            println(one == 1 ? N : 0);
        else {
            for(int i = str[0] == '1' ? 0 : 1; i < compress.size(); i += 2) 
                if(compress.get(i) == 1 && i > 0 && i < compress.size() - 1) 
                    max = Math.max(max , compress.get(i - 1) + compress.get(i + 1) + (one > 2 ? 1 : 0));
            
            for(int i = str[0] == '0' ? 0 : 1; i < compress.size(); i += 2)
                max = Math.max(max , compress.get(i) + (one > 1 ? 1 : 0));
            
            println(max);
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