import java.util.*;
import java.io.*;
public class QualificationRounds {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int k = nextInt();
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < n; i++) {
            int val = 0;
            for(int j = 0; j < k; j++)
                val = (val << 1) | nextInt();
            set.add(val);
        }
        
        int unique[] = new int[set.size()];
        int size = 0;
        for(int v : set)
            unique[size++] = v;
        
        boolean flag = false;
        
        for(int mask = 1; mask < (1 << size); mask++) {
            int cnt[] = new int[k];
            int total = 0;
            boolean poss = true;
            for(int j = 0; j < size; j++) {
                if((mask & (1 << j)) != 0) {
                    total++;
                    for(int p = 0; p < k; p++)
                        if((unique[j] & (1 << p)) != 0)
                            cnt[k - p - 1]++;
                }
            }
            
            for(int i = 0; i < k; i++)
                poss &= cnt[i] <= total / 2;
            
            flag |= poss;
        }
        
        println(flag ? "YES" : "NO");
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