import java.util.*;
import java.io.*;
public class Timetable {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int memo[][];
    static int minMiss[][];
    
    static int rec(int idx, int rem) {
        // System.out.println("idx " + idx + " rem " + rem);
        if(idx < 0)
            return 0;
        else if(memo[idx][rem] != -1)
            return memo[idx][rem];
        else {
            int min = Integer.MAX_VALUE;
            for(int i = 0; i <= Math.min(rem , minMiss[idx].length - 1); i++)
                min = Math.min(min , minMiss[idx][i] + rec(idx - 1, rem - i));
            
            return memo[idx][rem] = min;
        }
    }
    
    private static void solve() {
        
        
        int n = nextInt();
        int m = nextInt();
        int k = nextInt();
        
        int table[][] = new int[n][];
        for(int i = 0; i < n; i++) {
            table[i] = nextLine().chars().map(Character::getNumericValue).toArray();
            for(int j = 1; j < m; j++)
                table[i][j] += table[i][j - 1];
        }
        
        minMiss = new int[n][]; // minMiss[*][i] =  min days to go if want to miss i days
        for(int i = 0; i < n; i++) {
            // System.out.println(Arrays.toString(table[i]));
            minMiss[i] = new int[table[i][table[i].length - 1] + 1];
            int map[] = new int[minMiss[i].length];
            Arrays.fill(map, -1);
            for(int j = 0; j < table[i].length; j++)
                map[table[i][j]] = map[table[i][j]] == -1 ? j : map[table[i][j]];
            
            int oldLen = minMiss[i].length - 1;
            // System.out.println("old " + oldLen);
            if(oldLen > 0)
                minMiss[i][oldLen - 1] = 1; // just attend one day
            
            for(int miss = 0; miss < oldLen - 1; miss++) {
                int notMiss = oldLen - miss;
                minMiss[i][miss] = Integer.MAX_VALUE;
                for(int j = 0; j < m; j++)
                    if(table[i][j] >= notMiss)
                        minMiss[i][miss] = Math.min(minMiss[i][miss] , j - map[table[i][j] - notMiss + 1] + 1);
            }
        }
        /*
        System.out.println("minMiss");
        for(int i = 0; i < n; i++)
            System.out.println(i + " " + Arrays.toString(minMiss[i]));
        */
        memo = new int[n][k + 1];
        for(int a[] : memo) Arrays.fill(a, -1);
        long start = System.nanoTime();
        println(rec(n - 1, k));
        System.out.println("Time " + (System.nanoTime() - start) / 1e9);
        
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