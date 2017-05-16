import java.util.*;
import java.io.*;
public class SuccessRate {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
    
    /*
     * Solving Diophantine Equations
     * source : http://new.math.uiuc.edu/public348/python/diophantus.html
     * 
     */
    
    static long[] solveDiophantineEquation(long A, long B , long C) {
        long q = A / B;
        long r = A % B;
        if(r == 0)
            return new long[]{0 , C / B};
        else {
            long sol[] = solveDiophantineEquation(B, r, C);
            return new long[]{sol[1] , sol[0] - q*sol[1]};
        }
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            long f1 = nextLong();
            long f2 = nextLong();
//            long g1 = gcd(f1, f2);
//            f1 /= g1;
//            f2 /= g1;
            long t1 = nextLong();
            long t2 = nextLong();
//            long g2 = gcd(t1, t2);
//            t1 /= g2;
//            t2 /= g2;

//            if(C % gcd == 0) {
//                if(B == 0)
//                    println(-1);
//                else {
                    long min = Integer.MAX_VALUE;
                    for(int k = 1;k <= 100000;k++) {
                        long A = k * (t2 - t1);
                        long B = k * t1;
                        long C = k * (t1*f2 - t2*f1);
                        long gcd = gcd(A, B);
//                        println("A = " + A + " B = " + B + " C = " + C);
                        long sol[] = solveDiophantineEquation(A , -B , gcd);
                        long ans = (sol[0] + sol[1]) * (C / gcd);
//                        println(Arrays.toString(sol));
                        min = Math.min(min,ans);
                    }
                    println(min);
                }
//            }
//            else {
//                println(-1);
//            }
//        }
        
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