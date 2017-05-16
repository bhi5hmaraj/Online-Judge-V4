import java.util.*;
import java.io.*;
public class NonDivisibleSubset
{

    
    /************************ SOLUTION STARTS HERE ***********************/


    private static <Key> void frequency(Map<Key, Integer> mp, Key k) {
	Integer query = mp.get(k);
	mp.put(k, query == null ? 1 : query + 1);
    }

    
    private static void solve(FastScanner s1, PrintWriter out){

	int n = s1.nextInt();
	int k = s1.nextInt();
	HashMap<Integer,Integer> freq = new HashMap<>();
	for(int i=1;i<=n;i++)
	    frequency(freq, s1.nextInt() % k);
	
	int cnt = 0;
	cnt += (freq.remove(0) != null) ? 1 : 0;
	cnt += (k % 2 == 0) && (freq.remove(k / 2) != null) ? 1 : 0;
	for(int i=1;i <= k;i++){
	    if(freq.containsKey(i)){
		int f1 = freq.get(i);
		int f2 = freq.getOrDefault(k - i, Integer.MIN_VALUE);
		cnt += Math.max(f1,f2);
		freq.remove(i);
		freq.remove(k - i);
	    }
	}
	    
	out.print(cnt);
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