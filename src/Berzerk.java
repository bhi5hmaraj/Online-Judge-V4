import java.util.*;
import java.io.*;
public class Berzerk {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static enum State{
        Win , Lose , Loop
    }
    
    static int sub(int a , int b , int mod) {
        return (a - b + mod) % mod;
    }
    
    private static void solve() {

        int N = nextInt();
        int option[][] = new int[2][];
        int K1 = nextInt();
        option[0] = nextIntArray(K1);
        int K2 = nextInt();
        option[1] = nextIntArray(K2);
        
        State ans[][] = new State[2][N];
        Arrays.fill(ans[0], State.Loop);
        Arrays.fill(ans[1], State.Loop);
        
        int outDegree[][] = new int[2][N];
        Arrays.fill(outDegree[0], K1);
        Arrays.fill(outDegree[1], K2);
        
        
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.add(0);
        queue.add(N);
        ans[0][0] = ans[1][0] = State.Lose;
        
        while(!queue.isEmpty()) {
            int front = queue.remove();
            int r = front / N;
            int c = front % N;
            if(ans[r][c] == State.Lose) {
                for(int opt : option[1 - r]) {
                    int from = sub(c, opt, N);
                    if(ans[1 - r][from] == State.Loop) {
                        ans[1 - r][from] = State.Win;
                        queue.add(((1 - r) * N) + from);
                    }
                }
            } else {
                for(int opt : option[1 - r]) {
                    int from = sub(c, opt, N);
                    if(ans[1 - r][from] == State.Loop)
                        outDegree[1 - r][from]--;
                    if(outDegree[1 - r][from] == 0) {
                        ans[1 - r][from] = State.Lose;
                        queue.add(((1 - r) * N) + from);
                    }
                }
            }
        }
        
        for(int i = 0; i < 2; i++) {
            for(int j = 1; j < N; j++)
                print(ans[i][j] + " ");
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