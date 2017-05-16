import java.util.*;
import java.io.*;
public class KTree
{


    /************************ SOLUTION STARTS HERE ***********************/

    static final long MOD = (long)(1e9) + 7;
    static long cnt[];
    static long ways[][];
    static long add(long a , long b){
	return ((a % MOD) + (b % MOD)) % MOD;
    }

    static long count(int curr, int k) {

	if (cnt[curr] != 0)
	    return cnt[curr];
	else {
	    for (int i = 1; i <= k && curr - i >= 0; i++)
		cnt[curr] = add(cnt[curr], count(curr - i, k));

	    return cnt[curr];
	}

    }

    static long ways(int curr, int before, int k, int d) {

	if (ways[curr][before] != -1)
	    return ways[curr][before];
	else {
	    ways[curr][before] = 0;
	    for (int i = 1; i <= k && curr - i >= 0; i++)
		ways[curr][before] = add(ways[curr][before], ways(curr - i, i, k, d));

	    return ways[curr][before];
	}
    }
    
    private static void solve(FastScanner s1, PrintWriter out){

	int N = s1.nextInt();
	int K = s1.nextInt();
	int D = s1.nextInt();
	
	cnt  = new long[N + 1];
	cnt[0] = cnt[1] = 1;	
	count(N, K);
	
	ways = new long[N + 1][K + 1];
	for (int i = 0; i <= N; i++)
	    Arrays.fill(ways[i], -1);
	for (int i = 0; i <= N; i++)
	    Arrays.fill(ways[i], D, K + 1, cnt[i]);
	
	out.println(ways(N, 0, K, D));
	
    }



    /************************ SOLUTION ENDS HERE ************************/





    /************************ TEMPLATE STARTS HERE *********************/

    public static void main(String []args) throws IOException {
	FastScanner in  = new FastScanner(System.in);
	PrintWriter out = 
		new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)), false); 
	solve(in, out);
	in.close();
	out.close();
    }    

    static class FastScanner{
	BufferedReader reader;
	StringTokenizer st;
	FastScanner(InputStream stream){reader=new BufferedReader(new InputStreamReader(stream));st=null;}	
	String next()
	{while(st == null || !st.hasMoreTokens()){try{String line = reader.readLine();if(line == null){return null;}		    
	st = new StringTokenizer(line);}catch (Exception e){throw new RuntimeException();}}return st.nextToken();}
	String nextLine()  {String s=null;try{s=reader.readLine();}catch(IOException e){e.printStackTrace();}return s;}	    	  	
	int    nextInt()   {return Integer.parseInt(next());}
	long   nextLong()  {return Long.parseLong(next());}		
	double nextDouble(){return Double.parseDouble(next());}
	char   nextChar()  {return next().charAt(0);}
	int[]  nextIntArray(int n)         {int[] arr= new int[n];   int i=0;while(i<n){arr[i++]=nextInt();}  return arr;}
	long[] nextLongArray(int n)        {long[]arr= new long[n];  int i=0;while(i<n){arr[i++]=nextLong();} return arr;}	
	int[]  nextIntArrayOneBased(int n) {int[] arr= new int[n+1]; int i=1;while(i<=n){arr[i++]=nextInt();} return arr;}	    	
	long[] nextLongArrayOneBased(int n){long[]arr= new long[n+1];int i=1;while(i<=n){arr[i++]=nextLong();}return arr;}	    	
	void   close(){try{reader.close();}catch(IOException e){e.printStackTrace();}}				
    }

    /************************ TEMPLATE ENDS HERE ************************/
}