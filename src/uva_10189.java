import java.util.*;
import java.io.*;
public class uva_10189 {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    /*
     * Refined version of my old code
     */
    
    private static void solve() {

        int tc = 1;
        ArrayList<Pair> arl;
        while ((r = nextInt()) != 0 && (c = nextInt()) != 0) {
            println((tc > 1 ? '\n' : "") + "Field #" + (tc++) + ":");
            char arr[][] = new char[r][];
            for (int i = 0; i < r; i++)
                arr[i] = nextLine().toCharArray();

            for (int i = 0; i < r; i++) {
                for (int j = 0; j < c; j++) {
                    if (arr[i][j] == '*') {
                        print('*');
                        continue;
                    } else {
                        arl = validPoints(i, j);
                        int adj = 0;
                        for (Pair p : arl) {
                            if (arr[p.i][p.j] == '*')
                                adj++;
                        }
                        print(adj);
                    }
                }
                print('\n');
            }
        }
    }

    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }

    static int r, c;

    static boolean isValid(int i, int j) {
        return (i >= 0 && i < r && j >= 0 && j < c);
    }

    static ArrayList<Pair> validPoints(int l, int m) {
        ArrayList<Pair> arl = new ArrayList<>();
        for (int i = l - 1; i <= l + 1; i++) {
            for (int j = m - 1; j <= m + 1; j++) {
                if (!(i == l && j == m)) {
                    if (isValid(i, j)) {
                        arl.add(new Pair(i, j));
                    }
                }
            }
        }
        return arl;
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