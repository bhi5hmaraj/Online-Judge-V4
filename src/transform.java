/*
ID: bs3861
LANG: JAVA
TASK: transform
 */
import java.util.*;
import java.io.*;
public class transform {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int N;
    
    static char[][] rotate90(char [][] grid) {
        char[][] rotated = new char[N][N];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                rotated[j][N - i - 1] = grid[i][j];
        
        return rotated;
    }
    static char[][] rotate180(char [][] grid) { 
        return rotate90(rotate90(grid));
    }
    static char[][] rotate270(char [][] grid) {
        return rotate90(rotate180(grid));
    }
    static char[][] reflect(char[][] grid) {
        char reflected[][] = new char[N][];
        for(int i=0;i<N;i++)
            reflected[i] = new StringBuilder(new String(grid[i])).reverse().toString().toCharArray();
        return reflected;
    }
    static boolean equals(char[][] grid1 , char[][] grid2) {
        for(int i=0;i<N;i++)
            if(!new String(grid1[i]).equals(new String(grid2[i])))
                return false;
        
        return true;
    }
    
    private static void solve() {
        
        N = nextInt();
        char[][] from = new char[N][];
        char[][] to   = new char[N][];
        
        for(int i=0;i<N;i++)
            from[i] = nextLine().toCharArray();
        for(int i=0;i<N;i++)
            to[i] = nextLine().toCharArray();
        
        if(equals(rotate90(from) , to)){
            println(1);
        }
        else if(equals(rotate180(from), to)) {
            println(2);
        }
        else if(equals(rotate270(from), to)) {
            println(3);
        }
        else if(equals(reflect(from), to)) {
            println(4);
        }
        else if(equals(rotate90(reflect(from)), to)  ||
                equals(rotate180(reflect(from)), to) ||
                equals(rotate270(reflect(from)), to)) {
            println(5);
        }
        else if(equals(from, to)) {
            println(6);
        }
        else {
            println(7);
        }
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    static String file = "transform";
    static final boolean OJ = true;
    public static void main(String[] args) throws IOException {
        if(!OJ) {
            reader = new BufferedReader(new InputStreamReader(System.in));
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        }
        else {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file + ".in")));
            writer = new PrintWriter(new BufferedWriter(new FileWriter(file + ".out")), false);
        }
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