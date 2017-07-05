import java.util.*;
import java.io.*;
public class VanyaandTriangles {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class Rational {
        static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
        int num , den;
        Rational(int a , int b) {
            int g = gcd(a, b);
            num = a / g;
            den = b / g;
        }
        @Override
        public boolean equals(Object obj) {
            Rational other = (Rational) obj;
            return other.num == num && other.den == den;
        }
        @Override
        public int hashCode() {
            return Objects.hash(num , den);
        }
        @Override
        public String toString() {
            return "[" + num + " / " + den + "]";
        }
    }
    
    static long choose3(long a) {
        return (a * (a - 1) * (a - 2)) / 6;
    }
    static long choose2(long a) {
        return (a * (a - 1)) / 2;
    }

    private static void solve() {
        
        int n = nextInt();
        int pt[][] = new int[n][];
        for(int i = 0; i < n; i++)
            pt[i] = nextIntArray(2);
        
        long total = choose3(n);
        
        for(int i = 0; i < n; i++) {
            HashMap<Rational , Integer> freq = new HashMap<>();
            for(int j = i + 1; j < n; j++) 
                freq.merge(new Rational(pt[j][0] - pt[i][0], pt[j][1] - pt[i][1]), 1, Integer::sum);

            total -= freq.entrySet().stream().map(e -> choose2(e.getValue())).reduce(0L, Long::sum);
        }
        
        println(total);
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