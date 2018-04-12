import java.util.*;
import java.io.*;
public class MergeEquals {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Pair implements Comparable<Pair> {
        
        int index;
        long val;
        
        public Pair(long val, int index) {
            this.val = val;
            this.index = index;
        }

        @Override
        public int compareTo(Pair o) {
            return val != o.val ? Long.compare(val, o.val) : index - o.index;
        }
    }
    
    private static void solve() {
        
        int n = nextInt();
        int arr[] = nextIntArray(n);
        
        HashMap<Long, PriorityQueue<Pair>> map = new HashMap<>();
        
        for(int i = 0; i < n; i++) {
            int temp = arr[i];
            while(temp % 2 == 0)
                temp >>= 1;
            
            PriorityQueue<Pair> pq = map.getOrDefault(1L * temp, new PriorityQueue<>());
            pq.add(new Pair(arr[i], i));
            map.put(1L * temp, pq);
        }
        
        ArrayList<Pair> collect = new ArrayList<>();
        
        map.forEach((k, pq) -> {
            
            while(pq.size() > 1) {
                Pair first = pq.remove();
                Pair second = pq.remove();
                if(first.val == second.val)
                    second.val <<= 1;
                else
                    collect.add(first);
                pq.add(second);
            }
            
            collect.add(pq.remove());
            
        });
        
        Collections.sort(collect, (p1, p2) -> p1.index - p2.index);
        println(collect.size());
        collect.stream().forEach(p -> print(p.val + " "));
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