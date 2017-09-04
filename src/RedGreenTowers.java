import java.util.*;
import java.io.*;
public class RedGreenTowers {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int mod = (int) 1e9 + 7;

    private static void solve() {
        
        int R = nextInt();
        int G = nextInt();
        
        int DPprev[] = new int[R + 1];  // number of ways of building blocks using exactly i red blocks
        boolean canPrev[] = new boolean[R + 1];
        DPprev[0] = G > 0 ? 1 : 0;
        DPprev[1] = R > 0 ? 1 : 0;
        canPrev[0] = G > 0;
        canPrev[1] = R > 0;
        boolean flag = false;
        int h = 2;
        do {    // Upperbound = sqrt(R + G)
            int DPnew[] = new int[R + 1];
            boolean canNew[] = new boolean[R + 1];
            flag = false;
            for(int i = 0; i <= R; i++) {
                int j = ((h * (h + 1)) / 2) - i;    // green
                if(i >= h) {    // build base with red
                    DPnew[i] = DPprev[i - h];
                    canNew[i] |= canPrev[i - h];
                }
                if(j >= h && j <= G) {    // build base with green
                    DPnew[i] = (DPnew[i] + DPprev[i]) % mod;
                    canNew[i] |= canPrev[i];
                }
                flag |= canNew[i];
            }
            
            if(!flag)
                break;
            canPrev = canNew;
            DPprev = DPnew;
            h++;
            
        }while(flag);
        
        println("h " + h);
        int sum = 0;
        for(int a : DPprev)
            sum = (sum + a) % mod;
        
        println(sum);
        
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