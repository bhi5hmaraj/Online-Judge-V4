import java.util.*;
import java.io.*;
public class PetyaandExam {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean match(String pat , String text) {
        // System.out.println("pat " + pat + " text " + text);
        for(int i = 0; i < text.length(); i++)
                if(!(text.charAt(i) == pat.charAt(i) || (pat.charAt(i) == '?' && g[text.charAt(i) - 'a']))) 
                    return false;
        
        return true;
    }
    static boolean g[];
    private static void solve() {
        
        String good = nextLine();
        String pat = nextLine();
        int pos = pat.indexOf('*');
        String prefix = null , suffix = null , without = null;
        if(pos >= 0) {
            prefix = pat.substring(0, pos);
            suffix = pat.substring(pos + 1);
            without = prefix + suffix;
        }
        g = new boolean[26];
        good.chars().forEach(ch -> g[ch - 'a'] = true);
        
        int Q = nextInt();
        while(Q-->0) {
            String text = nextLine();
            boolean flag = false;
            if(text.length() == pat.length() && pos < 0) 
                flag = match(pat, text);
            else if(text.length() >= pat.length() && pos >= 0) {
                flag = match(prefix, text.substring(0, pos)) && 
                       match(suffix, text.substring(text.length() - suffix.length(), text.length()));
                for(int i = pos; i < text.length() - suffix.length(); i++)
                    flag &= !g[text.charAt(i) - 'a'];
            }
            else if(pos >= 0 && text.length() == pat.length() - 1)
                flag = match(without, text);
            println(flag ? "YES" : "NO");
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