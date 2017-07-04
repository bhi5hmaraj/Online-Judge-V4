import java.util.*;
import java.util.function.UnaryOperator;
import java.io.*;
public class VanyaandTriangles {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class Line implements Comparable<Line> {
        int xInNum , xInDen , yInNum , yInDen;
        static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
        Line(int x1 , int y1 , int x2 , int y2) {
            xInNum = x1 * y2 - y1 * x2;
            xInDen = y2 - y1;
            yInNum = y1 * x2 - x1 * y2;
            yInDen = x2 - x1;
            // System.out.println("before " + xInNum + " / " + xInDen + " " + yInNum + " / " + yInDen);
            int g1 = gcd(xInNum, xInDen);
            if(g1 != 0) {
                xInNum /= g1;
                xInDen /= g1;
            }
            int g2 = gcd(yInNum, yInDen);
            if(g2 != 0) {
                yInNum /= g2;
                yInDen /= g2;
            }
            //System.out.println(xInNum + " / " + xInDen + " " + yInNum + " / " + yInDen);
        }
        @Override
        public boolean equals(Object obj) {
            Line other = (Line) obj;
            return xInDen == other.xInDen && xInNum == other.xInNum &&
                   yInDen == other.yInDen && yInNum == other.yInNum;
        }
        @Override
        public int hashCode() {
            return Objects.hash(xInNum , xInDen , yInNum , yInDen);
        }
        @Override
        public int compareTo(Line o) {
            if(xInNum != o.xInNum)
                return xInNum - o.xInNum;
            else if(xInDen != o.xInDen)
                return xInDen - o.xInDen;
            else if(yInNum != o.yInNum)
                return yInNum - o.yInNum;
            else if(yInDen != o.yInDen)
                return yInDen - o.yInDen;
            else
                return 0;
        }
        @Override
        public String toString() {
            return xInNum + " / " + xInDen + " " + yInNum + " / " + yInDen;
        }
    }
    
    static int brute(int pt[][]) {
        int n = pt.length;
        int cnt = 0;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++) {
                Line l1 = new Line(pt[i][0], pt[i][1], pt[j][0], pt[j][1]);
                for(int k = j + 1; k < n; k++)
                    cnt += !l1.equals(new Line(pt[i][0], pt[i][1], pt[k][0], pt[k][1])) ? 1 : 0;
            }
        
        return cnt;
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int pt[][] = new int[n][];
        for(int i = 0; i < n; i++)
            pt[i] = nextIntArray(2);
        
        UnaryOperator<Long> choose3 = a -> (1L * a * (a - 1) * (a - 2)) / 6;
        TreeMap<Line , HashSet<Integer>> lines = new TreeMap<>();
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) 
                if(i != j) {
                    Line l = new Line(pt[i][0], pt[i][1], pt[j][0], pt[j][1]);
                    HashSet<Integer> set = lines.getOrDefault(l, new HashSet<>());
                    set.add(i);
                    lines.put(l, set);
                }
        
        long total = choose3.apply((long) n) - lines.entrySet()
                                               .stream()
                                               .map(e -> choose3.apply((long) e.getValue().size()))
                                               .reduce(0L, Long::sum);
        
        lines.forEach((k , v) -> println(k + " ==> " + v));
        println(total);
        println("brute " + brute(pt));
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