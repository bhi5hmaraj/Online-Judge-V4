import java.util.*;
import java.io.*;
public class Shockers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int Q = nextInt();
        HashSet<Integer> set = new HashSet<>();
        HashSet<Integer> noProb = new HashSet<>();
        int prevent = 0;
        boolean found = false;
        while (Q-->0) {
            found |= set.size() == 1 || noProb.size() == 25;
            char ch = nextChar();
            String str = next();
            switch(ch) {
            case '!':
                if(found)
                    prevent++;
                else if(set.size() == 0) 
                    str.chars().forEach(set::add);
                else {
                    HashSet<Integer> ns = new HashSet<>();
                    str.chars().filter(set::contains).forEach(ns::add);
                    set = ns;
                }
                noProb.stream().forEach(set::remove);
                break;
            case '?':   // first use of fall through
                if(found)
                    prevent++; 
            case '.':
                str.chars().forEach(set::remove);
                str.chars().forEach(noProb::add);
                break;
            }
        }
        

        println(Math.max(0 , prevent - 1));
        
        
        
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