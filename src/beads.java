/*
ID: bs3861
LANG: JAVA
TASK: beads
*/
import java.util.*;
import java.io.*;
public class beads {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int N = nextInt();
        char str[] = nextLine().toCharArray();
        int max = 0;
        for(int i=0;i<N;i++) {
            int left = (i - 1 + N) % N; // points at first default in anti clockwise direction
            char first = str[i] == 'w' ? 0 : str[i];
            for(;left != i && (str[left] == 'w' || first == 0 || str[left] == first);left = (left - 1 + N) % N)
                if(str[left] != 'w' && first == 0)
                    first = str[left];
            
            int right = (i + 1) % N;    // points at first default in clockwise direction
            first = 0;
            for(;right != (left + 1) % N && (str[right] == 'w' || first == 0 || str[right] == first);right = (right + 1) % N)
                if(str[right] != 'w' && first == 0)
                    first = str[right];
            
            // System.out.printf("i = %d , left = %d , right = %d\n" , i , left , right);
            
            if(left == i)
                max = N;
            else
                max = Math.max(max,(right - i - 1 + N) % N + (i - left + N) % N);
        }
        
        println(max);
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException  {
        // reader = new BufferedReader(new InputStreamReader(System.in));
        // writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        reader = new BufferedReader(new InputStreamReader(new FileInputStream("beads.in")));
        writer = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")), false);
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