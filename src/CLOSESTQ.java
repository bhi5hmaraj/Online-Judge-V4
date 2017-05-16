/************************         ௳            ************************/
import java.util.*;
import java.io.*;
class CLOSESTQ {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Point {
        int x , y;
        Point(int xx , int yy) {
            x = xx;
            y = yy;
        }
        @Override
        public int hashCode() {
            return 500 * x + y;
        }
        int distSq(Point that) {
            int d1 = x - that.x;
            int d2 = y - that.y;
            return d1*d1 + d2*d2;
        }
        @Override
        public boolean equals(Object obj) {
            Point that = (Point) obj;
            return x == that.x && y == that.y;
        }
        @Override
        public String toString() {
            return String.format("(%d , %d)", x , y);
        }
    }
    
    private static void solve() {

        int Q = nextInt();
        HashSet<Point> set = new HashSet<>();
        TreeMap<Integer, Integer> lines = new TreeMap<>();

        while (Q-- > 0) {
            char type = nextChar();
            Point pt = new Point(nextInt(), nextInt());
            if (type == '-') {
                set.stream().filter(p -> !p.equals(pt)).forEach(p -> {
                    int dist = p.distSq(pt);
                    lines.put(dist, lines.get(dist) - 1);
                    lines.remove(dist, 0);
                });
                set.remove(pt);
            } else {
                set.stream().forEach(p -> {
                    int dist = p.distSq(pt);
                    lines.put(dist, lines.getOrDefault(dist, 0) + 1);
                });
                set.add(pt);
            }
            println(lines.isEmpty() ? 0 : lines.firstKey());
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