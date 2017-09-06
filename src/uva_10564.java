import java.util.*;
import java.io.*;
public class uva_10564 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N , S;
        while((N = nextInt()) != 0) {
            S = nextInt();
            int table[][] = new int[2 * N - 1][];
            for(int i = 0; i < N; i++)
                table[i] = nextIntArray(N - i);
            for(int i = N; i < table.length; i++)
                table[i] = nextIntArray(i - N + 2);
            
            int MAX_SUM = 9 * table.length;
            long DP[][][] = new long[table.length][N][MAX_SUM + 1];
            for(int i = 0; i < N; i++)
                DP[table.length - 1][i][table[table.length - 1][i]] = 1;
            
            int adj = 1;
            
            for(int i = table.length - 2; i >= 0; i--) {
                if(i < N - 1)
                    adj = -1;
                
                int col = i >= N ? i - N + 2 : N - i;
                for(int j = 0; j < col; j++) {
                    int nj = j + adj;
                    int curr = table[i][j];
                    for(int k = curr; k <= MAX_SUM; k++) {
                        if(nj >= 0)
                            DP[i][j][k] = DP[i + 1][nj][k - curr];
                        if(!(i < N - 1 && j == col - 1))
                            DP[i][j][k] += DP[i + 1][j][k - curr];
                    }
                }
            }
            
            long totalWays = 0;
            if(S <= MAX_SUM)
                for(int i = 0; i < N; i++)
                    totalWays += DP[0][i][S];
            
            println(totalWays);
            
            if(totalWays > 0) {
                int curr = 0;
                for(int i = 0; i < N; i++)
                    if(DP[0][i][S] > 0) {
                        curr = i;
                        break;
                    }
                print(curr + " ");
                for(int i = 0; i < table.length - 1; i++) {
                    S -= table[i][curr];
                    if(i < N - 1) {
                        if(curr - 1 >= 0 && DP[i + 1][curr - 1][S] > 0) {
                            print("L");
                            curr--;
                        }
                        else 
                            print("R");
                    }
                    else {
                        if(DP[i + 1][curr][S] > 0)
                            print("L");
                        else {
                            print("R");
                            curr++;
                        }
                    }
                }
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