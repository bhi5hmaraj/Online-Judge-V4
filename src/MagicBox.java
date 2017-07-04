import java.util.*;
import java.io.*;
public class MagicBox {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final double EPS = 1e-9;
    static class Plane {
        double pt[];
        int fixed;  // This coordinate is constant for this particular plane
        double boundry[];
        Plane(double p[] , double b[] , int f) {
            pt = p;
            boundry = b;
            fixed = f;
        }
        boolean intersect(int line[][]) {   // line[0] = direction vector   line[1] = point in line
            if(line[0][fixed] == 0)
                return true;
            
            double lambda = (double)(boundry[fixed] - line[1][fixed]) / line[0][fixed];   
            double intersect[] = new double[3];
            for(int i = 0; i < 3; i++)
                intersect[i] = lambda * line[0][i] + line[1][i];
            
            boolean flag = true;
            System.out.println("intersection " + Arrays.toString(intersect));
            
            for(int i = 0; i < 3; i++) {
                if(i == fixed)
                    flag &= intersect[i] == boundry[fixed];
                else
                    flag &= intersect[i] >= 0.0 && intersect[i] <= boundry[i];
            }
            
            return flag;
        }
    }
    
    private static void solve() {
        
        double curr[] = new double[3];
        for(int i = 0; i < 3; i++) curr[i] = nextDouble();
        
        double box[] = new double[3];
        for(int i = 0; i < 3; i++) box[i] = nextDouble();
        
        int A[] = nextIntArray(6);
        int perm[] = {1 ,2 ,0};
        int score = 0;
        
        Plane planes[] = new Plane[6];
        for(int i = 0; i < 6; i++) {
            double pt[] = new double[3];
            double b[] = new double[3];
            for(int j = 0; j < 3; j++)
                if(j != perm[i]) {
                    pt[j] = box[j] / 2.0;
                    b[j] = box[j];
                }
            
            
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