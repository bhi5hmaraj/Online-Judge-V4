import java.util.*;
import java.io.*;
public class FriendsorNot  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        int N = nextInt();
        int K = nextInt();
        HashMap<String , HashMap<String , TreeSet<Integer>>> record = new HashMap<>();
        while(N-->0) {
            String from = next();
            String to = next();
            int t = nextInt();
            HashMap<String , TreeSet<Integer>> hm = record.getOrDefault(from, new HashMap<>());
            TreeSet<Integer> ts = hm.getOrDefault(to, new TreeSet<>());
            ts.add(t);
            hm.put(to, ts);
            record.put(from, hm);
        }
        
        HashSet<String> set = new HashSet<>();
        ArrayList<String> ans = new ArrayList<>();
        record.forEach((from , hm) -> {
            hm.forEach((to , ts) -> {
                String hash = from + " " + to;
                if(record.containsKey(to) && record.get(to).containsKey(from) && !set.contains(hash)) {
                    set.add(hash);
                    set.add(to + " " + from);
                    TreeSet<Integer> otherTs = record.get(to).get(from);
                    for(int t : ts) {
                        boolean flag = otherTs.contains(t);
                        if(flag)
                            otherTs.remove(t);
                        Integer ceil = otherTs.ceiling(t - K);
                        Integer floor = otherTs.floor(t + K); 
                        if(ceil != null && floor != null && ceil.intValue() <= floor.intValue()) {
                            ans.add(hash);
                            break;
                        }
                        if(flag)
                            otherTs.add(t);
                    }
                }
            });
        });

        println(ans.size());
        ans.stream().forEach(s -> println(s));
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