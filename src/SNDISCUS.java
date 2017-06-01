import java.util.*;
import java.io.*;
class SNDISCUS {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int manhattanDist(int pt1[] , int x , int y) {
        return Math.abs(pt1[0] - x) + Math.abs(pt1[1] - y);
    }
    
    static final int MAX_DIM = 50;
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            int N = nextInt();
            int Spt[][] = new int[N][];
            int Ept[][] = new int[N][];
            for(int i = 0; i < N; i++) {
                Spt[i] = nextIntArray(2);
                Ept[i] = nextIntArray(2);
            }
            
            int minTime = Integer.MAX_VALUE;
            
            for(int i = 1; i <= MAX_DIM; i++)
                for(int j = 1; j <= MAX_DIM; j++) {
                    int time = 0;
                    for(int k = 0; k < N; k++) {
                        if(Spt[k][0] == Ept[k][0] && (j >= Math.min(Spt[k][1] , Ept[k][1])
                                                  && j <= Math.max(Spt[k][1] , Ept[k][1])))     // Horizontal
                            time = Math.max(time , Math.abs(Spt[k][0] - i));
                        else if((Spt[k][1] == Ept[k][1]) && (i >= Math.min(Spt[k][0] , Ept[k][0]) 
                                                         && i <= Math.max(Spt[k][0] , Ept[k][0]))) // Vertical
                            time = Math.max(time , Math.abs(Spt[k][1] - j));
                        else
                            time = Math.max(time , Math.min(manhattanDist(Spt[k], i , j) , 
                                                            manhattanDist(Ept[k], i, j)));
                    }
                    
                    minTime = Math.min(minTime , time);
                }
            
            println(minTime);
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