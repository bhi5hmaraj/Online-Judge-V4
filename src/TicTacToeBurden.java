import java.util.*;
import java.io.*;
public class TicTacToeBurden {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        char grid[][][][] = new char[3][3][3][3];
        
        for(int i = 0; i < 11; i++) {
            String line = nextLine();
            if(i == 3 || i == 7)
                continue;
            int ii = i > 4 ? i - 1 : i;
            grid[ii / 3][0][ii % 3] = line.substring(0, 3).toCharArray();
            grid[ii / 3][1][ii % 3] = line.substring(4, 7).toCharArray();
            grid[ii / 3][2][ii % 3] = line.substring(8).toCharArray();
        }
        
        int r = nextInt() - 1;
        int c = nextInt() - 1;
        
        int rr = r % 3;
        int cc = c % 3;
        
        boolean isFree = false;
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                isFree |= grid[rr][cc][i][j] == '.';
        
        for(int i = 0; i < 3; i++) {
            for(int k = 0; k < 3; k++) {
                for(int j = 0; j < 3; j++) {
                    if(isFree) {
                        if(i == rr && j == cc)
                            print(new String(grid[i][j][k]).replace('.', '!'));
                        else
                            print(new String(grid[i][j][k]));
                    }
                    else 
                        print(new String(grid[i][j][k]).replace('.', '!'));
                    
                }
                print(' ');
            }
            print('\n');
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