import java.util.*;
import java.io.*;
public class PursuingtheHappiness {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static int[] KMPPrefixFunction(char str[]) {
        int n = str.length;
        int prefix[] = new int[n];  // Stores the length of largest border for a prefix 
        for(int i = 1; i < n; i++) {
            int border;
            for(border = prefix[i - 1]; border > 0 && str[border] != str[i]; border = prefix[border - 1])
                ;
            prefix[i] = str[i] == str[border] ? border + 1: 0;
        }
        
        return prefix;
    }
    
    static int index(String str , String pat) {
        int prefix[] = KMPPrefixFunction((pat + "$" + str).toCharArray());
        for(int i = 0; i < prefix.length; i++)
            if(prefix[i] == pat.length())
                return i - 2 * pat.length();
        
        return -1;
    }
    
    private static void solve() {
        
        String str = nextLine();
        String pat = "happiness";
        char aug[] = (pat + "$" + str).toCharArray();
        int prefix[] = KMPPrefixFunction(aug);
        ArrayList<Integer> pos = new ArrayList<>();
        for(int i = 0; i < prefix.length; i++) {
            if(prefix[i] == pat.length()) 
                pos.add(i - 2 * pat.length());
        }
        
        // println(pos);
        if(pos.size() > 2)
            println("NO");
        else if(pos.size() == 2) {
            println("YES");
            println((pos.get(0) + 1) + " " + (pos.get(1) + 2));
        }
        else if(pos.size() == 1) {
            println("YES");
            println((pos.get(0) + 1) + " " + (pos.get(0) + 2));
        }
/*
        else if(index(str, "ahppiness") == 0) 
            println("YES\n2 3");*/
        else {
            println("YES");
            Random rand = new Random();
            int a = rand.nextInt(str.length()) + 1;
            int b = rand.nextInt(str.length()) + 1;
            while(a == b) {
                a = rand.nextInt(str.length()) + 1;
                b = rand.nextInt(str.length()) + 1;
            }
            println(a + " " + b);
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