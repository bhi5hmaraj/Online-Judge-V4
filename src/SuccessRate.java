import java.util.*;
import java.io.*;
public class SuccessRate {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long[] extendedEuclid(long c , long d) {
        long a = Math.max(c , d);
        long b = Math.min(c , d);
        long rnm2[] = new long[]{0 , 1};    // r_n-2
        long rnm1[] = new long[]{1 , 0};    // r_n-1
        while(b != 0) {
            
            long r = a % b;
            println(String.format("a %d b %d r %d", a , b , r));
            println("before");
            println("rnm2 " + Arrays.toString(rnm2));
            println("rnm1 " + Arrays.toString(rnm1));
            
            long coeffA = rnm2[0] - (a / b) * rnm1[0];
            long coeffB = rnm2[1] - (a / b) * rnm1[1];
            a = b;
            b = r;
            rnm2[0] = rnm1[0];
            rnm2[1] = rnm1[1];
            rnm1[0] = coeffA;
            rnm1[1] = coeffB;
            
            println("after");
            println("rnm2 " + Arrays.toString(rnm2));
            println("rnm1 " + Arrays.toString(rnm1));   
            
        }
        return rnm2;
    }
    
    static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
    
    static long grt(long a , long b) {
        
        long ans = (a / b) + (a * b < 0 && Math.abs(a) % Math.abs(b) != 0 ? 0 : 1);
        println("grt a  " + a + " b " + b + " ans " + ans);
        return ans;
    }
    static long less(long a , long b) {
        return (a / b) + (a * b > 0 && a % b != 0 ? 0 : -1);
    }
    private static void solve() {
        /*
        int T = nextInt();
        while(T-->0) {
            long x = nextLong();
            long y = nextLong();
            long p = nextLong();
            long q = nextLong();
            long c = p * y - q * x;
            long a = q * Long.signum(c);
            long b = -p * Long.signum(c);
            long gcd = gcd(Math.abs(a), Math.abs(b));
            if(c % gcd != 0)
                println(-1);
            else {
                long soln[] = extendedEuclid(a, b);
                println("a " + a + " b " + b +  " c " + c);
                println("soln " + Arrays.toString(soln));
                long lo = Math.max(grt(-gcd * soln[1] - b, b) , grt(soln[0] - soln[1] - ((a + b) / gcd), (a + b) / gcd));
                long hi = less(soln[0] * gcd + a , a);
                println("lo " + lo + " hi " + hi);
                println("ans " + (c / gcd) * (soln[0] + lo * b / gcd));
            }
        }
        */
        println(Arrays.toString(extendedEuclid(2 , -1)));
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