import java.util.*;
import java.io.*;

public class TouristFBHC {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    
	static int gcd(int a, int b) {
		return a == 0 ? b : gcd(b % a, a);
	}

    
    private static void solve() {
        
    	for(int tc = 1, T = nextInt(); tc <= T; tc++) {
	    
    		int N = nextInt();
	    	int K = nextInt();
	    	long V = (nextLong() - 1) * K;
	    	
	    	String attractions[] = new String[N];
	    	for(int i = 0; i < N; i++)
	    		attractions[i] = nextLine();
	    	
	    	
	    	int lcm = N * K / gcd(N, K); 
	    	long cnt[] = new long[N];
	    	Arrays.fill(cnt, (V / lcm) * (lcm / N));
	    	
	    	for(int i = 0, leftOver = (int) (V % lcm); i < leftOver; i++)
	    		cnt[i % N]++;
	    	
//	    	System.out.println(Arrays.toString(cnt));
	    	
	    	long pair[][] = new long[N][2];	// 0 - cnt, 1 - popularity
	    	for(int i = 0; i < N; i++) {
	    		pair[i][0] = cnt[i];
	    		pair[i][1] = i;
	    	}
	    	
	    	Arrays.sort(pair, (p1, p2) -> p1[0] != p2[0] ? Long.compare(p1[0], p2[0]) : Long.compare(p1[1], p2[1]));
	    	Arrays.sort(pair, 0, K, (p1, p2) -> Long.compare(p1[1], p2[1]));
	    	
	    	print("Case #" + tc + ": ");
	    	for(int i = 0; i < K; i++)
	    		print(attractions[(int) pair[i][1]] + " ");
	    	
	    	print('\n');
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