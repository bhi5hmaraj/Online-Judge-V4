import java.util.*;
import java.io.*;
public class FeedwithCandy {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/

    static Comparator<int[]> comp = new Comparator<int[]>() {
        @Override
        public int compare(int[] o1, int[] o2) {
            return o1[1] != o2[1] ? o1[1] - o2[1] : o1[0] - o2[0];
        }
    };
    
    static int getMax(int pos) {
        int curr = x;
        TreeSet<int[]>[] set = new TreeSet[2];
        set[0] = new TreeSet<>(comp);
        set[1] = new TreeSet<>(comp);
        for(int i = 0; i < n; i++)
            set[candy[i][0]].add(new int[]{i , candy[i][1] , candy[i][2]});
        int probe[] = new int[]{Integer.MAX_VALUE , x , -1};
        int cnt = 0;
        while(set[pos].floor(probe) != null) {
            println("turn " + pos);
            int max[] = set[pos].first();
            for(int entry[] : set[pos].subSet(set[pos].first(), probe))
                max = entry[2] > max[2] ? entry : max;
            curr += max[2];
            println("candy before");
            set[pos].stream().forEach(a -> print(Arrays.toString(a) + " "));
            set[pos].remove(max);
            println("\ncandy taken");
            println(Arrays.toString(max));
            set[pos].stream().forEach(a -> print(Arrays.toString(a) + " "));
            print('\n');
            pos ^= 1;
            probe[1] = curr;
            cnt++;
        }
        
        return cnt;
    }
    static int x;
    static int candy[][];
    static int n;
    private static void solve() {
        
        n = nextInt();
        x = nextInt();
        candy = new int[n][];
        for(int i = 0; i < n; i++)
            candy[i] = nextIntArray(3);
        
       println(Math.max(getMax(0) , getMax(1)));
       
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