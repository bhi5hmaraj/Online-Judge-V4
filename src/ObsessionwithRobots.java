import java.util.*;
import java.io.*;
public class ObsessionwithRobots {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean isValid(int x , int y) {
        return x >= 0 && x <= 200 && y >= 0 && y <= 200;
    }
    
    static boolean eq(int... ar) {
        return ar[0] == ar[2] && ar[1] == ar[3];
    }
    
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    
    
    private static void solve() {
        int x = 100 , y = 100;
        boolean marked[][] = new boolean[201][201];
        boolean flag = true;
        char move[] = nextLine().toCharArray();
        for(char ch : move) {
            marked[x][y] = true;
            switch(ch) {
            case 'L':
                y--;
                break;
            case 'R':
                y++;
                break;
            case 'U':
                x--;
                break;
            case 'D':
                x++;
                break;
            }
            flag &= !marked[x][y];
        }
        
        if(!flag) {
            println("BUG");
            return;
        }
        
        int cx = 100 , cy = 100;
        for(char ch = move[0] , i = 0; i < move.length - 1; ch = move[++i]) {
            if(Math.abs(cx - x) + Math.abs(cy - y) <= 1) {
                println("BUG");
                return;
            }
            switch(ch) {
            case 'L':
                cy--;
                break;
            case 'R':
                cy++;
                break;
            case 'U':
                cx--;
                break;
            case 'D':
                cx++;
                break;
            }
        }
        
        println("OK");
        
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