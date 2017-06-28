import java.util.*;
import java.io.*;
public class MisterBandBoringGame  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int a = nextInt();
        int b = nextInt();
        int L = nextInt();
        int R = nextInt();
        
        String greedy = "";
        for(char ch = 'a'; ch <= 'a' + a - 1; ch++) greedy += ch; 
        for(int i = 0; i < b; i++) greedy += greedy.charAt(a - 1);
        greedy += greedy.substring(0, Math.min(b , a - 1));
        for(int i = 0; i < Math.max(a - b , 1); i++) greedy += (char) ('a' + a + i);
        for(int i = 0; i < b; i++) greedy += greedy.charAt(2* a + b - 1);
        
         println(greedy);
        
        boolean alph[] = new boolean[26];
        if(R - L + 1 >= greedy.length()) 
            greedy.chars().forEach(ch -> alph[ch - 'a'] = true);
        else {
            R--;
            L--;
            int gLen = greedy.length();
            for(int i = L; i <= (R >= L ? R : R + gLen); i++)
                alph[greedy.charAt(i % gLen) - 'a'] = true;
        }
        
        int unique = 0;
        for(boolean bool : alph) unique += bool ? 1 : 0;
        
        println(unique);

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