import java.util.*;
import java.io.*;
public class SortingbySubsequences {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static void shuffleArray(int[] array) {
        Random random = new Random();
        for (int i = array.length - 1; i > 0; i--) {
            int index = random.nextInt(i + 1);
            int temp = array[index];
            array[index] = array[i];
            array[i] = temp;
        }
    }

    
    static int[] compress(int arr[]) {
        int temp[] = Arrays.copyOf(arr, arr.length);
        int pair[][] = new int[arr.length][2];
        for(int i = 0; i < arr.length; i++) {
            pair[i][0] = arr[i];
            pair[i][1] = i + 1;
        }
        shuffleArray(temp);
        Arrays.sort(temp);
        Arrays.sort(pair, (p1 , p2) -> p1[0] - p2[0]);
        for(int i = 0; i < arr.length; i++)
            arr[i] = Arrays.binarySearch(temp, arr[i]);
        for(int i = 0; i < arr.length; i++)
            temp[i] = pair[i][1];
        return temp;
    }
    
    private static void solve() {
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        int index[] = compress(arr);
        
        boolean marked[] = new boolean[n];
        ArrayList<ArrayList<Integer>> arl = new ArrayList<>();
        for(int i = 0; i < n; i++)
            if(!marked[i]) {
                ArrayList<Integer> cycle = new ArrayList<>();
                marked[i] = true;
                cycle.add(index[i]);
                int next = arr[i];
                while(!marked[next]) {
                    marked[next] = true;
                    cycle.add(index[next]);
                    next = arr[next];
                }
                arl.add(cycle);
            }
        
        println(arl.size());
        arl.stream().forEach(cycle -> {
            print(cycle.size() + " ");
            cycle.stream().forEach(id -> print(id + " "));
            print('\n');
        });
        
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