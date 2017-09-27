import java.util.*;
import java.io.*;
public class SeregaandFun {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int Q = nextInt();

        final int BLOCK_SIZE = (int) (Math.sqrt(n) + 1); 
        LinkedList<Integer> blocks[] = new LinkedList[n / BLOCK_SIZE + 1];
        HashMap<Integer , Integer> freq[] = new HashMap[n / BLOCK_SIZE + 1];
        
        for(int i = 0; i < blocks.length; i++) {
            blocks[i] = new LinkedList<>();
            freq[i] = new HashMap<>();
        }
        for(int i = 0; i < n; i++) {
            blocks[i / BLOCK_SIZE].add(arr[i]);
            freq[i / BLOCK_SIZE].put(arr[i], freq[i / BLOCK_SIZE].getOrDefault(arr[i] , 0) + 1);
        }
        
        int ans = 0;
        while(Q-->0) {
            int type = nextInt();
            if(type == 1) {
                int L = ((nextInt() + ans - 1) % n);
                int R = ((nextInt() + ans - 1) % n);
                boolean add = false;
                for(int i = L / BLOCK_SIZE; i < R / BLOCK_SIZE; i++) {
                    add = true;
                    int toRem = blocks[i].pollLast();
                    freq[i].put(toRem, freq[i].get(toRem) - 1);
                    if(freq[i].get(toRem).intValue() == 0)
                        freq[i].remove(toRem);
                    blocks[i + 1].addFirst(toRem);
                    freq[i + 1].put(toRem, freq[i + 1].getOrDefault(toRem, 0) + 1);
                }
                
                int toRem = blocks[R / BLOCK_SIZE].remove((R % BLOCK_SIZE) + (add ? 1 : 0));
                freq[R / BLOCK_SIZE].put(toRem, freq[R / BLOCK_SIZE].get(toRem) - 1);
                if(freq[R / BLOCK_SIZE].get(toRem).intValue() == 0)
                    freq[R / BLOCK_SIZE].remove(toRem);
                blocks[L / BLOCK_SIZE].add(L % BLOCK_SIZE, toRem);
                freq[L / BLOCK_SIZE].put(toRem, freq[L / BLOCK_SIZE].getOrDefault(toRem, 0) + 1);
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