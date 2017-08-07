import java.util.*;
import java.io.*;
public class SuccessRate {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long floorDiv(long a , long b) { // computes b/a
        return Long.signum(a) * Long.signum(b) < 0 && b % a != 0 ? b / a - 1 : b / a;
    }
    static long[] extendedEuclid(long a , long b) {
        long rnm2[] = new long[]{0 , 1};    // r_n-2 , x , y
        long rnm1[] = new long[]{1 , 0};    // r_n-1 , u , v
        while(a != 0) {
            long r = ((b % a) + a) % a;
            long q = floorDiv(a, b);
            /*
            println(String.format("a %d b %d r %d", a , b , r));
            println("before");
            println("rnm2 " + Arrays.toString(rnm2));
            println("rnm1 " + Arrays.toString(rnm1));
            */
            long coeffA = rnm2[0] - q * rnm1[0];
            long coeffB = rnm2[1] - q * rnm1[1];
            b = a;
            a = r;
            rnm2[0] = rnm1[0];
            rnm2[1] = rnm1[1];
            rnm1[0] = coeffA;
            rnm1[1] = coeffB;
            /*
            println("after");
            println("rnm2 " + Arrays.toString(rnm2));
            println("rnm1 " + Arrays.toString(rnm1));   
            */
        }
        return new long[]{rnm2[0], rnm2[1] , b};
    }
    
    static long gcd(long a , long b) { return (b == 0) ? a : gcd(b, a % b); }
    
    static long grt(long a , long b) {
        long ans = (a / b) + (a * b < 0 && Math.abs(a) % Math.abs(b) != 0 ? 0 : 1);
        println("grt a  " + a + " b " + b + " ans " + ans);
        return ans;
    }
    static long less(long a , long b) {
        long ans = (a / b) + (a * b > 0 && a % b != 0 ? 0 : -1);
        println("less a  " + a + " b " + b + " ans " + ans);
        return ans;
    }
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            long x = nextLong();
            long y = nextLong();
            long p = nextLong();
            long q = nextLong();
            long c = p * y - q * x;
            long a = q;
            long b = -p;
            long gcd = gcd(Math.abs(a), Math.abs(b));
            if(c % gcd != 0)
                println(-1);
            else {
                long soln[] = extendedEuclid(a, b);
                println("a " + a + " b " + b +  " c " + c);
                println("soln " + Arrays.toString(soln));
                println("general ");
                println("x` = " + soln[0] + " - " + (a / gcd) + "n");
                println("y` = " + soln[1] + " + " + (b / gcd) + "n");
                long lo = -(long) 1e9;
                long hi =  (long) 1e9;
                if(Long.signum(floorDiv(a, gcd)) < 0)
                    lo = Math.max(lo , grt(soln[0] * gcd - a , a));
                else
                    hi = Math.min(hi , less(soln[0] * gcd + a , a));
                if(Long.signum(floorDiv(b, gcd)) < 0)
                    hi = Math.min(hi , less(-gcd * soln[1] + b, b));
                else
                    lo = Math.max(lo , grt(-gcd * soln[1] - b, b));
                if(Long.signum(floorDiv(a + b , gcd)) < 0)
                    hi = Math.min(hi , less(soln[0] - soln[1] + ((a + b) / gcd), (a + b) / gcd));
                else
                    lo = Math.max(lo , grt(soln[0] - soln[1] - ((a + b) / gcd), (a + b) / gcd));
                
                println("lo " + lo + " hi " + hi);
                long ans = Math.min((soln[1] + lo * b / gcd) , (soln[1] + hi * b / gcd));
                println("ans " + ans);
            }
        }
        
//        println(Arrays.toString(extendedEuclid(2 , -1 , -1)));
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