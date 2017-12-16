import java.util.*;
import java.io.*;
public class PhoneNumber {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N = nextInt();
        HashMap<String , HashSet<String>> map = new HashMap<>();
        while(N-->0) {
            String name = next();
            HashSet<String> set = map.getOrDefault(name, new HashSet<>());
            int M = nextInt();
            while(M-->0)
                set.add(next());
            
            map.put(name, set);
        }
        
        println(map.size());
        map.forEach((k , v) -> {
            print(k + " ");
            ArrayList<String> arl = new ArrayList<>(v);
            ArrayList<String> ans = new ArrayList<>();
            for(int i = 0; i < arl.size(); i++) {
                boolean flag = false;
                for(int j = 0; j < arl.size(); j++)
                    if(i != j)
                        flag |= arl.get(j).endsWith(arl.get(i));
                
                if(!flag)
                    ans.add(arl.get(i));
            }
            
            print(ans.size() + " ");
            ans.stream().forEach(s -> print(s + " "));
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