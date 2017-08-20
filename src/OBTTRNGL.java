import java.util.*;
import java.io.*;
class OBTTRNGL  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Vector {
        double         x, y;
        private double norm;
        

        public String toString() {
            return String.format("(%.3f , %.3f)", x , y);
        }
        
        Vector(double x, double y) {
            this.x = x;
            this.y = y;
            this.norm = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        }
        
        Vector() {
            this(0, 0);
        }

        double magnitude() {
            return norm;
        }
        
        double angleTo(Vector that) {
            double cosTheta = ((this.x * that.x) + (this.y * that.y)) / (this.magnitude() * that.magnitude());
            return Math.acos(cosTheta);
        }
        
        Vector add(Vector that) {
            return new Vector(this.x + that.x, this.y + that.y);
        }
        
        static Vector[] getPerpendicularUnit(Vector v) {
            Vector[] vecs = new Vector[2];
            double constant;
            if(v.x == 0) { 
                constant = 1.0 / Math.sqrt(1.0 + ((v.x * v.x) / (v.y * v.y)));
                vecs[0] = new Vector(constant, -constant * (v.x / v.y));
            }
            else {
                constant = 1.0 / Math.sqrt(1.0 + ((v.y * v.y) / (v.x * v.x)));
                vecs[0] = new Vector(-constant * (v.y / v.x), constant);
            }
            vecs[1] = new Vector(-vecs[0].x, -vecs[0].y);
            
            return vecs;
        }
        
        void multiply(double scalar) {
            x *= scalar;
            y *= scalar;
            norm *= scalar; 
        }
        
    }
    static double pt[][];
    static boolean isObtuse(int i , int a , int b) {
        Vector va = new Vector(pt[a][0] - pt[i][0], pt[a][1] - pt[i][1]);
        Vector vb = new Vector(pt[b][0] - pt[i][0], pt[b][1] - pt[i][1]);
        return compare(va.angleTo(vb), Math.PI / 2.0) == 1;
    }
    
    static int brute(int n , int a , int b) {
        double radius = 100;
        pt = new double[n][2];
        for (int i = 0; i < n; i++) {
            double angle = i * 2 * (Math.PI / (double)n);
            pt[i][0] = radius * Math.cos(angle);
            pt[i][1] = radius * Math.sin(angle);
        }
        int cnt = 0;
        for(int i = 0; i < n; i++)
            if(i != a && i != b) {
                cnt += isObtuse(i, a, b) || isObtuse(a, i, b) || isObtuse(b, i, a) ? 1 : 0;
            }
        
        //println("brute " + cnt);
        return cnt;
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
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            
            int k = nextInt();
            int A = nextInt();
            int B = nextInt();
            
            int a = Math.min(A , B);
            int b = Math.max(A , B);
            
            int minor = Math.min(b - a , k - (b - a));
            long ans = 0;
            if(!(k % 2 == 0 && b - a == k / 2)) {
                ans += minor - 1;
                ans += 1L * (k / 2 - minor - (k % 2 == 0 ? 1 : 0)) * 2;
            }
             println(ans);
//             brute(k, A - 1, B - 1);
            // println(brute(k, A - 1, B - 1));
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