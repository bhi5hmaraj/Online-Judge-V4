import java.util.*;
import java.io.*;
public class Hometask {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        char str[] = nextLine().toCharArray();
        int k = nextInt();
        char map[] = new char[26];
        while(k-->0) {
            String pair = nextLine();
            map[pair.charAt(0) - 'a'] = pair.charAt(1);
            map[pair.charAt(1) - 'a'] = pair.charAt(0);
        }
        
        int cnt = 0;
        char curr = str[0];
        int f1 = 1 , f2 = 0;
        for(int i = 1; i < str.length; i++) {
            if(str[i] == curr) 
                f1++;
            else if(str[i] == map[curr - 'a'])
                f2++;
            else {
                cnt += Math.min(f1, f2);
                f1 = 0;
                f2 = 0;
                curr = str[i];
                f1++;
            }
        }
        cnt += Math.min(f1, f2);
        println(cnt);
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