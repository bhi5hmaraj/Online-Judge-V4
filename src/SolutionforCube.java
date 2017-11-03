import java.util.*;
import java.io.*;
public class SolutionforCube {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static boolean check(int a[] , int b[] , int cube[]) {
        int aux1[] = new int[8];
        int aux2[] = new int[8];
        for(int i = 0; i < 8; i++)
            aux1[(i + 2) % 8] = a[i];
        for(int i = 0; i < 8; i++)
            aux2[(i - 2 + 8) % 8] = a[i];
        
        boolean f1 = true , f2 = true;
        for(int i = 0; i < 8; i += 2) {
            f1 &= cube[aux1[i + 1]] == cube[aux1[i]] && cube[b[i]] == cube[aux1[i]] && cube[b[i + 1]] == cube[aux1[i]];
            f2 &= cube[aux2[i + 1]] == cube[aux2[i]] && cube[b[i]] == cube[aux2[i]] && cube[b[i + 1]] == cube[aux2[i]];
        }
        
        return f1 | f2;
    }
    
    private static void solve() {
        
        int[] cube = nextIntArrayOneBased(24);
        int inv[] = {2,5,0,4,3,1};
        boolean filled[] = new boolean[6];
        int cnt = 0;
        for(int i = 0; i < 6; i++) {
            int col = cube[4 * i + 1];
            boolean flag = true;
            for(int j = 2; j <= 4; j++)
                flag &= (cube[4 * i + j] == col);
            
            filled[i] = flag;
            cnt += flag ? 1 : 0;
        }        
        // println("cnt " + cnt + " filled " + Arrays.toString(filled));
        if(cnt != 2)
            println("NO");
        else {
            for(int i = 0; i < 6; i++)
                if(filled[i] && !filled[inv[i]]) {
                    println("NO");
                    return;
                }
            
            int pos = -1;
            for(int i = 0; i < 6; i++)
                if(filled[i]) {
                    pos = i;
                    break;
                }
            
            int a[] , b[];
            a = b = null;
            switch(pos) {
            case 0:
                a = new int[]{13,14,5,6,17,18,21,22};
                b = new int[]{15,16,7,8,19,20,23,24};
                break;
            case 1:
                a = new int[]{16,14,3,4,17,19,10,9};
                b = new int[]{15,13,1,2,18,20,12,11};
                break;
            case 3:
                a = new int[]{24,22,1,3,5,7,9,11};
                b = new int[]{23,21,2,4,6,8,10,12};
                break;
            }
            
            println(check(a, b, cube) ? "YES" : "NO");
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