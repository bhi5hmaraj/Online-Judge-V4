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
    
    static int xx[] = {-1 , 0 , 1 , 0};
    static int yy[] = {0 , 1 , 0 , -1};
    
    
    private static void solve() {
        final int MAX = 100;
        int x = MAX , y = MAX;
        boolean marked[][] = new boolean[2 * MAX + 1][2 * MAX + 1];
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
        marked[x][y] = true;
        /*
        for(boolean b[] : marked){
            for(boolean t : b)
                print(t ? "X" : "O");
            print('\n');
        }
        */
        if(!flag) {
            println("BUG");
            return;
        }
        
        int cx = MAX , cy = MAX;
        int px = MAX , py = MAX;
        switch(move[0]) {
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
        for(int j = 0; j < 4; j++) {
            int dx = px + xx[j];
            int dy = py + yy[j];
            if(isValid(dx, dy) && !eq(dx , dy , cx , cy ) && marked[dx][dy]) {
                println("BUG");
                return;
            }
        }
        for(int i = 1; i < move.length; i++) {
            int nx = cx , ny = cy;
            switch(move[i]) {
            case 'L':
                ny--;
                break;
            case 'R':
                ny++;
                break;
            case 'U':
                nx--;
                break;
            case 'D':
                nx++;
                break;
            }
            for(int j = 0; j < 4; j++) {
                int dx = cx + xx[j];
                int dy = cy + yy[j];
                if(isValid(dx, dy) && !eq(dx , dy , px , py) && !eq(dx , dy , nx , ny ) && marked[dx][dy]) {
                    println("BUG");
                    return;
                }
            }
            
            px = cx;
            py = cy;
            cx = nx;
            cy = ny;
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