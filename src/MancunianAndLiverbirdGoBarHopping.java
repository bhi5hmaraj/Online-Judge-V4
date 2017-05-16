import java.util.*;
import java.io.*;
public class MancunianAndLiverbirdGoBarHopping {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    private static void solve() {
        
        int N = nextInt();
        int[][] graph = new int[N + 1][2];
        int[][] inverse = new int[N + 1][2];
        
        int[] cnt = new int[N + 1];
        int[] cntInv = new int[N + 1];
        int sz1[] = new int[N + 1];
        int sz2[] = new int[N + 1];
        Arrays.fill(cnt, 1);
        Arrays.fill(cntInv, 1);
        for(int i=1;i<=N - 1;i++) {
            int dir = nextInt();
            if(dir == 1) {
                graph[i][sz1[i]++] = (i + 1);
                inverse[i + 1][sz2[i + 1]++] =  i;
            }
            else {
                graph[i + 1][sz1[i + 1]++] = i;
                inverse[i][sz2[i]++] = (i + 1);
            }
        }
        
        for(int i=1;i<=N;i++) {
            if(sz1[i] == 0) {
                for(int start : inverse[i]) {
                    int curr = start;
                    int sz = 1;
                    while(true) {
                        cnt[curr] += sz++;
                        if(sz2[curr] == 0)
                            break;
                        curr = inverse[curr][0];
                    }
                }
                
            }
            if(sz2[i] == 0) {
                for(int start : graph[i]) {
                    int curr = start;
                    int sz = 1;
                    while(true) {
                        cntInv[curr] += sz++;
                        if(sz1[curr] == 0)
                            break;
                        curr = graph[curr][0];
                    }
                }
            }
        }
        
        // System.out.println("cnt " + Arrays.toString(cnt));
        // System.out.println("cntInv " + Arrays.toString(cntInv));
        int Q = nextInt();
        boolean flag = true;
        while(Q-->0) {
            if(nextChar() == 'Q') 
                println(flag ? cnt[nextInt()] : cntInv[nextInt()]);
            else
                flag = !flag;
        }
        
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/

    public static void main(String[] args) throws Exception {
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