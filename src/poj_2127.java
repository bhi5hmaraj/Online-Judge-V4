import java.util.*;
import java.io.*;
public class poj_2127 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class FenwickTree2D {
        /*
         * Stores max(1 ... i)
         */
        int tree[][];
        int N , M;
        FenwickTree2D(int N , int M) {
            this.N = N;
            this.M = M;
            tree = new int[N + 1][M + 1];
        }

        void update(int x, int y , int val) {
            for (; x <= N; x += (x & -x))
                for(int yy = y; yy <= M; yy += (yy & -yy))
                    tree[x][yy] = Math.max(tree[x][yy] , val);
        }

        int query(int x, int y) {
            int max = 0;
            for (; x > 0; x -= (x & -x))
                for(int yy = y; yy > 0; yy -= (yy & -yy))
                    max = Math.max(max , tree[x][yy]);

            return max;
        }
    }


    
    private static void solve() {
        
        int n = nextInt();
        int A[] = nextIntArray(n);  
        int m = nextInt();
        int B[] = nextIntArray(m);  

        TreeSet<Integer> set = new TreeSet<>();
        for(int a : A) set.add(a);
        for(int b : B) set.add(b);
        /*
        HashMap<Integer , Integer> compress = new HashMap<>();
        for(int num : set) compress.put(num, compress.size());
        
        for(int i = 0; i < n; i++) A[i] = compress.get(A[i]);
        for(int i = 0; i < m; i++) B[i] = compress.get(B[i]);
        */
        int DP[][] = new int[n][m];
        FenwickTree2D BIT2D = new FenwickTree2D(n, m);
        
        for(int asc : set) {
            for(int i = 0; i < n; i++)
                for(int j = 0; j < m; j++)
                    if(A[i] == B[j] && A[i] == asc) {
                        DP[i][j] = BIT2D.query(i, j) + 1;
                        BIT2D.update(i + 1, j + 1, DP[i][j]);
                    }
        }
        
        int LCISLen = 0;
        int curr_i = -1 , curr_j = -1;
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < m; j++)
                if(DP[i][j] > LCISLen) {
                    LCISLen = DP[i][j];
                    curr_i = i;
                    curr_j = j;
                }
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int new_i = -1, new_j = -1;
        while(!(new_i == curr_i && new_j == curr_j)) {
            stack.push(A[curr_i]);
            outer:
            for(int i = 0; i < curr_i; i++)
                for(int j = 0; j < curr_j; j++)
                    if(A[i] == B[i] && A[i] < A[curr_i] && DP[i][j] == DP[curr_i][curr_j] - 1) {
                        new_i = i;
                        new_j = j;
                        break outer;
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