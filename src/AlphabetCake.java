import java.util.*;
import java.io.*;
public class AlphabetCake {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N , M;
    static char grid[][];
    static int getCount(int x1 , int y1 , int x2 , int y2) {
        int sum = 0;
        
        for(int i=Math.min(x1,x2);i <= Math.max(x1,x2);i++)
            for(int j=Math.min(y1,y2);j <= Math.max(y1,y2);j++)
                sum += grid[i][j] == '?' ? 1 : 0;
        
        return sum;
    }
    static void fill(int x1 , int y1 , int x2 , int y2 , char ch) {
        for(int i=Math.min(x1,x2);i <= Math.max(x1,x2);i++)
            for(int j=Math.min(y1,y2);j <= Math.max(y1,y2);j++)
                grid[i][j] = ch;
    }
    private static int sumOfRegion(int prefixSum[][],int _x1,int _y1,int _x2,int _y2) {
        int x1 = Math.min(_x1,_x2);
        int x2 = Math.max(_x1,_x2);
        int y1 = Math.min(_y1,_y2);
        int y2 = Math.max(_y1,_y2);
        
        int entire = prefixSum[x2][y2];
        int W = y1 > 0 ? prefixSum[x2][y1 - 1] : 0;
        int N = x1 > 0 ? prefixSum[x1 - 1][y2] : 0;
        int NW = x1 > 0 && y1 > 0 ? prefixSum[x1 - 1][y1 - 1] : 0;      
        return entire - N - W + NW;
    }
    static void updatePrefixSum(int prefixSum[][]) {
        for(int i=0;i<N;i++)
            prefixSum[i][0] = grid[i][0] == '?' ? 1 : 0;

        for(int i=0;i<N;i++)
            for(int j=1;j<M;j++)
                prefixSum[i][j] = prefixSum[i][j-1] + (grid[i][j] == '?' ? 1 : 0);

        for(int i=1;i<N;i++)
            for(int j=0;j<M;j++)
                prefixSum[i][j] += prefixSum[i-1][j];   
    }
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            N = nextInt();
            M = nextInt();
            grid = new char[N][];
            for(int i=0;i<N;i++)
                grid[i] = nextLine().toCharArray();
            
            boolean marked[] = new boolean[26];
            int prefixSum[][] = new int[N][M];
            updatePrefixSum(prefixSum);
            for(int i=0;i<N;i++) {
                for(int j=0;j<M;j++) {
                    if(grid[i][j] != '?' && !marked[grid[i][j] - 'A']) {
                        
                        int maxArea = 0;
                        int x1 = -1 , y1 = -1;
                        int x2 = -1 , y2 = -1;
                        for(int k=0;k<N;k++)
                            for(int l=0;l<M;l++) {
                                for(int m=0;m<N;m++) {
                                    for(int n=0;n<M;n++) {
                                        if(i >= Math.min(k,m) && i <= Math.max(k,m)
                                            && j >= Math.min(l,n) && j <= Math.max(l,n) &&
                                                (grid[k][l] == grid[i][j] || grid[k][l] == '?')) {
                                            int area = (Math.abs(k - m) + 1) * (Math.abs(l - n) + 1);
                                            if(area == getCount(m, n, k, l) + 1 && area > maxArea) {
                                            //if(area == sumOfRegion(prefixSum, k, l, m, n) + 1 && area > maxArea) {
                                                maxArea = area;
                                                x1 = k;
                                                y1 = l;
                                                x2 = m;
                                                y2 = n;
                                            }
                                        }
                                    }
                                }
                            }
                        
                        fill(x1, y1, x2, y2, grid[i][j]);
                        marked[grid[i][j] - 'A'] = true;
                        updatePrefixSum(prefixSum);
                    }
                }
            }
            
            println("Case #" + tc + ":");
            for(int i=0;i<N;i++)
                println(new String(grid[i]));
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        //reader = new BufferedReader(new FileReader("A-large.in"));
        //writer = new PrintWriter("out.txt");
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