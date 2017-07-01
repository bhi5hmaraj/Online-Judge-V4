import java.util.*;
import java.util.function.UnaryOperator;
import java.io.*;
public class VanyaandTriangles {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Rational {
        // static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
        int num , den;
        Rational(int a , int b) {
            num = a;
            den = b;
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
    
    private static void solve() {
        
        
        int n = nextInt();
        int pt[][] = new int[n][];
        for(int i = 0; i < n; i++)
            pt[i] = nextIntArray(2);
        
        UnaryOperator<Long> choose3 = a -> (1L * a * (a - 1) * (a - 2))  / 6;
        HashMap<Rational , HashSet<Integer>> slopes = new HashMap<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                if(i != j) {
                    Rational slope = new Rational(pt[j][1] - pt[i][1], pt[j][0] - pt[i][0]);
                    HashSet<Integer> set = slopes.getOrDefault(slope, new HashSet<>());
                    set.add(i);
                    slopes.put(slope, set);
                }
        
        long total = choose3.apply((long) n) - slopes.entrySet()
                                               .stream()
                                               .map(e -> choose3.apply((long) e.getValue().size()))
                                               .reduce(0L, (a , b) -> a + b);
        
         println(slopes);
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