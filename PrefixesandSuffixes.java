import java.util.*;
import java.io.*;

public class PrefixesandSuffixes {
    
    
    
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
    
    static ArrayList<Integer>[] adj;
    static int reachable[];
    
    static int countReachable(int u, int par) {
    	for(int v : adj[u])
    		if(v != par)
    			reachable[u] += countReachable(v, u);
    	
    	return reachable[u];
    }
    
    
    private static void solve() {
    	
    	char str[] = nextLine().toCharArray();
    	int prefix[] = KMPPrefixFunction(str);
    	
    	System.out.println(Arrays.toString(prefix));
    	
    	adj = new ArrayList[str.length + 1];
    	reachable = new int[str.length + 1];
    	
    	for(int i = 0; i <= str.length; i++)
    		adj[i] = new ArrayList<>();
    	
    	for(int i = 1; i <= str.length; i++) {
    		reachable[prefix[i - 1]]++;
    		adj[prefix[i - 1]].add(i);
    	}
    	
    	countReachable(0, -1);
    	
    	ArrayDeque<int[]> stack = new ArrayDeque<>();
    	stack.push(new int[] {str.length, 1});
    	
    	for(int border = prefix[str.length - 1]; border > 0; border = prefix[border])
    		stack.push(new int[] {border, reachable[border]});
    	
    	println(stack.size());
    	stack.stream().forEach(pair -> println(pair[0] + " " + pair[1]));
    	
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