import java.util.*;
import java.io.*;
public class VanyaandTriangles {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static class Line implements Comparable<Line> {
        int sNum , sDen , yInNum , yInDen;
        int x; // for lines parallel to y axis
        static int gcd(int a , int b) { return (b == 0) ? a : gcd(b, a % b); }
        Line(int x1 , int y1 , int x2 , int y2) {
            sNum = y2 - y1;
            sDen = x2 - x1;
            yInNum = y1 * x2 - x1 * y2;
            yInDen = x2 - x1;
            // System.out.println("before " + xInNum + " / " + xInDen + " " + yInNum + " / " + yInDen);
            int g1 = gcd(sNum, sDen);
            if(g1 != 0) {
                sNum /= g1;
                sDen /= g1;
            }
            int g2 = gcd(yInNum, yInDen);
            if(g2 != 0) {
                yInNum /= g2;
                yInDen /= g2;
            }
            
            if(sDen == 0 && yInDen == 0)
                x = x1;
        }
        @Override
        public boolean equals(Object obj) {
            Line other = (Line) obj;
            return sDen == other.sDen && sNum == other.sNum &&
                   yInDen == other.yInDen && yInNum == other.yInNum && x == other.x;
        }
        @Override
        public int hashCode() {
            return Objects.hash(sNum , sDen , yInNum , yInDen , x);
        }
        @Override
        public int compareTo(Line o) {
            if(sNum != o.sNum)
                return sNum - o.sNum;
            else if(sDen != o.sDen)
                return sDen - o.sDen;
            else if(yInNum != o.yInNum)
                return yInNum - o.yInNum;
            else if(yInDen != o.yInDen)
                return yInDen - o.yInDen;
            else if(x != o.x)
                return x - o.x;
            else
                return 0;
        }
        @Override
        public String toString() {
            return sNum + " / " + sDen + " " + yInNum + " / " + yInDen + " x = " + x;
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
    
    static long choose3(long a) {
        return (a * (a - 1) * (a - 2)) / 6;
    }
    static long choose2(long a) {
        return (a * (a - 1)) / 2;
    }
    static final double EPS = 1e-8;
    static int compare(double a , double b) {
        if(a <= b - EPS)
            return -1;
        else if(a >= b + EPS)
            return 1;
        else
            return 0;
    }
    static int compare(int[] a , int b[]) {
        int left = a[0] * b[1];
        int right = a[1] * b[0];
        return left - right;
    }
    private static void solve() {
        
        int n = nextInt();
        int pt[][] = new int[n][];
        for(int i = 0; i < n; i++)
            pt[i] = nextIntArray(2);
        
        long total = choose3(n);
        
        for(int i = 0; i < n; i++) {
            Double slopes[] = new Double[n - i - 1];
            for(int j = i + 1; j < n; j++)
                slopes[j - i - 1] = Math.atan2(pt[i][1] - pt[j][1], pt[i][0] - pt[j][0]);
            
            Arrays.sort(slopes , VanyaandTriangles::compare);
            
            int streak = 1;
            for(int k = 1; k < slopes.length; k++) {
                if(compare(slopes[k] , slopes[k - 1]) == 0)
                    streak++;
                else {
                    total -= choose2(streak);
                    streak = 1;
                }
            }
            total -= choose2(streak);
        }
        
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