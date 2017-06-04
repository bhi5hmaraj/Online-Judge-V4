import java.util.*;
import java.io.*;
class UNIONSET {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            int K = nextInt();
            BitSet set[] = new BitSet[N];
            for(int i = 0; i < N; i++) {
                set[i] = new BitSet(K);
                int len = nextInt();
                while(len-->0)
                    set[i].set(nextInt() - 1);
            }
            
            Arrays.sort(set, (b1 , b2) -> b2.cardinality() - b1.cardinality());
            
            int cnt = 0;
            for(int i = 0; i < N && set[i].cardinality() >= (K + 1) / 2; i++)
                for(int j = i + 1; j < N; j++) {
                    BitSet temp = (BitSet) set[i].clone();
                    temp.or(set[j]);
                    cnt += temp.cardinality() == K ? 1 : 0;
                }
            
            println(cnt);
        }
        
    }
    
  private static void solve2() {
        
        int T = nextInt();
        while(T-->0) {
            
            int N = nextInt();
            int K = nextInt();
            BitSet set[] = new BitSet[N];
            for(int i = 0; i < N; i++) {
                set[i] = new BitSet(K);
                int len = nextInt();
                while(len-->0)
                    set[i].set(nextInt() - 1);
            }
            
            int cnt = 0;
            for(int i = 0; i < N; i++)
                for(int j = i + 1; j < N; j++) {
                    BitSet temp = (BitSet) set[i].clone();
                    temp.or(set[j]);
                    cnt += temp.cardinality() == K ? 1 : 0;
                }
            
            println(cnt);
        }
        
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
//        reader = new BufferedReader(new InputStreamReader(System.in));
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("UNIONSET_IN.txt")));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        long start = System.nanoTime();
        solve();
        System.out.println("Time " + ((System.nanoTime() - start) / 1e9));
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