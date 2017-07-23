import java.util.*;
import java.io.*;
public class poj_2430 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static final int INF = (int) 1e9;
    
    static void prettyPrint(int a[][]) {
        int n = a.length;
        int m = a[0].length;
        int temp[][] = new int[n + 1][m + 1];
        temp[0][0] = -1;
        for(int i = 1; i <= n; i++) {
            temp[i][0] = i - 1;
            System.arraycopy(a[i - 1], 0, temp[i], 1, m);
        }
        for(int j = 1; j <= m; j++)
            temp[0][j] = j - 1;
        
        for(int t[] : temp) {
            for(int tt : t)
                print(String.format("%3d ", tt));
            print('\n');
        }
        print('\n');
    }
    
    private static void solve() {
        
        int N = nextInt();
        int K = nextInt();
        int B = nextInt();
        
        int arr[][] = new int[N][];
        for(int i = 0; i < N; i++)
            arr[i] = nextIntArray(2);
        
        
        // long st = System.nanoTime();
        
        Arrays.sort(arr , new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1] != o2[1])
                    return o1[1] - o2[1];
                else
                    return o1[0] - o2[0];
            }
        });
        
        int sz = 1;
        for(int i = 1; i < N; i++)
            sz += arr[i][1] != arr[i - 1][1] ? 1 : 0;
        
        int compress[][] = new int[sz][2]; // 0 - up , 1 - down , 2 - both
        int ptr = 0;
        for(int i = 0; i < N; ) {
            compress[ptr][0] = arr[i][1];
            if(i + 1 < N && arr[i][1] == arr[i + 1][1]) {
                compress[ptr++][1] = 2;
                i += 2;
            } else {
                compress[ptr++][1] = arr[i][0] - 1;
                i++;
            }
        }
        
        int[][] costH1 = new int[sz][sz];   // one horizontal rect ----
        int[][] costH2 = new int[sz][sz];   // two horizontal rect ====
        int[][] costV = new int[sz][sz];    // one vertical   rect |   |
        
        /*        
        for(int i = 0; i < sz; i++)
            print(String.format("%5d ", compress[i][0]));
        print('\n');
        for(int i = 0; i < sz; i++)
            print(String.format("%5d ", compress[i][1]));
        print('\n');
        */

        for(int i = 0; i < sz; i++) {
            int firstUp = 0 , firstDown = 0;
            int lastUp = -1 , lastDown = -1;
            costH1[i][i] = compress[i][1] == 2 ? INF : 1;
            costH2[i][i] = compress[i][1] == 2 ? 2 : INF;
            costV[i][i] = 2;
            int first = compress[i][1];
            boolean flag = compress[i][1] != 2;
            if(compress[i][1] != 1) 
                firstUp = lastUp = compress[i][0];
            if(compress[i][1] != 0)
                firstDown = lastDown = compress[i][0];
            
            for(int j = i + 1; j < sz; j++) {
                costV[i][j] = 2 * (compress[j][0] - compress[i][0] + 1);
                flag &= compress[j][1] == first;
                costH1[i][j] = flag ? compress[j][0] - compress[i][0] + 1 : INF;
                if(firstUp == 0 && compress[j][1] != 1)
                    firstUp = lastUp = compress[j][0];
                if(firstDown == 0 && compress[j][1] != 0)
                    firstDown = lastDown = compress[j][0];
                
                lastUp = compress[j][1] != 1 ? compress[j][0] : lastUp;
                lastDown = compress[j][1] != 0 ? compress[j][0] : lastDown;
                costH2[i][j] = flag ? INF : (lastUp - firstUp + 1) + (lastDown - firstDown + 1);
            }
        }
        /*
        println("Vertical");
        prettyPrint(costV);
        println("Hori 1");
        prettyPrint(costH1);
        println("Hori 2");
        prettyPrint(costH2);
        */
        
        // println("Time : " + (System.nanoTime() - st) / 1e9); 
        int DP[][] = new int[K + 1][sz];
        Arrays.fill(DP[0], INF);
        for(int i = 0; i < sz; i++)
            DP[1][i] = Math.min(costV[0][i] , costH1[0][i]);
        
        for(int i = 2; i <= K; i++) {
            for(int j = 0; j < sz; j++) {
                DP[i][j] = INF;
                for(int k = -1; k < j; k++) {
                    int dp1 = k == -1 ? 0 : DP[i - 1][k];
                    int dp2 = k == -1 ? 0 : DP[i - 2][k];
                    DP[i][j] = Math.min(DP[i][j] , dp1 + Math.min(costV[k + 1][j] , costH1[k + 1][j]));
                    DP[i][j] = Math.min(DP[i][j] , dp2 + costH2[k + 1][j]);
                }
            }
        }
        
        //prettyPrint(DP);
        println(DP[K][sz - 1]);
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