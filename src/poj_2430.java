import java.util.*;
import java.io.*;
public class poj_2430 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int arr[][];
    static int costH[][];
    static int costV[][];
    
    static final int INF = (int) 1e9;
    
    private static void solve() {
        
        int N = nextInt();
        int K = nextInt();
        int B = nextInt();
        
        arr= new int[N][];
        costH = new int[N][N];
        costV = new int[N][N];
        
        for(int i = 0; i < N; i++)
            arr[i] = nextIntArray(2);
        
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
        /*        
        for(int i = 0; i < sz; i++)
            print(String.format("%5d ", compress[i][0]));
        print('\n');

        for(int i = 0; i < sz; i++)
            print(String.format("%5d ", compress[i][1]));
        
        */
        for(int i = 0; i < N; i++) {
            
            for(int j = i + 1; j < N; j++) {
                
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