import java.util.*;
import java.io.*;
public class StableNeighbors {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int colors[];
    static char map[] = {'R', 'O', 'Y','G', 'B', 'V'};
    static int getColor() { // gets a non-empty char
        for(int i=0;i<map.length;i++)
            if(colors[i] > 0)
                return i;
        
        return -1;
    }
    
    static boolean AC(StringBuilder sb) {
        for(int i=0;i<sb.length();i++)
            if(sb.charAt(i) == sb.charAt((i + 1) % sb.length()))
                return false;
        
        return true;
    }
    
    private static void solve() {
        
        int T = nextInt();
        for(int tc = 1;tc <= T;tc++) {
            int N = nextInt();
            colors = nextIntArray(6);
            int temp[] = Arrays.copyOf(colors, colors.length);
            Arrays.sort(temp);
            if(temp[3] + temp[4] < temp[5]) {
                println("Case #" + tc + ": IMPOSSIBLE");
                continue;
            }
            int maxCol = 0;
            for(int i=0;i<colors.length;i++)
                maxCol = colors[maxCol] < colors[i] ? i : maxCol;
            
            StringBuilder ans = new StringBuilder();
            N -= colors[maxCol];
            while(colors[maxCol]-->0)
                ans.append(map[maxCol]);
            while(N-->0) {
                int p = 0;
                int currLen = ans.length();
                int col = getColor();
                
                while(p < currLen && (ans.charAt(p) != ans.charAt((p + 1) % currLen)))
                    p++;
                
                if(p == currLen) {
                    p = 0;
                    while(p < currLen && (ans.charAt(p) == map[col] || ans.charAt((p + 1) % currLen) == map[col]))
                        p++;
                }
                // System.out.println("pos to ins = " + p);
                colors[col]--;
                ans.insert(p + 1, map[col]);
            }
            
            
            if(!AC(ans))
                throw new RuntimeException("WA for " + Arrays.toString(temp));
            
            println("Case #" + tc + ": " + ans);
        }
        
    }
    
    
    /************************ SOLUTION ENDS HERE ************************/
    
    
    
    
    
    /************************ TEMPLATE STARTS HERE **********************/
    
    public static void main(String[] args) throws IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));
        // writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false);
        writer = new PrintWriter("out.txt");
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