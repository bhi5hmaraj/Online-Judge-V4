import java.util.*;
import java.io.*;

public class YetAnotherProblemOnaSubsequence {
    
    
    
    /************************ SOLUTION STARTS HERE ************************/
    

    static class MMInt {       // MM (Modular Math) class 
        static final int mod = 998244353;
        static int sub(int a, int b) {return (a - b  + mod) % mod;}
        static int mul(int a, int b) {return (int) ((1L * a * b ) % mod);}
        static int add(int a, int b) {return (a + b) % mod;}
        static int div(int a, int b) {return mul(a, modInverse(b));}
        static int modInverse(int n) {return modPow(n, mod - 2);} // Fermat's little theorem
        static int modPow(long a , long b) {
            long pow = 1;
            while(b > 0) {
                if((b & 1L) == 1)
                    pow = ((pow * a) % mod);

                a = ((a * a) % (mod));
                b >>= 1;
            }
            return (int) pow;
        }
    }

    private static int MAX = 2000; // (Change it to max N limit (be careful with the index)					  
	private static int fact[] = new int[MAX + 5];
	private static int invFact[] = new int[MAX + 5];
	static {
		fact[1] = 1;
		fact[0] = 1;
		for (int i = 2; i <= MAX; i++)
			fact[i] = MMInt.mul(i, fact[i - 1]);
		for (int i = 0; i <= MAX; i++)
			invFact[i] = MMInt.modInverse(fact[i]);
	}

	private static int nCr(int n, int r) { // Precompute inv Factorials (Dont compute every time) 
		return MMInt.mul(fact[n], MMInt.mul(invFact[r], invFact[n - r]));
	}
	
	static int memo[];
	static int arr[];
	static int rec(int idx) {
		
		if(arr[idx] <= 0 || idx + arr[idx] >= arr.length)
			return 0;
		else if(memo[idx] != -1)
			return memo[idx];
		
		// not add anything after this , so we just choose arr[idx] from the right 
		int cnt = nCr(arr.length - idx - 1, arr[idx]);	
		
		for(int i = idx + arr[idx] + 1; i < arr.length; i++)
			cnt = MMInt.add(cnt, MMInt.mul(nCr(i - idx - 1, arr[idx]), rec(i)));
		
		return memo[idx] = cnt;
	}
    
    private static void solve() {
        
        int n = nextInt();
    	arr = nextIntArray(n);
    	
    	memo = new int[n];
    	Arrays.fill(memo, -1);
    	
    	int total = 0;
    	for(int i = 0; i < n; i++)
    		total = MMInt.add(total, rec(i));
    	
    	println(total);
    	
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