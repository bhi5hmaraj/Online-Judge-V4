import java.util.*;
import java.util.stream.Collectors;
import java.io.*;
public class Discounts {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Item {
        int idx , cost , type;
        Item (int... arg) {
            idx = arg[0];
            cost = arg[1];
            type = arg[2];
        }
    }
    
    @SuppressWarnings("unchecked")
    private static void solve() {
        
        
        int n = nextInt();
        int k = nextInt();
        
        ArrayList<Integer>[] cart = new ArrayList[k];
        for(int i = 0; i < k; i++)
            cart[i] = new ArrayList<>();
        
        Item arr[] = new Item[n];
        for(int i = 0; i < n; i++)
            arr[i] = new Item(i , nextInt() , nextInt());
        
        List<Integer> stools = Arrays.stream(arr).
                               filter(item -> item.type == 1).
                               map(item -> item.idx).
                               collect(Collectors.toList());
        
        stools.sort((id1 , id2) -> Integer.compare(arr[id2].cost, arr[id1].cost));
        int curr = Math.min(k - 1 , stools.size());
        boolean marked[] = new boolean[n];
        long discounted = 0;
        for(int i = 0; i < curr; i++) {
            discounted += arr[stools.get(i)].cost;
            marked[stools.get(i)] = true;
            cart[i].add(stools.get(i) + 1);
        }
        
        if(stools.size() >= k)
            discounted += Arrays.stream(arr).min(new Comparator<Item>() {
                public int compare(Item o1, Item o2) {
                    return o1.cost - o2.cost;
                };
            }).get().cost;
        else {
            int last = 0;
            for(; curr < k - 1; curr++) {
                for(; last < n; last++)
                    if(!marked[last]) {
                        marked[last] = true;
                        cart[curr].add(last + 1);
                        break;
                    }
            }
        }
        
        for(int i = 0; i < n; i++)
            if(!marked[i])
                cart[curr].add(i + 1);
        
        long total = (2L * Arrays.stream(arr).mapToLong(it -> it.cost).sum()) - discounted;
        println(String.format("%d.%d", total / 2 , 5 * (total % 2)));
        Arrays.stream(cart).forEach(c -> {
            print(c.size() + " ");
            c.stream().forEach(index -> print(index + " "));
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