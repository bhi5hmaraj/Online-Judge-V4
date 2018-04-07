import java.util.*;
import java.io.*;
public class SavingTheUniverseAgain {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static long getCost(char str[]) {
        long cost = 0;
        long mult = 1;
        for(char ch : str)
            if(ch == 'C')
                mult <<= 1;
            else
                cost += mult;
        
        return cost;
            
    }
    
    private static void solve() {
        
        
        for(int tc = 1 , T = nextInt(); tc <= T; tc++) {
            
            long D = nextLong();
            char str[] = next().toCharArray();
            
            int iter = 0;
            while(getCost(str) > D) {
//                System.out.println(new String(str) + " cost = " + getCost(str));
                int ptr = str.length - 1;
                for(; ptr > 0 && !(str[ptr - 1] == 'C' && str[ptr] == 'S'); ptr--)
                    ;
                
                if(ptr == 0) // SSS...CCCC cant change anything
                    break;
                else {  // swap
                    char temp = str[ptr];
                    str[ptr] = str[ptr - 1];
                    str[ptr - 1] = temp;
                    iter++;
                }
                
            }
            
            println("Case #" + tc + ": " + (getCost(str) <= D ? iter : "IMPOSSIBLE"));
            
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