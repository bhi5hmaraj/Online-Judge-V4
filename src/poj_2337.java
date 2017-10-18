import java.util.*;
import java.io.*;
public class poj_2337  {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
    static class Edge implements Comparable<Edge> {
        int v , key;
        Edge(int v , int k) {
            this.v = v;
            this.key = k;
        }
        @Override
        public int compareTo(Edge o) {
            return words[o.key].compareTo(words[key]);
        }
    }
    
    static String words[];
    static ArrayList<Edge> adj[];
    
    
    
    private static void solve() {
        
        
        int T = nextInt();
        while(T-->0) {
            int n = nextInt();
            HashMap<String , Integer> map = new HashMap<String , Integer>();
            words = new String[n];
            adj = new ArrayList[26];
            
            int inDegree[] = new int[26];
            
            for(int i = 0; i < 26; i++)
                adj[i] = new ArrayList<>();
            
            for(int i = 0; i < n; i++)
                map.put(words[i] = nextLine(), i);
            
            for(int i = 0; i < n; i++) { 
                adj[words[i].charAt(0) - 'a'].add(new Edge(words[i].charAt(words[i].length() - 1) - 'a', i));
                inDegree[words[i].charAt(words[i].length() - 1) - 'a']++;
            }
            for(int i = 0; i < n; i++)
                Collections.sort(adj[i]);
            
            boolean equal = true;
            int cntOut = 0;
            int cntIn  = 0;
            
            for(int i = 0; i < 26; i++) {
                equal &= inDegree[i] == adj[i].size();
                cntOut += adj[i].size() == inDegree[i] + 1 ? 1 : 0;
                cntIn  += inDegree[i] == adj[i].size() + 1 ? 1 : 0;
            }
            
            if(equal || cntIn == cntOut) {
                
            }
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