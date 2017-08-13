import java.util.*;
import java.io.*;
public class AnagramSearch {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        char s[] = nextLine().toCharArray();
        char p[] = nextLine().toCharArray();
        
        int n = s.length;
        int m = p.length;
        if(m > n)
            println(0);
        else {
            int f1[] = new int[128];
            int f2[] = new int[128];
            for(int i = 0; i < m; i++) {
                f1[s[i]]++;
                f2[p[i]]++;
            }
            boolean flag = true;
            int delta = 0;
            for(int i = 0; i < 128; i++) {
                flag &= f1[i] <= f2[i];
                delta += f2[i] - f1[i];
            }
            int cnt = flag && f1['?'] == delta ? 1 : 0;
            for(int i = m; i < n; i++) {
                f1[s[i]]++;
                f1[s[i - m]]--;
                flag = true;
                delta = 0;
                for(int j = 0; j < 128; j++) {
                    flag &= f1[j] <= f2[j];
                    delta += f2[j] - f1[j];
                }
                cnt += flag && f1['?'] == delta ? 1 : 0;
            }
            println(cnt);
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