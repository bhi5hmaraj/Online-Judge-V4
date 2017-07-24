import java.util.*;
import java.io.*;
public class StringReconstruction  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        int n = nextInt();
        final int MAX = (int) 2e6; 
        ArrayList<Integer> toAdd[]    = new ArrayList[MAX + 2];
        ArrayList<Integer> toRemove[] = new ArrayList[MAX + 2];
        String arr[] = new String[n];
        
        int startPos[][] = new int[n][];
        int maxLen = 0;
        for(int i = 0; i < n; i++) {
            arr[i] = next();
            int m = arr[i].length();
            int k = nextInt();
            startPos[i] = new int[k];
            for(int j = 0; j < k; j++) {
                int L = nextInt();
                int R = L + m;
                maxLen = Math.max(maxLen , R - 1);
                startPos[i][j] = L;
                toAdd[L] = toAdd[L] == null ? new ArrayList<>() : toAdd[L];
                toRemove[R] = toRemove[R] == null ? new ArrayList<>() : toRemove[R];
                toAdd[L].add(i);
                toRemove[R].add(i);
            }
        }
        char ans[] = new char[maxLen];
        HashMap<Integer , Integer> multiSet = new HashMap<>();
        for(int i = 1; i <= maxLen; i++) {
            
            //println("i " + i + " toAdd " + toAdd[i] + " toRemove " + toRemove[i]);
            
            if(toRemove[i] != null)
                for(int a : toRemove[i]){
                    int f = multiSet.get(a);
                    if(f > 1)
                        multiSet.put(a, f - 1);
                    else
                        multiSet.remove(a);
                }
                    
            if(toAdd[i] != null)
                for(int a : toAdd[i])
                    multiSet.merge(a, 1, Integer::sum);
            
            if(multiSet.isEmpty())
                ans[i - 1] = 'a';
            else {
                int elem = 0;
                for(Map.Entry<Integer, Integer> e : multiSet.entrySet()) {
                    elem = e.getKey();
                    break;
                }
                int lo = 0 , hi = startPos[elem].length - 1;
                int floor = -1;
                while(lo <= hi) {
                    int mid = (lo + hi) >> 1;
                    if(startPos[elem][mid] <= i) {
                        floor = startPos[elem][mid];
                        lo = mid + 1;
                    }
                    else
                        hi = mid - 1;
                }
                
                ans[i - 1] = arr[elem].charAt(i - floor);
            }
        }
        
        println(new String(ans));
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