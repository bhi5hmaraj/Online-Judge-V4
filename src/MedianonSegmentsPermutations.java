import java.util.*;
import java.io.*;

public class MedianonSegmentsPermutations {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    
    private static void solve() {
    	
    	int n = nextInt();
    	int m = nextInt();
    	int p[] = nextIntArray(n);
    	
    	HashMap<Integer, Integer>[] left  = new HashMap[2];
    	HashMap<Integer, Integer>[] right = new HashMap[2];
    	
    	for(int i = 0; i < 2; i++) {
    		left[i]  = new HashMap<>();
    		right[i] = new HashMap<>();
    	}
    	
    	int pos = -1;
    	for(int i = 0; i < n; i++)
    		if(p[i] == m) {
    			pos = i;
    			break;
    		}
    	
    	
    	for(int i = pos + 1, cummulative = 0; i < n; i++) {
    		cummulative += p[i] > m ? -1 : 1;
    		right[(i - pos) % 2].merge(cummulative, 1, Integer::sum);
    	}
    	
    	long ways = 1 + right[0].getOrDefault(0, 0) + right[1].getOrDefault(-1, 0);
    	
    	
    	for(int i = pos - 1, cummulative = 0; i >= 0; i--) { 
    		cummulative += p[i] > m ? -1 : 1;
    		if((pos - i) % 2 == 1) {
    			ways += right[1].getOrDefault(-cummulative, 0);
    			ways += right[0].getOrDefault(-(1 + cummulative), 0);
    			ways += cummulative == -1 ? 1 : 0;
    		} else {
    			ways += right[0].getOrDefault(-cummulative, 0);
    			ways += right[1].getOrDefault(-(1 + cummulative), 0);
    			ways += cummulative == 0 ? 1 : 0;
    		}
    	}
    	
    	println(ways);
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