import java.util.*;
import java.io.*;
public class Mikeandstrings {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static HashSet<String> getCyclicRotation(String str) {
        
        HashSet<String> set = new HashSet<>();
        for(int i=str.length() - 1;i >= 0;i--) {
            String shift = str.substring(i) + str.substring(0, i);
            ArrayList<Integer> arl = disp.getOrDefault(shift, new ArrayList<>());
            arl.add(i);
            disp.put(shift, arl);
            set.add(shift);
        }
        
        // System.out.println("set " + set);
        
        return set;
    }
    
    static HashMap<String , ArrayList<Integer>> disp = new HashMap<>();
    
    private static void solve() {
        
        int N = nextInt();
        String arr[] = new String[N];
        for(int i=0;i<N;i++)
            arr[i] = nextLine();
        
        int len = arr[0].length();
        HashSet<String> set = getCyclicRotation(arr[0]);
        for(String s : arr)
            if(!set.contains(s)) {
                println(-1);
                return;
            }
        
        HashMap<String , Integer> freq = new HashMap<>();
        for(String s : arr)
            freq.put(s, freq.getOrDefault(s, 0) + 1);
        
        int min = Integer.MAX_VALUE;
        // println("disp " + disp);
        
        for(Map.Entry<String, Integer> e : freq.entrySet()) {
            int cost = 0;
            
            for(Map.Entry<String, Integer> f : freq.entrySet()) { 
                int minShift = Integer.MAX_VALUE;
                
                for(int d1 : disp.get(e.getKey()))
                    for(int d2 : disp.get(f.getKey()))
                        minShift = Math.min(minShift,(d1 - d2 + len) % len);
                
                cost += f.getValue() * minShift;
            }
            
            min = Math.min(min,cost);
        }
    
        println(min);

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