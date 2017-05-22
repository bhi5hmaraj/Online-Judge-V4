import java.util.*;
import static  java.lang.Math.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.io.*;
class SAMESNAK {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            Point2D.Double p11 = new Point2D.Double(nextInt(), nextInt());
            Point2D.Double p12 = new Point2D.Double(nextInt(), nextInt());

            Point2D.Double p21 = new Point2D.Double(nextInt(), nextInt());
            Point2D.Double p22 = new Point2D.Double(nextInt(), nextInt());

            Line2D.Double l1 = new Line2D.Double(p11, p12);
            Line2D.Double l2 = new Line2D.Double(p21, p22);
            if(!l1.intersectsLine(l2))
                println("no");
            else {
                if(l1.ptLineDist(p21) == 0 && l1.ptLineDist(p22) == 0)
                    println("yes");
                else if(p11.equals(p21) || p11.equals(p22) ||
                   p12.equals(p21) || p12.equals(p22))
                    println("yes");
                else
                    println("no");
            }
        }
        
    }
    
    static boolean samePoint(int p1[] , int p2[]) {
        return p1[0] == p2[0] && p1[1] == p2[1];
    }
    
    private static void solve2() {
        int T = nextInt();
        while(T-->0) {
            int pt11[] = nextIntArray(2);
            int pt12[] = nextIntArray(2);
            int pt21[] = nextIntArray(2);
            int pt22[] = nextIntArray(2);
            if(pt11[0] == pt12[0] && pt21[0] == pt22[0] && pt11[0] == pt21[0])
                println((max(pt21[1] , pt22[1]) < min(pt11[1] , pt12[1]) || min(pt21[1] , pt22[1]) > max(pt11[1] , pt12[1]) ? "no" : "yes"));
            else if(pt11[1] == pt12[1] && pt21[1] == pt22[1] && pt11[1] == pt21[1])
                println((max(pt21[0] , pt22[0]) < min(pt11[0] , pt12[0]) || min(pt21[0] , pt22[0]) > max(pt11[0] , pt12[0]) ? "no" : "yes"));
            else if(samePoint(pt11, pt21) || samePoint(pt11, pt22) || samePoint(pt12, pt21) || samePoint(pt12, pt22))
                println("yes");
            else
                println("no");
        }
    }
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        st     = null;
        solve2();
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