import java.util.*;
import java.io.*;
public class Tshirtbuying {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
            int n = nextInt();
            int p[] = nextIntArray(n);
            int a[] = nextIntArray(n);
            int b[] = nextIntArray(n);
            
            TreeSet<Integer> front[] = new TreeSet[4];
            TreeSet<Integer> back[] = new TreeSet[4];
            for(int i = 1; i <= 3; i++) {
                front[i] = new TreeSet<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return p[o1] - p[o2];
                    }
                });
                back[i] = new TreeSet<>(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return p[o1] - p[o2];
                    }
                });
            }
        
            for(int i = 0; i < n; i++) {
                front[a[i]].add(i);
                back[b[i]].add(i);
            }
        
            int m = nextInt();
            while(m-->0) {
                int c = nextInt();
                if(front[c].isEmpty() && back[c].isEmpty())
                    print("-1 ");
                else  {
                    int idx = (front[c].isEmpty() ? Integer.MAX_VALUE : p[front[c].first()]) < 
                              (back[c].isEmpty() ? Integer.MAX_VALUE : p[back[c].first()]) ? 
                                      front[c].first() : back[c].first();
                    front[a[idx]].remove(idx);
                    back[b[idx]].remove(idx);
                    print(p[idx] + " ");
                }
                    
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