import java.util.*;
import java.io.*;
public class SagheertheHausmeister {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N = nextInt();
        int M = nextInt();
        
        char grid[][] = new char[N][];
        for(int i = 0; i < N; i++)
            grid[i] = nextLine().toCharArray();
        
        int fromLeft[] = new int[N];
        int fromRight[] = new int[N];
        
        for(int i = 0; i < N; i++) {
            int ptr = 0;
            for(int j = 0; j < M; j++) {
                ptr++;
                if(grid[i][1 + j] == '1')
                    fromLeft[i] = ptr;
                if(grid[i][M - j] == '1')
                    fromRight[i] = ptr;
            }
        }
        /*
        System.out.println("left " + Arrays.toString(fromLeft));
        System.out.println("right " + Arrays.toString(fromRight));
        
        */
        // false , 0 = left
        
        int minTime = Integer.MAX_VALUE;
        
        for(int mask = 0; mask < (1 << (N - 1)); mask++) {
            boolean currDir = false;
            int time = 0;
            
            for(int floor = N - 2; floor >= 0; floor--) {
                boolean nextDir = (mask & (1 << floor)) != 0;   // true - right
                if(currDir == nextDir)
                    time += (2 * (currDir ? fromRight[floor + 1] : fromLeft[floor + 1])) + 1;
                else
                    time += M + 2;
                
                currDir = nextDir;
            }
            
            time += (currDir ? fromRight[0] : fromLeft[0]); 
            minTime = Math.min(minTime , time);
        }
        
        println(minTime);
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