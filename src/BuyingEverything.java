import java.util.*;
import java.io.*;
public class BuyingEverything {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static ArrayList<Integer> pos;
    static int k , m;
    static long memo[][];
    
    static final long INF = (long) 1e16;
    static long findOpt(int idx , int rem) {
        if(rem == 0)
            return 0;
        else if(memo[idx][rem] != -1)
            return memo[idx][rem];
        else {
            int bought = m - rem;
            long min = INF;
            for(int i = idx + 1; i + rem <= pos.size(); i++) {
                long cost = 1L * (pos.get(i) - pos.get(idx)) * (bought == 0 ? 1 : 1L * bought * k);
                long nextVal = findOpt(i, rem - 1);
                if(nextVal < INF)
                    min = Math.min(min , cost + nextVal);
            }
            return memo[idx][rem] = min;
        }
    }
    
    
    private static void solve() {
        
        
        int n = nextInt();
        m = nextInt();
        k = nextInt();
        
        int b[] = nextIntArray(n);
        pos = new ArrayList<>();
        pos.add(0);
        for(int i = 0; i < n; i++)
            if(b[i] == 1)
                pos.add(i);
        
        memo = new long[pos.size()][m + 1];
        for(long t[] : memo)
            Arrays.fill(t, -1);
        
        long ret = findOpt(0, m);
        println(ret == INF ? -1 : ret);
    }
    
    private static void solve2() {
        
        
        
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        new Thread(null, new Runnable() {
            public void run() {
                new BuyingEverything().run();
            }
        }, "Increase Stack", 1 << 25).start();

    }

    void run(){ 
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve();
        try {
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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