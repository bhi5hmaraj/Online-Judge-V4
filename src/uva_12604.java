import java.util.*;
import java.io.*;
public class uva_12604  {
    
    
    
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
    
    
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            char alph[] = nextLine().toCharArray();
            String word = nextLine();
            String encrypt = nextLine();
            
            int inv[] = new int[128];
            for(int i = 0; i < alph.length; i++)
                inv[alph[i]] = i;
            
            ArrayList<Integer> possible = new ArrayList<>();
            for(int i = 0; i < alph.length; i++) {
                StringBuilder sb = new StringBuilder(word + "$");
                for(int j = 0; j < encrypt.length(); j++)
                    sb.append(alph[(inv[encrypt.charAt(j)] - i + alph.length) % alph.length]);
                
                int prefix[] = KMPPrefixFunction(sb.toString().toCharArray());
                
                int cnt = 0;
                for(int j = 0; j < encrypt.length(); j++)
                    cnt += (prefix[j + word.length() + 1] == word.length()) ? 1 : 0; 
                        
                if(cnt == 1)
                    possible.add(i);
                    
            }
            
            println(possible.isEmpty() ? "no solution" 
                   :possible.size() == 1 ? "unique: " + possible.get(0) 
                   :"ambiguous: " + String.join(" ", possible.stream().map(String::valueOf).toArray(String[]::new))
                    );
            
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