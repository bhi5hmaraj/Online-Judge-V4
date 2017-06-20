import java.util.*;
import java.io.*;
public class RestoreGraph {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        long k = nextInt();
        
        ArrayList<Integer> freq[] = new ArrayList[n];
        for(int i = 0; i < n; i++)
            freq[i] = new ArrayList<>();
        int maxDist = 0;
        
        for(int i = 1; i <= n; i++) {
            int d = nextInt();
            freq[d].add(i);
            maxDist = Math.max(maxDist , d);
        }
        
        for(int i = 0; i <= maxDist; i++)
            if(freq[i].size() == 0) {
                println(-1);
                return;
            }
        
        int E = 0;
        for(int i = 0; i < maxDist; i++)
            if(1L * k * freq[i].size() < 1L * freq[i + 1].size()) {
                E += freq[i + 1].size();
                println(-1);
                return;
            }
        
        println(E);
        for(int i = 1 , szFrom = freq[i - 1].size(); i <= maxDist; i++)
            for(int j = 0 , szTo = freq[i].size(); j < szTo; j++) {
                E++;
                println(freq[i - 1].get(j % szFrom) + " " + freq[i].get(j));
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