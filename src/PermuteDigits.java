import java.util.*;
import java.io.*;
public class PermuteDigits {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    
    private static void solve() {
        
        
        long a = nextLong();
        long b = nextLong();
        
        TreeMap<Integer, Integer> multiSet = new TreeMap<>();
        int digitA[] = String.valueOf(a).chars().map(Character::getNumericValue).toArray();
        int digitB[] = String.valueOf(b).chars().map(Character::getNumericValue).toArray();
        
        for(int d : digitA)
            multiSet.put(d, multiSet.getOrDefault(d, 0) + 1);
        
        if(digitB.length > digitA.length) {
            println(multiSet.descendingMap().entrySet().stream().map(e -> {
                    String s = "";
                    for(int j = 0; j < e.getValue(); j++)
                        s += e.getKey();
                    return s;
                }).reduce((s1, s2) -> s1.concat(s2)).get());
            
            return;
        }
        
        if(digitA.length > digitB.length) 
            multiSet.put(0, multiSet.get(0) - (digitA.length - digitB.length));
        
        String ans = "";
        String prefix = "";
        
        for(int i = 0; i < digitB.length; i++) {
            Integer floor = multiSet.lowerKey(digitB[i]);
            if(floor != null) { 
                multiSet.put(floor, multiSet.get(floor) - 1);
                String suffix = 
                multiSet.descendingMap().entrySet().stream().map(e -> {
                    String s = "";
                    for(int j = 0; j < e.getValue(); j++)
                        s += e.getKey();
                    return s;
                }).reduce((s1, s2) -> s1.concat(s2)).get();
                
                ans = prefix + floor + suffix;
                multiSet.put(floor, multiSet.get(floor) + 1);
            }
            
            if(!multiSet.containsKey(digitB[i]))
                break;
            else {
                prefix += digitB[i];
                int freq = multiSet.get(digitB[i]);
                if(freq == 1)
                    multiSet.remove(digitB[i]);
                else 
                    multiSet.put(digitB[i], freq - 1);
            }
        }
        
        if(prefix.length() == digitB.length)
            println(prefix);
        else 
            println(Long.parseLong(ans));
        
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