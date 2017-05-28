import java.util.*;
import java.io.*;
class ISSNAKE {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    static char grid[][];
    static int N;
    static boolean marked[][];
    static int x[] = {-1 , 0 , 1 , 0};
    static int y[] = {0 , 1 , 0 , -1};
    
    static final char SNAKE = '#';
    
    static boolean isVaild(int i , int j) {
        return i >= 0 && i < 2 && j >= 0 && j < N && grid[i][j] == SNAKE;
    }
    
    static void dfs(int i , int j) {
        marked[i][j] = true;
        for(int k = 0; k < 4; k++) {
            int ii = i + x[k];
            int jj = j + y[k];
            if(isVaild(ii, jj) && !marked[ii][jj])
                dfs(ii, jj);
        }
    }
    
    static int countComponents() {
        int cnt = 0;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < N; j++) {
                if(grid[i][j] == SNAKE && !marked[i][j]) {
                    cnt++;
                    dfs(i, j);
                }
            }
        
        return cnt;
    }
    
    private static void solve() {
        
        int T = nextInt();
        while(T-->0) {
            N = nextInt();
            grid = new char[2][];
            marked = new boolean[2][N];
            grid[0] = nextLine().toCharArray();
            grid[1] = nextLine().toCharArray();
            
            if(countComponents() > 1)
                println("no");
            else {
                int fill[] = new int[N];
                boolean orient[] = new boolean[N];
                for(int  i = 0; i < N; i++) {
                    fill[i] = (grid[0][i] == SNAKE ? 1 : 0) + (grid[1][i] == SNAKE ? 1 : 0);
                    if(fill[i] == 1)
                        orient[i] = grid[0][i] == SNAKE;
                }
                int ptr = 0;
                boolean lastOrient = false;
                boolean first = true;
                boolean flag = true;
                int blockLength = 0;
                while(ptr < N && fill[ptr] != 1) ptr++;
                while(ptr < N && fill[ptr] != 0) {
                    if(fill[ptr] == 2) {
                        blockLength = 0;
                        while(ptr < N && fill[ptr] == 2) {
                            blockLength++;
                            ptr++;
                        }
                    } else {
                        if(first) {
                            first = false;
                            /*System.out.println("first");*/
                        }
                        else {
                            /*System.out.println("last orient = " + (lastOrient ? "UP" : "DOWN") + " block len = " + blockLength
                                             + " curr orient = " + (orient[ptr] ? "UP" : "DOWN"));
                            */
                            lastOrient = blockLength % 2 == 1 ? !lastOrient : lastOrient;
                            if(orient[ptr] != lastOrient) {
                                flag = false;
                                break;
                            }
                        }
                        lastOrient = orient[ptr];
                        while(ptr < N && fill[ptr] == 1)
                            ptr++;
                    }
                }
                
                println(flag ? "yes" : "no");
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