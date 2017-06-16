import java.util.*;
import java.io.*;
public class VolatileKite {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
// https://www.topcoder.com/community/data-science/data-science-tutorials/geometry-concepts-basic-concepts/
  //Compute the dot product AB ⋅ BC
      static int dot(int[] A, int[] B, int[] C){
          int[] AB = new int[2];
          int[] BC = new int[2];
          AB[0] = B[0]-A[0];
          AB[1] = B[1]-A[1];
          BC[0] = C[0]-B[0];
          BC[1] = C[1]-B[1];
          int dot = AB[0] * BC[0] + AB[1] * BC[1];
          return dot;
      }
      //Compute the cross product AB x AC
      static int cross(int[] A, int[] B, int[] C){
          int[] AB = new int[2];
          int[] AC = new int[2];
          AB[0] = B[0]-A[0];
          AB[1] = B[1]-A[1];
          AC[0] = C[0]-A[0];
          AC[1] = C[1]-A[1];
          int cross = AB[0] * AC[1] - AB[1] * AC[0];
          return cross;
      }
      //Compute the distance from A to B
      static double distance(int[] A, int[] B){
          int d1 = A[0] - B[0];
          int d2 = A[1] - B[1];
          return Math.sqrt(d1*d1+d2*d2);
      }
      //Compute the distance from AB to C
      //if isSegment is true, AB is a segment, not a line.
      static double linePointDist(int[] A, int[] B, int[] C, boolean isSegment){
          double dist = cross(A,B,C) / distance(A,B);
          if(isSegment){
              int dot1 = dot(A,B,C);
              if(dot1 > 0)return distance(B,C);
              int dot2 = dot(B,A,C);
              if(dot2 > 0)return distance(A,C);
          }
          return Math.abs(dist);
      }
    
    private static void solve() {
        
        
        int n = nextInt();
        int p[][] = new int[n][];
        for(int i = 0; i < n; i++)
            p[i] = nextIntArray(2);
        
        double min = Double.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            int prev = (i - 1 + n) % n;
            int next = (i + 1) % n;
            min = Math.min(linePointDist(p[prev], p[next], p[i], true) , min);
            min = Math.min(min , distance(p[i], p[next]) / 2.0);
        }
        
        println(min);
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