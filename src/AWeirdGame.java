import java.util.*;
import java.io.*;
public class AWeirdGame {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static char S[] , T[];
    static TreeSet<Integer>[] set;
    
    static boolean check(int L , int R) {
        int last = -1;
        for(char ch : T) {
            Integer ceil = set[ch - 'a'].higher(last);
            if(ceil == null)
                return false;
            else if(ceil >= L && ceil <= R) {
                ceil = set[ch - 'a'].higher(R);
                if(ceil == null)
                    return false;
            }
                
            last = ceil;
        }
        
        return true;
    }
    
    private static void solve() {
        
        
        int t = nextInt();
        while(t-->0) {
            S = nextLine().toCharArray();
            T = nextLine().toCharArray();
            set = new TreeSet[26];
            for(int i = 0; i < 26; i++)
                set[i] = new TreeSet<>();
            for(int i = 0; i < S.length; i++)
                set[S[i] - 'a'].add(i);
            
            /*
            for(int i = 0; i < 26; i++)
                println((char)('a' + i) + " " + set[i]);
            */
            int end = 0;
            long cnt = 0;
            for(int i = 0; i < S.length; i++) {
                int opt = i + 1;
                int L = end;
                int R = i;
                while(L <= R) {
                    int mid = (L + R) >> 1;
                    boolean ret = check(mid, i);
                    // println("L " + mid + " R " + i + " ret "+ ret); 
                    if(ret) {
                        opt = mid;
                        R = mid - 1;
                    }
                    else
                        L = mid + 1;
                }
                cnt += i - opt + 1;
                end = opt;
            }
            
            println(cnt);
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